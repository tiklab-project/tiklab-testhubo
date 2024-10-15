package io.tiklab.testhubo.statistics.service;


import io.tiklab.testhubo.common.MagicValue;
import io.tiklab.testhubo.statistics.dao.StatisticsDao;
import io.tiklab.testhubo.statistics.model.CaseStatisticsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StatisticsServicelmpl implements StatisticsService {

    @Autowired
    StatisticsDao statisticsDao;


    @Override
    public Map<String, Object> getTotalAndStatusStatistics(CaseStatisticsModel caseStatisticsModel) {
        Map<String, Object> statusList = statisticsDao.getTotalAndStatus(caseStatisticsModel);
        return statusList;
    }

    @Override
    public HashMap<String,  List<Map<String, Object>>>  getNewCreateCaseStatistics(CaseStatisticsModel caseStatisticsModel) {

        List<Map<String, Object>> resultList = statisticsDao.getNewCreateCaseStatistics(caseStatisticsModel);

        // 将查询结果转为日期到类型和数量的映射
        Map<Date, Map<String, Integer>> resultMap = new HashMap<>();
        for (Map<String, Object> row : resultList) {
            java.sql.Date date = (java.sql.Date) row.get("date");
            String type = (String) row.get("case_type");
            Integer count = ((Number) row.get("count")).intValue();
            resultMap.computeIfAbsent(date, k -> new HashMap<>()).put(type, count);
        }

        Date startTime = caseStatisticsModel.getStartTime();
        Date endTime = caseStatisticsModel.getEndTime();
        // 生成日期范围
        List<java.sql.Date> dates = generateDateRange(startTime, endTime);

        List<Map<String, Object>> fullList = new ArrayList<>();
        List<Map<String, Object>> hasDataList = new ArrayList<>();

        for (java.sql.Date date : dates) {
            Map<String, Object> map = new HashMap<>();
            map.put("date", date.toString());

            Map<String, Integer> typeCounts = resultMap.getOrDefault(date, new HashMap<>());
            int apiUnitCount = typeCounts.getOrDefault(MagicValue.CASE_TYPE_API_UNIT, 0);
            int apiSceneCount = typeCounts.getOrDefault(MagicValue.CASE_TYPE_API_SCENE, 0);
            int apiPerformCount = typeCounts.getOrDefault(MagicValue.CASE_TYPE_API_PERFORM, 0);
            int webSceneCount = typeCounts.getOrDefault(MagicValue.CASE_TYPE_WEB, 0);
            int appSceneCount = typeCounts.getOrDefault(MagicValue.CASE_TYPE_APP, 0);
            int functionCount = typeCounts.getOrDefault(MagicValue.CASE_TYPE_FUNCTION, 0);

            map.put("API_UNIT", apiUnitCount);
            map.put("API_SCENE", apiSceneCount);
            map.put("API_PERFORM", apiPerformCount);
            map.put("WEB_SCENE", webSceneCount);
            map.put("APP_SCENE", appSceneCount);
            map.put("FUNCTION", functionCount);

            fullList.add(map);

            // 如果当天有数据，则添加到 hasDataList
            if (apiUnitCount != 0 || apiSceneCount != 0 || apiPerformCount != 0 || webSceneCount != 0 || appSceneCount != 0 || functionCount != 0) {
                hasDataList.add(map);
            }
        }

        HashMap<String, List<Map<String, Object>>> statisticsMap = new HashMap<>();
        statisticsMap.put("hasDataList", hasDataList);
        statisticsMap.put("fullList", fullList);

        return statisticsMap;
    }


    @Override
    public HashMap<String,  Object>  getCaseTestStatistics(CaseStatisticsModel caseStatisticsModel) {
        Map<String, Object> totalAndStatus = statisticsDao.getTotalAndStatus(caseStatisticsModel);
        List<Map<String, Object>> caseTestResult = statisticsDao.getCaseTestResult(caseStatisticsModel);

        // 转换 caseTestResult 的格式
        Map<String, Map<String, Integer>> formattedCaseTestResult = formattedCaseTestResult(caseTestResult);

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", totalAndStatus);
        resultMap.put("case", formattedCaseTestResult);

        return  resultMap;
    }

    @Override
    public Map<String, Map<String, Integer>> getAllCaseTestStatistics(CaseStatisticsModel caseStatisticsModel) {
        List<Map<String, Object>> allCaseTestResult = statisticsDao.getAllCaseTestResult(caseStatisticsModel);
        Map<String, Map<String, Integer>> stringMapMap = formattedCaseTestResult(allCaseTestResult);

        return stringMapMap;
    }

    @Override
    public Map<String, Map<String, Integer>> getCaseTestResultNumberStatistics(CaseStatisticsModel caseStatisticsModel) {
        List<Map<String, Object>> caseTestResultNumberStatistics = statisticsDao.getCaseTestResultNumberStatistics(caseStatisticsModel);
        Map<String, Map<String, Integer>> stringMapMap = formattedCaseTestResult(caseTestResultNumberStatistics);
        return stringMapMap;
    }


    /**
     * 生成日期范围
     */
    private List<java.sql.Date> generateDateRange(Date startDate, Date endDate) {
        List<java.sql.Date> dates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        java.sql.Date end = new java.sql.Date(endDate.getTime());
        while (!calendar.getTime().after(end)) {
            dates.add(new java.sql.Date(calendar.getTimeInMillis()));
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return dates;
    }

    /**
     * 转换格式
     */
    private Map<String, Map<String, Integer>> formattedCaseTestResult(List<Map<String, Object>> caseTestResult){
        Map<String, Map<String, Integer>> formattedCaseTestResult = new HashMap<>();
        for (Map<String, Object> result : caseTestResult) {
            String caseType = (String) result.get("case_type");

            Object passObj = result.getOrDefault("pass", 0);
            Object failObj = result.getOrDefault("fail", 0);
            Object errorObj = result.getOrDefault("error", 0);

            // 将 Number 转换为 Integer，处理可能的 null 值
            int pass = (passObj != null) ? ((Number) passObj).intValue() : 0;
            int fail = (failObj != null) ? ((Number) failObj).intValue() : 0;
            int error = (errorObj != null) ? ((Number) errorObj).intValue() : 0;

            Map<String, Integer> statusMap = new HashMap<>();
            statusMap.put("total", pass+fail+error);
            statusMap.put("pass", pass);
            statusMap.put("fail", fail);
            statusMap.put("error", error);

            formattedCaseTestResult.put(caseType, statusMap);
        }

        return formattedCaseTestResult;
    }


}
































