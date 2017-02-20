package com.j13.bar.server.facade.resp;

import com.j13.bar.server.poppy.anno.Parameter;

public class CommentResp {

    @Parameter(desc="评论id")
    private int cid;
    @Parameter(desc="评论内容")
    private String content;
    @Parameter(desc="评论产生时间，可能是抓取时间，客户端不显示")
    private long createTime;
    @Parameter(desc="评论发布者用户id")
    private int userId;
    @Parameter(desc="评论发布者用户名")
    private String userName;
    @Parameter(desc="评论发布者用户头像")
    private String userImg;
    @Parameter(desc="点赞次数")
    private int praiseCount;
    @Parameter(desc="分享次数")
    private int shareCount;
    @Parameter(desc="总评论次数")
    private int commentCount;
    @Parameter(desc="回复此评论id")
    private int replyCid;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public int getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(int praiseCount) {
        this.praiseCount = praiseCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getReplyCid() {
        return replyCid;
    }

    public void setReplyCid(int replyCid) {
        this.replyCid = replyCid;
    }
}
