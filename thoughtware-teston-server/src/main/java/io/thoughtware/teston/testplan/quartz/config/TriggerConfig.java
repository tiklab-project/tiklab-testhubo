package io.thoughtware.teston.testplan.quartz.config;

import org.quartz.*;
import org.springframework.stereotype.Component;

@Component
public class TriggerConfig {


    public Trigger trigger(JobDetail jobDetail,String testPlanId,String cron,Boolean state) {
        //触发器名
        String triggerName = testPlanId + "_" +cron;

        TriggerBuilder<CronTrigger> triggerBuilder = TriggerBuilder.newTrigger()
                .withIdentity(triggerName, testPlanId)
                .withSchedule(CronScheduleBuilder.cronSchedule(cron));

        // Job存在则指定job
        if (!state){
            triggerBuilder.forJob(jobDetail);
        }

        return triggerBuilder.build();
    }
}
