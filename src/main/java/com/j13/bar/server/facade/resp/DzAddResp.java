package com.j13.bar.server.facade.resp;

import com.j13.bar.server.poppy.anno.Parameter;

public class DzAddResp {
    @Parameter(desc="段子id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
