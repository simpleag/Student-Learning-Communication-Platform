package com.zwp.slcp.sqlserver.mapper;

import com.zwp.slcp.apicommon.entity.MapUser;
import com.zwp.slcp.apicommon.entity.MapUserKey;

public interface MapUserMapper {
    int deleteByPrimaryKey(MapUserKey key);

    int insert(MapUser record);

    int insertSelective(MapUser record);

    MapUser selectByPrimaryKey(MapUserKey key);

    int updateByPrimaryKeySelective(MapUser record);

    int updateByPrimaryKey(MapUser record);
}