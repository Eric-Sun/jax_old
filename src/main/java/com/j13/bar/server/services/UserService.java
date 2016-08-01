package com.j13.bar.server.services;

import com.j13.bar.server.core.HDConstants;
import com.j13.bar.server.core.RequestData;
import com.j13.bar.server.core.log.HDLogger;
import com.j13.bar.server.core.log.HDLoggerEntity;
import com.j13.bar.server.daos.UserDAO;
import com.j13.bar.server.exceptions.PasswordErrorException;
import com.j13.bar.server.utils.MD5Util;
import com.j13.bar.server.vos.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-3-19
 * Time: 下午4:21
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

//    public UserVO login(String mobile, String password) throws PasswordErrorException {
//        String passwordAfterMD5 = MD5Util.getMD5String(password);
//        UserVO vo = userDAO.loginByMobile(mobile, passwordAfterMD5);
//        return vo;
//    }
//
//    public UserVO register(String mobile, String password,String nickName) {
//        String passwordAfterMD5 = MD5Util.getMD5String(password);
//        long id = userDAO.register(mobile, passwordAfterMD5,nickName);
//        UserVO vo = new UserVO();
//        vo.setUid(id);
//        HDLogger.info(HDLoggerEntity.p("user", "register", -1, ""));
//        return vo;
//    }


    public long addMachineUser(RequestData request) {
        String nickName = request.getString("userName");
        String thumbUrl = request.getString("thumbUrl");


        return userDAO.register(HDConstants.MACHINE_MOBILE, HDConstants.MACHINE_PASSWORD, nickName, HDConstants.User.MACHINE_USER);
    }


}
