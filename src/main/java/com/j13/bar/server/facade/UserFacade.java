package com.j13.bar.server.facade;

import com.j13.bar.server.core.HDConstants;
import com.j13.bar.server.daos.AdminUserDAO;
import com.j13.bar.server.daos.UserDAO;
import com.j13.bar.server.poppy.exceptions.CommonException;
import com.j13.bar.server.core.ErrorCode;
import com.j13.bar.server.facade.req.UserLoginReq;
import com.j13.bar.server.facade.req.UserRegisterReq;
import com.j13.bar.server.facade.resp.UserLoginResp;
import com.j13.bar.server.facade.resp.UserRegisterResp;
import com.j13.bar.server.poppy.anno.Action;
import com.j13.bar.server.poppy.core.CommandContext;
import com.j13.bar.server.services.ThumbService;
import com.j13.bar.server.utils.MD5Util;
import com.j13.bar.server.vos.UserVO;
import org.apache.commons.fileupload.FileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-3-19
 * Time: 下午4:21
 * To change this template use File | Settings | File Templates.
 */
@Component
public class UserFacade {
    private static Logger LOG = LoggerFactory.getLogger(UserFacade.class);

    @Autowired
    UserDAO userDAO;
    @Autowired
    AdminUserDAO adminUserDAO;
    @Autowired
    ThumbService thumbService;


    @Action(name = "user.login", desc = "用户登陆")
    public UserLoginResp login(CommandContext ctxt, UserLoginReq req) {
        UserLoginResp resp = new UserLoginResp();
        String mobile = req.getMobile();
        String password = req.getPassword();
        String passwordAfterMD5 = MD5Util.getMD5String(password);
        UserVO vo = null;
        try {
            vo = userDAO.loginByMobile(mobile, passwordAfterMD5);
        } catch (EmptyResultDataAccessException e) {
            LOG.info("password error. mobile={},password={}", mobile, password);
            throw new CommonException(ErrorCode.User.PASSWORD_NOT_RIGHT);
        }

        com.j13.bar.server.poppy.util.BeanUtils.copyProperties(resp, vo);
        return resp;
    }


    /**
     * @return userId if registered successfully
     * -1 is mobile existed
     * -2 is nickName existed
     */
    @Action(name = "user.register", desc = "用户注册")
    public UserRegisterResp register(CommandContext ctxt, UserRegisterReq req) {
        UserRegisterResp resp = new UserRegisterResp();
        String mobile = req.getMobile();
        String password = req.getPassword();
        String nickName = req.getNickName();
        int isMachine = req.getIsMachine();
        FileItem file = req.getHeadImg();
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
        resp.setId(id);
        return resp;
    }


}
