package com.team14.clientProject.statisticsPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StatisticController {

    @GetMapping("/statistics")
    public ModelAndView getStatisticsPage() {
        ModelAndView modelAndView = new ModelAndView("/statistics/statistics");
        return modelAndView;
    }
}
