package com.liubing.actuator.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class TestThreadPoolExecutor {
    private static final Logger logger = LoggerFactory.getLogger(TestThreadPoolExecutor.class);

    /**
     * @PostContruct 在方法上加该注解会在项目启动的时候执行该方法，也可以理解为在spring容器初始化的时候执行该方法。
     * 从Java EE5规范开始，Servlet中增加了两个影响Servlet生命周期的注解，@PostConstruct和@PreDestroy，这两个注解被用来修饰一个非静态的void（）方法。
     * 如果想在生成对象时完成某些初始化操作，而偏偏这些初始化操作又依赖于依赖注入，那么久无法在构造函数中实现。
     * 为此，可以使用@PostConstruct注解一个方法来完成初始化，@PostConstruct注解的方法将会在依赖注入完成后被自动调用。
     * @Constructor >> @Autowired >> @PostConstruct
     */
    @PostConstruct
    public void init() {
//        MyHealthIndicator myHealthIndicator = new MyHealthIndicator(ThreadPoolExecutorConfig.executor);


//        autoAddTask();
    }


    @Autowired
    ThreadPoolExecutor threadPoolExecutorA;

    /**
     * 模拟添加任务
     */
    public void autoAddTask() {
        AtomicLong finishTaskNum = new AtomicLong();
        new Thread(() ->{

            while(true) {
                long andIncrement = finishTaskNum.getAndIncrement();
//                int methodRunTime = ThreadLocalRandom.current().nextInt(10, 20);
                int methodRunTime = 600;

                runTask(andIncrement, methodRunTime);

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e2) {
                }
            }
        }).start();

    }

    public void runTask(long andIncrement, int methodRunTime) {

        logger.info("开始任务:" + andIncrement + "  methodRunTime:" + methodRunTime);
        try {

            int activeCount = threadPoolExecutorA.getActiveCount();
            int poolSize = threadPoolExecutorA.getPoolSize();
            BlockingQueue<Runnable> queue = threadPoolExecutorA.getQueue();
            //队列剩余空间数
            int remainingCapacity = queue.remainingCapacity();
            //队列中的任务
            int queueSize = queue.size();
            logger.info("activeCount:" + activeCount + "    "
                    + "poolSize:" + poolSize + "    "
                    + "remainingCapacity:" + remainingCapacity + "    "
                    + "queueSize:" + queueSize + "    ");

            threadPoolExecutorA.execute(() ->{
                try {
                    TimeUnit.SECONDS.sleep(methodRunTime);
                    logger.info("完成任务.."+ andIncrement);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        catch (java.util.concurrent.RejectedExecutionException e){
            logger.info("通过线程池创建线程任务失败:" + e.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.info("其他异常:" + e.toString());
        }

    }
}
