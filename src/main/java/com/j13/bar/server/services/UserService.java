package com.j13.bar.server.services;

import com.j13.bar.server.core.HDConstants;
import com.j13.bar.server.core.RequestData;
import com.j13.bar.server.core.log.LOG;
import com.j13.bar.server.daos.UserDAO;
import com.j13.bar.server.exceptions.CommonException;
import com.j13.bar.server.exceptions.ErrorCode;
import com.j13.bar.server.utils.MD5Util;
import com.j13.bar.server.vos.UserVO;
import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-3-19
 * Time: 下午4:21
 * To change this template use File | Settings | File Templates.
 */
@Service("UserService")
public class UserService {

    @Autowired
    UserDAO userDAO;

    @Autowired
    ThumbService thumbService;

    public UserVO login(RequestData rd) {
        String mobile = rd.getString("mobile");
        String password = rd.getString("password");
        String passwordAfterMD5 = MD5Util.getMD5String(password);
        UserVO vo = null;
        try {
            vo = userDAO.loginByMobile(mobile, passwordAfterMD5);
        } catch (EmptyResultDataAccessException e) {
            LOG.info("password error. mobile={},password={}", mobile, password);
            throw new CommonException(ErrorCode.PASSWORD_NOT_RIGHT);
        }
        return vo;
    }

    /**
     * @param request
     * @return userId if registered successfully
     * -1 is mobile existed
     * -2 is nickName existed
     */
    public long register(RequestData request) {
        String mobile = request.getString("mobile");
        String password = request.getString("password");
        String nickName = request.getString("nickName");
        int isMachine = request.getInteger("isMachine");


        // check mobile exists
        if (isMachine != HDConstants.USER_IS_MACHINE && userDAO.mobileExisted(mobile)) {
            LOG.info("mobile existed. mobile={}", mobile);
            throw new CommonException(ErrorCode.MOBILE_EXISTED);
        }

        // check nickName exists
        if (userDAO.nickNameExisted(nickName)) {
            LOG.info("nickname existed. nickname={}", nickName);
            throw new CommonException(ErrorCode.NICKNAME_EXISTED);
        }

        String passwordAfterMD5 = MD5Util.getMD5String(password);
        FileItem file = request.getFile();
        String fileName = null;
        if (file != null) {
            fileName = thumbService.uploadThumb(file);
        } else {
            fileName = thumbService.randomDefaultThumb();
        }
        long id = userDAO.register(mobile, passwordAfterMD5, nickName, isMachine, fileName);

        return id;
    }


}
