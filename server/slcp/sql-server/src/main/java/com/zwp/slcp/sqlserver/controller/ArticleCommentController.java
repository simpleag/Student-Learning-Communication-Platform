package com.zwp.slcp.sqlserver.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zwp.slcp.apicommon.entity.*;
import com.zwp.slcp.apicommon.response.FrontApiResponseEntity;
import com.zwp.slcp.apicommon.response.ResponseCode;
import com.zwp.slcp.apicommon.utils.StringUtils;
import com.zwp.slcp.sqlserver.mapper.ArticleCommentMapper;
import com.zwp.slcp.sqlserver.mapper.MapUserArticlecommentMapper;
import com.zwp.slcp.sqlserver.service.ArticleCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ASUS on 2018/4/22.
 */
@RestController
@CrossOrigin
@RequestMapping("/ac")
public class ArticleCommentController {
    @Autowired
    private ArticleCommentMapper articleCommentMapper;
    @Autowired
    private ArticleCommentService articleCommentService;
    @Autowired
    private MapUserArticlecommentMapper mapUserArticlecommentMapper;

    @RequestMapping("/listUsersComment")
    @ResponseBody
    PageInfo<ArticleComment> listUsersComment(Long userId, Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, pageNumber, pageSize)) {
            return null;
        } else {
            PageHelper.startPage(pageNumber, pageSize);
            List<ArticleComment> detailArticleCommentList = articleCommentMapper.selectByUserId(userId);
            PageInfo<ArticleComment> articleCommentPageInfo = new PageInfo<>(detailArticleCommentList);
            return articleCommentPageInfo;
        }
    }

    @RequestMapping("/listArticlesComment")
    @ResponseBody
    PageInfo<DetailArticleComment> listArticlesComment(Long userId, Long articleId, Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, articleId, pageNumber, pageSize)) {
            return null;
        } else {
            PageHelper.startPage(pageNumber, pageSize);
            List<DetailArticleComment> detailArticleCommentList = articleCommentMapper.selectDetailByArticleId(userId, articleId);
            PageInfo<DetailArticleComment> detailArticleCommentPageInfo = new PageInfo<>(detailArticleCommentList);
            return detailArticleCommentPageInfo;
        }
    }

    @RequestMapping("/createComment")
    @ResponseBody
    String createComment(@RequestBody ArticleComment articleComment) {
        Long commentId = articleCommentService.createArticleComment(articleComment);
        if (commentId != 0L) {
            return FrontApiResponseEntity.SUCC().data("commentId", commentId).build();
        } else {
            return FrontApiResponseEntity.ERR(ResponseCode.SQLFAIl).build();
        }
    }

    @RequestMapping("/updateAttentionType")
    @ResponseBody
    String updateAttentionType(Long userId, Long articleCommentId) {
        if (StringUtils.isBlank(userId, articleCommentId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {

            MapUserArticlecommentKey key = new MapUserArticlecommentKey(articleCommentId, userId);
            MapUserArticlecomment mapUserArticlecomment = mapUserArticlecommentMapper.selectByPrimaryKey(key);
            if (mapUserArticlecomment == null) {
                mapUserArticlecomment = new MapUserArticlecomment(articleCommentId, userId, 1);
                mapUserArticlecomment.setCreateTime(System.currentTimeMillis());
                if (articleCommentService.createArticleCommentApprove(mapUserArticlecomment)) {
                    return FrontApiResponseEntity.SUCC().build();
                } else {
                    return FrontApiResponseEntity.ERR(ResponseCode.SQLFAIl).build();
                }
            } else {
                if (mapUserArticlecomment.getUserApproveType() == 0) {
                    mapUserArticlecomment.setUserApproveType(1);
                } else {
                    mapUserArticlecomment.setUserApproveType(0);
                }
                mapUserArticlecomment.setUpdateTime(System.currentTimeMillis());

                if (articleCommentService.updateArticleCommentApprove(mapUserArticlecomment)) {
                    return FrontApiResponseEntity.SUCC().build();
                } else {
                    return FrontApiResponseEntity.ERR(ResponseCode.SQLFAIl).build();
                }
            }
        }
    }
}
