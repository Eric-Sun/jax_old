package com.j13.bar.server.vos;

import java.util.Date;
import java.util.List;

public class DZVO {
    private long fid;

    private long id;
    private long userId;
    private String content;
    private String userName;
    private String img;
    private Date createtime;
    private int source;
    private List<CommentVO> topComments;
    private List<CommentVO> commonComments;

    public List<CommentVO> getTopComments() {
        return topComments;
    }

    public void setTopComments(List<CommentVO> topComments) {
        this.topComments = topComments;
    }

    public List<CommentVO> getCommonComments() {
        return commonComments;
    }

    public void setCommonComments(List<CommentVO> commonComments) {
        this.commonComments = commonComments;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public long getFid() {
        return fid;
    }

    public void setFid(long fid) {
        this.fid = fid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
