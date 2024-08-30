package io.thoughtware.testrubo.testplan.quartz.config;

import org.quartz.*;
import org.springframework.stereotype.Component;

@Component
public class TriggerConfig {


    public Trigger trigger(JobDetail jobDetail,String testPlanId,String cron,String triggerName) {

        TriggerBuilder<CronTrigger> triggerBuilder = TriggerBuilder.newTrigger()
                .withIdentity(triggerName, testPlanId)
                .withSchedule(CronScheduleBuilder.cronSchedule(cron));


        triggerBuilder.forJob(jobDetail);


        return triggerBuilder.build();
    }
}
