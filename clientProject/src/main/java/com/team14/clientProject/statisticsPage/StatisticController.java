package com.team14.clientProject.statisticsPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StatisticController {

    @GetMapping("/statistics")
    public ModelAndView getStatisticsOverviewPage() {
        return new ModelAndView("statistics/statisticsOverview");
    }

    @GetMapping("/location-statistics")
    public ModelAndView getLocationStatistics() {
        return new ModelAndView("statistics/locationStatistics");
    }

    @GetMapping("/event-statistics")
    public ModelAndView getEventStatistics() {
        return new ModelAndView("statistics/eventStatistics");
    }

    @GetMapping("/time-statistics")
    public ModelAndView getTimeStatistics() {
        return new ModelAndView("statistics/timeStatistics");
    }
}
