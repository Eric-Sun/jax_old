package com.j13.bar.server.services;

import com.j13.bar.server.daos.FavoriteDAO;
import com.j13.bar.server.poppy.anno.Action;
import com.j13.bar.server.vos.DZVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    @Autowired
    FavoriteDAO favoriteDAO;

    @Action("favorite.add")
    public long add(Integer userId, Integer dzId) {
        long fid = favoriteDAO.add(userId, dzId);
        return fid;
    }

    @Action("favorite.list")
    public List<DZVO> list(Integer userId) {
        return favoriteDAO.list(userId);
    }

}
