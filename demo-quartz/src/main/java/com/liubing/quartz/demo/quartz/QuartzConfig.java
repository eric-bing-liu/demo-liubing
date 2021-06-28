package com.liubing.quartz.demo.quartz;


import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 定时任务 - quartz
 */
@Configuration
public class QuartzConfig {

//    @Bean
    public JobDetail testTask1() {
        return JobBuilder.newJob(Task1.class).withIdentity("testTask1").storeDurably().build();
    }

//    @Bean
    public Trigger testQuartzTrigger1() {
        //5秒执行一次
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5)
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(testTask1())  // JobDetail
                .withIdentity("testTask1")  //
                .withSchedule(scheduleBuilder)
                .build();
    }





//    @Bean
    public JobDetail testTask2() {
        return JobBuilder.newJob(Task2.class).withIdentity("testTask2").storeDurably().build();
    }

//    @Bean
    public Trigger testQuartzTrigger2() {
        //cron方式，每隔4秒执行一次
        return TriggerBuilder.newTrigger().forJob(testTask2())
                .withIdentity("testTask2")
                .withSchedule(CronScheduleBuilder.cronSchedule("*/2 * * * * ?"))
                .build();
    }

}
