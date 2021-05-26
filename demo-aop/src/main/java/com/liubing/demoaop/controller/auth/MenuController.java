package com.liubing.demoaop.controller.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/auth/menu")
@RestController
public class MenuController {


    @RequestMapping(value = {"/index", "/"})
    public String index() {
        System.out.println("this is http://localhost:8000/auth/menu/    method execute function....");
        if (1 == 1) {
            int a = 1 / 0;
        }
        return "auth.menu.index";
    }
}
