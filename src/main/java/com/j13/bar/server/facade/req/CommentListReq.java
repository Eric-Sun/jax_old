package com.j13.bar.server.facade.req;

import com.j13.bar.server.poppy.anno.Parameter;

public class CommentListReq {

//    @Description("段子id") Integer dzId,
//    @Description("分页开始Id,首页传0") Integer lastId,
//    @Description("加载类型,loadmore:加载更多,refresh:刷新最新") String type,
//    @Description("分页展示评论数目") Integer pageSize,
//    @Description("评论列表类型,new:最新评论,hot:热门评论") String category

    @Parameter(desc="段子id")
    private int dzId;
    @Parameter(desc="分页开始Id,首页传0")
    private int lastId;
    @Parameter(desc="加载类型,loadmore:加载更多,refresh:刷新最新")
    private String type;
    @Parameter(desc="分页展示评论数目")
    private int pageSize;
    @Parameter(desc="评论列表类型,new:最新评论,hot:热门评论")
    private String category;

    public int getDzId() {
        return dzId;
    }

    public void setDzId(int dzId) {
        this.dzId = dzId;
    }

    public int getLastId() {
        return lastId;
    }

    public void setLastId(int lastId) {
        this.lastId = lastId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
