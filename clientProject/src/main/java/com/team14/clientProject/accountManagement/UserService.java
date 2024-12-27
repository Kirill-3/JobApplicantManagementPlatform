package com.team14.clientProject.accountManagement;

import com.team14.clientProject.adminPage.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String getUserNameById(int userId) {
        String query = "SELECT firstName FROM users WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{userId}, String.class);
    }

    public User getUserById(int userId) {
        String query = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{userId}, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("passwordHashed"));
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            user.setRole(rs.getString("role"));
            user.setLastLogin(rs.getTimestamp("lastLogin").toLocalDateTime());
            user.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
            return user;
        });
    }
}