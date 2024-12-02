package com.team14.clientProject.statisticsPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/statistics")
public class StatisticController {
    @GetMapping
    public String getStatisticsPage() {
        return "statistics/statistics";
    }
}
