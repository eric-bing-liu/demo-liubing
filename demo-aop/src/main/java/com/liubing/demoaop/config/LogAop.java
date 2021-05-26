package com.liubing.demoaop.config;


import java.lang.annotation.*;

/**
 * 自定义操作日志注解
 */
@Target({ElementType.METHOD}) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented
public @interface LogAop {

    String param1() default "default.param1.info";

    String param2() default "default.param2.info";
}
