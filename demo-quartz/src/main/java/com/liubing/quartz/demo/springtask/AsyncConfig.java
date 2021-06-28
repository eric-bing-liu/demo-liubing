package com.liubing.quartz.demo.springtask;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Async注解  可以配置线程池相关信息，使用这个注解时，可以指定线程池。
 */
@Configuration
public class AsyncConfig {

    /**
     * Set the ThreadPoolExecutor's maximum pool size
     */
    private static final int MAX_POOL_SIZE = 4;

    /**
     * Set the ThreadPoolExecutor's core pool size.
     */
    private static final int CORE_POOL_SIZE = 2;

    /**
     *
     * @return
     */
    @Bean("asyncTaskExecutor")
    public AsyncTaskExecutor asyncTaskExecutor() {
        ThreadPoolTaskExecutor asyncTaskExecutor = new ShowThreadPoolTaskExecutor();
        asyncTaskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        asyncTaskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        asyncTaskExecutor.setQueueCapacity(6);
        asyncTaskExecutor.setKeepAliveSeconds(20);
        asyncTaskExecutor.setThreadNamePrefix("async-task-thread-pool-");
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        asyncTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        asyncTaskExecutor.initialize();
        return asyncTaskExecutor;
    }
}
