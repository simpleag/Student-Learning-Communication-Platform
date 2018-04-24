package com.zwp.slcp.sqlserver.mapper;

import com.zwp.slcp.apicommon.entity.ArticleComment;
import com.zwp.slcp.apicommon.entity.DetailArticleComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleCommentMapper {
    int deleteByPrimaryKey(Long articleCommentId);

    Long insert(ArticleComment record);

    int insertSelective(ArticleComment record);

    ArticleComment selectByPrimaryKey(Long articleCommentId);

    List<ArticleComment> selectByUserId(Long articleCommentId);

    List<ArticleComment> selectDetailByAuthorId(@Param("userId") Long userId, @Param("authorId") Long authorId);
    //根据文章id搜索对应的评论
    List<DetailArticleComment> selectDetailByArticleId(@Param("userId") Long userId, @Param("articleId") Long articleId);


    int updateByPrimaryKeySelective(ArticleComment record);

    int updateByPrimaryKey(ArticleComment record);
}