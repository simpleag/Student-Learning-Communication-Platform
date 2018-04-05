package com.zwp.slcp.sqlserver.mapper;

import com.zwp.slcp.apicommon.entity.MapUserTag;
import com.zwp.slcp.apicommon.entity.MapUserTagKey;

public interface MapUserTagMapper {
    int deleteByPrimaryKey(MapUserTagKey key);

    int insert(MapUserTag record);

    int insertSelective(MapUserTag record);

    MapUserTag selectByPrimaryKey(MapUserTagKey key);

    int updateByPrimaryKeySelective(MapUserTag record);

    int updateByPrimaryKey(MapUserTag record);
}