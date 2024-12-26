package com.team14.clientProject.accountManagement;

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
}