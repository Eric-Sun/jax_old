package com.j13.bar.server.facade.req;

import com.j13.bar.server.poppy.anno.Parameter;

public class CommentAddMachineReq {

    //String content, Integer dzId, Integer hot, String sourceCommentId

    @Parameter(desc = "评论内容")
    private String content;
    @Parameter(desc = "对应的段子id")
    private int dzId;
    @Parameter(desc = "热度")
    private int hot;
    @Parameter(desc = "对应来源位置的评论的id")
    private String sourceCommentId;
    @Parameter(desc="是否是top的评论，0为不是，1为是")
    private int isTop;

    public int getIsTop() {
        return isTop;
    }

    public void setIsTop(int isTop) {
        this.isTop = isTop;
    }

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

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public String getSourceCommentId() {
        return sourceCommentId;
    }

    public void setSourceCommentId(String sourceCommentId) {
        this.sourceCommentId = sourceCommentId;
    }
}
