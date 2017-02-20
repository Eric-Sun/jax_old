package com.j13.bar.server.facade.req;

import com.j13.bar.server.poppy.anno.Parameter;

public class CommentCountReq {
    @Parameter(desc="用户id")
    private int userId;
    @Parameter(desc="评论的id")
    private int cid;
    @Parameter(desc="计数的类型，点赞是0，分享是1")
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

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

