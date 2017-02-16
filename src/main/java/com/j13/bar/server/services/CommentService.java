package com.j13.bar.server.services;

import com.google.common.collect.Lists;
import com.j13.bar.server.core.HDConstants;
import com.j13.bar.server.daos.CommentDAO;
import com.j13.bar.server.exceptions.CommonException;
import com.j13.bar.server.exceptions.ErrorCode;
import com.j13.bar.server.helper.MachineUserHolder;
import com.j13.bar.server.helper.TicketManager;
import com.j13.bar.server.poppy.anno.Action;
import com.j13.bar.server.poppy.anno.Description;
import com.j13.bar.server.poppy.anno.NeedTicket;
import com.j13.bar.server.vos.*;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class CommentService {


    private static int SHARE_OPERATION = 1;
    private static int PRAISE_OPERATION = 0;

    private static String LOADMORE = "loadmore";
    private static String REFRESH = "refresh";
    private static String HOT = "hot";
    private static String NEW = "new";

    @Autowired
    CommentDAO commentDAO;

    @Autowired
    MachineUserHolder machineUserHolder;

    @Autowired
    TicketManager ticketManager;


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
    @Description("添加段子的评论")
    @NeedTicket
    public CommentAddResp add(@Description("评论的内容") String content,
                              @Description("段子id") Integer dzId,
                              @Description("评论者的userId") Integer userId,
                              @Description("如果是评论别的用户的评论的时候需要传") Integer cid,
                              @Description("评论唯一标识，客户端计算，用来防止用户重发, 例如:MD5(tid+userId+comment+客户端当前时间戳精确到小时)") String triggerId) {

        CommentAddResp resp = new CommentAddResp();

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


    @Action(name="comment.count")
    @NeedTicket
    public CommonResultResp count(Integer userId, Integer cid, Integer type) {
        CommonResultResp resp = new CommonResultResp();
        if (type == PRAISE_OPERATION) {
            commentDAO.praise(userId, cid);
        } else {
            commentDAO.share(userId, cid);
        }
        return resp;
    }


    @Action(name="comment.delete")
    @Description("删除评论")
    @NeedTicket
    public CommonResultResp delete(Integer userId, Integer cid) {

        CommonResultResp resp = new CommonResultResp();
        commentDAO.delete(userId, cid);
        return resp;
    }

    @Action(name="comment.list")
    @NeedTicket
    @Description("查询段子下的所有的评论")
    public CommentListResp list(
            @Description("段子id") Integer dzId,
            @Description("分页开始Id,首页传0") Integer lastId,
            @Description("加载类型,loadmore:加载更多,refresh:刷新最新") String type,
            @Description("分页展示评论数目") Integer pageSize,
            @Description("评论列表类型,new:最新评论,hot:热门评论") String category
    ) {
        CommentListResp resp = new CommentListResp();
        List<CommentVO> commentVOList = null;
        if (type.equals(LOADMORE)) {
            commentVOList = commentDAO.listCommon(dzId, lastId, pageSize);
        } else if (type.equals(REFRESH)) {
            commentVOList = commentDAO.listCommon(dzId, 0, pageSize);
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
