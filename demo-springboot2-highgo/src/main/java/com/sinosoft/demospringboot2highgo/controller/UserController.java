package com.sinosoft.demospringboot2highgo.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinosoft.demospringboot2highgo.mapper.SUserMapper;
import com.sinosoft.demospringboot2highgo.model.SUser;
import com.sinosoft.demospringboot2highgo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {


    @Autowired
    UserService userService;

    @Autowired
    SUserMapper sUserMapper;



    @RequestMapping(value = {"/page"})
    public Object page(String userName,
                       @RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex,
                       @RequestParam(value = "pageSize", defaultValue = "5") int pageSize){
        Map map = new HashMap();
        map.put("userName", userName);

        Page page = PageHelper.startPage(pageIndex, pageSize, true);
        List<SUser> listPage = sUserMapper.getListPage(map);

        System.out.println("getTotal:"+page.getTotal());
        return listPage;
    }
    @RequestMapping(value = {"/detail"})
    public SUser detail(String id){
        return sUserMapper.selectByPrimaryKey(id);
    }
    @RequestMapping(value = {"/insert"})
    public Object insert(String id, String userName){
        SUser sUser = new SUser();
        sUser.setId(id);
        sUser.setUserName(userName);
        return sUserMapper.insert(sUser);
    }

    @RequestMapping(value = {"/update"})
    public Object update(String id, String userName){
        SUser sUser = new SUser();
        sUser.setId(id);
        sUser.setUserName(userName);
        return sUserMapper.updateByPrimaryKey(sUser);
    }

    @RequestMapping(value = {"/delete"})
    public Object delete(String id){
        return sUserMapper.deleteByPrimaryKey(id);
    }





    @RequestMapping(value = {"/testTransaction"})
    public Object testTransaction(){

        try {
            return userService.testTransaction();
        } catch (Exception e) {
//            e.printStackTrace();
            return e.toString();
        }
    }
    @RequestMapping(value = {"/testTransaction2"})
    public Object testTransaction2(){

        try {
            return userService.testTransaction2();
        } catch (Exception e) {
//            e.printStackTrace();
            return e.toString();
        }
    }
}
