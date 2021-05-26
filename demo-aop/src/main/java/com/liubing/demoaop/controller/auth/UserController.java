package com.liubing.demoaop.controller.auth;

import com.liubing.demoaop.config.LogAop;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/auth/user")
@RestController
public class UserController {

    @RequestMapping(value = {"/index", "/"})
    public String index() {
        System.out.println("auth.user.index");
        return "auth.user.index";
    }


    @LogAop(param1 = "param1.value", param2 = "params2.value")
    @RequestMapping(value = {"/index2"})
    public String index2() {
        System.out.println("auth.user.index");
        return "auth.user.index2";
    }

    @LogAop()
    @RequestMapping(value = {"/index3"})
    public String index3() {
        System.out.println("auth.user.index");
        return "auth.user.index3";
    }


    @LogAop()
    @RequestMapping(value = {"/index4"})
    public String index4() {
        int i = 1 / 0;
        return "auth.user.index4";
    }

}
