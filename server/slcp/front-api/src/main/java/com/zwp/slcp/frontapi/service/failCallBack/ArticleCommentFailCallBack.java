package com.zwp.slcp.frontapi.service.failCallBack;

import com.github.pagehelper.PageInfo;
import com.zwp.slcp.apicommon.entity.ArticleComment;
import com.zwp.slcp.apicommon.entity.DetailArticleComment;
import com.zwp.slcp.frontapi.service.ArticleCommentService;
import org.springframework.stereotype.Component;

/**
 * Created by ASUS on 2018/4/23.
 */
@Component
public class ArticleCommentFailCallBack implements ArticleCommentService {
    @Override
    public PageInfo<ArticleComment> listUsersComment(Long userId, Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public String createComment(ArticleComment articleComment) {
        return null;
    }

    @Override
    public String updateAttentionType(Long userId, Long articleCommentId) {
        return null;
    }

    @Override
    public PageInfo<DetailArticleComment> listArticlesComment(Long userId, Long articleId, Integer pageNumber, Integer pageSize) {
        return null;
    }
}
