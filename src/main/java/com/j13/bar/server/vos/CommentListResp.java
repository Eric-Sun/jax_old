package com.j13.bar.server.vos;

import com.google.common.collect.Lists;

import java.util.List;

public class CommentListResp {

    private int dzId;
    private int praiseCount;
    private int commentCount;
    private List<CommentResp> comments = Lists.newLinkedList();

    public int getDzId() {
        return dzId;
    }

    public void setDzId(int dzId) {
        this.dzId = dzId;
    }

    public int getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(int praiseCount) {
        this.praiseCount = praiseCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public List<CommentResp> getComments() {
        return comments;
    }

    public void setComments(List<CommentResp> comments) {
        this.comments = comments;
    }
}
