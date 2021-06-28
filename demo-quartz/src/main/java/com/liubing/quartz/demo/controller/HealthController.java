package com.liubing.quartz.demo.controller;

import com.liubing.quartz.demo.springtask.ShowThreadPoolTaskExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class HealthController implements HealthIndicator {
    private static final Logger logger = LoggerFactory.getLogger(HealthController.class);

    @Autowired
    ShowThreadPoolTaskExecutor showThreadPoolTaskExecutor;

    @Override
    public Health health() {

        //核心线程数
        int corePoolSize = showThreadPoolTaskExecutor.getThreadPoolExecutor().getCorePoolSize();
        //活跃线程数
        int activeCount = showThreadPoolTaskExecutor.getThreadPoolExecutor().getActiveCount();
        //完成的任务数
        long completedTaskCount = showThreadPoolTaskExecutor.getThreadPoolExecutor().getCompletedTaskCount();
        //线程数历史高峰线
        int largestPoolSize = showThreadPoolTaskExecutor.getThreadPoolExecutor().getLargestPoolSize();
        //当前池中线程数
        int poolSize = showThreadPoolTaskExecutor.getThreadPoolExecutor().getPoolSize();
        BlockingQueue<Runnable> queue = showThreadPoolTaskExecutor.getThreadPoolExecutor().getQueue();
        //队列剩余空间数
        int remainingCapacity = queue.remainingCapacity();
        //队列中的任务
        int queueSize = queue.size();
        //最大线程数
        int maximumPoolSize = showThreadPoolTaskExecutor.getThreadPoolExecutor().getMaximumPoolSize();

        //如果当前活跃线程数 大于  80%的最大线程数，就认证是down
        double rate = BigDecimal.valueOf(activeCount)
                .divide(BigDecimal.valueOf(maximumPoolSize), 2, BigDecimal.ROUND_HALF_UP)
                .doubleValue();

        Map<String, Object> infoMap = new HashMap<String, Object>();
        infoMap.put("核心线程数",corePoolSize);
        infoMap.put("当前活跃线程数", activeCount);
        infoMap.put("线程峰值", largestPoolSize);
        infoMap.put("完成的任务数", completedTaskCount);
        infoMap.put("当前池中线程数", poolSize);
        infoMap.put("队列剩余大小", remainingCapacity);
        infoMap.put("当前队列中的任务数", queueSize);
        infoMap.put("设置最大线程数", maximumPoolSize);
        if(rate > 0.8) {
            return Health.down().withDetails(infoMap).build();
        }
        else {
            return Health.up().withDetails(infoMap).build();
        }
//        return null;
    }
}
