package com.liubing.quartz.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);


    @Qualifier("showThreadPoolTaskExecutor")
    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @RequestMapping(value = {"/index", "/"})
    public Long index() {
        return new Date().getTime();
    }
}
