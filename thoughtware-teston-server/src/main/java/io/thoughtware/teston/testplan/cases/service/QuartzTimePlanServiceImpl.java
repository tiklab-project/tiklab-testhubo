package io.thoughtware.teston.testplan.cases.service;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.rpc.annotation.Exporter;
import io.thoughtware.teston.common.TestOnUnit;
import io.thoughtware.teston.testplan.cases.dao.QuartzTimePlanDao;
import io.thoughtware.teston.testplan.cases.entity.QuartzTimePlanEntity;
import io.thoughtware.teston.testplan.cases.model.QuartzTimePlan;
import io.thoughtware.teston.testplan.cases.model.QuartzTimePlanQuery;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.toolkit.join.JoinTemplate;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
* 定时任务 服务
*/
@Service
@Exporter
public class QuartzTimePlanServiceImpl implements QuartzTimePlanService {

    @Autowired
    QuartzTimePlanDao quartzTimePlanDao;

    @Autowired
    JoinTemplate joinTemplate;


    @Override
    public String createQuartzTimePlan(@NotNull @Valid QuartzTimePlan quartzTimePlan) {
        String cron = TestOnUnit.weekCron(quartzTimePlan.getTime(), quartzTimePlan.getWeek());
        quartzTimePlan.setCron(cron);
        QuartzTimePlanEntity quartzTimePlanEntity = BeanMapper.map(quartzTimePlan, QuartzTimePlanEntity.class);

        return quartzTimePlanDao.createQuartzTimePlan(quartzTimePlanEntity);
    }

    @Override
    public void updateQuartzTimePlan(@NotNull @Valid QuartzTimePlan quartzTimePlan) {
        QuartzTimePlanEntity quartzTimePlanEntity = BeanMapper.map(quartzTimePlan, QuartzTimePlanEntity.class);

        quartzTimePlanDao.updateQuartzTimePlan(quartzTimePlanEntity);
    }

    @Override
    public void deleteQuartzTimePlan(@NotNull String id) {
        quartzTimePlanDao.deleteQuartzTimePlan(id);
    }

    @Override
    public void deleteAllQuartzTimePlan(String quartzPlanId) {
        QuartzTimePlanQuery quartzTimePlanQuery = new QuartzTimePlanQuery();
        quartzTimePlanQuery.setQuartzPlanId(quartzPlanId);
        List<QuartzTimePlan> quartzTimePlanList = findQuartzTimePlanList(quartzTimePlanQuery);

        if(quartzTimePlanList!= null && !quartzTimePlanList.isEmpty()){
            for(QuartzTimePlan quartzTimePlan : quartzTimePlanList){
                deleteQuartzTimePlan(quartzTimePlan.getId());
            }
        }
    }


    @Override
    public QuartzTimePlan findOne(String id) {
        QuartzTimePlanEntity quartzTimePlanEntity = quartzTimePlanDao.findQuartzTimePlan(id);

        QuartzTimePlan quartzTimePlan = BeanMapper.map(quartzTimePlanEntity, QuartzTimePlan.class);
        return quartzTimePlan;
    }


    @Override
    public List<QuartzTimePlan> findList(List<String> idList) {
        List<QuartzTimePlanEntity> quartzTimePlanEntityList =  quartzTimePlanDao.findQuartzTimePlanList(idList);

        List<QuartzTimePlan> quartzTimePlanList =  BeanMapper.mapList(quartzTimePlanEntityList,QuartzTimePlan.class);
        return quartzTimePlanList;
    }

    @Override
    public QuartzTimePlan findQuartzTimePlan(@NotNull String id) {
        QuartzTimePlan quartzTimePlan = findOne(id);

        joinTemplate.joinQuery(quartzTimePlan);
        return quartzTimePlan;
    }

    @Override
    public QuartzTimePlan findQuartzTimePlanByQuartzPlanId(String quartzPlanId) {
        QuartzTimePlanQuery quartzTimePlanQuery = new QuartzTimePlanQuery();
        quartzTimePlanQuery.setQuartzPlanId(quartzPlanId);
        List<QuartzTimePlan> quartzTimePlanList = findQuartzTimePlanList(quartzTimePlanQuery);

        if(CollectionUtils.isNotEmpty(quartzTimePlanList)){
            QuartzTimePlan quartzTimePlan = new QuartzTimePlan();
            StringBuilder showTime = new StringBuilder();
            for(QuartzTimePlan quartzTime : quartzTimePlanList){
                Map<String, String> map = TestOnUnit.cronWeek(quartzTime.getCron());
                showTime.append(map.get("cron")).append(" | ");
            }
            QuartzTimePlan quartzTimePlan1 = quartzTimePlanList.get(0);
            showTime.append(quartzTimePlan1.getTime());
            quartzTimePlan.setShowTime(showTime.toString());

            return quartzTimePlan;
        }

        return null;
    }

    @Override
    public List<QuartzTimePlan> findAllQuartzTimePlan() {
        List<QuartzTimePlanEntity> quartzTimePlanEntityList =  quartzTimePlanDao.findAllQuartzTimePlan();

        List<QuartzTimePlan> quartzTimePlanList =  BeanMapper.mapList(quartzTimePlanEntityList,QuartzTimePlan.class);

        joinTemplate.joinQuery(quartzTimePlanList);
        return quartzTimePlanList;
    }

    @Override
    public List<QuartzTimePlan> findQuartzTimePlanList(QuartzTimePlanQuery quartzTimePlanQuery) {
        List<QuartzTimePlanEntity> quartzTimePlanEntityList = quartzTimePlanDao.findQuartzTimePlanList(quartzTimePlanQuery);

        List<QuartzTimePlan> quartzTimePlanList = BeanMapper.mapList(quartzTimePlanEntityList,QuartzTimePlan.class);

        joinTemplate.joinQuery(quartzTimePlanList);

        return quartzTimePlanList;
    }

    @Override
    public Pagination<QuartzTimePlan> findQuartzTimePlanPage(QuartzTimePlanQuery quartzTimePlanQuery) {
        Pagination<QuartzTimePlanEntity>  pagination = quartzTimePlanDao.findQuartzTimePlanPage(quartzTimePlanQuery);

        List<QuartzTimePlan> quartzTimePlanList = BeanMapper.mapList(pagination.getDataList(),QuartzTimePlan.class);

        joinTemplate.joinQuery(quartzTimePlanList);

        return PaginationBuilder.build(pagination,quartzTimePlanList);
    }


}