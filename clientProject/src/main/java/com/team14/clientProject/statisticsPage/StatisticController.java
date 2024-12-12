package com.team14.clientProject.statisticsPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

@Controller
public class StatisticController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/statistics")
    public ModelAndView getStatisticsOverviewPage(Locale locale) {
        ModelAndView modelAndView = new ModelAndView("statistics/statisticsOverview");
        String title = messageSource.getMessage("statistics.overview.title", null, locale);
        modelAndView.addObject("title", title);
        return modelAndView;
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
