package com.zwp.slcp.sqlserver.mapper;

import com.zwp.slcp.apicommon.entity.MapUserDiscusscomment;
import com.zwp.slcp.apicommon.entity.MapUserDiscusscommentKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MapUserDiscusscommentMapper {
    int deleteByPrimaryKey(MapUserDiscusscommentKey key);

    int insert(MapUserDiscusscomment record);

    int insertSelective(MapUserDiscusscomment record);

    MapUserDiscusscomment selectByPrimaryKey(MapUserDiscusscommentKey key);

    int updateByPrimaryKeySelective(MapUserDiscusscomment record);

    int updateByPrimaryKey(MapUserDiscusscomment record);
}