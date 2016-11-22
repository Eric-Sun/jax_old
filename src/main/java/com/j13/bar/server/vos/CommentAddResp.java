package com.j13.bar.server.vos;

import com.j13.bar.server.core.HDConstants;

public class CommentAddResp {
    private int result = HDConstants.ResponseStatus.SUCCESS;
    private int cid;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}
