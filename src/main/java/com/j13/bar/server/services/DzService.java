package com.j13.bar.server.services;

import com.j13.bar.server.core.HDConstants;
import com.j13.bar.server.core.RequestData;
import com.j13.bar.server.daos.DZCursorDAO;
import com.j13.bar.server.daos.DZDAO;
import com.j13.bar.server.helper.MachineUserHolder;
import com.j13.bar.server.vos.DZVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service("DzService")
public class DzService {
    private static Logger LOG = LoggerFactory.getLogger(DzService.class);

    @Autowired
    DZDAO dzDAO;
    @Autowired
    MachineUserHolder machineUserHolder;

    @Autowired
    DZCursorDAO dzCursorDAO;

    public List<DZVO> list(RequestData request) {
        String deviceId = request.getDeviceId();
        long dzId = dzCursorDAO.getCursor(deviceId);
        List<DZVO> dzvoList = dzDAO.list10(dzId);
        dzCursorDAO.addCursor(deviceId, 10);
        return dzvoList;
    }


    public long addFetchedDZ(RequestData request) {
        String content = request.getString("content");
        String md5 = request.getString("md5");
        int fetchSource = request.getInteger("fetchSource");
        String sourceId = request.getString("sourceDZId");
        int machineUser = machineUserHolder.randomOne();

        // 查看该内容是否有
        if (dzDAO.checkExist(md5)) {
            LOG.info("dz existed. md5 = " + md5);
            return -1;
        }
        long id = dzDAO.insert(machineUser, content, HDConstants.DEFAULT_IMG_ID, md5, fetchSource,
                sourceId);

        LOG.info("dz insert .id = " + id);
        return id;
    }


    public int setMachineUserToDZ(RequestData requestData) {
        int limitSize = requestData.getInteger("size");
        List<Integer> list = dzDAO.getNoUserDZ(limitSize);
        for (Integer dzId : list) {
            int randomMachineUserId = machineUserHolder.randomOne();
            dzDAO.updateDZWithMachineUser(dzId, randomMachineUserId);
            LOG.info("update dz with machine user. dzId={} machineUserId={}", dzId, randomMachineUserId);
        }
        LOG.info("update dz finished . size={}", list.size());
        return list.size();
    }

}