package com.j13.bar.server.facade;

import com.google.common.collect.Lists;
import com.j13.bar.server.daos.FavoriteDAO;
import com.j13.bar.server.facade.req.FavoriteAddReq;
import com.j13.bar.server.facade.req.FavoriteListReq;
import com.j13.bar.server.facade.resp.FavoriteAddResp;
import com.j13.bar.server.facade.resp.FavoriteListResp;
import com.j13.bar.server.facade.resp.GetDZResp;
import com.j13.bar.server.poppy.anno.Action;
import com.j13.bar.server.poppy.core.CommandContext;
import com.j13.bar.server.poppy.util.BeanUtils;
import com.j13.bar.server.vos.DZVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FavoriteFacade {

    @Autowired
    FavoriteDAO favoriteDAO;

    @Action(name = "favorite.add", desc = "添加段子到收藏夹")
    public FavoriteAddResp add(CommandContext ctxt, FavoriteAddReq req) {
        FavoriteAddResp resp = new FavoriteAddResp();
        int userId = req.getUserId();
        int dzId = req.getDzId();
        long fid = favoriteDAO.add(userId, dzId);
        resp.setFid(fid);
        return resp;
    }

    @Action(name = "favorite.list", desc = "查看收藏列表")
    public FavoriteListResp list(CommandContext ctxt, FavoriteListReq req) {
        FavoriteListResp resp = new FavoriteListResp();
        int userId = req.getUserId();
        List<DZVO> dzList = favoriteDAO.list(userId);
        List<GetDZResp> dzRespList = Lists.newLinkedList();
        for (DZVO dz : dzList) {
            GetDZResp r = new GetDZResp();
            BeanUtils.copyProperties(r, dz);
            dzRespList.add(r);
        }
        resp.setData(dzRespList);
        return resp;
    }

}
