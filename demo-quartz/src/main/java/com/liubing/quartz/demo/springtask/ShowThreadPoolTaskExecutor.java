package com.liubing.quartz.demo.springtask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ShowThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {
    private static final Logger logger = LoggerFactory.getLogger(ShowThreadPoolTaskExecutor.class);

    private void showThreadPoolInfo(String prefix){
        ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutor();
        if(null==threadPoolExecutor){
            return;
        }
        logger.info("{}, {},taskCount [{}], completedTaskCount [{}], activeCount [{}], queueSize [{}]",
                this.getThreadNamePrefix(),
                prefix,
                threadPoolExecutor.getTaskCount(),
                threadPoolExecutor.getCompletedTaskCount(),
                threadPoolExecutor.getActiveCount(),
                threadPoolExecutor.getQueue().size()
        );

    }

    @Override
    public void execute(Runnable task) {
        showThreadPoolInfo("1. do execute Runnable");
        super.execute(task);
    }

    @Override
    public void execute(Runnable task, long startTimeout) {
        showThreadPoolInfo("2. do execute Runnable");
        super.execute(task, startTimeout);
    }

    @Override
    public Future<?> submit(Runnable task) {
        showThreadPoolInfo("1. do submit Runnable");
        return super.submit(task);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        showThreadPoolInfo("2. do submit Callable");
        return super.submit(task);
    }

    @Override
    public ListenableFuture<?> submitListenable(Runnable task) {
        showThreadPoolInfo("1. do submitListenable Runnable");
        return super.submitListenable(task);
    }

    @Override
    public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
        showThreadPoolInfo("2. do submitListenable Callable");
        return super.submitListenable(task);
    }
}
