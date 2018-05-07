package com.zwp.slcp.frontapi.service.failCallBack;

import com.github.pagehelper.PageInfo;
import com.zwp.slcp.apicommon.entity.DetailDiscuss;
import com.zwp.slcp.apicommon.entity.Discuss;
import com.zwp.slcp.apicommon.entity.HomeDiscuss;
import com.zwp.slcp.frontapi.service.DiscussService;
import org.springframework.stereotype.Component;

/**
 * Created by ASUS on 2018/4/23.
 */
@Component
public class DiscussFailCallBack implements DiscussService{
    @Override
    public PageInfo<HomeDiscuss> listHomeDiscussByTime(Long userId, Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public PageInfo<HomeDiscuss> listHomeDiscussByHot(Long userId, Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public PageInfo<HomeDiscuss> listTagHomeDiscussByHot(Long userId, Integer tagId, Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public PageInfo<HomeDiscuss> listTagHomeDiscussByTime(Long userId, Integer tagId, Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public DetailDiscuss findOneById(Long userId, Long discussId) {
        return null;
    }

    @Override
    public String findOneWithCommentById(Long userId, Long discussId) {
        return null;
    }

    @Override
    public String findOneByCommentId(Long userId, Long discussCommentId) {
        return null;
    }

    @Override
    public PageInfo<HomeDiscuss> listUsersDiscuss(Long userId, Long authorId, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public PageInfo<HomeDiscuss> listUsersFavoriteDiscuss(Long userId, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public String createDiscuss(Discuss discuss) {
        return null;
    }

    @Override
    public String updateDiscuss(Discuss discuss) {
        return null;
    }

    @Override
    public String updateUserAttentionType(Long userId, Long discussId, Integer type) {
        return null;
    }
}
