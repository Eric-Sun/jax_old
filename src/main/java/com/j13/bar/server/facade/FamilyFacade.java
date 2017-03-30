package com.j13.bar.server.facade;

import com.j13.bar.server.core.ErrorCode;
import com.j13.bar.server.facade.req.CommentAddMachineReq;
import com.j13.bar.server.facade.resp.CommentAddMachineResp;
import com.j13.bar.server.facade.resp.FamilyAddResp;
import com.j13.bar.server.poppy.anno.Action;
import com.j13.bar.server.poppy.core.CommandContext;
import com.j13.bar.server.poppy.exceptions.CommonException;
import org.springframework.stereotype.Component;

@Component
public class FamilyFacade {


    @Action(name = "family.add",
            desc = "添加家族")
    public FamilyAddResp add(CommandContext ctxt, CommentAddMachineReq req) {
        FamilyAddResp resp = new FamilyAddResp();

        return resp;
    }

}
