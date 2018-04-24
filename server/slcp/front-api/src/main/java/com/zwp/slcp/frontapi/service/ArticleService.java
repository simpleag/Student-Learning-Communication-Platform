package com.zwp.slcp.frontapi.service;

import com.github.pagehelper.PageInfo;
import com.zwp.slcp.apicommon.entity.Article;
import com.zwp.slcp.apicommon.entity.DetailArticle;
import com.zwp.slcp.apicommon.entity.HomeArticle;
import com.zwp.slcp.frontapi.service.failCallBack.ArticleFailCallBack;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ASUS on 2018/4/23.
 */
@FeignClient(value = "SQLSERVER", fallback = ArticleFailCallBack.class)
public interface ArticleService {
    @RequestMapping("/article/listHomeArticlesByTime")
    @ResponseBody
    PageInfo<HomeArticle> listHomeArticlesByTime(Long userId, Integer pageNumber, Integer pageSize);

    @RequestMapping("/article/listHomeArticlesByHot")
    @ResponseBody
    PageInfo<HomeArticle> listHomeArticlesByHot(Long userId,Integer pageNumber, Integer pageSize);

    @RequestMapping("/article/listTagsHomeArticlesByHot")
    @ResponseBody
    PageInfo<HomeArticle> listTagsHomeArticlesByHot(Long userId, Integer tagId,Integer pageNumber, Integer pageSize);

    @RequestMapping("/article/listTagsHomeArticlesByTime")
    @ResponseBody
    PageInfo<HomeArticle> listTagsHomeArticlesByTime(Long userId, Integer tagId,Integer pageNumber, Integer pageSize);

    @RequestMapping("/article/findOneById")
    @ResponseBody
    DetailArticle findOneById(Long userId, Long articleId);

    @RequestMapping("/article/findOneWithCommentById")
    @ResponseBody
    String findOneWithCommentById(Long userId, Long articleId);

    @RequestMapping("/article/findOneWithCommentByCommentId")
    @ResponseBody
    String findOneByCommentId(Long userId, Long articleCommentId);

    @RequestMapping("/article/listUsersArticles")
    @ResponseBody
    PageInfo<HomeArticle> listUsersArticles(Long userId, Long authorId, Integer pageNum, Integer pageSize);

    @RequestMapping("/article/listUsersFavoriteArticles")
    @ResponseBody
    PageInfo<HomeArticle> listUsersFavoriteArticles(Long userId, Integer pageNum, Integer pageSize);

    @RequestMapping("/article/createArticle")
    @ResponseBody
    String createArticle(Article article);

    @RequestMapping("/article/updateArticle")
    @ResponseBody
    String updateArticle(Article article);

    @RequestMapping("/article/updateUserAttentionType")
    @ResponseBody
    String updateUserAttentionType (Long userId, Long articleId, Integer type);
}
