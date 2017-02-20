package com.j13.bar.server.facade.req;

import com.j13.bar.server.poppy.anno.Parameter;

public class SetMachineUserToDZReq {
    @Parameter(desc="此次一共获取多少个")
    private int size;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
