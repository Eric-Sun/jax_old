package com.j13.bar.server.services;

import com.j13.bar.server.core.HDConstants;
import com.j13.bar.server.core.log.LOG;
import com.j13.bar.server.daos.AdminUserDAO;
import com.j13.bar.server.daos.UserDAO;
import com.j13.bar.server.exceptions.CommonException;
import com.j13.bar.server.exceptions.ErrorCode;
import com.j13.bar.server.poppy.ErrorResponse;
import com.j13.bar.server.poppy.anno.Action;
import com.j13.bar.server.utils.MD5Util;
import com.j13.bar.server.vos.AdminUserVO;
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
public class UserService {

    @Autowired
    UserDAO userDAO;


    @Autowired
    AdminUserDAO adminUserDAO;
    @Autowired
    ThumbService thumbService;


    @Action(name="user.login")
    public UserVO login(String mobile, String password) {
        String passwordAfterMD5 = MD5Util.getMD5String(password);
        UserVO vo = null;
        try {
            vo = userDAO.loginByMobile(mobile, passwordAfterMD5);
        } catch (EmptyResultDataAccessException e) {
            LOG.info("password error. mobile={},password={}", mobile, password);
            vo = new UserVO();
            vo.setCode(ErrorCode.User.PASSWORD_NOT_RIGHT);
            return vo;
        }
        return vo;
    }


    /**
     * @return userId if registered successfully
     * -1 is mobile existed
     * -2 is nickName existed
     */
    @Action(name="user.register")
    public long register(String mobile, String password, String nickName, Integer isMachine, FileItem file) {

        // check mobile exists
        if (isMachine != HDConstants.USER_IS_MACHINE && userDAO.mobileExisted(mobile)) {
            LOG.info("mobile existed. mobile={}", mobile);
            throw new CommonException(ErrorCode.User.MOBILE_EXISTED);
        }

        // check nickName exists
        if (userDAO.nickNameExisted(nickName)) {
            LOG.info("nickname existed. nickname={}", nickName);
            throw new CommonException(ErrorCode.User.NICKNAME_EXISTED);
        }

        String passwordAfterMD5 = MD5Util.getMD5String(password);
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
