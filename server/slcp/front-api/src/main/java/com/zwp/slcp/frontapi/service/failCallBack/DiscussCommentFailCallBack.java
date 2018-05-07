package com.zwp.slcp.frontapi.service.failCallBack;

import com.github.pagehelper.PageInfo;
import com.zwp.slcp.apicommon.entity.DetailDiscussComment;
import com.zwp.slcp.apicommon.entity.DiscussComment;
import com.zwp.slcp.frontapi.service.DiscussCommentService;
import org.springframework.stereotype.Component;

/**
 * Created by ASUS on 2018/4/24.
 */
@Component
public class DiscussCommentFailCallBack implements DiscussCommentService {
    @Override
    public PageInfo<DiscussComment> listUsersComment(Long userId, Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public String createComment(DiscussComment discussComment) {
        return null;
    }

    @Override
    public String updateAttentionType(Long userId, Long discussCommentId) {
        return null;
    }

    @Override
    public PageInfo<DetailDiscussComment> listDiscussComment(Long userId, Long discussId, Integer pageNumber, Integer pageSize) {
        return null;
    }
}
