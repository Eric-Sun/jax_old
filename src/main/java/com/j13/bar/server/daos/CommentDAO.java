package com.j13.bar.server.daos;

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


    public List<CommentVO> listTop(int dzId) {
        String sql = "select c.user_id,c.content,c.is_top,c.hot,u.nick_name from comment c " +
                "left join user u on u.id=c.user_id where dz_id=? and is_top=1";
        return j.query(sql, new Object[]{dzId}, new RowMapper<CommentVO>() {
            @Override
            public CommentVO mapRow(ResultSet rs, int rowNum) throws SQLException {
                CommentVO vo = new CommentVO();
                vo.setUserId(rs.getInt(1));
                vo.setContent(rs.getString(2));
                vo.setIsTop(rs.getInt(3));
                vo.setHot(rs.getInt(4));
                vo.setUserName(rs.getString(5));
                return vo;
            }
        });
    }

    public List<CommentVO> listCommon(int dzId) {
        String sql = "select c.user_id,c.content,c.is_top,c.hot,u.nick_name from comment c " +
                "left join user u on u.id=c.user_id where dz_id=? and is_top=0";
        return j.query(sql, new Object[]{dzId}, new RowMapper<CommentVO>() {
            @Override
            public CommentVO mapRow(ResultSet rs, int rowNum) throws SQLException {
                CommentVO vo = new CommentVO();
                vo.setUserId(rs.getInt(1));
                vo.setContent(rs.getString(2));
                vo.setIsTop(rs.getInt(3));
                vo.setHot(rs.getInt(4));
                vo.setUserName(rs.getString(5));
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
}
