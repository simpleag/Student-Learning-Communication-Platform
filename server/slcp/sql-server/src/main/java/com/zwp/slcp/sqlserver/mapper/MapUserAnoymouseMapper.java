package com.zwp.slcp.sqlserver.mapper;

import com.zwp.slcp.apicommon.entity.MapUserAnoymouse;
import com.zwp.slcp.apicommon.entity.MapUserAnoymouseKey;

public interface MapUserAnoymouseMapper {
    int deleteByPrimaryKey(MapUserAnoymouseKey key);

    int insert(MapUserAnoymouse record);

    int insertSelective(MapUserAnoymouse record);

    MapUserAnoymouse selectByPrimaryKey(MapUserAnoymouseKey key);

    int updateByPrimaryKeySelective(MapUserAnoymouse record);

    int updateByPrimaryKey(MapUserAnoymouse record);
}