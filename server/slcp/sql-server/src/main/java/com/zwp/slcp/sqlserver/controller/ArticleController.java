package com.zwp.slcp.sqlserver.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zwp.slcp.apicommon.entity.*;
import com.zwp.slcp.apicommon.response.FrontApiResponseEntity;
import com.zwp.slcp.apicommon.response.ResponseCode;
import com.zwp.slcp.apicommon.utils.StringUtils;
import com.zwp.slcp.sqlserver.mapper.ArticleCommentMapper;
import com.zwp.slcp.sqlserver.mapper.ArticleMapper;
import com.zwp.slcp.sqlserver.mapper.MapUserArticleMapper;
import com.zwp.slcp.sqlserver.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ASUS on 2018/4/20.
 */
@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private MapUserArticleMapper mapUserArticleMapper;
    @Autowired
    private ArticleCommentMapper articleCommentMapper;

    /**
     *
     * @param userId
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequestMapping("/listHomeArticlesByTime")
    @ResponseBody
    PageInfo<HomeArticle> listHomeArticlesByTime(Long userId,Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, pageNumber, pageSize)) {
            return null;
        } else {
            PageHelper.startPage(pageNumber, pageSize);
            List<HomeArticle> articleList = articleMapper.selectArticleHomeOrderByTime(userId);
            PageInfo<HomeArticle> homeArticlePageInfo = new PageInfo<>(articleList);
            return homeArticlePageInfo;
        }
    }

    @RequestMapping("/listHomeArticlesByHot")
    @ResponseBody
    PageInfo<HomeArticle> listHomeArticlesByHot(Long userId,Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, pageNumber, pageSize)) {
            return null;
        } else {
            PageHelper.startPage(pageNumber, pageSize);
            List<HomeArticle> articleList = articleMapper.selectArticleHomeOrderByHot(userId);
            PageInfo<HomeArticle> homeArticlePageInfo = new PageInfo<>(articleList);
            return homeArticlePageInfo;
        }
    }

    @RequestMapping("/listTagsHomeArticlesByHot")
    @ResponseBody
    PageInfo<HomeArticle> listTagsHomeArticlesByHot(Long userId, Integer tagId,Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, tagId, pageNumber, pageSize)) {
            return null;
        } else {
            PageHelper.startPage(pageNumber, pageSize);
            List<HomeArticle> articleList = articleMapper.selectTagsArticleOrderByHot(userId, tagId);
            PageInfo<HomeArticle> homeArticlePageInfo = new PageInfo<>(articleList);
            return homeArticlePageInfo;
        }
    }

    @RequestMapping("/listTagsHomeArticlesByTime")
    @ResponseBody
    PageInfo<HomeArticle> listTagsHomeArticlesByTime(Long userId, Integer tagId,Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, tagId, pageNumber, pageSize)) {
            return null;
        } else {
            PageHelper.startPage(pageNumber, pageSize);
            List<HomeArticle> articleList = articleMapper.selectTagsArticleOrderByTime(userId, tagId);
            PageInfo<HomeArticle> homeArticlePageInfo = new PageInfo<>(articleList);
            return homeArticlePageInfo;
        }
    }

    @RequestMapping("/findOneById")
    @ResponseBody
    DetailArticle findOneById(Long userId, Long articleId) {
        if (StringUtils.isBlank(userId, articleId)) {
            return null;
        } else {
            return articleMapper.selectDetailArticle(userId, articleId);
        }
    }

    @RequestMapping("/findOneWithCommentById")
    @ResponseBody
    String findOneWithCommentById(Long userId, Long articleId) {
        if (StringUtils.isBlank(userId, articleId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            DetailArticle article = articleMapper.selectDetailArticle(userId, articleId);
            PageHelper.startPage(1,10);
            List<DetailArticleComment> detailArticleCommentList = articleCommentMapper.selectDetailByArticleId(userId, articleId);
            PageInfo<DetailArticleComment > detailArticlePageInfo = new PageInfo<>(detailArticleCommentList);
            return FrontApiResponseEntity.SUCC().data("article", article).data("comment", detailArticlePageInfo).build();
        }
    }

    @RequestMapping("/findOneWithCommentByCommentId")
    @ResponseBody
    String findOneByCommentId(Long userId, Long articleCommentId) {
        if (StringUtils.isBlank(userId, articleCommentId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            DetailArticle article = articleMapper.selectDetailArticleByComment(userId, articleCommentId);
            if (article == null) {
                return FrontApiResponseEntity.SYS_ERR().build();
            }
            List<DetailArticleComment> detailArticleCommentList = articleCommentMapper.selectDetailByArticleId(userId, article.getArticleId());
            PageInfo<DetailArticleComment > detailArticlePageInfo = new PageInfo<>(detailArticleCommentList);
            return FrontApiResponseEntity.SUCC().data("article", article).data("comment", detailArticlePageInfo).build();
        }
    }

    @RequestMapping("/listUsersArticles")
    @ResponseBody
    PageInfo<HomeArticle> listUsersArticles(Long userId, Long authorId, Integer pageNum, Integer pageSize) {
        if (StringUtils.isBlank(userId, authorId, pageNum, pageSize)) {
            return null;
        }else {
            PageHelper.startPage(pageNum, pageSize);
            List<HomeArticle> homeArticleList = articleMapper.selectsArticleByAuthor(userId, authorId);
            PageInfo<HomeArticle> homeArticlePageInfo = new PageInfo<>(homeArticleList);
            return homeArticlePageInfo;
        }
    }

    @RequestMapping("/listUsersFavoriteArticles")
    @ResponseBody
    PageInfo<HomeArticle> listUsersFavoriteArticles(Long userId, Integer pageNum, Integer pageSize) {
        if (StringUtils.isBlank(userId, pageNum, pageSize)) {
            return null;
        }else {
            PageHelper.startPage(pageNum, pageSize);
            List<HomeArticle> homeArticleList = articleMapper.selectsArticleByFavorite(userId);
            PageInfo<HomeArticle> homeArticlePageInfo = new PageInfo<>(homeArticleList);
            return homeArticlePageInfo;
        }
    }

    @RequestMapping("/createArticle")
    @ResponseBody
    String createArticle(Article article) {
        article.setCreateTime(System.currentTimeMillis());
        article.setUpdateTime(System.currentTimeMillis());
        article.setArticleState(1);
        if (StringUtils.isBlank(article.getArticleAuthorId())) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            return articleService.createArticle(article);
        }
    }

    @RequestMapping("/updateArticle")
    @ResponseBody
    String updateArticle(Article article) {
        if (StringUtils.isBlank(article.getArticleId())) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            article.setUpdateTime(System.currentTimeMillis());
            if (articleMapper.updateByPrimaryKeySelective(article) == 0) {
                return FrontApiResponseEntity.ERR(ResponseCode.SQLFAIl).build();
            } else {
                return FrontApiResponseEntity.SUCC().build();
            }
        }
    }

    /**
     *
     * @param userId
     * @param articleId
     * @param type 1为赞同2为收藏
     * @return
     */
    @RequestMapping("/updateUserAttentionType")
    @ResponseBody
    String updateUserAttentionType (Long userId, Long articleId, Integer type) {
        boolean success = false;
        Integer sqlUpdateType = 1;
        if (StringUtils.isBlank(userId, articleId, type)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {

            MapUserArticleKey key = new MapUserArticleKey(articleId, userId);
            MapUserArticle mapUserArticle = mapUserArticleMapper.selectByPrimaryKey(key);
            if (mapUserArticle == null) {
                mapUserArticle = new MapUserArticle(articleId, userId, 1, 0
                        , System.currentTimeMillis(), System.currentTimeMillis());
                if (type == 1) {
                    mapUserArticle.setUserApproveType(1);
                    mapUserArticle.setUserFavoriteType(0);
                } else {
                    mapUserArticle.setUserApproveType(0);
                    mapUserArticle.setUserFavoriteType(1);
                }
                success = articleService.createUserAttentionType(mapUserArticle);

            } else {
                //1为赞同2为收藏
                if (type==1 && mapUserArticle.getUserApproveType()==0) {
                    mapUserArticle.setUserApproveType(1);
                } else if (type==1 && mapUserArticle.getUserApproveType()==1) {
                    mapUserArticle.setUserApproveType(0);
                    sqlUpdateType = 2;
                } else if (type==2 && mapUserArticle.getUserFavoriteType()==0) {
                    mapUserArticle.setUserFavoriteType(1);
                    sqlUpdateType = 3;
                } else if (type==2 && mapUserArticle.getUserFavoriteType()==1) {
                    mapUserArticle.setUserFavoriteType(0);
                    sqlUpdateType = 4;
                } else {
                    return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
                }
                success = articleService.updateUserAttentionType(mapUserArticle, sqlUpdateType);
            }
        }
        if (success) {
            return FrontApiResponseEntity.SUCC().build();
        } else {
            return FrontApiResponseEntity.ERR(ResponseCode.SQLFAIl).build();
        }
    }


}
