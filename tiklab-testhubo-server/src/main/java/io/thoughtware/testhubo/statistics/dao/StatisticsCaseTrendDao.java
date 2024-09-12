package io.thoughtware.testhubo.statistics.dao;

import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.testhubo.statistics.entity.StatisticsCaseTrendEntity;
import io.thoughtware.testhubo.statistics.model.StatisticsCaseTrendQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 接口环境 数据访问
 */
@Repository
public class StatisticsCaseTrendDao {

    private static Logger logger = LoggerFactory.getLogger(StatisticsCaseTrendDao.class);

    @Autowired
    JpaTemplate jpaTemplate;


    public String createStatisticsCaseTrend(StatisticsCaseTrendEntity statisticsCaseTrendEntity) {
        String repositoryId = statisticsCaseTrendEntity.getRepositoryId();
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT ")
                .append(" SUM(CASE WHEN status IS NULL OR status = 0 THEN 1 ELSE 0 END) AS notstarted, ")
                .append(" SUM(CASE WHEN status = 1 THEN 1 ELSE 0 END) AS inprogress, ")
                .append(" SUM(CASE WHEN status = 2 THEN 1 ELSE 0 END) AS completed ")
                .append(" FROM teston_testcase t ")
                .append(" WHERE t.repository_id =  '").append(repositoryId).append("'");

        Map<String, Object> maps = jpaTemplate.getJdbcTemplate().queryForMap(sqlBuilder.toString());
        // 从 Map 中获取值，并转换为 Number 类型
        Object notStartedObj = maps.getOrDefault("notstarted", 0);
        Object inProgressObj = maps.getOrDefault("inprogress", 0);
        Object completedObj = maps.getOrDefault("completed", 0);

        // 将 Number 转换为 Integer，处理可能的 null 值
        int notStarted = (notStartedObj != null) ? ((Number) notStartedObj).intValue() : 0;
        int inProgress = (inProgressObj != null) ? ((Number) inProgressObj).intValue() : 0;
        int completed = (completedObj != null) ? ((Number) completedObj).intValue() : 0;

        // 将转换后的值设置到实体对象中
        statisticsCaseTrendEntity.setNotStarted(notStarted);
        statisticsCaseTrendEntity.setInProgress(inProgress);
        statisticsCaseTrendEntity.setCompleted(completed);

        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        String format = formater.format(new Date());
        statisticsCaseTrendEntity.setRecordTime(format);

        return jpaTemplate.save(statisticsCaseTrendEntity,String.class);
    }


    public List<Map<String, Object>> getStatisticsCaseTrend(StatisticsCaseTrendQuery statisticsCaseTrend) {
        String startTime = statisticsCaseTrend.getStartTime();
        String endTime = statisticsCaseTrend.getEndTime();
        String repositoryId = statisticsCaseTrend.getRepositoryId();

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT DATE(record_time) AS date, SUM(not_started) AS not_started, SUM(in_progress) AS in_progress, SUM(completed) AS completed ")
                .append("FROM teston_statistic_case_trend ")
                .append("WHERE record_time BETWEEN ? AND ? ");

        // 添加 repository_id 的条件
        if (repositoryId != null) {
            sqlBuilder.append(" And teston_statistic_case_trend.repository_id = ? ");
        }

        sqlBuilder.append("GROUP BY DATE(record_time) ")
                .append("ORDER BY DATE(record_time) ");

        String sql = sqlBuilder.toString();

        // 执行查询
        List<Map<String, Object>> resultList;
        if (repositoryId == null) {
            resultList = jpaTemplate.getJdbcTemplate().queryForList(
                    sql,
                    startTime,
                    endTime
            );
        } else {
            resultList = jpaTemplate.getJdbcTemplate().queryForList(
                    sql,
                    startTime,
                    endTime,
                    repositoryId
            );
        }

        return resultList;
    }


    public void updateStatisticsCaseTrend(StatisticsCaseTrendEntity statisticsCaseTrendEntity){
        jpaTemplate.update(statisticsCaseTrendEntity);
    }


    public void deleteStatisticsCaseTrend(String id){
        jpaTemplate.delete(StatisticsCaseTrendEntity.class,id);
    }

    public void deleteStatisticsCaseTrend(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }


    public StatisticsCaseTrendEntity findStatisticsCaseTrend(String id){
        return jpaTemplate.findOne(StatisticsCaseTrendEntity.class,id);
    }


    public List<StatisticsCaseTrendEntity> findAllStatisticsCaseTrend() {
        return jpaTemplate.findAll(StatisticsCaseTrendEntity.class);
    }

    public List<StatisticsCaseTrendEntity> findStatisticsCaseTrendList(List<String> idList) {
        return jpaTemplate.findList(StatisticsCaseTrendEntity.class,idList);
    }


}