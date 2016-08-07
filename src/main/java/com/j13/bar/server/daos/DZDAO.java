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
public class DZDAO {

    @Autowired
    JdbcTemplate j;


    public List<DZVO> list10(long dzId) {
        String sql = "select d.id,d.user_id,d.content,u.nick_name,u.img from dz d left join user u on u.id=d.user_id  where " +
                "d.id in (?,?,?,?,?,?,?,?,?,?) ";
        long dzId1 = dzId;
        long dzId2 = dzId1 + 1;
        long dzId3 = dzId2 + 1;
        long dzId4 = dzId3 + 1;
        long dzId5 = dzId4 + 1;
        long dzId6 = dzId5 + 1;
        long dzId7 = dzId6 + 1;
        long dzId8 = dzId7 + 1;
        long dzId9 = dzId8 + 1;
        long dzId10 = dzId9 + 1;

        return j.query(sql, new Object[]{dzId1, dzId2, dzId3, dzId4, dzId5, dzId6, dzId7, dzId8, dzId9, dzId10}, new RowMapper<DZVO>() {
            @Override
            public DZVO mapRow(ResultSet rs, int rowNum) throws SQLException {
                DZVO DZVO = new DZVO();
                DZVO.setId(rs.getLong(1));
                DZVO.setUserId(rs.getLong(2));
                DZVO.setContent(rs.getString(3));
                DZVO.setUserName(rs.getString(4));
                DZVO.setImg(rs.getString(5));
                return DZVO;
            }
        });
    }


    public long insert(final long userId, final String content, final long imgId, final String md5, final int fetchSource,
                       final String sourceId) {
        final String sql = "insert into dz (user_id,content,img_id,createtime,md5,source,source_dz_id) values (?,?,?,now(),?,?,?)";
        KeyHolder holder = new GeneratedKeyHolder();

        j.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pstmt.setLong(1, userId);
                pstmt.setString(2, content);
                pstmt.setLong(3, imgId);
                pstmt.setString(4, md5);
                pstmt.setInt(5, fetchSource);
                pstmt.setString(6, sourceId);
                return pstmt;
            }
        }, holder);

        return holder.getKey().longValue();
    }

    public boolean checkExist(String md5) {
        String sql = "select count(1) from dz where md5=?";
        return j.queryForInt(sql, new Object[]{md5}) == 0 ? false : true;
    }
}
