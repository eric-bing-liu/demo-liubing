package com.liubing.actuator.controller;

import com.liubing.actuator.mapper.SUserMapper;
import com.liubing.actuator.model.SUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RequestMapping(value = "/user")
@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    SUserMapper sUserMapper;

    @RequestMapping(value = {"/detail/{id}"})
    public Object detail(@PathVariable(name = "id")String id) {
        logger.info("request to path : /user/detail");

        SUser sUser = sUserMapper.selectByPrimaryKey(id);

        return sUser.toString();
    }

    @RequestMapping(value = {"/detailById"})
    public String detailById(@RequestParam(name = "id")String id) {
        logger.info("request to path : /user/detail");

        SUser sUser = sUserMapper.selectByPrimaryKey(id);

        return sUser.toString() + "";
    }
}
