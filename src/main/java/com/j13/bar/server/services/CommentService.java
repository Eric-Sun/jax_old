package com.j13.bar.server.services;

import com.j13.bar.server.core.RequestData;
import com.j13.bar.server.daos.CommentDAO;
import com.j13.bar.server.helper.MachineUserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CommentService")
public class CommentService {

    @Autowired
    CommentDAO commentDAO;

    @Autowired
    MachineUserHolder machineUserHolder;


    public int addMachine(RequestData rd) {
        int userId = machineUserHolder.randomOne();
        String content = rd.getString("content");
        int dzId = rd.getInteger("dzId");
        String sourceCommentId = rd.getString("sourceCommentId");
        int hot = rd.getInteger("hot");
        int cId = commentDAO.addMachine(dzId, userId, content, hot, sourceCommentId);
        return cId;
    }


    public int addMachineTop(RequestData rd) {
        int userId = machineUserHolder.randomOne();
        String content = rd.getString("content");
        String sourceCommentId = rd.getString("sourceCommentId");
        int dzId = rd.getInteger("dzId");
        int hot = rd.getInteger("hot");
        int cId = commentDAO.addMachineTop(dzId, userId, content, hot, sourceCommentId);
        return cId;
    }

    public int add(RequestData rd) {

        int userId = rd.getInteger("userId");
        String content = rd.getString("content");
        int dzId = rd.getInteger("dzId");

        int cid = commentDAO.add(dzId, userId, content);

        return cid;
    }

}
