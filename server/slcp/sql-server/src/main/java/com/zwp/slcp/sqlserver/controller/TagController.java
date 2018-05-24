package com.zwp.slcp.sqlserver.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zwp.slcp.apicommon.entity.FullTag;
import com.zwp.slcp.apicommon.entity.MapUserTag;
import com.zwp.slcp.apicommon.entity.MapUserTagKey;
import com.zwp.slcp.apicommon.entity.Tag;
import com.zwp.slcp.apicommon.response.FrontApiResponseEntity;
import com.zwp.slcp.apicommon.response.ResponseCode;
import com.zwp.slcp.apicommon.utils.StringUtils;
import com.zwp.slcp.sqlserver.mapper.MapUserTagMapper;
import com.zwp.slcp.sqlserver.mapper.TagMapper;
import com.zwp.slcp.sqlserver.service.TagService;
import com.zwp.slcp.sqlserver.service.UserService;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ASUS on 2018/4/19.
 */
@RestController
@CrossOrigin
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private MapUserTagMapper mapUserTagMapper;
    @Autowired
    private TagService tagService;
    private static final Logger logger = LoggerFactory.getLogger(TagController.class);

    //用户关注的标签
    @RequestMapping("/userAttentionTags")
    @ResponseBody
    PageInfo<FullTag> userAttentionTags(Long userId, Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, pageNumber, pageSize)) {
            return null;
        } else {
            PageHelper.startPage(pageNumber, pageSize);
            logger.info(pageNumber+ "pageSize"+ pageSize);
            List<FullTag> list = tagMapper.selectByUserId(userId);
            PageInfo<FullTag> appsPageInfo = new PageInfo<FullTag>(list);
            return appsPageInfo;
        }

    }
    @RequestMapping("/listUnattentionTags")
    @ResponseBody
    PageInfo<Tag> listUnattentionTags(Long userId, Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, pageNumber, pageSize)) {
            return null;
        } else {
            PageHelper.startPage(pageNumber, pageSize);
            List<Tag> list = tagMapper.selectNotAttentionByUserId(userId);
            PageInfo<Tag> tagPageInfo = new PageInfo<Tag>(list);
            return tagPageInfo;
        }
    }

    @RequestMapping("/listAllTags")
    @ResponseBody
    PageInfo<Tag> listAllTags(Long userId) {
        if (StringUtils.isBlank(userId)) {
            return null;
        } else {
            List<Tag> tagList = tagMapper.selectAllTag();
            PageInfo<Tag> tagPageInfo = new PageInfo<Tag>(tagList);
            return tagPageInfo;
        }
    }

    @RequestMapping("/updateUserAttentionTag")
    @ResponseBody
    String updateUserAttentionTag(Long userId, Integer tagId) {
        Boolean isSuccess = false;
        if (StringUtils.isBlank(userId, tagId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            MapUserTagKey key = new MapUserTagKey();
            key.setTagId(tagId);
            key.setUserId(userId);

            MapUserTag mapUserTag = mapUserTagMapper.selectByPrimaryKey(key);
            if (mapUserTag == null) {
                mapUserTag = new MapUserTag();
                mapUserTag.setTagId(tagId);
                mapUserTag.setUserId(userId);
                mapUserTag.setUserAttentionType(1);
                isSuccess = tagService.createUserAttentionTagType(mapUserTag);
            } else {
                if (mapUserTag.getUserAttentionType() == 0) {
                    mapUserTag.setUserAttentionType(1);
                } else {
                    mapUserTag.setUserAttentionType(0);
                }
                isSuccess = tagService.updateUserAttentionTagType(mapUserTag);
            }
        }
        if (isSuccess) {
            return FrontApiResponseEntity.SUCC().build();
        } else {
            return FrontApiResponseEntity.ERR(ResponseCode.SQLFAIl).build();
        }
    }

}
