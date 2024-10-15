package io.tiklab.testhubo.statistics.service;

import io.tiklab.testhubo.statistics.model.StatisticsCaseTrend;
import io.tiklab.testhubo.statistics.model.StatisticsCaseTrendQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
* 接口环境 服务接口
*/
@JoinProvider(model = StatisticsCaseTrend.class)
public interface StatisticsCaseTrendService {



    /**
    * 创建接口环境
    * @param apiEnv
    * @return
    */
    String createStatisticsCaseTrend(@NotNull @Valid StatisticsCaseTrend apiEnv);

    /**
    * 更新接口环境
    * @param apiEnv
    */
    void updateStatisticsCaseTrend(@NotNull @Valid StatisticsCaseTrend apiEnv);

    /**
    * 删除接口环境
    * @param id
    */
    void deleteStatisticsCaseTrend(@NotNull String id);

    @FindOne
    StatisticsCaseTrend findOne(@NotNull String id);
    @FindList
    List<StatisticsCaseTrend> findList(List<String> idList);

    /**
    * 根据id查找接口环境
    * @param id
    * @return
    */
    StatisticsCaseTrend findStatisticsCaseTrend(@NotNull String id);

    /**
    * 查找所有接口环境
    * @return
    */
    @FindAll
    List<StatisticsCaseTrend> findAllStatisticsCaseTrend();


    /**
     * 查看用例趋势
     */
    List<Map<String, Object>> getStatisticsCaseTrend(StatisticsCaseTrendQuery statisticsCaseTrend);

}