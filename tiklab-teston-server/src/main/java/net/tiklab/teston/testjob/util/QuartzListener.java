package net.tiklab.teston.testjob.util;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

/**
 * @author Scorpio(limingliang)
 * @version 1.0
 * @className QuartzListener
 * @description TODO
 * @date 2021/8/18 10:24
 **/
@Component
public class QuartzListener implements ApplicationListener<ApplicationEvent> {

    private static boolean loaded = false;

    @Autowired
    private QuartzManager quartzManager;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            //避免多次执行
            if (!loaded) {
                loaded = true;
                //定时任务启动
                try {
                    quartzManager.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (event instanceof ContextStartedEvent) {

            }

        }
    }

    /**
     * 初始注入scheduler
     *
     * @return
     * @throws SchedulerException
     */
    @Bean
    public Scheduler scheduler() throws SchedulerException {
        SchedulerFactory schedulerFactoryBean = new StdSchedulerFactory();
        return schedulerFactoryBean.getScheduler();
    }

}
