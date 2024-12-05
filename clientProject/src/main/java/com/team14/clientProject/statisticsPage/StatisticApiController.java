package com.team14.clientProject.statisticsPage;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
public class StatisticApiController {

    @Autowired
    private StatisticService statisticService;

    @GetMapping("/location")
    public List<Map<String, Object>> getLocationRecruitment() {
        return statisticService.getLocationRecruitment();
    }

    @GetMapping("/time-period")
    public List<Map<String, Object>> getTimePeriodRecruitment() {
        return statisticService.getTimePeriodRecruitment();
    }

    @GetMapping("/event")
    public List<Map<String, Object>> getEventRecruitment() {
        return statisticService.getEventRecruitment();
    }
}
