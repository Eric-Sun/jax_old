package com.j13.bar.server.poppy.core;

import com.j13.bar.server.poppy.anno.Parameter;

public class CommonResultResp {
    @Parameter(desc="操作结果成功为0，其他是失败")
    private int result;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
