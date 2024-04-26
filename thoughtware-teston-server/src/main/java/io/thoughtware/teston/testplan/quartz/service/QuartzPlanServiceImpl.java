package io.thoughtware.teston.testplan.quartz.service;

import io.thoughtware.core.exception.ApplicationException;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.rpc.annotation.Exporter;
import io.thoughtware.teston.testplan.quartz.dao.QuartzPlanDao;
import io.thoughtware.teston.testplan.quartz.entity.QuartzPlanEntity;
import io.thoughtware.teston.testplan.quartz.model.QuartzPlan;
import io.thoughtware.teston.testplan.quartz.model.QuartzPlanQuery;
import io.thoughtware.teston.testplan.quartz.model.QuartzTimePlan;
import io.thoughtware.teston.testplan.quartz.model.QuartzTimePlanQuery;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
* 定时任务 服务
*/
@Service
@Exporter
public class QuartzPlanServiceImpl implements QuartzPlanService {

    @Autowired
    QuartzPlanDao quartzPlanDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    QuartzTimePlanService quartzTimePlanService;


    @Override
    public String createQuartzPlan(@NotNull @Valid QuartzPlan quartzPlan) {
        quartzPlan.setCreateTime(new Timestamp(System.currentTimeMillis()));
        quartzPlan.setState(0);
        QuartzPlanEntity quartzPlanEntity = BeanMapper.map(quartzPlan, QuartzPlanEntity.class);
        String quartzPlanId = quartzPlanDao.createQuartzPlan(quartzPlanEntity);

        if (quartzPlan.getWeekList() == null || quartzPlan.getWeekList().length == 0){
            throw new ApplicationException(50001,"无法获取到执行时间");
        }

        for (Integer integer : quartzPlan.getWeekList()) {
            QuartzTimePlan quartzTimePlan = new QuartzTimePlan();
            quartzTimePlan.setQuartzPlanId(quartzPlanId);
            quartzTimePlan.setWeek(integer);
            quartzTimePlan.setExeType(quartzPlan.getExeType());
            quartzTimePlan.setTime(quartzPlan.getTime());
            quartzTimePlan.setRepositoryId(quartzPlan.getRepositoryId());
            quartzTimePlan.setTestPlanId(quartzPlan.getTestPlanId());
            quartzTimePlanService.createQuartzTimePlan(quartzTimePlan);
        }

        return quartzPlanId;
    }

    @Override
    public void updateQuartzPlan(@NotNull @Valid QuartzPlan quartzPlan) {
        //删除原来的执行时间
        quartzTimePlanService.deleteAllQuartzTimePlan(quartzPlan.getId());
        if (quartzPlan.getWeekList() == null || quartzPlan.getWeekList().length == 0){
            throw new ApplicationException(50001,"无法获取到执行时间");
        }

        for (Integer integer : quartzPlan.getWeekList()) {
            QuartzTimePlan quartzTimePlan = new QuartzTimePlan();
            quartzTimePlan.setQuartzPlanId(quartzPlan.getId());
            quartzTimePlan.setWeek(integer);
            quartzTimePlan.setExeType(quartzPlan.getExeType());
            quartzTimePlan.setTime(quartzPlan.getTime());
            quartzTimePlan.setRepositoryId(quartzPlan.getRepositoryId());
            quartzTimePlan.setTestPlanId(quartzPlan.getTestPlanId());
            quartzTimePlanService.createQuartzTimePlan(quartzTimePlan);
        }

        QuartzPlanEntity quartzPlanEntity = BeanMapper.map(quartzPlan, QuartzPlanEntity.class);
        quartzPlanDao.updateQuartzPlan(quartzPlanEntity);
    }

    @Override
    public void updateQuartzPlanState(@NotNull @Valid QuartzPlan quartzPlan) {
        QuartzPlanEntity quartzPlanEntity = BeanMapper.map(quartzPlan, QuartzPlanEntity.class);
        quartzPlanDao.updateQuartzPlan(quartzPlanEntity);
    }

