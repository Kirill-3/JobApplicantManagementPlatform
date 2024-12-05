package com.team14.clientProject.statisticsPage;

import java.util.List;
import java.util.Map;

public interface StatisticService {
    List<Map<String, Object>> getLocationRecruitment();
    List<Map<String, Object>> getTimePeriodRecruitment();
    List<Map<String, Object>> getEventRecruitment();
}
