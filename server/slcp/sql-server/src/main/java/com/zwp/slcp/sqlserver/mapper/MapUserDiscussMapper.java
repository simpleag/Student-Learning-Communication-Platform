package com.zwp.slcp.sqlserver.mapper;

import com.zwp.slcp.apicommon.entity.MapUserDiscuss;
import com.zwp.slcp.apicommon.entity.MapUserDiscussKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MapUserDiscussMapper {
    int deleteByPrimaryKey(MapUserDiscussKey key);

    int insert(MapUserDiscuss record);

    int insertSelective(MapUserDiscuss record);

    MapUserDiscuss selectByPrimaryKey(MapUserDiscussKey key);

    int updateByPrimaryKeySelective(MapUserDiscuss record);

    int updateByPrimaryKey(MapUserDiscuss record);
}