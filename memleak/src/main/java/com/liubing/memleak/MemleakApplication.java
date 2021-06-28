package com.liubing.memleak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * vmOption 添加参数
 * 内存1g  young-gen 256m
 * java -jar -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=128m -Xms512m -Xmx512m -Xmn256m -Xss256k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC memleak-0.0.1-SNAPSHOT.jar
 */
@SpringBootApplication
public class MemleakApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemleakApplication.class, args);
    }

}
