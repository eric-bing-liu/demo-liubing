package com.liubing.actuator.controller;


import com.liubing.actuator.config.TestThreadPoolExecutor;
import com.liubing.actuator.config.ThreadPoolExecutorConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@RequestMapping(value = {"/task"})
@RestController
public class TaskController {

    @Autowired
    TestThreadPoolExecutor testThreadPoolExecutor;


    /**
     * 通过线程池创建线程任务
     * 创建线程任务
     * http://localhost:8291/task/t1
     * 查看线程状态
     * http://localhost:8291/actuator/customEndPoint
     * @return
     */
    @RequestMapping(value = {"/t1"})
    public Long t1() {

        new Thread(() ->{
            for (int i = 0; i < 20; i++) {
                testThreadPoolExecutor.runTask(i, 10);
            }
        }).start();




        return new Date().getTime();
    }
}
