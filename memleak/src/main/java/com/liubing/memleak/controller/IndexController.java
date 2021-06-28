package com.liubing.memleak.controller;


import com.liubing.memleak.module.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class IndexController {

    private List<Person> list = new ArrayList<Person>();

    @RequestMapping(value = {"/index", "/"})
    public String index() {


        Person person = new Person();
        list.add(person);
        System.out.println("index.request.list.count:" + list.size());
        return "index....." + list.size();
    }
}
