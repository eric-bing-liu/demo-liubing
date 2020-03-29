package com.liubing.demoaop.controller.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/auth/menu")
@RestController
public class MenuController {


    @RequestMapping(value = {"/index","/"})
    public String index(){
        return "auth.menu.index";
    }
}
