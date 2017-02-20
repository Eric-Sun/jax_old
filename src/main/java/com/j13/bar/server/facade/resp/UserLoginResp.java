package com.j13.bar.server.facade.resp;

import com.j13.bar.server.poppy.anno.Parameter;

public class UserLoginResp {
    @Parameter(desc="用户id")
    private long userId;
    @Parameter(desc="用户名")
    private String userName;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
