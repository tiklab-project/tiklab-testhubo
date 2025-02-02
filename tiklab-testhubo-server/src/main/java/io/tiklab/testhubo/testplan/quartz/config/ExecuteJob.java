package io.tiklab.testhubo.testplan.quartz.config;

import io.tiklab.testhubo.support.environment.model.ApiEnv;
import io.tiklab.testhubo.support.environment.service.ApiEnvService;
import io.tiklab.testhubo.testplan.cases.model.TestPlan;
import io.tiklab.testhubo.testplan.cases.service.TestPlanService;
import io.tiklab.testhubo.testplan.execute.model.TestPlanTestData;
import io.tiklab.testhubo.testplan.execute.model.TestPlanTestResponse;
import io.tiklab.testhubo.testplan.execute.service.TestPlanExecuteDispatchService;
import io.tiklab.testhubo.testplan.quartz.model.QuartzPlan;
import io.tiklab.testhubo.testplan.quartz.service.QuartzPlanService;
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

        if(testPlan.getApiEnvId()==null){
            logger.error("Api Env is null");
            return;
        }

        ApiEnv apiEnv = apiEnvService.findApiEnv(testPlan.getAppEnvId());
        if(apiEnv==null){return;}

        TestPlanTestData testPlanTestData = new TestPlanTestData();
        testPlanTestData.setTestPlanId(testPlanId);
        testPlanTestData.setRepositoryId(apiEnv.getRepositoryId());
        testPlanTestData.setApiEnv(apiEnv.getPreUrl());
        testPlanExecuteDispatchService.execute(testPlanTestData);

        //循环获取返回结果
        loopExeResult(testPlanId);
        //执行完成再获取一下返回结果，用于更新历史数据
        testPlanExecuteDispatchService.exeResult(testPlanId);

        logger.warn("组：{}，测试计划：{},当前定时任务: {}, 定时任务触发完成",group,testPlanId,quartzPlanId);

        schedulerConfig.removeJob(group,testPlanId,cron);


        updateQuartz(group,testPlanId,quartzPlanId,cron,exeType);
    }

    private void loopExeResult(String testPlanId){
        TestPlanTestResponse testPlanTestResponse = testPlanExecuteDispatchService.exeResult(testPlanId);
        if(testPlanTestResponse.getStatus()==1){
            loopExeResult(testPlanId);
        }
    }

    private void updateQuartz(String group,String testPlanId,String quartzPlanId,String cron,Integer exeType){
        QuartzPlan quartzPlan = new QuartzPlan();
        quartzPlan.setId(quartzPlanId);
        quartzPlan.setTestPlanId(testPlanId);
        quartzPlan.setState(1);
        quartzPlanService.updateQuartzPlanState(quartzPlan);

        logger.info("update ----  success");

        //1单次  2循环
        if(exeType==2){
            schedulerConfig.scheduler(group,quartzPlanId,testPlanId,cron,exeType);
        }

    }


}
