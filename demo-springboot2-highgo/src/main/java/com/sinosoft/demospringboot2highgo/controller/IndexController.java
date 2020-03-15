package com.sinosoft.demospringboot2highgo.controller;

import com.sinosoft.demospringboot2highgo.mapper.SUserMapper;
import com.sinosoft.demospringboot2highgo.model.SUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class IndexController {


    @RequestMapping(value = {"/index","/"})
    public String index(){
        return "aaaaaaaaaaaaaaaa";
    }



}
