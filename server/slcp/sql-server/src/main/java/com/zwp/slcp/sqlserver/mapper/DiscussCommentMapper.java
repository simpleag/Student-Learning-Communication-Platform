package com.zwp.slcp.sqlserver.mapper;

import com.zwp.slcp.apicommon.entity.DetailDiscuss;
import com.zwp.slcp.apicommon.entity.DetailDiscussComment;
import com.zwp.slcp.apicommon.entity.DiscussComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussCommentMapper {
    int deleteByPrimaryKey(Long discussCommentId);

    int insert(DiscussComment record);

    int insertSelective(DiscussComment record);

    DiscussComment selectByPrimaryKey(Long discussCommentId);

    List<DiscussComment> selectByUserId(Long discussCommentAuthorId);

    //根据讨论Id查找对应的评论列表
    List<DetailDiscussComment> selectDetailByDiscussId(@Param("userId") Long userId, @Param("discussId") Long discussId);

    List<DetailDiscussComment> selectDetailByAuthorId(@Param("userId") Long userId, @Param("authorId") Long authorId);

    int updateByPrimaryKeySelective(DiscussComment record);

    int updateByPrimaryKey(DiscussComment record);

}