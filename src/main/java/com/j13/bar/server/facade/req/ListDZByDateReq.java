package com.j13.bar.server.facade.req;

import com.j13.bar.server.poppy.anno.Parameter;

public class ListDZByDateReq {
    //Integer pageNum, Integer size, String date

    @Parameter(desc="页数，第一页传0")
    private int pageNum;
    @Parameter(desc="每条页数")
    private int size;
    @Parameter(desc="查询日期 格式：MM/dd/yyyy")
    private String date;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
