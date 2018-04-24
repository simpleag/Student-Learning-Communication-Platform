package com.zwp.slcp.frontapi.service;

import com.github.pagehelper.PageInfo;
import com.zwp.slcp.apicommon.entity.ArticleComment;
import com.zwp.slcp.frontapi.service.failCallBack.ArticleCommentFailCallBack;
import com.zwp.slcp.frontapi.service.failCallBack.UserFailCallBack;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ASUS on 2018/4/23.
 */
@FeignClient(value = "SQLSERVER", fallback = ArticleCommentFailCallBack.class)
public interface ArticleCommentService {
    @RequestMapping("/ac/listUsersComment")
    @ResponseBody
    PageInfo<ArticleComment> listUsersComment(Long userId, Integer pageNumber, Integer pageSize);

    @RequestMapping("/ac/createComment")
    @ResponseBody
    String createComment(ArticleComment articleComment);

    @RequestMapping("/ac/updateAttentionType")
    @ResponseBody
    String updateAttentionType(Long userId, Long articleCommentId);
}
