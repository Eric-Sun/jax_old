package com.j13.bar.server.facade.resp;

import com.j13.bar.server.poppy.anno.Parameter;

import java.util.List;

public class DemoResp {

    @Parameter(desc = "id is this")
    private int id;
    @Parameter(desc = "name is this")
    private String name;

    @Parameter(desc="bb")
    private DemoInnerResp d;

    @Parameter(desc=" aaa")
    private List<DemoInnerResp> list;

    public DemoInnerResp getD() {
        return d;
    }

    public void setD(DemoInnerResp d) {
        this.d = d;
    }

    public List<DemoInnerResp> getList() {
        return list;
    }

    public void setList(List<DemoInnerResp> list) {
        this.list = list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
