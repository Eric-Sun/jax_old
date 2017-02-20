package com.j13.bar.server.vos;

import com.google.common.collect.Lists;
import com.j13.bar.server.poppy.anno.Parameter;

import java.util.List;

public class CommentListResp {

    @Parameter(desc="段子id")
    private int dzId;
    @Parameter(desc="这个段子点赞的数量")
    private int praiseCount;
    @Parameter(desc="这个段子的评论数量")
    private int commentCount;
    @Parameter(desc="评论的列表")
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
