package com.j13.bar.server.facade;

import com.j13.bar.server.poppy.core.CommandContext;
import com.j13.bar.server.facade.req.DemoReq;
import com.j13.bar.server.facade.resp.DemoResp;
import com.j13.bar.server.poppy.anno.Action;
import org.springframework.stereotype.Component;

@Component
public class DemoFacade {

    @Action(name = "demo", desc = "范德萨范德萨")
    public DemoResp demo(CommandContext ctxt, DemoReq req) {
        DemoResp resp = new DemoResp();
        resp.setId(1222);
        resp.setName(req.getName());

        return resp;
    }


}
