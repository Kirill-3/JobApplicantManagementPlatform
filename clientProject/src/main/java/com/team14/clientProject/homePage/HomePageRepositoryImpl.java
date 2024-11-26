package com.team14.clientProject.homePage;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class HomePageRepositoryImpl implements HomePageRepository {
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Applicants> ApplicantRowMapper;


}
