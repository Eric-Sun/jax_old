package com.j13.bar.server.services;

import com.j13.bar.server.core.RequestData;
import com.j13.bar.server.daos.FavoriteDAO;
import com.j13.bar.server.vos.DZVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("FavoriteService")
public class FavoriteService {

    @Autowired
    FavoriteDAO favoriteDAO;

    public long add(RequestData rd) {
        int userId = rd.getUid();
        int dzId = rd.getInteger("dzId");
        long fid = favoriteDAO.add(userId, dzId);
        return fid;
    }

    public List<DZVO> list(RequestData rd) {
        int userId = rd.getUid();
        return favoriteDAO.list(userId);
    }

}
