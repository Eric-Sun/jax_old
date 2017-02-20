package com.j13.bar.server.vos;

import com.j13.bar.server.core.HDConstants;
import com.j13.bar.server.poppy.anno.Parameter;

public class CommentAddResp {
    @Parameter(desc="评论id")
    private int cid;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}
