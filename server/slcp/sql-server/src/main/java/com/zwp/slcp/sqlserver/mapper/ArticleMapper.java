package com.zwp.slcp.sqlserver.mapper;

import com.zwp.slcp.apicommon.entity.Article;
import com.zwp.slcp.apicommon.entity.DetailArticle;
import com.zwp.slcp.apicommon.entity.HomeArticle;
import com.zwp.slcp.apicommon.entity.Info;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleMapper {
    int deleteByPrimaryKey(Long articleId);

    Long insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Long articleId);

    //显示主页的文章列表 根据时间倒序
    List<HomeArticle> selectArticleHomeOrderByTime(Long userId);

    //显示主页的文章列表 根据时间倒序
    List<HomeArticle> selectArticleHomeOrderByHot(Long userId);

    //显示标签页下面的文章 类似主页根据热度和时间排序
    List<HomeArticle> selectTagsArticleOrderByHot(@Param("userId")Long userId, @Param("tagId")Integer tagId);

    //显示用户发表的文章
    List<HomeArticle> selectsArticleByAuthor(@Param("userId") Long userId, @Param("authorId") Long authorId);

    //显示tag下的文章
    List<HomeArticle> selectTagsArticleOrderByTime(@Param("userId")Long userId, @Param("tagId")Integer tagId);

    //显示用户收藏的文章
    List<HomeArticle> selectsArticleByFavorite(Long userId);

    //显示一篇文章的具体内容
    DetailArticle selectDetailArticle(@Param("userId") Long userId, @Param("articleId") Long articleId);

    //根据文章的评论查找对应的文章
    DetailArticle selectDetailArticleByComment(@Param("userId") Long userId, @Param("articleCommentId") Long articleCommentId);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);

    //文章评论+1
    int updateCommentNumber(@Param("ArticleId") Long ArticleId, @Param("updateTime") Long updateTime);
}