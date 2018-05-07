package com.zwp.slcp.frontapi.controlller;

import com.github.pagehelper.PageInfo;
import com.zwp.slcp.apicommon.entity.FullTag;
import com.zwp.slcp.apicommon.entity.Tag;
import com.zwp.slcp.apicommon.response.FrontApiResponseEntity;
import com.zwp.slcp.apicommon.response.ResponseCode;
import com.zwp.slcp.apicommon.utils.StringUtils;
import com.zwp.slcp.frontapi.service.ArticleService;
import com.zwp.slcp.frontapi.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ASUS on 2018/4/26.
 */
@RestController
@CrossOrigin
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagService tagService;
    @Autowired
    private ArticleService articleService;

    private static final String LOGIN_USER_ID = "LOGIN_USER_ID";
    private final Logger logger = LoggerFactory.getLogger(TagController.class);

    @RequestMapping(value = "/allTags")
    @ResponseBody
    String allTags(Long userId, Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, pageNumber, pageSize)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        }else {
            PageInfo<FullTag> userAttentionTags = tagService.userAttentionTags(userId, pageNumber, pageSize);
            if (userAttentionTags == null) {
                userAttentionTags = new PageInfo<>();
            }
            PageInfo<Tag> userUnAttentionTags = tagService.listUnattentionTags(userId, pageNumber, pageSize);
            if (userUnAttentionTags == null) {
                userUnAttentionTags = new PageInfo<>();
            }

            return FrontApiResponseEntity.SUCC().data("attentionTags", userAttentionTags)
                    .data("unAttentionTags", userUnAttentionTags).build();

        }
    }

    @RequestMapping(value = "/userAttentionTags")
    @ResponseBody
    String userAttentionTags(Long userId, Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, pageNumber, pageSize)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        }else {
            PageInfo<FullTag> userAttentionTags = tagService.userAttentionTags(userId, pageNumber, pageSize);
            if (userAttentionTags == null) {
                userAttentionTags = new PageInfo<>();
            }
            return FrontApiResponseEntity.SUCC().data("attentionTags", userAttentionTags).build();

        }
    }
}
