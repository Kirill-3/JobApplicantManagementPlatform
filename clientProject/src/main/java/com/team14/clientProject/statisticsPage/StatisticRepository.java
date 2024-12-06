package com.team14.clientProject.statisticsPage;

import java.util.List;
import java.util.Map;

public interface StatisticRepository {
    List<Map<String, Object>> fetchLocationRecruitment();
    List<Map<String, Object>> fetchTimePeriodRecruitment();
    List<Map<String, Object>> fetchEventRecruitment();
}
