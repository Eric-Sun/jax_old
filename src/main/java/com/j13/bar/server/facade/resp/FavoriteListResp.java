package com.j13.bar.server.facade.resp;

import com.j13.bar.server.poppy.anno.Parameter;

import java.util.List;

public class FavoriteListResp {
    @Parameter(desc="数据")
    private List<GetDZResp> data;

    public List<GetDZResp> getData() {
        return data;
    }

    public void setData(List<GetDZResp> data) {
        this.data = data;
    }
}
