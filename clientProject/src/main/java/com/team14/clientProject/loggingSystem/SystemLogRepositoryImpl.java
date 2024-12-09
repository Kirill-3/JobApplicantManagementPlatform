package com.team14.clientProject.loggingSystem;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SystemLogRepositoryImpl implements SystemLogRepository {
    private JdbcTemplate jdbcTemplate;
    private RowMapper<SystemLog> SystemLogMapper;

    public SystemLogRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        createSystemLogRowMapper();
    }

    private void createSystemLogRowMapper() {
        SystemLogMapper = (rs, rowNum) -> {
            return new SystemLog(rs.getInt("systemLogId"),
                    rs.getInt("userId"),
                    rs.getString("actionTaken"),
                    rs.getString("timestamp"));
        };
    }


    @Override
    public List<SystemLog> getLogs() {
        String sql = "SELECT * FROM systemLogs ORDER BY timestamp DESC";
        return jdbcTemplate.query(sql, SystemLogMapper);
    }

    @Override
    public void addUserLog() {
        String sql = "INSERT INTO systemLogs (userId, actionTaken) VALUES ((SELECT MAX(ID) FROM users), 'addedUser')";
        jdbcTemplate.update(sql);
    }

    @Override
    public void removeUserLog(int UserID) {
        String sql = "INSERT INTO systemLogs (userId, actionTaken) VALUES (?, 'removedUser')";
        jdbcTemplate.update(sql, UserID);
    }
}
