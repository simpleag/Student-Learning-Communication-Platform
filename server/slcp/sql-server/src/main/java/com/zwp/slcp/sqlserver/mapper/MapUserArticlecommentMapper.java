package com.zwp.slcp.sqlserver.mapper;

import com.zwp.slcp.apicommon.entity.MapUserArticlecomment;
import com.zwp.slcp.apicommon.entity.MapUserArticlecommentKey;

public interface MapUserArticlecommentMapper {
    int deleteByPrimaryKey(MapUserArticlecommentKey key);

    int insert(MapUserArticlecomment record);

    int insertSelective(MapUserArticlecomment record);

    MapUserArticlecomment selectByPrimaryKey(MapUserArticlecommentKey key);

    int updateByPrimaryKeySelective(MapUserArticlecomment record);

    int updateByPrimaryKey(MapUserArticlecomment record);
}