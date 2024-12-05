package com.team14.clientProject.statisticsPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class StatisticRepositoryImpl implements StatisticRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> fetchLocationRecruitment() {
        String sql = "select location, count(*) as count from applicants group by location";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> fetchTimePeriodRecruitment() {
        String sql = "select date_format(createdAt, '%Y-%m') as time_period, count(*) as count from applicants group by time_period";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> fetchEventRecruitment() {
        String sql = "select eventAttended, count(*) as count from applicants group by eventAttended";
        return jdbcTemplate.queryForList(sql);
    }
}
