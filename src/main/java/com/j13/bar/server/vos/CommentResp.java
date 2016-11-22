package com.j13.bar.server.vos;

import com.google.common.collect.Lists;

import java.util.List;

public class CommentResp {
    private int cid;
    private String content;
    private long createTime;
    private int userId;
    private String userName;
    private String userImg;
    private int praiseCount;
    private int shareCount;
    private int commentCount;
    private List<CommentReplyResp> replys = Lists.newLinkedList();

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

    public List<CommentReplyResp> getReplys() {
        return replys;
    }

    public void setReplys(List<CommentReplyResp> replys) {
        this.replys = replys;
    }
}
