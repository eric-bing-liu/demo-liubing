package com.sinosoft.demospringboot2highgo.service;


import com.sinosoft.demospringboot2highgo.mapper.SUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    SUserMapper sUserMapper;

    @Transactional
    public String testTransaction() throws Exception {
        Map map2 = new HashMap();
        map2.put("id", "2");
        map2.put("userName", "2-userName");
        sUserMapper.updateById(map2);

        //sql内异常  会回滚
//        Map map1 = new HashMap();
//        map1.put("id", "1");
//        sUserMapper.updateById(map1);

        //runtime exception 也会回滚
        if (1 == 1) {
            throw new RuntimeException("ccccccccccccccc");
        }

        //不会回滚
        if (1 == 1) {
            throw new Exception("cccccccccccc");
        }
        return "end......";
    }


    @Transactional(rollbackFor = {Exception.class})
    public String testTransaction2() throws Exception {
        Map map2 = new HashMap();
        map2.put("id", "2");
        map2.put("userName", "2-userName");
        sUserMapper.updateById(map2);

        //sql内异常  会回滚
//        Map map1 = new HashMap();
//        map1.put("id", "1");
//        sUserMapper.updateById(map1);

        //runtime exception 也会回滚
//        if (1 == 1) {
//            throw new RuntimeException("RuntimeException.ccccccccccccccc");
//        }

        //不会回滚
        if (1 == 1) {
            throw new Exception("Exception.cccccccccccc");
        }
        return "end......";
    }
}
