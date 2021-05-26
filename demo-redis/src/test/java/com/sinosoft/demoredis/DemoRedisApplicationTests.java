package com.sinosoft.demoredis;

import com.sinosoft.demoredis.config.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class DemoRedisApplicationTests {

    @Autowired
    RedisService redisService;

    @Test
    void Test1() {
        redisService.set("b1", "b1............");
        System.out.println(redisService.get("b1"));
        redisService.set("b2", "b2............");
        System.out.println(redisService.get("b2"));
        redisService.set("b3", "b3............");
        System.out.println(redisService.get("b3"));

        for (int i = 0; i < 50; i++) {
            int finalI = i;
            new Thread(() -> {
                double r = Math.random();
                redisService.set("a" + finalI, String.valueOf(r));
                System.out.println("a" + finalI + " = " + String.valueOf(r).equals(redisService.get("a" + finalI)));
            }).start();
        }

        try {
            Thread.sleep(100 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void contextLoads() {
        redisService.set("b1", "b1............");
        System.out.println(redisService.get("b1"));
        redisService.set("b2", "b2............");
        System.out.println(redisService.get("b2"));
        redisService.set("b3", "b3............");
        System.out.println(redisService.get("b3"));

        System.out.println(Math.random());
        System.out.println(Math.random());
        System.out.println(Math.random());


        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    double r = Math.random();
                    redisService.set("a" + finalI, String.valueOf(r));
                    System.out.println("a" + finalI + j + " = " + String.valueOf(r).equals(redisService.get("a" + finalI)));
                }
            }).start();
            System.out.println("---------------" + finalI + "-----------------");
        }

        try {
            Thread.sleep(100 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
