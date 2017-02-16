package com.j13.bar.server.facade.req;

import com.j13.bar.server.poppy.anno.Description;
import com.j13.bar.server.poppy.anno.Parameter;

public class DemoReq {

    @Parameter(desc="这个是id")
    private int id;
    @Parameter(desc="这个是name")
    private String name ;

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