    @Override
    public void deleteQuartzPlan(@NotNull String id) {
        quartzPlanDao.deleteQuartzPlan(id);
        quartzTimePlanService.deleteAllQuartzTimePlan(id);
    }

    @Override
    public void deleteAllQuartzPlan(String testPlanId) {
        QuartzPlanQuery quartzPlanQuery = new QuartzPlanQuery();
        quartzPlanQuery.setTestPlanId(testPlanId);
        List<QuartzPlan> quartzPlanList = findQuartzPlanList(quartzPlanQuery);
        for(QuartzPlan quartzPlan:quartzPlanList){
            deleteQuartzPlan(quartzPlan.getId());
        }
    }


    @Override
    public QuartzPlan findOne(String id) {
        QuartzPlanEntity quartzPlanEntity = quartzPlanDao.findQuartzPlan(id);

        QuartzPlan quartzPlan = BeanMapper.map(quartzPlanEntity, QuartzPlan.class);
        return quartzPlan;
    }


    @Override
    public List<QuartzPlan> findList(List<String> idList) {
        List<QuartzPlanEntity> quartzPlanEntityList =  quartzPlanDao.findQuartzPlanList(idList);

        List<QuartzPlan> quartzPlanList =  BeanMapper.mapList(quartzPlanEntityList,QuartzPlan.class);
        return quartzPlanList;
    }

    @Override
    public QuartzPlan findQuartzPlan(@NotNull String id) {
        QuartzPlan quartzPlan = findOne(id);

        QuartzTimePlanQuery quartzTimePlanQuery = new QuartzTimePlanQuery();
        quartzTimePlanQuery.setQuartzPlanId(id);
        List<QuartzTimePlan> quartzTimePlanList = quartzTimePlanService.findQuartzTimePlanList(quartzTimePlanQuery);
        if(quartzTimePlanList!=null){
            List<Integer> weekList = new ArrayList<>();
            for(QuartzTimePlan quartzTimePlan:quartzTimePlanList){
                Integer week = quartzTimePlan.getWeek();
                weekList.add(week);
            }

            // 将列表转换为数组
            Integer[] weekArray = weekList.toArray(new Integer[0]);
            quartzPlan.setWeekList(weekArray);
            quartzPlan.setTime(quartzTimePlanList.get(0).getTime());
        }

        return quartzPlan;
    }

    @Override
    public List<QuartzPlan> findAllQuartzPlan() {
        List<QuartzPlanEntity> quartzPlanEntityList =  quartzPlanDao.findAllQuartzPlan();

        List<QuartzPlan> quartzPlanList =  BeanMapper.mapList(quartzPlanEntityList,QuartzPlan.class);

        joinTemplate.joinQuery(quartzPlanList);
        return quartzPlanList;
    }

    @Override
    public List<QuartzPlan> findQuartzPlanList(QuartzPlanQuery quartzPlanQuery) {
        List<QuartzPlanEntity> quartzPlanEntityList = quartzPlanDao.findQuartzPlanList(quartzPlanQuery);
        List<QuartzPlan> quartzPlanList = BeanMapper.mapList(quartzPlanEntityList,QuartzPlan.class);

        if (quartzPlanList.isEmpty()){return Collections.emptyList();}

        for (QuartzPlan quartzPlan : quartzPlanList) {
            QuartzTimePlan quartzTimePlan = quartzTimePlanService.findQuartzTimePlanByQuartzPlanId(quartzPlan.getId());
            quartzPlan.setQuartzTimePlan(quartzTimePlan);
        }

        return quartzPlanList;
    }

    @Override
    public Pagination<QuartzPlan> findQuartzPlanPage(QuartzPlanQuery quartzPlanQuery) {
        Pagination<QuartzPlanEntity>  pagination = quartzPlanDao.findQuartzPlanPage(quartzPlanQuery);

        List<QuartzPlan> quartzPlanList = BeanMapper.mapList(pagination.getDataList(),QuartzPlan.class);

        joinTemplate.joinQuery(quartzPlanList);

        return PaginationBuilder.build(pagination,quartzPlanList);
    }


}