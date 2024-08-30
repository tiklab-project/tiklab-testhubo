package io.thoughtware.testrubo.testplan.quartz.config;

import io.thoughtware.testrubo.common.TestRuboUnit;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JobDetailConfig {

    public JobDetail jobDetail(String group,String testPlanId,String quartzPlanId,String cron,Integer exeType) {
        Map<String, String> map = TestRuboUnit.cronWeek(cron);
        String weekTime = map.get("weekTime");
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("group",group);
        jobDataMap.put("testPlanId",testPlanId);
        jobDataMap.put("quartzPlanId",quartzPlanId);
        jobDataMap.put("cron",cron);
        jobDataMap.put("weekTime",weekTime);
        jobDataMap.put("exeType",exeType);

        String jobName = group + "_" + quartzPlanId+ "_" +testPlanId;

        //指定任务描述具体的实现类
        return JobBuilder.newJob(ExecuteJob.class)
                .setJobData(jobDataMap)
                // 指定任务的名称
                .withIdentity(jobName,group)
                .build();
    }


}
