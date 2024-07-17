package io.thoughtware.teston.statistics.controller;

import io.thoughtware.core.Result;
import io.thoughtware.teston.statistics.model.NewCreateCaseStatisticsModel;
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

    @RequestMapping(path="/getTotalAndStatusStatistics",method = RequestMethod.POST)
    public Result<Map<String, Object>> createEnvironment(@RequestBody NewCreateCaseStatisticsModel newCreateCaseStatisticsModel){
        Map<String, Object> totalAndStatusStatistics = statisticsService.getTotalAndStatusStatistics(newCreateCaseStatisticsModel);

        return Result.ok(totalAndStatusStatistics);
    }

    @RequestMapping(path="/getNewCreateCaseStatistics",method = RequestMethod.POST)
    public Result<HashMap<String,  List<Map<String, Object>>>> getNewCreateCaseStatistics(@RequestBody @NotNull @Valid NewCreateCaseStatisticsModel newCreateCaseStatisticsModel){
        HashMap<String,  List<Map<String, Object>>> apiStatusStatistics = statisticsService.getNewCreateCaseStatistics(newCreateCaseStatisticsModel);

        return Result.ok(apiStatusStatistics);
    }

    @RequestMapping(path="/getCaseTestStatistics",method = RequestMethod.POST)
    public Result<HashMap<String, Object>> getCaseTestStatistics(@RequestBody @NotNull @Valid NewCreateCaseStatisticsModel newCreateCaseStatisticsModel){
        HashMap<String, Object> caseTestStatistics = statisticsService.getCaseTestStatistics(newCreateCaseStatisticsModel);

        return Result.ok(caseTestStatistics);
    }


}
