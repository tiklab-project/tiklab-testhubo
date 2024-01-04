package io.thoughtware.teston.testplan.quartz.config;

import io.thoughtware.teston.support.environment.model.ApiEnv;
import io.thoughtware.teston.support.environment.service.ApiEnvService;
import io.thoughtware.teston.testplan.cases.model.TestPlan;
import io.thoughtware.teston.testplan.cases.service.TestPlanService;
import io.thoughtware.teston.testplan.execute.model.TestPlanTestData;
import io.thoughtware.teston.testplan.execute.model.TestPlanTestResponse;
import io.thoughtware.teston.testplan.execute.service.TestPlanExecuteDispatchService;
import io.thoughtware.teston.testplan.quartz.model.QuartzPlan;
import io.thoughtware.teston.testplan.quartz.service.QuartzPlanService;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExecuteJob implements Job {
    private static final Logger logger = LoggerFactory.getLogger(ExecuteJob.class);

    public static QuartzPlanService quartzPlanService;
    public static SchedulerConfig schedulerConfig;
    public static TestPlanExecuteDispatchService testPlanExecuteDispatchService;
    public static TestPlanService testPlanService;
    public static ApiEnvService apiEnvService;

    @Autowired
    public  void setQuartzPlanService(QuartzPlanService quartzPlanService) {
        ExecuteJob.quartzPlanService = quartzPlanService;
    }

    @Autowired
    public void setSchedulerConfig(SchedulerConfig schedulerConfig) {
        ExecuteJob.schedulerConfig = schedulerConfig;
    }

    @Autowired
    public void setTestPlanExecuteDispatchService(TestPlanExecuteDispatchService testPlanExecuteDispatchService) {
        ExecuteJob.testPlanExecuteDispatchService = testPlanExecuteDispatchService;
    }

    @Autowired
    public void setTestPlanService(TestPlanService testPlanService) {
        ExecuteJob.testPlanService = testPlanService;
    }

    @Autowired
    public void setApiEnvService(ApiEnvService apiEnvService) {
        ExecuteJob.apiEnvService = apiEnvService;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap map = jobExecutionContext.getMergedJobDataMap();
        String testPlanId = (String)map.get("testPlanId");
        String quartzPlanId = (String)map.get("quartzPlanId");
        String group = (String)map.get("group");
        String cron = (String)map.get("cron");
        int exeType = (int)map.get("exeType");

        TestPlan testPlan = testPlanService.findTestPlan(testPlanId);
        if(testPlan==null){return;}

        if(testPlan.getApiEnv()==null){
            logger.error("Api Env is null");
            return;
        }

        ApiEnv apiEnv = apiEnvService.findApiEnv(testPlan.getApiEnv());
        if(apiEnv==null){return;}

        TestPlanTestData testPlanTestData = new TestPlanTestData();
        testPlanTestData.setTestPlanId(testPlanId);
        testPlanTestData.setRepositoryId(apiEnv.getRepositoryId());
        testPlanTestData.setApiEnv(apiEnv.getPreUrl());
        testPlanExecuteDispatchService.execute(testPlanTestData);
        loopExeResult();

        logger.warn("组：{}，测试计划：{},定时任务触发完成",group,testPlanId);

        schedulerConfig.removeJob(group,testPlanId,cron);

        updateQuartz(group,quartzPlanId,testPlanId,cron,exeType);
    }

    private void loopExeResult(){
        TestPlanTestResponse testPlanTestResponse = testPlanExecuteDispatchService.exeResult();
        if(testPlanTestResponse.getStatus()==1){
            loopExeResult();
        }
    }

    private void updateQuartz(String group,String testPlanId,String quartzPlanId,String cron,Integer exeType){
        QuartzPlan quartzPlan = new QuartzPlan();
        quartzPlan.setId(quartzPlanId);
        quartzPlan.setTestPlanId(testPlanId);
        quartzPlan.setState(1);
        quartzPlanService.updateQuartzPlanState(quartzPlan);

        //1单次  2循环
        if(exeType==2){
            schedulerConfig.scheduler(group,quartzPlanId,testPlanId,cron,exeType);
        }

    }


}
