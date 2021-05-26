package com.sinosoft.demoredis.controller;

import com.sinosoft.demoredis.config.RedisLock;
import com.sinosoft.demoredis.config.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class IndexController {

    @Autowired
    RedisService redisService;

    @RequestMapping(path = {"/", "/index"})
    public String index() {


        log.info("get redis error, key : {} || {}", "aaaaaaaaa", "bbbb");

        redisService.set("b1", "b1............");
        redisService.set("b2", "b2............");
        redisService.set("b3", "b3............");
        System.out.println(redisService.get("b1"));
        System.out.println(redisService.get("b2"));
        System.out.println(redisService.get("b3"));

        return "index.response";
    }


    /**
     * 测试redis锁
     * http://localhost:9000/index1
     * http://localhost:9000/index2
     * <p>
     * index1 -- begin
     * index2 -- begin
     * index1 -- true
     * index1 -- begin sleep
     * index1 -- end sleep
     * index1 -- unlock
     * index2 -- true
     * index2 -- begin sleep
     * index2 -- end sleep
     * index2 -- unlock
     */
    @Autowired
    RedisLock redisLock;

    @RequestMapping(path = {"/index1"})
    public String index1() throws InterruptedException {

        log.info("index1 -- begin");
        log.info("index1 -- " + redisLock.lock("aaaa"));

        log.info("index1 -- begin sleep");
        Thread.sleep(5 * 1000);
        log.info("index1 -- end sleep");
        redisLock.unlock("aaaa");
        log.info("index1 -- unlock");

        return "index1.response";
    }

    @RequestMapping(path = {"/index2"})
    public String index2() throws InterruptedException {
        log.info("index2 -- begin");
        log.info("index2 -- " + redisLock.lock("aaaa"));

        log.info("index2 -- begin sleep");
        Thread.sleep(5 * 1000);
        log.info("index2 -- end sleep");
        redisLock.unlock("aaaa");
        log.info("index2 -- unlock");

        return "index2.response";
    }
}
