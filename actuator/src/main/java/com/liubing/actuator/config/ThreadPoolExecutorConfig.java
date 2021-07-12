package com.liubing.actuator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池初始化
 */
@EnableAsync
@Configuration
public class ThreadPoolExecutorConfig {

    @Bean("threadPoolExecutorA")
    public ThreadPoolExecutor threadPoolExecutorA() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,//有效线程数 至少这个数
                10,//最多线程数
                10,//等待 N个单位时间后，最多线程数币有效线程数多出来的那部分线程信息将释放掉
                TimeUnit.SECONDS,//单位时间的单位
                new ArrayBlockingQueue<Runnable>(20) //待处理队列大小
        );
        return executor;
    }

    @Bean("threadPoolExecutorB")
    public ThreadPoolExecutor threadPoolExecutorB() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10,
                20,
                1,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<Runnable>(200)
        );
        return executor;
    }

}
