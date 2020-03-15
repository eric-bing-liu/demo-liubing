package com.sinosoft.codegenerator.mapper;

import com.sinosoft.codegenerator.model.SUser;
import java.util.List;

public interface SUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(SUser record);

    SUser selectByPrimaryKey(String id);

    List<SUser> selectAll();

    int updateByPrimaryKey(SUser record);
}