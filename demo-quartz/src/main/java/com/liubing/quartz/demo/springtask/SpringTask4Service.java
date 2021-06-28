package com.liubing.quartz.demo.springtask;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Future;

@Component
public class SpringTask4Service {

    /**
     * 无返回值
     * @throws InterruptedException
     */
    @Async
    public void task4() throws InterruptedException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String beign = sdf.format(new Date());
        System.out.println("task4 begin----" + beign);

        Thread.sleep(5 * 1000);

        System.out.println("task4 " + beign + " end----" + sdf.format(new Date()));
    }


    /**
     * 无返回值，指定线程池
     * @throws InterruptedException
     */
    @Async(value = "asyncTaskExecutor")
    public void task5() throws InterruptedException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String beign = sdf.format(new Date());
        System.out.println("task5 begin----" + beign);

        Thread.sleep(5 * 1000);

        System.out.println("task5 " + beign + " end----" + sdf.format(new Date()));
    }

    /**
     * 有返回值
     * @throws InterruptedException
     */
    @Async
    public Future<String> task4_withReturnValue1() throws InterruptedException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String beign = sdf.format(new Date());
        System.out.println("task4-1 begin----" + beign);

        Thread.sleep(5 * 1000);

        System.out.println("task4-1 " + beign + " end----" + sdf.format(new Date()));

        return new AsyncResult<>("task4-1_withReturnValue");
    }
    @Async
    public Future<String> task4_withReturnValue2() throws InterruptedException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String beign = sdf.format(new Date());
        System.out.println("task4-2 begin----" + beign);

        Thread.sleep(10 * 1000);

        System.out.println("task4-2 " + beign + " end----" + sdf.format(new Date()));

        return new AsyncResult<>("task4-2_withReturnValue");
    }
}
