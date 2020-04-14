package com.sinosoft.demoredis;


import com.sinosoft.demoredis.config.RedisLock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestA {


    @Autowired
    RedisLock redisLock;


    @Test
    void Test1() throws InterruptedException {

        System.out.println(redisLock.lock("aaaa"));
        System.out.println(redisLock.lock("aaaa"));

        redisLock.unlock("aaaa");
        System.out.println(redisLock.lock("aaaa"));
        redisLock.unlock("aaaa");

    }
}
