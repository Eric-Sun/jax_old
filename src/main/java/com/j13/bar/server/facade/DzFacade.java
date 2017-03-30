package com.j13.bar.server.facade;

import com.google.common.collect.Lists;
import com.j13.bar.server.core.HDConstants;
import com.j13.bar.server.daos.CommentDAO;
import com.j13.bar.server.daos.DZCursorDAO;
import com.j13.bar.server.daos.DZDAO;
import com.j13.bar.server.poppy.exceptions.CommonException;
import com.j13.bar.server.core.ErrorCode;
import com.j13.bar.server.facade.req.*;
import com.j13.bar.server.facade.resp.*;
import com.j13.bar.server.helper.MachineUserHolder;
import com.j13.bar.server.poppy.anno.Action;
import com.j13.bar.server.poppy.anno.NeedTicket;
import com.j13.bar.server.poppy.core.CommandContext;
import com.j13.bar.server.poppy.core.CommonResultResp;
import com.j13.bar.server.utils.MD5Util;
import com.j13.bar.server.vos.CommentVO;
import com.j13.bar.server.vos.DZVO;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class DzFacade {
    private static Logger LOG = LoggerFactory.getLogger(DzFacade.class);

    @Autowired
    DZDAO dzDAO;
    @Autowired
    MachineUserHolder machineUserHolder;

    @Autowired
    DZCursorDAO dzCursorDAO;

    @Autowired
    CommentDAO commentDAO;

    @Action(name = "dz.list", desc = "段子列表，用于首页的列表展示")
    public DZListResp list(CommandContext ctxt, DZListReq req) {
        String deviceId = ctxt.getDeviceId();
        long dzId = dzCursorDAO.getCursor(deviceId);
        List<DZVO> dzvoList = dzDAO.list10(dzId);
        dzCursorDAO.addCursor(deviceId, 10);
        DZListResp resp = new DZListResp();
        for (DZVO dzvo : dzvoList) {
            GetDZResp r = new GetDZResp();
            try {
                BeanUtils.copyProperties(r, dzvo);
            } catch (Exception e) {
                throw new CommonException(ErrorCode.System.SYSTEM_ERROR);
            }
            resp.getData().add(r);
        }
        return resp;
    }

    @Action(name = "dz.get", desc = "段子的详细信息，用于段子的详情页等页面的展示")
    public GetDZResp getDZ(CommandContext ctxt, GetDZReq req) {
        int dzId = req.getDzId();
        DZVO dz = dzDAO.get(dzId);
        List<CommentVO> topList = commentDAO.listTop(dzId, 0, 10);
        int commonSize = 10 - topList.size();
        List<CommentVO> commonList = commentDAO.listCommon(dzId, 0, commonSize);
        dz.setTopComments(topList);
        dz.setCommonComments(commonList);
        GetDZResp resp = new GetDZResp();
        try {
            BeanUtils.copyProperties(resp, dz);
        } catch (Exception e) {
            LOG.error("dzId={}", dzId, e);
            throw new CommonException(ErrorCode.System.SYSTEM_ERROR);
        }
        return resp;
    }


    @Action(name = "dz.addFetchedDZ", desc = "抓取系统添加段子的接口，如果段子已经存在，返回对应的段子Id")
    public AddFetchedDZResp addFetchedDZ(CommandContext ctxt, AddFetchedDZReq req) {
        AddFetchedDZResp resp = new AddFetchedDZResp();
        String content = req.getContent();
        String md5 = req.getMd5();
        int fetchSource = req.getFetchSource();
        String sourceDZId = req.getSourceDZId();
        int machineUser = machineUserHolder.randomOne();

        long savedDzId = 0;
        try {
            savedDzId = dzDAO.getDZByMd5(md5);
        } catch (DataAccessException e) {
            LOG.info("dz existed. md5 = {}", md5);
        }
        if (savedDzId != 0) {
            resp.setId(savedDzId);
            return resp;
        }
        long id = dzDAO.addMachine(machineUser, content, HDConstants.DEFAULT_IMG_ID, md5, fetchSource,
                sourceDZId);

        resp.setId(id);
        LOG.info("dz insert .id = " + id);
        return resp;
    }


    @Action(name = "dz.setMachineUserToDZ", desc = "抓取系统早期将没有用户id的段子绑定一个随机用户")
    public SetMachineUserToDZResp setMachineUserToDZ(CommandContext ctxt, SetMachineUserToDZReq req) {
        SetMachineUserToDZResp resp = new SetMachineUserToDZResp();
        int size = req.getSize();
        List<Integer> list = dzDAO.getNoUserDZ(size);
        for (Integer dzId : list) {
            int randomMachineUserId = machineUserHolder.randomOne();
            dzDAO.updateDZWithMachineUser(dzId, randomMachineUserId);
            LOG.info("update dz with machine user. dzId={} machineUserId={}", dzId, randomMachineUserId);
        }
        LOG.info("update dz finished . size={}", list.size());
        resp.setSize(list.size());
        return resp;
    }

    @Action(name = "dz.add", desc = "添加段子")
    public DzAddResp add(CommandContext ctxt, AddDZReq req) {
        DzAddResp resp = new DzAddResp();
        String content = req.getContent();
        int userId = req.getUserId();
        String md5 = MD5Util.getMD5String(content);

        if (dzDAO.checkExist(md5)) {
            LOG.info("dz existed. md5 = " + md5);
            resp.setId(-1);
            return resp;
        }
        long id = dzDAO.add(userId, content, md5);
        resp.setId(id);
        return resp;
    }

    @Action(name = "dz.listDZByDate", desc = "后台接口，用于将段子按照天进行查询")
    public ListDZByDateResp listDZByDate(CommandContext ctxt, ListDZByDateReq req) {
        ListDZByDateResp resp = new ListDZByDateResp();
        String date = req.getDate();
        int size = req.getSize();
        int pageNum = req.getPageNum();
        Date date2 = null;
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        try {
            date2 = sdf.parse(date);
        } catch (ParseException e) {
            throw new CommonException(ErrorCode.Common.INPUT_PARAMETER_ERROR);
        }
        List<DZVO> dzList = dzDAO.listOneDayDZ(date2, size, pageNum);
        List<GetDZResp> respDzList = Lists.newLinkedList();
        for (DZVO dz : dzList) {
            GetDZResp r = new GetDZResp();
            try {
                BeanUtils.copyProperties(r, dz);
            } catch (Exception e) {
                throw new CommonException(ErrorCode.System.SYSTEM_ERROR);
            }
            respDzList.add(r);
        }
        resp.setData(respDzList);
        return resp;
    }

    @Action(name = "dz.sizeDZByDate", desc = "后台接口，获取某一天的新增段子的数量")
    public SizeDZByDateResp sizeDZByDate(CommandContext ctxt, SizeDZByDateReq req) {
        String date = req.getDate();
        SizeDZByDateResp resp = new SizeDZByDateResp();
        Date date2 = null;
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        try {
            date2 = sdf.parse(date);
        } catch (ParseException e) {
            throw new CommonException(ErrorCode.Common.INPUT_PARAMETER_ERROR);
        }
        int size = dzDAO.sizeOneDayDZ(date2);
        resp.setSize(size);
        return resp;
    }


    @Action(name = "dz.praise", desc = "给段子点赞")
    @NeedTicket
    public CommonResultResp praise(CommandContext ctxt, PraiseReq req) {
        int userId = req.getUserId();
        int dzId = req.getDzId();
        CommonResultResp resp = new CommonResultResp();
        dzDAO.praise(userId, dzId);
        return resp;
    }
}
