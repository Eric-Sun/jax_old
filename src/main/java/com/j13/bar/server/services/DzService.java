package com.j13.bar.server.services;

import com.j13.bar.server.core.HDConstants;
import com.j13.bar.server.core.RequestData;
import com.j13.bar.server.daos.DZCursorDAO;
import com.j13.bar.server.daos.DZDAO;
import com.j13.bar.server.vos.DZVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("DzService")
public class DzService {
    private static Logger LOG = LoggerFactory.getLogger(DzService.class);

    @Autowired
    DZDAO dzDAO;

    @Autowired
    DZCursorDAO dzCursorDAO;

    public List<DZVO> list(RequestData request) {
        String deviceId = request.getDeviceId();
        long dzId = dzCursorDAO.getCursor(deviceId);
        List<DZVO> dzvoList = dzDAO.list10(dzId);
        dzCursorDAO.addCursor(deviceId, 10);
        return dzvoList;
    }


    public long add(RequestData request) {
        String content = request.getString("content");
        String md5 = request.getString("md5");
        int fetchSource = request.getInteger("fetchSource");
        String sourceId = request.getString("sourceDZId");

        // 查看该内容是否有
        if (dzDAO.checkExist(md5)) {
            LOG.info("dz existed. md5 = " + md5);
            return -1;
        }
        long id = dzDAO.insert(HDConstants.MACHINE_USER_ID, content, HDConstants.DEFAULT_IMG_ID, md5, fetchSource,
                sourceId);

        LOG.info("dz insert .id = " + id);
        return id;
    }


}
