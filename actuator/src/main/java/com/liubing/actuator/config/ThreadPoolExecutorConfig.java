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
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,
                10,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(20)
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
