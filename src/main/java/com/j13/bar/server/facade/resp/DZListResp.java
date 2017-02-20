package com.j13.bar.server.facade.resp;

import com.google.common.collect.Lists;
import com.j13.bar.server.poppy.anno.Parameter;
import com.j13.bar.server.vos.DZVO;

import java.util.List;

public class DZListResp {

    @Parameter(desc = "数据")
    List<GetDZResp> data = Lists.newLinkedList();

    public List<GetDZResp> getData() {
        return data;
    }

    public void setData(List<GetDZResp> data) {
        this.data = data;
    }
}
