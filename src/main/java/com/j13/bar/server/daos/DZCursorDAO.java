package com.j13.bar.server.daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class DZCursorDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;


    public long insert(final long userId, final long dzId, final String deviceId) {
        KeyHolder holder = new GeneratedKeyHolder();
        final String sql = "insert into dz_cursor (user_id,dz_id,device_id) values(?,?,?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                final PreparedStatement pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                pstmt.setLong(1, userId);
                pstmt.setLong(2, dzId);
                pstmt.setString(3, deviceId);
                return pstmt;
            }
        }, holder);
        return holder.getKey().longValue();
    }


    public long getCursor(String deviceId) {
        final String sql = "select dz_id from dz_cursor where device_id=?";
        return jdbcTemplate.queryForLong(sql, new Object[]{deviceId});
    }

    public void addCursor(String deviceId, long step) {
        String sql = "update dz_cursor set dz_id=dz_id+? where device_id=?";
        jdbcTemplate.update(sql, new Object[]{step, deviceId});
    }


}
