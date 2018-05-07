package com.zwp.slcp.frontapi.service.failCallBack;

import com.github.pagehelper.PageInfo;
import com.zwp.slcp.apicommon.entity.Anoymous;
import com.zwp.slcp.apicommon.entity.DetailAnoymous;
import com.zwp.slcp.apicommon.entity.HomeAnoymous;
import com.zwp.slcp.frontapi.service.AnoymousService;
import org.springframework.stereotype.Component;

/**
 * Created by ASUS on 2018/4/23.
 */
@Component
public class AnoymousFailCallBack implements AnoymousService{
    @Override
    public PageInfo<HomeAnoymous> listHomeAnoymouseByTime(Long userId, Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public PageInfo<HomeAnoymous> listHomeAnoymouseByHot(Long userId, Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public DetailAnoymous findOneById(Long userId, Long anoymousId) {
        return null;
    }

    @Override
    public String findOneWithCommentById(Long userId, Long anoymousId) {
        return null;
    }

    @Override
    public String findOneByCommentId(Long userId, Long anoumousCommentId) {
        return null;
    }

    @Override
    public PageInfo<HomeAnoymous> listUsersAnoymous(Long userId, Long authorId, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public PageInfo<HomeAnoymous> listUsersFavoriteAnoymous(Long userId, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public String createAnoymous(Anoymous anoymous) {
        return null;
    }

    @Override
    public String updateAnoymous(Anoymous anoymous) {
        return null;
    }

    @Override
    public String updateUserAttentionType(Long userId, Long discussId, Integer type) {
        return null;
    }
}
