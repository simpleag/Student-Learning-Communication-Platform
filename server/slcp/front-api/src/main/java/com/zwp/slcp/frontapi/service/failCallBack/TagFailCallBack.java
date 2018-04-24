package com.zwp.slcp.frontapi.service.failCallBack;

import com.github.pagehelper.PageInfo;
import com.zwp.slcp.apicommon.entity.FullTag;
import com.zwp.slcp.apicommon.entity.Tag;
import com.zwp.slcp.frontapi.service.TagService;
import org.springframework.stereotype.Component;

/**
 * Created by ASUS on 2018/4/23.
 */
@Component
public class TagFailCallBack implements TagService{
    @Override
    public PageInfo<FullTag> userAttentionTags(Long userId, Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public PageInfo<Tag> listUnattentionTags(Long userId, Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public String updateUserAttentionTag(Long userId, Integer tagId) {
        return null;
    }
}
