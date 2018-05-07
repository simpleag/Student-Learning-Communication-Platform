package com.zwp.slcp.frontapi.service.failCallBack;

import com.github.pagehelper.PageInfo;
import com.zwp.slcp.apicommon.entity.AnoymousComment;
import com.zwp.slcp.apicommon.entity.DetailAnoymousComment;
import com.zwp.slcp.frontapi.service.AnoymousCommentService;
import org.springframework.stereotype.Component;

/**
 * Created by ASUS on 2018/4/24.
 */
@Component
public class AnoymouseCommentFailCallBack implements AnoymousCommentService{
    @Override
    public PageInfo<AnoymousComment> listUsersComment(Long userId, Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public String createComment(AnoymousComment anoymousComment) {
        return null;
    }

    @Override
    public String updateAttentionType(Long userId, Long anoymousCommentId) {
        return null;
    }

    @Override
    public PageInfo<DetailAnoymousComment> listAnoymousComment(Long userId, Long anoymousId, Integer pageNumber, Integer pageSize) {
        return null;
    }
}
