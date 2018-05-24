package com.zwp.slcp.frontapi.controlller;

import com.github.pagehelper.Page;
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

    @RequestMapping(value = "/allUserAttentionAndUnattention")
    @ResponseBody
    String allUserAttentionAndUnattention(Long userId, Long targetUserId, Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, targetUserId, pageNumber, pageSize)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        }else {
            PageInfo<FullTag> userAttentionTags = tagService.userAttentionTags(targetUserId, pageNumber, pageSize);
            if (userAttentionTags == null) {
                userAttentionTags = new PageInfo<>();
            }
            PageInfo<Tag> userUnAttentionTags = tagService.listUnattentionTags(targetUserId, pageNumber, pageSize);
            if (userUnAttentionTags == null) {
                userUnAttentionTags = new PageInfo<>();
            }

            return FrontApiResponseEntity.SUCC().data("attentionTags", userAttentionTags)
                    .data("unAttentionTags", userUnAttentionTags).build();

        }
    }

    @RequestMapping(value = "/allTags")
    @ResponseBody
    String allTags(Long userId){
        if (StringUtils.isBlank(userId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            PageInfo<Tag> tagPageInfo = tagService.listAllTags(userId);
            if (tagPageInfo==null) {
                return FrontApiResponseEntity.SYS_ERR().build();
            } else {
                return FrontApiResponseEntity.SUCC().data("listTags", tagPageInfo).build();
            }
        }

    }

    @RequestMapping(value = "/userAttentionTags")
    @ResponseBody
    String userAttentionTags(Long userId,Long targetUserId, Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, targetUserId, pageNumber, pageSize)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        }else {
            PageInfo<FullTag> userAttentionTags = tagService.userAttentionTags(targetUserId, pageNumber, pageSize);
            if (userAttentionTags == null) {
                userAttentionTags = new PageInfo<>();
                return FrontApiResponseEntity.SYS_ERR().message("无关注标签").data("attentionTags", userAttentionTags).build();
            }
            return FrontApiResponseEntity.SUCC().data("attentionTags", userAttentionTags).build();

        }
    }

    @RequestMapping(value = "/updateUserAttentionTag")
    @ResponseBody
    String updateUserAttentionTag(Long userId,Integer tagId) {
        if (StringUtils.isBlank(userId, tagId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        }else {
            return tagService.updateUserAttentionTag(userId, tagId);

        }
    }


}
