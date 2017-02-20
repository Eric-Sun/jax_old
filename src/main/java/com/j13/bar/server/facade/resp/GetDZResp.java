package com.j13.bar.server.facade.resp;

import com.j13.bar.server.poppy.anno.Parameter;
import com.j13.bar.server.vos.CommentVO;

import java.util.Date;
import java.util.List;

public class GetDZResp {

    @Parameter(desc="")
    private long fid;
    @Parameter(desc="段子id")
    private long id;
    @Parameter(desc="发布者用户id")
    private long userId;
    @Parameter(desc="段子内容")
    private String content;
    @Parameter(desc="发布者用户名")
    private String userName;
    @Parameter(desc="发布者头像地址")
    private String img;
    @Parameter(desc="发布时间")
    private Date createtime;
    @Parameter(desc="来源")
    private int source;
    @Parameter(desc="热门评论列表")
    private List<CommentResp> topComments;
    @Parameter(desc="普通评论列表")
    private List<CommentResp> commonComments;

    public List<CommentResp> getTopComments() {
        return topComments;
    }

    public void setTopComments(List<CommentResp> topComments) {
        this.topComments = topComments;
    }

    public List<CommentResp> getCommonComments() {
        return commonComments;
    }

    public void setCommonComments(List<CommentResp> commonComments) {
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
