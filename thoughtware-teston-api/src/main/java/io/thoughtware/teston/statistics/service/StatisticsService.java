package io.thoughtware.teston.statistics.service;

import io.thoughtware.teston.statistics.model.NewCreateCaseStatisticsModel;

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
    Map<String, Object> getTotalAndStatusStatistics(NewCreateCaseStatisticsModel newCreateCaseStatisticsModel);

    /**
     * 接口新增的统计信息
     * @return
     */
    HashMap<String,  List<Map<String, Object>>> getNewCreateCaseStatistics(NewCreateCaseStatisticsModel newCreateCaseStatisticsModel);

    /**
     * 获取用例测试的统计信息
     * 包含总数量统计，状态数量统计
     * 每个测试的执行情况的统计
     * @param newCreateCaseStatisticsModel
     * @return
     */
    HashMap<String,  Object>  getCaseTestStatistics(NewCreateCaseStatisticsModel newCreateCaseStatisticsModel);
}