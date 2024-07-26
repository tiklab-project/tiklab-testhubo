package io.thoughtware.teston.statistics.controller;

import io.thoughtware.core.Result;
import io.thoughtware.teston.statistics.model.CaseStatisticsModel;
import io.thoughtware.teston.statistics.model.StatisticsCaseTrendQuery;
import io.thoughtware.teston.statistics.service.StatisticsCaseTrendService;
import io.thoughtware.teston.statistics.service.StatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 接口环境 控制器
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    private static Logger logger = LoggerFactory.getLogger(StatisticsController.class);

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private StatisticsCaseTrendService statisticsCaseTrendService;


    @RequestMapping(path="/getTotalAndStatusStatistics",method = RequestMethod.POST)
    public Result<Map<String, Object>> createEnvironment(@RequestBody CaseStatisticsModel caseStatisticsModel){
        Map<String, Object> totalAndStatusStatistics = statisticsService.getTotalAndStatusStatistics(caseStatisticsModel);

        return Result.ok(totalAndStatusStatistics);
    }

    @RequestMapping(path="/getNewCreateCaseStatistics",method = RequestMethod.POST)
    public Result<HashMap<String,  List<Map<String, Object>>>> getNewCreateCaseStatistics(@RequestBody @NotNull @Valid CaseStatisticsModel caseStatisticsModel){
        HashMap<String,  List<Map<String, Object>>> apiStatusStatistics = statisticsService.getNewCreateCaseStatistics(caseStatisticsModel);

        return Result.ok(apiStatusStatistics);
    }

    @RequestMapping(path="/getCaseTestStatistics",method = RequestMethod.POST)
    public Result<HashMap<String, Object>> getCaseTestStatistics(@RequestBody @NotNull @Valid CaseStatisticsModel caseStatisticsModel){
        HashMap<String, Object> caseTestStatistics = statisticsService.getCaseTestStatistics(caseStatisticsModel);

        return Result.ok(caseTestStatistics);
    }

    @RequestMapping(path="/getAllCaseTestStatistics",method = RequestMethod.POST)
    public Result<Map<String, Map<String, Integer>>> getAllCaseTestStatistics(@RequestBody @NotNull @Valid CaseStatisticsModel caseStatisticsModel){
        Map<String, Map<String, Integer>> allCaseTestStatistics = statisticsService.getAllCaseTestStatistics(caseStatisticsModel);

        return Result.ok(allCaseTestStatistics);
    }

    @RequestMapping(path="/getCaseTestResultNumberStatistics",method = RequestMethod.POST)
    public Result<Map<String, Map<String, Integer>>> getCaseTestResultNumberStatistics(@RequestBody @NotNull @Valid CaseStatisticsModel caseStatisticsModel){
        Map<String, Map<String, Integer>> caseTestResultNumberStatistics = statisticsService.getCaseTestResultNumberStatistics(caseStatisticsModel);

        return Result.ok(caseTestResultNumberStatistics);
    }

    @RequestMapping(path="/getStatisticsCaseTrend",method = RequestMethod.POST)
    public Result<List<Map<String, Object>>> getStatisticsCaseTrend(@RequestBody @NotNull @Valid StatisticsCaseTrendQuery statisticsCaseTrendQuery){
        List<Map<String, Object>> statisticsCaseTrend = statisticsCaseTrendService.getStatisticsCaseTrend(statisticsCaseTrendQuery);

        return Result.ok(statisticsCaseTrend);
    }
}
