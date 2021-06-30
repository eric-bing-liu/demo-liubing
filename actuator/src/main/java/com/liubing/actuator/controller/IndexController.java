package com.liubing.actuator.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = {"/index", "/"})
    public Long index() {
        logger.info("request to path : /index");
        return new Date().getTime();
    }


    @RequestMapping(value = {"/redis"})
    public Long redis() {
        logger.info("request to path : /redis");

        return new Date().getTime();
    }



}
