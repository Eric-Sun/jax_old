package com.j13.bar.server.facade.req;

import com.j13.bar.server.poppy.anno.Parameter;
import org.apache.commons.fileupload.FileItem;

public class FamilyAddReq {

    @Parameter(desc="家族名字")
    private String name;
    @Parameter(desc="头像图片")
    private FileItem headImg;
    @Parameter(desc="封面图片")
    private FileItem coverImg;
    @Parameter(desc="简介")
    private String brief;
    @Parameter(desc="创建者uid")
    private int creatorUserId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FileItem getHeadImg() {
        return headImg;
    }

    public void setHeadImg(FileItem headImg) {
        this.headImg = headImg;
    }

    public FileItem getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(FileItem coverImg) {
        this.coverImg = coverImg;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public int getCreatorUserId() {
        return creatorUserId;
    }

    public void setCreatorUserId(int creatorUserId) {
        this.creatorUserId = creatorUserId;
    }
}
