package com.liubing.swaggerui.demoswaggerui.controller;


import com.liubing.swaggerui.demoswaggerui.utils.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;


/**
 * 访问swaggerUI时，地址发生变化： http://localhost:8300/swagger-ui/index.html
 * maven依赖改为spring-starter
 */
@Api(tags="主页管理")
@RestController
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);


    @ApiOperation(value = "获取主页基本信息", notes = "note信息")
    @RequestMapping(value = {"/index", "/"}, method = RequestMethod.GET)
    public String index() {
        //
        return "index.....";
    }

    @ApiOperation(value = "index2.value", notes = "index2.note")
    @RequestMapping(value = {"/index2"}, method = RequestMethod.POST)
    public String index2() {
        //
        return "index2.....";
    }

    @RequestMapping(value = {"/index3"}, method = RequestMethod.GET)
    public String index3() {
        String fileInfo = FileUtil.readFileContent("C:\\Users\\Administrator\\Pictures\\readFile-.txt");

//        for (int i = 0; i < 10; i++) {
//            String fileInfo = FileUtil.readFileContent("C:\\Users\\Administrator\\Pictures\\readFile-" + i + ".txt");
//            System.out.println("fileInfo-"+i+":" + fileInfo);
//        }


        return "index3.....";
    }



}
