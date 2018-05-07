package com.zwp.slcp.frontapi.service;

import com.github.pagehelper.PageInfo;
import com.zwp.slcp.apicommon.entity.Article;
import com.zwp.slcp.apicommon.entity.DetailArticle;
import com.zwp.slcp.apicommon.entity.HomeArticle;
import com.zwp.slcp.frontapi.service.failCallBack.ArticleFailCallBack;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ASUS on 2018/4/23.
 */
@FeignClient(value = "SQLSERVER", fallback = ArticleFailCallBack.class)
public interface ArticleService {
    @RequestMapping("/article/listHomeArticlesByTime")
    @ResponseBody
    PageInfo<HomeArticle> listHomeArticlesByTime(@RequestParam(value = "userId") Long userId, @RequestParam(value = "pageNumber") Integer pageNumber, @RequestParam(value = "pageSize") Integer pageSize);

    @RequestMapping("/article/listHomeArticlesByHot")
    @ResponseBody
    PageInfo<HomeArticle> listHomeArticlesByHot(@RequestParam(value = "userId") Long userId,@RequestParam(value = "pageNumber") Integer pageNumber, @RequestParam(value = "pageSize") Integer pageSize);

    @RequestMapping("/article/listTagsHomeArticlesByHot")
    @ResponseBody
    PageInfo<HomeArticle> listTagsHomeArticlesByHot(@RequestParam(value = "userId") Long userId, @RequestParam(value = "tagId") Integer tagId,@RequestParam(value = "pageNumber") Integer pageNumber, @RequestParam(value = "pageSize") Integer pageSize);

    @RequestMapping("/article/listTagsHomeArticlesByTime")
    @ResponseBody
    PageInfo<HomeArticle> listTagsHomeArticlesByTime(@RequestParam(value = "userId") Long userId, @RequestParam(value = "tagId") Integer tagId,  @RequestParam(value = "pageNumber") Integer pageNumber, @RequestParam(value = "pageSize") Integer pageSize);

    @RequestMapping("/article/findOneById")
    @ResponseBody
    DetailArticle findOneById(@RequestParam(value = "userId") Long userId, @RequestParam(value = "articleId") Long articleId);

    @RequestMapping("/article/findOneWithCommentById")
    @ResponseBody
    String findOneWithCommentById(@RequestParam(value = "userId") Long userId, @RequestParam(value = "articleId") Long articleId);

    @RequestMapping("/article/findOneWithCommentByCommentId")
    @ResponseBody
    String findOneByCommentId(@RequestParam(value = "userId") Long userId, @RequestParam(value = "articleCommentId") Long articleCommentId);

    @RequestMapping("/article/listUsersArticles")
    @ResponseBody
    PageInfo<HomeArticle> listUsersArticles(@RequestParam(value = "userId") Long userId, @RequestParam(value = "authorId") Long authorId, @RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize);

    @RequestMapping("/article/listUsersFavoriteArticles")
    @ResponseBody
    PageInfo<HomeArticle> listUsersFavoriteArticles(@RequestParam(value = "userId") Long userId, @RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize);

    @RequestMapping("/article/createArticle")
    @ResponseBody
    String createArticle(Article article);

    @RequestMapping("/article/updateArticle")
    @ResponseBody
    String updateArticle(Article article);

    @RequestMapping("/article/updateUserAttentionType")
    @ResponseBody
    String updateUserAttentionType (@RequestParam(value = "userId") Long userId, @RequestParam(value = "articleId") Long articleId, @RequestParam(value = "type") Integer type);
}
