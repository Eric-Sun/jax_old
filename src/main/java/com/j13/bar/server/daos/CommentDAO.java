package com.j13.bar.server.daos;

import com.j13.bar.server.vos.CommentResp;
import com.j13.bar.server.vos.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class CommentDAO {


    @Autowired
    JdbcTemplate j;

    public int addMachine(final int dzId, final int userId, final String content, final int hot,
                          final String sourceCommentId) {

        final String sql = "insert into comment (dz_id,user_id,content,createtime,hot,source_comment_id) values (?,?,?,now(),?,?)";
        KeyHolder holder = new GeneratedKeyHolder();

        j.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pstmt.setInt(1, dzId);
                pstmt.setInt(2, userId);
                pstmt.setString(3, content);
                pstmt.setInt(4, hot);
                pstmt.setString(5, sourceCommentId);
                return pstmt;
            }
        }, holder);

        return holder.getKey().intValue();

    }


    public List<CommentVO> listTop(int dzId, int lastId, int size) {
        String sql = "select c.user_id,c.content,u.nick_name,u.img,c.createtime,c.id,c.praise,c.share,c.reply_cid from comment c " +
                "left join user u on u.id=c.user_id where dz_id=? and is_top=1 limit ?,?";
        return j.query(sql, new Object[]{dzId, lastId, size}, new RowMapper<CommentVO>() {
            @Override
            public CommentVO mapRow(ResultSet rs, int rowNum) throws SQLException {
                CommentVO vo = new CommentVO();
                vo.setUserId(rs.getInt(1));
                vo.setContent(rs.getString(2));
                vo.setUserName(rs.getString(3));
                vo.setUserImg(rs.getString(4));
                vo.setCreateTime(rs.getDate(5).getTime());
                vo.setCid(rs.getInt(6));
                vo.setPraiseCount(rs.getInt(7));
                vo.setShareCount(rs.getInt(8));
                vo.setReplyCid(rs.getInt(9));
                return vo;
            }
        });
    }

    public List<CommentVO> listCommon(int dzId, int lastId, int size) {
        String sql = "select c.user_id,c.content,u.nick_name,u.img,c.createtime,c.id,c.praise,c.share,c.reply_cid from comment c " +
                "left join user u on u.id=c.user_id where dz_id=? and is_top=0 limit ?,?";
        return j.query(sql, new Object[]{dzId, lastId, size}, new RowMapper<CommentVO>() {
            @Override
            public CommentVO mapRow(ResultSet rs, int rowNum) throws SQLException {
                CommentVO vo = new CommentVO();
                vo.setUserId(rs.getInt(1));
                vo.setContent(rs.getString(2));
                vo.setUserName(rs.getString(3));
                vo.setUserImg(rs.getString(4));
                vo.setCreateTime(rs.getDate(5).getTime());
                vo.setCid(rs.getInt(6));
                vo.setPraiseCount(rs.getInt(7));
                vo.setShareCount(rs.getInt(8));
                vo.setReplyCid(rs.getInt(9));
                return vo;
            }
        });
    }

    public int addMachineTop(final int dzId, final int userId, final String content, final int hot,
                             final String sourceCommentId) {
        final String sql = "insert into comment (dz_id,user_id,content,createtime,is_top,hot,source_comment_id) values (?,?,?,now(),?,?,?)";
        KeyHolder holder = new GeneratedKeyHolder();

        j.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pstmt.setInt(1, dzId);
                pstmt.setInt(2, userId);
                pstmt.setString(3, content);
                pstmt.setInt(4, 1);
                pstmt.setInt(5, hot);
                pstmt.setString(6, sourceCommentId);
                return pstmt;
            }
        }, holder);

        return holder.getKey().intValue();
    }

    public int add(final int dzId, final int userId, final String content) {
        final String sql = "insert into comment (dz_id,user_id,content,createtime) values (?,?,?,now())";
        KeyHolder holder = new GeneratedKeyHolder();

        j.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pstmt.setInt(1, dzId);
                pstmt.setInt(2, userId);
                pstmt.setString(3, content);
                return pstmt;
            }
        }, holder);

        return holder.getKey().intValue();
    }

    public void praise(Integer userId, Integer cid) {
        String sql = "update comment set praise=praise+1 where id=? and user_id=?";
        j.update(sql, new Object[]{cid, userId});
    }

    public void share(Integer userId, Integer cid) {
        String sql = "update comment set share=share+1 where id=? and user_id=?";
        j.update(sql, new Object[]{cid, userId});
    }

    public void delete(Integer userId, Integer cid) {
        String sql = "update comment set deleted=1 where id=? and user_id=?";
        j.update(sql, new Object[]{cid, userId});
    }


    public int addForAnotherComment(final Integer dzId, final Integer userId, final String content, final Integer cid) {
        final String sql = "insert into comment (dz_id,user_id,content,createtime,reply_cid) values (?,?,?,now(),?)";
        KeyHolder holder = new GeneratedKeyHolder();

        j.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pstmt.setInt(1, dzId);
                pstmt.setInt(2, userId);
                pstmt.setString(3, content);
                pstmt.setInt(4, cid);
                return pstmt;
            }
        }, holder);

        return holder.getKey().intValue();
    }

    /**
     * 获取单条评论
     *
     * @param replyCid
     * @return
     */
    public CommentVO getOne(int replyCid) {
        String sql = "select c.user_id,c.content,u.nick_name,u.img,c.createtime,c.id,c.praise,c.share,c.reply_cid from comment c " +
                "left join user u on u.id=c.user_id where id=?";
        return j.queryForObject(sql, new Object[]{replyCid}, new RowMapper<CommentVO>() {
            @Override
            public CommentVO mapRow(ResultSet rs, int rowNum) throws SQLException {
                CommentVO vo = new CommentVO();
                vo.setUserId(rs.getInt(1));
                vo.setContent(rs.getString(2));
                vo.setUserName(rs.getString(3));
                vo.setUserImg(rs.getString(4));
                vo.setCreateTime(rs.getDate(5).getTime());
                vo.setCid(rs.getInt(6));
                vo.setPraiseCount(rs.getInt(7));
                vo.setShareCount(rs.getInt(8));
                vo.setReplyCid(rs.getInt(9));
                return vo;
            }
        });
    }


    public int count(int dzId) {
        String sql = "select count(1) from comment where dz_id=?";
        return j.queryForObject(sql, new Object[]{dzId}, Integer.class);
    }

    /**
     * 检查插入的机器抓取的评论是否已经存在
     *
     * @param dzId
     * @param sourceCommentId
     * @return 如果存在返回true
     */
    public boolean checkMachineCommentExist(int dzId, String sourceCommentId) {
        String sql = "select count(1) from comment where dz_id=? and source_comment_id=?";
        int count = j.queryForObject(sql, new Object[]{}, Integer.class);
        return count > 0 ? true : false;
    }
}
