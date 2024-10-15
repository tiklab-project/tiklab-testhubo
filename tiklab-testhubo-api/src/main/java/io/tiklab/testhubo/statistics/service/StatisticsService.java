package io.tiklab.testhubo.statistics.service;

import io.tiklab.testhubo.statistics.model.CaseStatisticsModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 获取系统统计信息
*/
public interface StatisticsService {

    /**
     * 接口状态的统计信息
     * 总数量统计，状态数量统计
     * @return
     */
    Map<String, Object> getTotalAndStatusStatistics(CaseStatisticsModel caseStatisticsModel);

    /**
     * 接口新增的统计信息
     * @return
     */
    HashMap<String,  List<Map<String, Object>>> getNewCreateCaseStatistics(CaseStatisticsModel caseStatisticsModel);

    /**
     * 获取用例测试的统计信息
     * 包含总数量统计，状态数量统计
     * 每个测试的执行情况的统计
     * @param caseStatisticsModel
     * @return
     */
    HashMap<String,  Object>  getCaseTestStatistics(CaseStatisticsModel caseStatisticsModel);

    /**
     * 查询用例的执行情况
     */
    Map<String, Map<String, Integer>>  getAllCaseTestStatistics(CaseStatisticsModel caseStatisticsModel);

    /**
     * 获取测试结果数据
     * 可以通过repositoryId，时间范围 筛选
     * @param caseStatisticsModel
     * @return
     */
    Map<String, Map<String, Integer>> getCaseTestResultNumberStatistics(CaseStatisticsModel caseStatisticsModel);
}