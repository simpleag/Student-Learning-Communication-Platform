package com.zwp.slcp.sqlserver.mapper;

import com.zwp.slcp.apicommon.entity.DetailDiscuss;
import com.zwp.slcp.apicommon.entity.DiscussComment;

import java.util.List;

public interface DiscussCommentMapper {
    int deleteByPrimaryKey(Long discussCommentId);

    int insert(DiscussComment record);

    int insertSelective(DiscussComment record);

    DiscussComment selectByPrimaryKey(Long discussCommentId);

    List<DiscussComment> selectByUserId(Long discussCommentAuthorId);

    //根据讨论Id查找对应的评论列表
    List<DetailDiscuss> selectDetailByDiscussId(Long userId, Long discussId);

    int updateByPrimaryKeySelective(DiscussComment record);

    int updateByPrimaryKey(DiscussComment record);

}