package com.liubing.actuator.mapper;

import com.liubing.actuator.model.SUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(SUser record);

    SUser selectByPrimaryKey(String id);

    List<SUser> selectAll();

    int updateByPrimaryKey(SUser record);
}