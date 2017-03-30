package com.j13.bar.server.facade;

import com.j13.bar.server.daos.CommentDAO;
import com.j13.bar.server.poppy.exceptions.CommonException;
import com.j13.bar.server.core.ErrorCode;
import com.j13.bar.server.facade.req.*;
import com.j13.bar.server.facade.resp.CommentAddMachineResp;
import com.j13.bar.server.helper.MachineUserHolder;
import com.j13.bar.server.helper.TicketManager;
import com.j13.bar.server.poppy.anno.Action;
import com.j13.bar.server.poppy.anno.NeedTicket;
import com.j13.bar.server.poppy.core.CommandContext;
import com.j13.bar.server.vos.*;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentFacade {


    private static int SHARE_OPERATION = 1;
    private static int PRAISE_OPERATION = 0;

    private static String LOADMORE = "loadmore";
    private static String REFRESH = "refresh";
    private static String HOT = "hot";
    private static String NEW = "new";
    // 插入的机器抓取的评论是否置顶，1为置顶
    private static int TOP = 1;

    @Autowired
    CommentDAO commentDAO;

    @Autowired
    MachineUserHolder machineUserHolder;

    @Autowired
    TicketManager ticketManager;


    @Action(name = "comment.addMachine", desc = "抓取系统添加评论，如果返回2001说明插入的评论已经在对应的dzId中存在，忽略处理")
    public CommentAddMachineResp addMachine(CommandContext ctxt, CommentAddMachineReq req) {
        CommentAddMachineResp resp = new CommentAddMachineResp();
        int dzId = req.getDzId();
        String content = req.getContent();
        int isTop = req.getIsTop();
        int hot = req.getHot();
        String sourceCommentId = req.getSourceCommentId();
        int userId = machineUserHolder.randomOne();
        int cid = 0;
        boolean exist = commentDAO.checkMachineCommentExist(dzId, sourceCommentId);
        if (exist) {
            throw new CommonException(ErrorCode.Comment.MACHINE_COMMENT_EXISTED);
        }
        if (isTop == TOP) {
            cid = commentDAO.addMachineTop(dzId, userId, content, hot, sourceCommentId);
        } else {
            cid = commentDAO.addMachine(dzId, userId, content, hot, sourceCommentId);
        }
        resp.setId(cid);
        return resp;
    }


    @Action(name = "comment.add", desc = "添加评论")
    @NeedTicket
    public CommentAddResp add(CommandContext ctxt, CommentAddReq req) {

        CommentAddResp resp = new CommentAddResp();

        int cid = req.getCid();
        int dzId = req.getDzId();
        int userId = req.getUserId();
        String content = req.getContent();
        int finalCid = 0;
        if (cid == 0) {
            // check user
            finalCid = commentDAO.add(dzId, userId, content);
        } else {
            // 评论的是评论
            finalCid = commentDAO.addForAnotherComment(dzId, userId, content, cid);
        }

        resp.setCid(finalCid);
        return resp;
    }


    @Action(name = "comment.count", desc = "段子评论操作的计数")
    @NeedTicket
    public CommonResultResp count(CommandContext ctxt, CommentCountReq req) {
        CommonResultResp resp = new CommonResultResp();
        int cid = req.getCid();
        int userId = req.getUserId();
        int type = req.getType();
        if (type == PRAISE_OPERATION) {
            commentDAO.praise(userId, cid);
        } else {
            commentDAO.share(userId, cid);
        }
        return resp;
    }


    @Action(name = "comment.delete", desc = "删除段子评论")
    @NeedTicket
    public CommonResultResp delete(CommandContext ctxt, CommentDeleteReq req) {
        int userId = req.getUserId();
        int cid = req.getCid();

        CommonResultResp resp = new CommonResultResp();
        commentDAO.delete(userId, cid);
        return resp;
    }

    @Action(name = "comment.list", desc = "评论列表")
    @NeedTicket
    public CommentListResp list(CommandContext ctxt, CommentListReq req) {

        String type = req.getType();
        int dzId = req.getDzId();
        int lastId = req.getLastId();
        int pageSize = req.getPageSize();
        String category = req.getCategory();
        CommentListResp resp = new CommentListResp();
        List<CommentVO> commentVOList = null;
        if (type.equals(LOADMORE)) {
            if (category.equals(NEW))
                commentVOList = commentDAO.listCommon(dzId, lastId, pageSize);
            else
                commentVOList = commentDAO.listTop(dzId, lastId, pageSize);
        } else if (type.equals(REFRESH)) {

            if (category.equals(NEW))
                commentVOList = commentDAO.listCommon(dzId, 0, pageSize);
            else
                commentVOList = commentDAO.listTop(dzId, 0, pageSize);
        } else {
            throw new CommonException(ErrorCode.Common.INPUT_PARAMETER_ERROR);
        }

        for (CommentVO vo : commentVOList) {
            CommentResp commentResp = new CommentResp();
            try {
                BeanUtils.copyProperties(commentResp, vo);
            } catch (Exception e) {
                throw new CommonException(ErrorCode.System.SYSTEM_ERROR);
            }
            resp.getComments().add(commentResp);

            if (vo.getReplyCid() != 0) {
                CommentVO replyCommentVO = commentDAO.getOne(vo.getReplyCid());
                CommentReplyResp commentReplyResp = new CommentReplyResp();
                try {
                    BeanUtils.copyProperties(commentReplyResp, replyCommentVO);
                } catch (Exception e) {
                    throw new CommonException(ErrorCode.System.SYSTEM_ERROR);
                }
                commentResp.getReplys().add(commentReplyResp);
            }
        }

        int count = commentDAO.count(dzId);
        resp.setCommentCount(count);
        return resp;
    }
}
