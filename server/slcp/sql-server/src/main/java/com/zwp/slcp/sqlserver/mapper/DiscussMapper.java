package com.zwp.slcp.sqlserver.mapper;

import com.zwp.slcp.apicommon.entity.DetailDiscuss;
import com.zwp.slcp.apicommon.entity.Discuss;
import com.zwp.slcp.apicommon.entity.HomeDiscuss;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussMapper {
    int deleteByPrimaryKey(Long discussId);

    int insert(Discuss record);

    int insertSelective(Discuss record);

    Discuss selectByPrimaryKey(Long discussId);

    //根据时间/回复量倒序显示主页的讨论
    List<HomeDiscuss> selectHomeDiscussOrderByTime(Long userId);

    List<HomeDiscuss> selectHomeDiscussOrderByHot(Long userId);

    //查询标签下的文章
    List<HomeDiscuss> selectTagsDiscussOrderByHot(@Param("userId") Long userId, @Param("tagId") Integer tagId);

    List<HomeDiscuss> selectTagsDiscussOrderByTime(@Param("userId") Long userId, @Param("tagId") Integer tagId);

    //查询用户收藏的文章
    List<HomeDiscuss> selectUserFavoriteDiscuss(Long userId);

    //根据作者显示讨论列表
    List<HomeDiscuss> selectHomeDiscussByAuthorId(@Param("userId") Long userId, @Param("authorId") Long authorId);

    //根据讨论Id显示讨论具体内容
    DetailDiscuss selectDiscussDetailById(Long userId, Long discussId);

    //根据讨论评论id查找对应的讨论
    DetailDiscuss selectDiscussDetailByComment(@Param("userId") Long userId, @Param("discussCommentId") Long discussCommentId);

    int updateByPrimaryKeySelective(Discuss record);

    int updateByPrimaryKey(Discuss record);

    int updateCommentNumber(Long updateTime, Long discussId);
}