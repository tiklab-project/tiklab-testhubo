package net.tiklab.teston.testjob.util;

import net.tiklab.teston.testjob.model.QuartzTask;
import net.tiklab.teston.testjob.service.QuartzTaskService;
import org.apache.commons.collections.CollectionUtils;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Scorpio(limingliang)
 * @version 1.0
 * @className QuartzManager
 * @description TODO
 * @date 2021/8/18 10:24
 **/
@Component
@Scope("singleton")
public class QuartzManager implements ApplicationContextAware {

    private static final String TRIGGER_DEFAULT_GROUP_NAME = "TRIGGER_DEFAULT_GROUP_NAME";

    private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();

    private Logger logger = LoggerFactory.getLogger(QuartzManager.class);

    private ApplicationContext applicationContext;

    private Scheduler scheduler;

    @Autowired
    private AutowiringSpringBeanJobFactory autowiringSpringBeanJobFactory;

    @Resource
    private QuartzTaskService quartzTaskService;

    public void start() {
        //启动所有任务
        try {
            this.scheduler = schedulerFactory.getScheduler();
            scheduler.setJobFactory(autowiringSpringBeanJobFactory);
            List<QuartzTask> quartzTaskList = quartzTaskService.getCron();
            if(CollectionUtils.isNotEmpty(quartzTaskList)){
                for (QuartzTask task : quartzTaskList) {
                    if (ObjectUtils.isEmpty(task.getQuartzMaster())){
                        return;
                    }
                    addJob(task.getId(), task.getQuartzMaster().getTaskClassUrl(), task.getCronExpression(), null);
                }
            }

            logger.info("start jobs finished.");
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("init Scheduler failed");
        }
    }

    public boolean addJob(String jobName, String jobClass, String cronExp, String groupName) {
        boolean result = false;
        if (!CronExpression.isValidExpression(cronExp)) {
            logger.error("Illegal cron expression format({})", cronExp);
            return result;
        }
        try {
            JobDetail jobDetail = JobBuilder.newJob().withIdentity(new JobKey(jobName, groupName))
                    .ofType((Class<Job>) Class.forName(jobClass))
                    .build();
            Trigger trigger = TriggerBuilder.newTrigger()
                    .forJob(jobDetail)
                    .withSchedule(CronScheduleBuilder.cronSchedule(cronExp))
                    .withIdentity(new TriggerKey(jobName, groupName))
                    .build();
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            logger.error("QuartzManager add job failed");
        }
        return result;
    }

    public boolean updateJob(String jobName, String cronExp, String groupName) {
        boolean result = false;
        if (!CronExpression.isValidExpression(cronExp)) {
            logger.error("Illegal cron expression format({})", cronExp);
            return result;
        }
        JobKey jobKey = new JobKey(jobName, groupName);
        TriggerKey triggerKey = new TriggerKey(jobName, TRIGGER_DEFAULT_GROUP_NAME);
        try {
            if (scheduler.checkExists(jobKey) && scheduler.checkExists(triggerKey)) {
                JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                Trigger newTrigger = TriggerBuilder.newTrigger()
                        .forJob(jobDetail)
                        .withSchedule(CronScheduleBuilder.cronSchedule(cronExp))
                        .withIdentity(new TriggerKey(jobName, TRIGGER_DEFAULT_GROUP_NAME))
                        .build();
                scheduler.rescheduleJob(triggerKey, newTrigger);
                result = true;
            } else {
                logger.error("update job name:{},group name:{} or trigger name:{},group name:{} not exists..",
                        jobKey.getName(), jobKey.getGroup(), triggerKey.getName(), triggerKey.getGroup());
            }
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
            logger.error("update job name:{},group name:{} failed!", jobKey.getName(), jobKey.getGroup());
        }
        return result;
    }

    public boolean deleteJob(String jobName, String groupName) {
        boolean result = false;
        JobKey jobKey = new JobKey(jobName, groupName);
        try {
            if (scheduler.checkExists(jobKey)) {
                result = scheduler.deleteJob(jobKey);
            } else {
                logger.error("delete job name:{},group name:{} not exists.", jobKey.getName(), jobKey.getGroup());
            }
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
            logger.error("delete job name:{},group name:{} failed!", jobKey.getName(), jobKey.getGroup());
        }
        return result;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
