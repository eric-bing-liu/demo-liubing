package com.liubing.actuator.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * 补充health参数的方法
 * my1为health的参数头
 * 效果访问地址：
 * http://localhost:8291/actuator/health
 */
@Component("my1")
public class MyHealthIndicator implements HealthIndicator {

    private static final String VERSION = "v1.0.0";

    @Override
    public Health health() {
        int code = check();
        if (code != 0) {
            Health.down().withDetail("code", code).withDetail("version", VERSION).build();
        }
        return Health.up().withDetail("code", code)
                .withDetail("version", VERSION).up().build();
    }

    private int check() {
        return 0;
    }
}
