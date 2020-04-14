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

    @RequestMapping(path = {"/","/index"})
    public String index(){


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
     */
    @Autowired
    RedisLock redisLock;
    @RequestMapping(path = {"/index1"})
    public String index1() throws InterruptedException {

        log.debug("index1 -- begin");
        log.debug("index1 -- " + redisLock.lock("aaaa"));

        Thread.sleep(5 * 1000);
        redisLock.unlock("aaaa");
        log.debug("index1 -- unlock");

        return "index1.response";
    }
    @RequestMapping(path = {"/index2"})
    public String index2() throws InterruptedException {
        System.out.println("index2 begin");
        System.out.println("index2" + redisLock.lock("aaaa"));

        Thread.sleep(5 * 1000);
        redisLock.unlock("aaaa");
        System.out.println("index2 unlock");

        return "index2.response";
    }
}
