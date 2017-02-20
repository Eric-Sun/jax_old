package com.j13.bar.server.facade.req;

import com.j13.bar.server.poppy.anno.Parameter;

public class AddDZReq {
    //Integer userId, String content
    @Parameter(desc="用户id")
    private int userId;
    @Parameter(desc="内容")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
