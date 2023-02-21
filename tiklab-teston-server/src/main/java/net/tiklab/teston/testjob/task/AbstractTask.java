package net.tiklab.teston.testjob.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

/**
 * @author Scorpio(limingliang)
 * @version 1.0
 * @className AbstractTask
 * @description TODO
 * @date 2020/6/16 10:47
 **/
public abstract class AbstractTask implements Job {

    private Logger logger = LoggerFactory.getLogger(AbstractTask.class);

    protected abstract void doExecute(JobExecutionContext context);

    protected String cronExpression;

    @PostConstruct
    public void init() {
        this.cronExpression = "0 0 23 * * ?";
    }

    @Override
    public void execute(JobExecutionContext context) {
        try {
            doExecute(context);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            logger.error("job execute failed!");
        }
    }

    public String getCronExpression() {
        return cronExpression;
    }

}
