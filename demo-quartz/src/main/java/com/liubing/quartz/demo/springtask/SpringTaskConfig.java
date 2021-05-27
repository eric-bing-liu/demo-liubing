package com.liubing.quartz.demo.springtask;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static java.lang.Thread.sleep;

/**
 * spring task 任务调度
 * 默认情况下，任务执行是串行的。  即task1执行完，才能执行后面的task1。
 */
@Component
public class SpringTaskConfig {

    /**
     * 固定间隔2秒，可以引用变量
     * fixedRate：以每次开始时间作为测量，间隔固定时间
     */
//    @Scheduled(fixedRate = 2 * 1000)
    public void task1() throws InterruptedException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String beign = sdf.format(new Date());
        System.out.println("task1 begin----" + beign);

        sleep(10 * 1000);

        System.out.println("task1 " + beign + " end----" + sdf.format(new Date()));
    }

    /**
     * 固定延迟2秒，从前一次任务结束开始计算，延迟3秒执行
     */
//    @Scheduled(fixedDelay = 2000)
    public void task2() throws InterruptedException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String beign = sdf.format(new Date());
        System.out.println("task2 begin----" + beign);

        sleep(10 * 1000);

        System.out.println("task2 " + beign + " end----" + sdf.format(new Date()));
    }

    /**
     * cron表达式，从任何一个值开始每隔2秒执行一次
     */
//    @Scheduled(cron = "*/2 * * * * ?")
    public void task3() throws InterruptedException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String beign = sdf.format(new Date());
        System.out.println("task2 begin----" + beign);

        sleep(5 * 1000);

        System.out.println("task3 " + beign + " end----" + sdf.format(new Date()));
    }


    /**
     * 异步服务
     */
    @Autowired
    SpringTask4Service springTask4Service;
    /**
     * cron表达式，从任何一个值开始每隔2秒执行一次
     */
    @Scheduled(cron = "*/20 * * * * ?")
    public void task4() throws InterruptedException, ExecutionException {
        //无返回值
//        springTask4Service.task4();

        //有返回值
        Future<String> future1 = springTask4Service.task4_withReturnValue1();
        Future<String> future2 = springTask4Service.task4_withReturnValue2();
        //如果两个都未完成
        while (!(future1.isDone() && future2.isDone())){
            System.out.println("没有都完成，等待1s  future1：" + future1.isDone() + "   future2：" + future2.isDone() + "   ");
            sleep(1000);
        }
        System.out.println(future1.get());
        System.out.println(future2.get());

        System.out.println("所有任务都结束。。。");
    }
}
