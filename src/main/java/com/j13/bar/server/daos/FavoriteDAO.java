package com.j13.bar.server.daos;

import com.j13.bar.server.vos.DZVO;
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
public class FavoriteDAO {

    @Autowired
    JdbcTemplate j;

    public long add(final int userId, final int dzId) {

        final String sql = "insert into favorite (user_id,dz_id,createtime) values (?,?,now())";
        KeyHolder holder = new GeneratedKeyHolder();

        j.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pstmt.setLong(1, userId);
                pstmt.setLong(2, dzId);
                return pstmt;
            }
        }, holder);

        return holder.getKey().longValue();
    }


    public int size(int userId) {
        String sql = "select count(1) from favorite where user_id=?";
        return j.queryForInt(sql, new Object[]{userId});
    }


    public void delete(int userId, int fid) {
        String sql = "update favorite set deleted=1 where user_id=? and id=?";
        j.update(sql, new Object[]{userId, fid});
    }


    public List<DZVO> list(int userId) {
        String sql = "select f.id,d.id,d.content,u.nick_name,u.img from dz d left join favorite f on f.dz_id=d.id " +
                "left join user u on u.id=d.user_id where f.user_id=? order by createtime desc";

        return j.query(sql, new Object[]{userId}, new RowMapper<DZVO>() {
            @Override
            public DZVO mapRow(ResultSet rs, int rowNum) throws SQLException {
                DZVO dz = new DZVO();
                dz.setFid(rs.getInt(1));
                dz.setId(rs.getInt(2));
                dz.setContent(rs.getString(3));
                dz.setUserName(rs.getString(4));
                dz.setImg(rs.getString(5));
                return dz;
            }
        });
    }


}
