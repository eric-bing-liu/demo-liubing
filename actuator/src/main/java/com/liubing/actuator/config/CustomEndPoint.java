package com.liubing.actuator.config;

import com.liubing.actuator.controller.IndexController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.health.Health;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自定义端点
 * 请求地址：http://localhost:8291/actuator/customEndPoint
 *
 * @Endpoint    构建 rest api 的唯一路径
 * @ReadOperation   GET请求，响应状态为 200 如果没有返回值响应 404（资源未找到）
 * @WriteOperation  POST请求，响应状态为 200 如果没有返回值响应 204（无响应内容）
 * @DeleteOperation DELETE请求，响应状态为 200 如果没有返回值响应 204（无响应内容）
 */
@Endpoint(id = "customEndPoint")
@Component
public class CustomEndPoint {

    private static final Logger logger = LoggerFactory.getLogger(CustomEndPoint.class);

    @Autowired
    ThreadPoolExecutor threadPoolExecutorA;
    @Autowired
    ThreadPoolExecutor threadPoolExecutorB;

    /**
     * 读取以后，接口返回的信息。
     * @return
     */
    @ReadOperation
    public Map<String, Object> getInfo() {
        Map<String, Object> dataMap = new LinkedHashMap<>();
        dataMap.put("自定义信息1", "custom endpoint1 ");
        dataMap.put("自定义信息2", "custom endpoint2 ");
        dataMap.put("自定义信息3", "custom endpoint3 ");


        dataMap.put("threadPoolExecutorA", getThreadPoolExecutorInfo(threadPoolExecutorA));
        dataMap.put("threadPoolExecutorB", getThreadPoolExecutorInfo(threadPoolExecutorB));

        return dataMap;
    }

    public Health getThreadPoolExecutorInfo(ThreadPoolExecutor threadPoolExecutor) {

        //核心线程数
        int corePoolSize = threadPoolExecutor.getCorePoolSize();
        //活跃线程数
        int activeCount = threadPoolExecutor.getActiveCount();
        //完成的任务数
        long completedTaskCount = threadPoolExecutor.getCompletedTaskCount();
        //线程数历史高峰线
        int largestPoolSize = threadPoolExecutor.getLargestPoolSize();
        //当前池中线程数
        int poolSize = threadPoolExecutor.getPoolSize();
        BlockingQueue<Runnable> queue = threadPoolExecutor.getQueue();
        //队列剩余空间数
        int remainingCapacity = queue.remainingCapacity();
        //队列中的任务
        int queueSize = queue.size();
        //最大线程数
        int maximumPoolSize = threadPoolExecutor.getMaximumPoolSize();

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

    }
}
