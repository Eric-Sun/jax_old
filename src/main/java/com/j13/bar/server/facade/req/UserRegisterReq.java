package com.j13.bar.server.facade.req;

import com.j13.bar.server.poppy.anno.Parameter;
import org.apache.commons.fileupload.FileItem;

public class UserRegisterReq {
//    String mobile, String password, String nickName, Integer isMachine, FileItem file

    @Parameter(desc="注册用手机号")
    private String mobile;
    @Parameter(desc="密码（已加密）")
    private String password;
    @Parameter(desc="昵称")
    private String nickName;
    @Parameter(desc="是否是机器人，0为不是，1为是")
    private int isMachine;
    @Parameter(desc="头像")
    private FileItem headImg;

    public FileItem getHeadImg() {
        return headImg;
    }

    public void setHeadImg(FileItem headImg) {
        this.headImg = headImg;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getIsMachine() {
        return isMachine;
    }

    public void setIsMachine(int isMachine) {
        this.isMachine = isMachine;
    }


}
