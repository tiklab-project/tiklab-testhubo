package io.thoughtware.teston.testplan.quartz.config;

import io.thoughtware.teston.common.TestOnUnit;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SchedulerConfig {
    
    private static final Logger logger = LoggerFactory.getLogger(SchedulerConfig.class);

    @Autowired
    JobDetailConfig jobDetailConfig;

    @Autowired
    TriggerConfig triggerConfig;

    // 从 Factory 中获取 Scheduler 实例
    private static final SchedulerFactory schedulerFactory =  new StdSchedulerFactory();

    public void scheduler(String group,String testPlanId,String quartzPlanId,String cron,Integer exeType) {
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();

            Boolean isNewTrigger = false;

            JobKey jobKey = JobKey.jobKey(group);
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            if (Objects.isNull(jobDetail)){
                jobDetail = jobDetailConfig.jobDetail(group, testPlanId, quartzPlanId,cron,exeType);
                isNewTrigger=true;
            }


            scheduler.scheduleJob(
                    jobDetail,
                    triggerConfig.trigger(
                            jobDetail,
                            testPlanId,
                            cron,
                            isNewTrigger
                    )
            );

            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }

        }catch (SchedulerException e){
            throw new RuntimeException("Error while initializing scheduler", e);
        }
    }

    public void removeJob(String group,String testPlanId ,String cron){
        String triggerName =testPlanId + "_"+cron;
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            JobKey jobKey = JobKey.jobKey(group);

            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName);
            // 停止触发器
            scheduler.pauseTrigger(triggerKey);
            // 移除触发器
            scheduler.unscheduleJob(triggerKey);

            logger.warn("移除触发器 --- 时间：" + TestOnUnit.weekTime(cron) +"  cron:"+ cron  );

            int size = scheduler.getTriggersOfJob(jobKey).size();

            if (size <= 1){
                // 删除任务
                scheduler.deleteJob(jobKey);
                logger.warn("Job触发完成：" + jobKey + "，移除Job" );
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    

}
