package com.j13.bar.server.services;

import com.j13.bar.server.core.HDConstants;
import com.j13.bar.server.daos.CommentDAO;
import com.j13.bar.server.daos.DZCursorDAO;
import com.j13.bar.server.daos.DZDAO;
import com.j13.bar.server.exceptions.CommonException;
import com.j13.bar.server.exceptions.ErrorCode;
import com.j13.bar.server.helper.MachineUserHolder;
import com.j13.bar.server.poppy.anno.Action;
import com.j13.bar.server.poppy.anno.NeedTicket;
import com.j13.bar.server.utils.MD5Util;
import com.j13.bar.server.vos.CommentVO;
import com.j13.bar.server.vos.DZVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class DzService {
    private static Logger LOG = LoggerFactory.getLogger(DzService.class);

    @Autowired
    DZDAO dzDAO;
    @Autowired
    MachineUserHolder machineUserHolder;

    @Autowired
    DZCursorDAO dzCursorDAO;

    @Autowired
    CommentDAO commentDAO;

    @Action("dz.list")
    public List<DZVO> list(String deviceId) {
        long dzId = dzCursorDAO.getCursor(deviceId);
        List<DZVO> dzvoList = dzDAO.list10(dzId);
        dzCursorDAO.addCursor(deviceId, 10);
        return dzvoList;
    }

    @Action("dz.get")
    public DZVO getDZ(Integer dzId) {
        DZVO dz = dzDAO.get(dzId);
        List<CommentVO> topList = commentDAO.listTop(dzId, 0, 10);
        int commonSize = 10 - topList.size();
        List<CommentVO> commonList = commentDAO.listCommon(dzId, 0, commonSize);
        dz.setTopComments(topList);
        dz.setCommonComments(commonList);
        return dz;
    }


    @Action("dz.addFetchedDZ")
    public Long addFetchedDZ(String content, String md5, Integer fetchSource, String sourceDZId) {
        int machineUser = machineUserHolder.randomOne();
        // 查看该内容是否有
        if (dzDAO.checkExist(md5)) {
            LOG.info("dz existed. md5 = " + md5);
            return new Long(-1);
        }
        long id = dzDAO.addMachine(machineUser, content, HDConstants.DEFAULT_IMG_ID, md5, fetchSource,
                sourceDZId);

        LOG.info("dz insert .id = " + id);
        return new Long(id);
    }


    @Action("dz.setMachineUserToDZ")
    public int setMachineUserToDZ(Integer size) {
        List<Integer> list = dzDAO.getNoUserDZ(size);
        for (Integer dzId : list) {
            int randomMachineUserId = machineUserHolder.randomOne();
            dzDAO.updateDZWithMachineUser(dzId, randomMachineUserId);
            LOG.info("update dz with machine user. dzId={} machineUserId={}", dzId, randomMachineUserId);
        }
        LOG.info("update dz finished . size={}", list.size());
        return list.size();
    }

    @Action("dz.add")
    public int add(Integer userId, String content) {
        String md5 = MD5Util.getMD5String(content);

        if (dzDAO.checkExist(md5)) {
            LOG.info("dz existed. md5 = " + md5);
            return -1;
        }
        return dzDAO.add(userId, content, md5);
    }

    @Action("dz.listDZByDate")
    public List<DZVO> listDZByDate(Integer pageNum, Integer size, String date) {
        Date date2 = null;
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        try {
            date2 = sdf.parse(date);
        } catch (ParseException e) {
            throw new CommonException(ErrorCode.Common.INPUT_PARAMETER_ERROR);
        }
        return dzDAO.listOneDayDZ(date2, size, pageNum);
    }

    @Action("dz.sizeDZByDate")
    public int sizeDZByDate(String date) {
        Date date2 = null;
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        try {
            date2 = sdf.parse(date);
        } catch (ParseException e) {
            throw new CommonException(ErrorCode.Common.INPUT_PARAMETER_ERROR);
        }
        return dzDAO.sizeOneDayDZ(date2);
    }


    @Action("dz.praise")
    @NeedTicket
    public int praise(Integer userId, Integer dzId) {
        dzDAO.praise(userId, dzId);
        return 0;
    }
}
