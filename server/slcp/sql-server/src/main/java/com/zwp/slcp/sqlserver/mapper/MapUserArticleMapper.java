package com.zwp.slcp.sqlserver.mapper;

import com.zwp.slcp.apicommon.entity.MapUserArticle;
import com.zwp.slcp.apicommon.entity.MapUserArticleKey;

public interface MapUserArticleMapper {
    int deleteByPrimaryKey(MapUserArticleKey key);

    int insert(MapUserArticle record);

    int insertSelective(MapUserArticle record);

    MapUserArticle selectByPrimaryKey(MapUserArticleKey key);

    int updateByPrimaryKeySelective(MapUserArticle record);

    int updateByPrimaryKey(MapUserArticle record);
}