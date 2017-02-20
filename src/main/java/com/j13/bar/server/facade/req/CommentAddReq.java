package com.j13.bar.server.facade.req;

import com.j13.bar.server.poppy.anno.Parameter;

public class CommentAddReq {
    // @Description("评论的内容") String content,
//    @Description("段子id") Integer dzId,
//    @Description("评论者的userId") Integer userId,
//    @Description("如果是评论别的用户的评论的时候需要传") Integer cid,
//    @Description("评论唯一标识，客户端计算，用来防止用户重发, 例如:MD5(tid+userId+comment+客户端当前时间戳精确到小时)"

    @Parameter(desc="评论的内容")
    private String content;
    @Parameter(desc="段子id")
    private int dzId;
    @Parameter(desc="评论者的userId")
    private int userId;
    @Parameter(desc="如果是评论别的用户的评论的时候需要传")
    private int cid;
    @Parameter(desc="评论唯一标识，客户端计算，用来防止用户重发, 例如:MD5(tid+userId+comment+客户端当前时间戳精确到小时)")
    private String triggerId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getDzId() {
        return dzId;
    }

    public void setDzId(int dzId) {
        this.dzId = dzId;
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

    public String getTriggerId() {
        return triggerId;
    }

    public void setTriggerId(String triggerId) {
        this.triggerId = triggerId;
    }
}
