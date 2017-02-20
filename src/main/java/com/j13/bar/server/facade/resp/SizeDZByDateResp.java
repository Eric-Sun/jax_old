package com.j13.bar.server.facade.resp;

import com.j13.bar.server.poppy.anno.Parameter;

public class SizeDZByDateResp {
    @Parameter(desc="数量")
    private int size;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
