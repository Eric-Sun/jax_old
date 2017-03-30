package com.j13.bar.server.facade.req;

import com.j13.bar.server.poppy.anno.Parameter;

public class UserLoginReq {
    @Parameter(desc="手机号")
    private String mobile;
    @Parameter(desc="密码")
    private String password;

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
}
