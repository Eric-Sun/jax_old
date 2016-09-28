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
import java.util.Calendar;
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


    public long addMachine(final long userId, final String content, final long imgId, final String md5, final int fetchSource,
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


    public List<Integer> getNoUserDZ(int limitSize) {
        String sql = "select id from dz where user_id=1 limit " + limitSize;
        return j.queryForList(sql, new Object[]{}, Integer.class);
    }


    public void updateDZWithMachineUser(int dzId, int machineUserId) {
        String sql = "update dz set user_id=? where id=?";
        j.update(sql, new Object[]{machineUserId, dzId});
    }

    public DZVO getMachineDZ(int dzId) {
        String sql = "select d.id,d.user_id,d.content,u.nick_name,u.img from dz d left join user u on u.id=d.user_id  where " +
                "d.id = ? and is_machine=1";
        return j.queryForObject(sql, new Object[]{dzId}, new RowMapper<DZVO>() {
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


    public int add(final int userId, final String content, final String md5) {
        final String sql = "insert into dz (user_id,content,img_id,createtime,md5) values (?,?,?,now(),?)";
        KeyHolder holder = new GeneratedKeyHolder();

        j.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pstmt.setLong(1, userId);
                pstmt.setString(2, content);
                pstmt.setString(3, md5);
                return pstmt;
            }
        }, holder);

        return holder.getKey().intValue();
    }

    public List<DZVO> listOneDayDZ(java.util.Date date, int size, int pageNum) {

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date);
        cal1.set(Calendar.HOUR_OF_DAY, 0);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date);
        cal2.set(Calendar.HOUR_OF_DAY, 23);
        cal2.set(Calendar.MINUTE, 59);
        cal2.set(Calendar.SECOND, 59);


        String sql = "select dz.id,dz.content,dz.source,dz.createtime from dz where createtime between ? and ? order by id desc limit " + pageNum * size + "," + size;
        return j.query(sql, new Object[]{cal1.getTime(), cal2.getTime()}, new RowMapper<DZVO>() {
            @Override
            public DZVO mapRow(ResultSet rs, int rowNum) throws SQLException {
                DZVO DZVO = new DZVO();
                DZVO.setId(rs.getInt(1));
                DZVO.setContent(rs.getString(2));
                DZVO.setSource(rs.getInt(3));
                DZVO.setCreatetime(rs.getDate(4));
                return DZVO;
            }
        });
    }

    public int sizeOneDayDZ(java.util.Date date) {

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date);
        cal1.set(Calendar.HOUR_OF_DAY, 0);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date);
        cal2.set(Calendar.HOUR_OF_DAY, 23);
        cal2.set(Calendar.MINUTE, 59);
        cal2.set(Calendar.SECOND, 59);


        String sql = "select count(1) from dz where createtime between ? and ? ";
        return j.queryForInt(sql, new Object[]{cal1.getTime(), cal2.getTime()});
    }
}
