package com.zwp.slcp.sqlserver.mapper;

import com.zwp.slcp.apicommon.entity.MapUserAnoymousecomment;
import com.zwp.slcp.apicommon.entity.MapUserAnoymousecommentKey;

public interface MapUserAnoymousecommentMapper {
    int deleteByPrimaryKey(MapUserAnoymousecommentKey key);

    int insert(MapUserAnoymousecomment record);

    int insertSelective(MapUserAnoymousecomment record);

    MapUserAnoymousecomment selectByPrimaryKey(MapUserAnoymousecommentKey key);

    int updateByPrimaryKeySelective(MapUserAnoymousecomment record);

    int updateByPrimaryKey(MapUserAnoymousecomment record);
}