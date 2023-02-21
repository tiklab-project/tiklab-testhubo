package net.tiklab.teston.testjob.task;

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
 * web用例执行任务
 */
public class TestWebCaseExecTask extends AbstractTask {

    Logger logger = LoggerFactory.getLogger(TestWebCaseExecTask.class);
    @Autowired
    QuartzMasterService quartzMasterService;

    @Autowired
    QuartzTaskDao quartzTaskDao;

    @Autowired
    QuartzTestcaseDao quartzTestcaseDao;


//    @Autowired
//    @Reference(address = "${agent.address}")
//    WebSceneTestService testWebCaseExecService;

    @Autowired
    private QuartzManager quartzManager;

    @Override
    protected void doExecute(JobExecutionContext context) {
        String jobName = context.getJobDetail().getKey().getName();
        logger.info("定时任务测试结果", jobName);
        if (!ObjectUtils.isEmpty(jobName)) {
            String[] split = jobName.split("_");
            String quartzMasterId = String.valueOf(split[split.length - 1]);
            String userId = String.valueOf(split[split.length - 2]);  //用户的id
            //查询定时任务主表
            QuartzMaster quartzMaster = quartzMasterService.findQuartzMaster(quartzMasterId);
            //当定时任务为1（正在进行中时才执行）
            if (quartzMaster.getState() == 1) {
                String repositoryId = quartzMaster.getRepository().getId();

                //查询定时任务时间
                List<QuartzTaskEntity> quartzTaskList = quartzTaskDao.findQuartzTaskList(new QuartzTaskQuery().setQuartzMasterId(quartzMasterId));
                for (QuartzTaskEntity quartzTask : quartzTaskList) {
                    long exTime = quartzTask.getEndTime().getTime();
                    long currentTime = System.currentTimeMillis();
                    long l = currentTime - 1000 * 6;
                    //执行时间小于等于当前时间 大于当前时间的前一分钟
                    if (currentTime >= exTime && exTime > l) {
                        //查询定时任务用例
                        List<QuartzTestcaseEntity> quartzTestcaseList = quartzTestcaseDao.findQuartzTestcaseList(new QuartzTestcaseQuery().setQuartzMasterId(quartzMasterId));
                        if (CollectionUtils.isNotEmpty(quartzTestcaseList)) {
                            for (QuartzTestcaseEntity quartzTestcase : quartzTestcaseList) {

                                String testCaseId = quartzTestcase.getTestcaseId();
//                                List<WebUnitCaseEntity> webStepList = webStepDao.findWebUnitCaseList(new WebUnitCaseQuery().setTestCaseId(testCaseId));
//                                if (CollectionUtils.isNotEmpty(webStepList)) {
//                                    List<WebUnitCase> webSteps = BeanMapper.mapList(webStepList, WebUnitCase.class);
//                                    WebSceneTestRequest testWebCaseExec = new WebSceneTestRequest();
//                                    testWebCaseExecService.execute(testWebCaseExec);
//                                    //定时任务执行成功 修改状态
//                                    quartzMaster.setState(2);
//                                    quartzMasterService.update(quartzMaster);
//                                }
                            }
                        }
                    }
                    //当前时间大于定时任务结束时间  清除缓存
                    if (System.currentTimeMillis() > quartzTask.getEndTime().getTime()) {
                        quartzManager.deleteJob(jobName, null);
                    }
                }
            }


        }

    }
}
