package com.liubing.demoaop.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {


    @RequestMapping(value = {"/index", "/"})
    public String index() {
        System.out.println("request body  /index");
        return "index.index";
    }

    @RequestMapping(value = "/version")
    public String version() {
        System.out.println("request body  /version");
        return "index.version";
    }
}
