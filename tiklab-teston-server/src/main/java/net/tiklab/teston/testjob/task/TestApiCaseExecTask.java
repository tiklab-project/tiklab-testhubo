package net.tiklab.teston.testjob.task;

import net.tiklab.beans.BeanMapper;
import net.tiklab.teston.apitest.http.scenetest.model.ApiSceneTestRequest;
import net.tiklab.teston.apitest.http.unittest.dao.ApiUnitCaseDao;
import net.tiklab.teston.apitest.http.unittest.entity.ApiUnitCaseEntity;
import net.tiklab.teston.apitest.http.unittest.model.ApiUnitCase;
import net.tiklab.teston.apitest.http.unittest.model.ApiUnitCaseQuery;
import net.tiklab.teston.testjob.dao.QuartzTaskDao;
import net.tiklab.teston.testjob.dao.QuartzTestcaseDao;
import net.tiklab.teston.testjob.entity.QuartzTaskEntity;
import net.tiklab.teston.testjob.entity.QuartzTestcaseEntity;
import net.tiklab.teston.testjob.model.QuartzMaster;
import net.tiklab.teston.testjob.model.QuartzTaskQuery;
import net.tiklab.teston.testjob.model.QuartzTestcaseQuery;
import net.tiklab.teston.testjob.service.QuartzMasterService;
import net.tiklab.teston.testjob.util.QuartzManager;
import org.apache.commons.collections.CollectionUtils;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * api用例执行任务
 */
public class TestApiCaseExecTask extends AbstractTask {

    Logger logger = LoggerFactory.getLogger(TestApiCaseExecTask.class);

//    @Autowired
//    ApiSceneTestService testApiCaseExecService;

    @Autowired
    QuartzMasterService quartzMasterService;

    @Autowired
    QuartzTaskDao quartzTaskDao;

    @Autowired
    QuartzTestcaseDao quartzTestcaseDao;

    @Autowired
    ApiUnitCaseDao apiUnitCaseDao;

    @Autowired
    private QuartzManager quartzManager;

    @Override
    protected void doExecute(JobExecutionContext context) {
        String jobName = context.getJobDetail().getKey().getName();
        logger.info("定时任务测试结果",jobName);
        if (!ObjectUtils.isEmpty(jobName)){
            String[] split = jobName.split("_");
            String quartzMasterId = String.valueOf(split[split.length-1]);
            String userId = String.valueOf(split[split.length-2]);  //用户的id
            //查询定时任务主表
            QuartzMaster quartzMaster = quartzMasterService.findQuartzMaster(quartzMasterId);
            String repositoryId = quartzMaster.getRepository().getId();
            //查询定时任务环境
//            ApiEnv apiEnv = quartzMaster.getApiEnv();
            String prepositionUrl=null;
//            if (!ObjectUtils.isEmpty(apiEnv)){
//                //前置url
//                prepositionUrl  = apiEnv.getPrepositionUrl();
//            }
            //查询定时任务时间
            List<QuartzTaskEntity> quartzTaskList = quartzTaskDao.findQuartzTaskList(new QuartzTaskQuery().setQuartzMasterId(quartzMasterId));
            for (QuartzTaskEntity quartzTask:quartzTaskList){
                long exTime = quartzTask.getEndTime().getTime();
                long currentTime = System.currentTimeMillis();
                long l = currentTime - 1000 * 6;
                //执行时间小于等于当前时间 大于当前时间的前一分钟
                if (currentTime>=exTime&&exTime>l) {
                    //查询定时任务用例
                    List<QuartzTestcaseEntity> quartzTestcaseList = quartzTestcaseDao.findQuartzTestcaseList(new QuartzTestcaseQuery().setQuartzMasterId(quartzMasterId));
                    if(CollectionUtils.isNotEmpty(quartzTestcaseList)){
                        for (QuartzTestcaseEntity quartzTestcase:quartzTestcaseList){

                            String testCaseId = quartzTestcase.getTestcaseId();
                            List<ApiUnitCaseEntity> stepList = apiUnitCaseDao.findApiUnitCaseList(new ApiUnitCaseQuery().setTestCaseId(testCaseId));
                            if (CollectionUtils.isNotEmpty(stepList)){
                                List<ApiUnitCase> steps = BeanMapper.mapList(stepList, ApiUnitCase.class);
                                //封装执行测试的对象
                                ApiSceneTestRequest testApiCaseExec = new ApiSceneTestRequest();
//                                testApiCaseExecService.execute(testApiCaseExec);
                            }
                        }
                    }
                }
                //当当前时间大于定时任务结束时间  清除缓存
                if (System.currentTimeMillis()>quartzTask.getEndTime().getTime()){
                    quartzManager.deleteJob(jobName, null);
                }
            }

            }

        }
}
