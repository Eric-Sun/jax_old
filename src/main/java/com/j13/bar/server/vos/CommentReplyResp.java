package com.j13.bar.server.vos;

import com.j13.bar.server.poppy.anno.Parameter;

public class CommentReplyResp {
    @Parameter(desc="评论id")
    private int cid;
    @Parameter(desc="评论内容")
    private String content;
    @Parameter(desc="用户id")
    private int userId;
    @Parameter(desc="用户名")
    private String userName;
    @Parameter(desc="用户头像")
    private String userImg;

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
}
