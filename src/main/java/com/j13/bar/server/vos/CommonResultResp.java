package com.j13.bar.server.vos;

import com.j13.bar.server.core.HDConstants;

public class CommonResultResp {
    private int result = HDConstants.ResponseStatus.SUCCESS;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
