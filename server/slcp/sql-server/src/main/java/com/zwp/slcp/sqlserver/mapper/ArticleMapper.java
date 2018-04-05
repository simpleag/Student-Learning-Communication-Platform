package com.zwp.slcp.sqlserver.mapper;

import com.zwp.slcp.apicommon.entity.Article;
import com.zwp.slcp.apicommon.entity.DetailArticle;
import com.zwp.slcp.apicommon.entity.HomeArticle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {
    int deleteByPrimaryKey(Long articleId);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Long articleId);

    //显示主页的文章列表 根据时间倒序
    List<HomeArticle> selectArticleHomeOrderByTime(Long userId);

    //显示主页的文章列表 根据时间倒序
    List<HomeArticle> selectArticleHomeOrderByHot(Long userId);

    //显示标签页下面的文章 类似主页根据热度和时间排序
    List<HomeArticle> selectTagsArticleOrderByHot(Long userId);

    //显示用户发表的文章
    List<HomeArticle> selectsArticleByAuthor(Long userId, Long authorId);

    //显示tag下的文章
    List<HomeArticle> selectTagsArticleOrderByTime(Long userId);

    //显示用户收藏的文章
    List<HomeArticle> selectsArticleByFavorite(Long userId);

    //显示一篇文章的具体内容
    DetailArticle selectDetailArticle(Long userId, Long articleId);

    //根据文章的评论查找对应的文章
    DetailArticle selectDetailArticleByComment(Long userId, Long articleCommentId);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);

    //文章评论+1
    int updateCommentNumber(Long ArticleId, Long updateTime);
}