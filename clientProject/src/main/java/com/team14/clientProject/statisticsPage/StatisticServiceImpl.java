package com.team14.clientProject.statisticsPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StatisticServiceImpl implements StatisticService{

    @Autowired
    private StatisticRepository statisticRepository;

    @Override
    public List<Map<String, Object>> getLocationRecruitment() {
        return statisticRepository.fetchLocationRecruitment();
    }

    @Override
    public List<Map<String, Object>> getTimePeriodRecruitment() {
        return statisticRepository.fetchTimePeriodRecruitment();
    }

    @Override
    public List<Map<String, Object>> getEventRecruitment() {
        return statisticRepository.fetchEventRecruitment();
    }
}
