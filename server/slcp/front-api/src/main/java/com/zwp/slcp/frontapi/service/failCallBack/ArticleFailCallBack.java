package com.zwp.slcp.frontapi.service.failCallBack;

import com.github.pagehelper.PageInfo;
import com.zwp.slcp.apicommon.entity.Article;
import com.zwp.slcp.apicommon.entity.DetailArticle;
import com.zwp.slcp.apicommon.entity.HomeArticle;
import com.zwp.slcp.frontapi.service.ArticleService;
import org.springframework.stereotype.Component;

/**
 * Created by ASUS on 2018/4/23.
 */
@Component
public class ArticleFailCallBack implements ArticleService{
    @Override
    public PageInfo<HomeArticle> listHomeArticlesByTime(Long userId, Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public PageInfo<HomeArticle> listHomeArticlesByHot(Long userId, Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public PageInfo<HomeArticle> listTagsHomeArticlesByHot(Long userId, Integer tagId, Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public PageInfo<HomeArticle> listTagsHomeArticlesByTime(Long userId, Integer tagId, Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public DetailArticle findOneById(Long userId, Long articleId) {
        return null;
    }

    @Override
    public String findOneWithCommentById(Long userId, Long articleId) {
        return null;
    }

    @Override
    public String findOneByCommentId(Long userId, Long articleCommentId) {
        return null;
    }

    @Override
    public PageInfo<HomeArticle> listUsersArticles(Long userId, Long authorId, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public PageInfo<HomeArticle> listUsersFavoriteArticles(Long userId, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public String createArticle(Article article) {
        return null;
    }

    @Override
    public String updateArticle(Article article) {
        return null;
    }

    @Override
    public String updateUserAttentionType(Long userId, Long articleId, Integer type) {
        return null;
    }
}
