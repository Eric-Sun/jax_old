package com.j13.bar.server.services;

import com.j13.bar.server.daos.CommentDAO;
import com.j13.bar.server.helper.MachineUserHolder;
import com.j13.bar.server.poppy.anno.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    CommentDAO commentDAO;

    @Autowired
    MachineUserHolder machineUserHolder;


    @Action(name = "comment.addMachine")
    public int addMachine(String content, Integer dzId, Integer hot, String sourceCommentId) {
        int userId = machineUserHolder.randomOne();
        int cId = commentDAO.addMachine(dzId, userId, content, hot, sourceCommentId);
        return cId;
    }

    @Action(name = "comment.addMachineTop")
    public int addMachineTop(String content, String sourceCommentId,
                             Integer dzId, Integer hot) {
        int userId = machineUserHolder.randomOne();
        int cId = commentDAO.addMachineTop(dzId, userId, content, hot, sourceCommentId);
        return cId;
    }

    @Action(name = "comment.add")
    public int add(Integer userId, String content, Integer dzId) {
        int cid = commentDAO.add(dzId, userId, content);
        return cid;
    }

}
