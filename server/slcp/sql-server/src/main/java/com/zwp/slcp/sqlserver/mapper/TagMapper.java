package com.zwp.slcp.sqlserver.mapper;

import com.zwp.slcp.apicommon.entity.FullTag;
import com.zwp.slcp.apicommon.entity.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagMapper {
    int deleteByPrimaryKey(Integer tagId);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Integer tagId);

    List<Tag> selectAllTag();

    List<Tag> selectNotAttentionByUserId(Long userId);

    List<FullTag> selectByUserId(Long userId);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);
}