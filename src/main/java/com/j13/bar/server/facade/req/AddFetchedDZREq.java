package com.j13.bar.server.facade.req;

import com.j13.bar.server.poppy.anno.Parameter;

public class AddFetchedDZReq {
    //String content, String md5, Integer fetchSource, String sourceDZId

    @Parameter(desc = "段子内容")
        private String content;
    @Parameter(desc = "MD5")
    private String md5;
    @Parameter(desc = "来源id")
    private int fetchSource;
    @Parameter(desc = "来源位置的对应id")
    private String sourceDZId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public int getFetchSource() {
        return fetchSource;
    }

    public void setFetchSource(int fetchSource) {
        this.fetchSource = fetchSource;
    }

    public String getSourceDZId() {
        return sourceDZId;
    }

    public void setSourceDZId(String sourceDZId) {
        this.sourceDZId = sourceDZId;
    }
}
