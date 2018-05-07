package com.zwp.slcp.frontapi.controlller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zwp.slcp.apicommon.constant.MyConstant;
import com.zwp.slcp.apicommon.entity.*;
import com.zwp.slcp.apicommon.response.FrontApiResponseEntity;
import com.zwp.slcp.apicommon.response.ResponseCode;
import com.zwp.slcp.apicommon.utils.StringUtils;
import com.zwp.slcp.frontapi.service.ActiveService;
import com.zwp.slcp.frontapi.service.AnoymousCommentService;
import com.zwp.slcp.frontapi.service.ArticleCommentService;
import com.zwp.slcp.frontapi.service.DiscussCommentService;
import javafx.beans.binding.ObjectExpression;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ASUS on 2018/4/24.
 */
@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private AnoymousCommentService anoymousCommentService;
    @Autowired
    private DiscussCommentService discussCommentService;
    @Autowired
    private ArticleCommentService articleCommentService;
    @Autowired
    private ActiveService activeService;

    private static String LOGIN_USER_ID = "LOGIN_USER_ID";

    @RequestMapping(value = "/listUsersComment")
    @ResponseBody
    String listUsersComment(Long userId, Long loginUserId, Integer pageNumber, Integer pageSize) {
        //
        PageInfo<ArticleComment> pageInfo = articleCommentService.listUsersComment(userId, pageNumber, pageSize);
        PageInfo<DiscussComment> discussCommentPageInfo = discussCommentService.listUsersComment(userId, pageNumber, pageSize);
        if (userId.longValue() == loginUserId.longValue()) {
            PageInfo<AnoymousComment> anoymousCommentPageInfo = anoymousCommentService.listUsersComment(userId, pageNumber, pageSize);
            return FrontApiResponseEntity.SUCC().data("articleCommentList", pageInfo).data("discussCommentList", discussCommentPageInfo)
                    .data("anoymousCommentList", anoymousCommentPageInfo).build();
        } else {
            return FrontApiResponseEntity.SUCC().data("articleCommentList", pageInfo).data("discussCommentList", discussCommentPageInfo).build();
        }
    }

    @RequestMapping(value = "/createArticleComment")
    @ResponseBody
    String createArticleComment(ArticleComment articleComment) {
        if (StringUtils.isBlank(articleComment.getArticleCommentAuthorId(), articleComment.getArticleCommentContent())) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        }
        //判断评论的长度

        articleComment.setArticleCommentState(1);
        articleComment.setCreateTime(System.currentTimeMillis());
        articleComment.setUpdateTime(System.currentTimeMillis());
        JSONObject jsonObject =  JSON.parseObject(articleCommentService.createComment(articleComment));
        if (jsonObject.getString("code").equals("200")) {
            Long acId = jsonObject.getLong("commentId");
            String content = "用户发飙了一篇文章的评论";
            activeService.createActive(MyConstant.MONGODB_COLL_NAME, articleComment.getArticleCommentAuthorId(), acId, 3, content);

        }
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "/createAnoymousComment")
    @ResponseBody
    String createAnoymousComment(AnoymousComment anoymousComment) {
        if (StringUtils.isBlank(anoymousComment.getAnoymousCommentAuthorId(), anoymousComment.getAnoymousCommentContent())) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        }
        //判断评论的长度

        anoymousComment.setAnoymousCommentState(1);
        anoymousComment.setCreateTime(System.currentTimeMillis());
        anoymousComment.setUpdateTime(System.currentTimeMillis());
        return anoymousCommentService.createComment(anoymousComment);
    }

    @RequestMapping(value = "/listComment")
    @ResponseBody
    String listComment(Long userId, Long targetId, Integer pageNumber, Integer pageSize, Integer type) {
        if (StringUtils.isBlank(userId, targetId, pageNumber, pageSize, type)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        }
//        System.out.println("np");
        PageInfo<DetailDiscussComment> detailDiscussCommentPageInfo = null;
        PageInfo<DetailArticleComment> detailArticleCommentPageInfo = null;
        PageInfo<DetailAnoymousComment> detailAnoymousCommentPageInfo = null;
        if (type == 1) {
            detailDiscussCommentPageInfo = discussCommentService.listDiscussComment(userId, targetId, pageNumber, pageSize);
        } else if (type == 2) {
            detailArticleCommentPageInfo = articleCommentService.listArticlesComment(userId, targetId, pageNumber, pageSize);
        } else if (type == 3) {
            detailAnoymousCommentPageInfo = anoymousCommentService.listAnoymousComment(userId, targetId, pageNumber, pageSize);
        }
        if (detailDiscussCommentPageInfo==null && detailArticleCommentPageInfo==null && detailAnoymousCommentPageInfo==null) {
            return FrontApiResponseEntity.SYS_ERR().build();
        } else if (type == 1 && detailDiscussCommentPageInfo!=null){
            return FrontApiResponseEntity.SUCC().data("comment", detailDiscussCommentPageInfo).build();
        } else if (type == 2 && detailArticleCommentPageInfo!=null){
            return FrontApiResponseEntity.SUCC().data("comment", detailArticleCommentPageInfo).build();
        } else if (type == 3 && detailAnoymousCommentPageInfo!=null) {
            return FrontApiResponseEntity.SUCC().data("comment", detailAnoymousCommentPageInfo).build();
        } else {
            return FrontApiResponseEntity.SYS_ERR().build();
        }
    }

    @RequestMapping(value = "/createDiscussComment")
    @ResponseBody
    String createDiscussComment(DiscussComment discussComment) {
        if (StringUtils.isBlank(discussComment.getDiscussCommentAuthorId(), discussComment.getDiscussCommentContent())) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        }
        //判断评论的长度

        discussComment.setDiscussCommentState(1);
        discussComment.setCreateTime(System.currentTimeMillis());
        discussComment.setUpdateTime(System.currentTimeMillis());
        JSONObject jsonObject = JSON.parseObject(discussCommentService.createComment(discussComment));
        if (jsonObject == null) {
            return FrontApiResponseEntity.SYS_ERR().build();
        }
        System.out.println(jsonObject.toJSONString());
        if (jsonObject.getString("code").equals("200")) {
            String content = "用户发表了一篇讨论的评论";
            Long commentId = jsonObject.getLong("commentId");
            activeService.createActive(MyConstant.MONGODB_COLL_NAME, discussComment.getDiscussCommentAuthorId(), commentId, 5, content);

        }
        return jsonObject.toJSONString();
    }
    @RequestMapping(value = "/approveComment")
    @ResponseBody
    String approveComment(Long userId, Long commentId, Integer type) {
        if (StringUtils.isBlank(userId, commentId, type)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        }
        String returnString;
        String content;
        //讨论赞同
        if (type == 1) {
            returnString = articleCommentService.updateAttentionType(userId, commentId);
            content = "用户赞同了一条文章的评论";
            //感觉动态的迁移到sqlservice中
            activeService.createActive(MyConstant.MONGODB_COLL_NAME, userId, commentId, 6, content);
        } else if (type == 2) {
            returnString = discussCommentService.updateAttentionType(userId, commentId);
            content = "用户赞同了一条讨论的评论";
            activeService.createActive(MyConstant.MONGODB_COLL_NAME, userId, commentId, 7, content);
        } else if (type == 3) {
            content = "用户赞同了一条讨论的评论";
            returnString = anoymousCommentService.updateAttentionType(userId, commentId);
            activeService.createActive(MyConstant.MONGODB_COLL_NAME, userId, commentId, 7, content);
        } else {
            returnString = FrontApiResponseEntity.SYS_ERR().build();
        }
        //还需要加入mongodb的插入动态
        return returnString;
    }

}
