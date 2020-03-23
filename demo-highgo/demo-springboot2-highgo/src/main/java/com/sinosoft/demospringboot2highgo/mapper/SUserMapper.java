package com.sinosoft.demospringboot2highgo.mapper;


import com.sinosoft.demospringboot2highgo.model.SUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface SUserMapper {

    int deleteByPrimaryKey(String id);

    int insert(SUser record);

    SUser selectByPrimaryKey(String id);

    List<SUser> selectAll();

    int updateByPrimaryKey(SUser record);

    List<SUser> getListPage(Map map);

    int updateById(Map map);


}