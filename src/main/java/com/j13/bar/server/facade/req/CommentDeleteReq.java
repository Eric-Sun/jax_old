package com.j13.bar.server.facade.req;

import com.j13.bar.server.poppy.anno.Parameter;

public class CommentDeleteReq {
//    Integer userId, Integer cid

    @Parameter(desc="用户id")
    private int userId;
    @Parameter(desc="评论id")
    private int cid;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}
