package io.thoughtware.teston.statistics.service;

import io.thoughtware.core.exception.ApplicationException;
import io.thoughtware.teston.repository.model.Repository;
import io.thoughtware.teston.repository.service.RepositoryService;
import io.thoughtware.teston.statistics.dao.StatisticsCaseTrendDao;
import io.thoughtware.teston.statistics.entity.StatisticsCaseTrendEntity;
import io.thoughtware.teston.statistics.model.StatisticsCaseTrend;
import io.thoughtware.teston.statistics.model.StatisticsCaseTrendQuery;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.*;


/**
* app环境 服务
*/
@Service
public class StatisticsCaseTrendServiceImpl implements StatisticsCaseTrendService {

    @Autowired
    StatisticsCaseTrendDao statisticsCaseTrendDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    RepositoryService repositoryService;

    @Scheduled(cron = "0 59 23 * * ?")
    public void recordRepositoryEveryday() {
        List<Repository> allRepository = repositoryService.findAllRepository();

        for(int i=0;i<allRepository.size();i++) {
            String id = allRepository.get(i).getId();
            StatisticsCaseTrend statisticsCaseTrend = new StatisticsCaseTrend();
            statisticsCaseTrend.setRepositoryId(id);
            createStatisticsCaseTrend(statisticsCaseTrend);
        }
    }


    @Override
    public String createStatisticsCaseTrend(@NotNull @Valid StatisticsCaseTrend statisticsCaseTrend) {
        StatisticsCaseTrendEntity statisticsCaseTrendEntity = BeanMapper.map(statisticsCaseTrend, StatisticsCaseTrendEntity.class);

        return statisticsCaseTrendDao.createStatisticsCaseTrend(statisticsCaseTrendEntity);
    }

    @Override
    public void updateStatisticsCaseTrend(@NotNull @Valid StatisticsCaseTrend statisticsCaseTrend) {
        StatisticsCaseTrendEntity statisticsCaseTrendEntity = BeanMapper.map(statisticsCaseTrend, StatisticsCaseTrendEntity.class);

        statisticsCaseTrendDao.updateStatisticsCaseTrend(statisticsCaseTrendEntity);
    }

    @Override
    public void deleteStatisticsCaseTrend(@NotNull String id) {
        statisticsCaseTrendDao.deleteStatisticsCaseTrend(id);
    }


    @Override
    public StatisticsCaseTrend findOne(String id) {
        StatisticsCaseTrendEntity statisticsCaseTrendEntity = statisticsCaseTrendDao.findStatisticsCaseTrend(id);

        StatisticsCaseTrend statisticsCaseTrend = BeanMapper.map(statisticsCaseTrendEntity, StatisticsCaseTrend.class);
        return statisticsCaseTrend;
    }

    @Override
    public List<StatisticsCaseTrend> findList(List<String> idList) {
        List<StatisticsCaseTrendEntity> statisticsCaseTrendEntityList =  statisticsCaseTrendDao.findStatisticsCaseTrendList(idList);

        List<StatisticsCaseTrend> statisticsCaseTrendList =  BeanMapper.mapList(statisticsCaseTrendEntityList,StatisticsCaseTrend.class);
        return statisticsCaseTrendList;
    }

    @Override
    public StatisticsCaseTrend findStatisticsCaseTrend(@NotNull String id) {
        StatisticsCaseTrend statisticsCaseTrend = findOne(id);

        joinTemplate.joinQuery(statisticsCaseTrend);

        return statisticsCaseTrend;
    }

    @Override
    public List<StatisticsCaseTrend> findAllStatisticsCaseTrend() {
        List<StatisticsCaseTrendEntity> statisticsCaseTrendEntityList =  statisticsCaseTrendDao.findAllStatisticsCaseTrend();

        List<StatisticsCaseTrend> statisticsCaseTrendList =  BeanMapper.mapList(statisticsCaseTrendEntityList,StatisticsCaseTrend.class);

        joinTemplate.joinQuery(statisticsCaseTrendList);

        return statisticsCaseTrendList;
    }

    @Override
    public List<Map<String, Object>> getStatisticsCaseTrend(StatisticsCaseTrendQuery statisticsCaseTrend){
        List<Map<String, Object>> statisticsCaseTrend1 = statisticsCaseTrendDao.getStatisticsCaseTrend(statisticsCaseTrend);
        Map<Date, Map<String, Integer>> resultMap = new HashMap<>();
        for (Map<String, Object> row : statisticsCaseTrend1) {
            java.sql.Date date = (java.sql.Date) row.get("date");

            // 从 Map 中获取值，并转换为 Number 类型
            Object notStartedObj = row.getOrDefault("not_started", 0);
            Object inProgressObj = row.getOrDefault("in_progress", 0);
            Object completedObj = row.getOrDefault("completed", 0);

            // 将 Number 转换为 Integer，处理可能的 null 值
            int not_started = (notStartedObj != null) ? ((Number) notStartedObj).intValue() : 0;
            int in_progress = (inProgressObj != null) ? ((Number) inProgressObj).intValue() : 0;
            int completed = (completedObj != null) ? ((Number) completedObj).intValue() : 0;

            HashMap<String, Integer> objectObjectHashMap = new HashMap<>();
            objectObjectHashMap.put("notStarted",not_started);
            objectObjectHashMap.put("inProgress",in_progress);
            objectObjectHashMap.put("completed",completed);

            resultMap.put(date, objectObjectHashMap);
        }


        String startDateStr = statisticsCaseTrend.getStartTime();
        String endDateStr = statisticsCaseTrend.getEndTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate = dateFormat.parse(startDateStr);
            Date endDate = dateFormat.parse(endDateStr);
            // 生成日期范围
            List<java.sql.Date> dates = generateDateRange(startDate, endDate);

            List<Map<String, Object>> fullList = new ArrayList<>();

            for (java.sql.Date date : dates) {
                Map<String, Object> map = new HashMap<>();
                map.put("date", date.toString());
                Map<String, Integer> typeCounts = resultMap.getOrDefault(date, new HashMap<>());

                map.put("notStarted",typeCounts.getOrDefault("notStarted",0));
                map.put("inProgress",typeCounts.getOrDefault("inProgress",0));
                map.put("completed",typeCounts.getOrDefault("completed",0));


                fullList.add(map);
            }

            return fullList;
        } catch (Exception e){
            throw new ApplicationException(e);
        }
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
}