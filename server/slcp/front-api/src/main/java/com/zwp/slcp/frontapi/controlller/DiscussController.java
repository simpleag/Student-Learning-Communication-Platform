package com.zwp.slcp.frontapi.controlller;

import com.github.pagehelper.PageInfo;
import com.zwp.slcp.apicommon.entity.*;
import com.zwp.slcp.apicommon.response.FrontApiResponseEntity;
import com.zwp.slcp.apicommon.response.ResponseCode;
import com.zwp.slcp.apicommon.utils.StringUtils;
import com.zwp.slcp.frontapi.service.DiscussCommentService;
import com.zwp.slcp.frontapi.service.DiscussService;
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
@RequestMapping("/discuss")
public class DiscussController {
    @Autowired
    private DiscussService discussService;
    @Autowired
    private DiscussCommentService discussCommentService;

    private final Logger logger = LoggerFactory.getLogger(DiscussController.class);

    @RequestMapping(value = "/listOrderByTime")
    @ResponseBody
    String listOrderByTime(Long userId, Integer pageNumber, Integer pageSize) {
        System.out.println("id"+ userId + "number"+ pageNumber+" "+ pageSize);
        if (StringUtils.isBlank(userId, pageNumber, pageSize)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            PageInfo<HomeDiscuss> homePageInfo = discussService.listHomeDiscussByTime(userId, pageNumber, pageSize);
            if (homePageInfo == null) {
                return FrontApiResponseEntity.SYS_ERR().build();
            } else {
                return FrontApiResponseEntity.SUCC().data("discussList", homePageInfo).build();
            }
        }
    }

    @RequestMapping(value = "/listOrderByHot")
    @ResponseBody
    String listOrderByHot(Long userId, Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, pageNumber, pageSize)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            PageInfo<HomeDiscuss> homeDiscussPageInfo = discussService.listHomeDiscussByHot(userId, pageNumber, pageSize);
            if (homeDiscussPageInfo == null) {
                return FrontApiResponseEntity.SYS_ERR().build();
            } else {
                return FrontApiResponseEntity.SUCC().data("discussList", homeDiscussPageInfo).build();
            }
        }
    }

    @RequestMapping(value = "/listTagsDiscussOrderByHot")
    @ResponseBody
    String listTagsDiscussOrderByHot(Long userId, Integer tagId, Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, tagId, pageNumber, pageSize)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            PageInfo<HomeDiscuss> homeDiscussPageInfo = discussService.listTagHomeDiscussByHot(userId, tagId, pageNumber, pageSize);
            if (homeDiscussPageInfo == null) {
                return FrontApiResponseEntity.SYS_ERR().build();
            } else {
                return FrontApiResponseEntity.SUCC().data("discussList", homeDiscussPageInfo).build();
            }
        }
    }

    @RequestMapping(value = "/listTagsDiscussOrderByTime")
    @ResponseBody
    String listTagsDiscussOrderByTime(Long userId, Integer tagId, Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, tagId, pageNumber, pageSize)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            PageInfo<HomeDiscuss> homeDisscussPageInfo = discussService.listTagHomeDiscussByTime(userId, tagId, pageNumber, pageSize);
            if (homeDisscussPageInfo == null) {
                return FrontApiResponseEntity.SYS_ERR().build();
            } else {
                return FrontApiResponseEntity.SUCC().data("discussList", homeDisscussPageInfo).build();
            }
        }
    }

    @RequestMapping(value = "/detail")
    @ResponseBody
    String detail(Long userId, Long articleId) {
        if (StringUtils.isBlank(userId, articleId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            return discussService.findOneWithCommentById(userId, articleId);
        }
    }

    @RequestMapping(value = "/findByCommentId")
    @ResponseBody
    String findByCommentId(Long userId, Long commentId) {
        if (StringUtils.isBlank(userId, commentId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {

            return discussService.findOneByCommentId(userId, commentId);
        }
    }

    @RequestMapping(value = "/authors")
    @ResponseBody
    String authors(Long userId, Long authorId, Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, authorId, pageNumber, pageSize)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            PageInfo<HomeDiscuss> homeDiscussPageInfo =  discussService.listUsersDiscuss(userId, authorId, pageNumber, pageSize);
            if (homeDiscussPageInfo == null) {
                return FrontApiResponseEntity.SYS_ERR().build();
            }
            return FrontApiResponseEntity.SUCC().data("discussList", homeDiscussPageInfo).build();
        }
    }

    @RequestMapping(value = "/userFavorite")
    @ResponseBody
    String userFavorite(Long userId, Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, pageNumber, pageSize)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            PageInfo<HomeDiscuss> homeDiscussPageInfo =  discussService.listUsersFavoriteDiscuss(userId, pageNumber, pageSize);
            if (homeDiscussPageInfo == null) {
                return FrontApiResponseEntity.SYS_ERR().build();
            }
            return FrontApiResponseEntity.SUCC().data("discussList", homeDiscussPageInfo).build();
        }
    }

    @RequestMapping(value = "/create")
    @ResponseBody
    String create(Long userId, String tiltle, String content, Integer tagId) {
        if (StringUtils.isBlank(userId, tiltle, content, tagId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {

            Discuss discuss = new Discuss(userId, tiltle, content, tagId);
            return discussService.createDiscuss(discuss);
        }
    }

    @RequestMapping(value = "/updateState")
    @ResponseBody
    String updateState(Long discussId, Integer state) {
        if (StringUtils.isBlank(discussId, state)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            Discuss discuss = new Discuss(discussId);
            discuss.setDiscussState(state);
            return discussService.updateDiscuss(discuss);
        }
    }

    @RequestMapping(value = "/baseData")
    @ResponseBody
    String baseData(Long userId, Long articleId) {
        if (StringUtils.isBlank(articleId, userId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            DetailDiscuss detailDiscuss = discussService.findOneById(userId, articleId);
            if (detailDiscuss == null) {
                return FrontApiResponseEntity.SYS_ERR().build();
            }
            return FrontApiResponseEntity.SUCC().data("detail", detailDiscuss).build();
        }
    }

    @RequestMapping(value = "/approve")
    @ResponseBody
    String approve(Long userId, Long articleId) {
        System.out.println("front");
        if (StringUtils.isBlank(articleId, userId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            //添加用户对应的动态 重复赞同情况考虑

            return  discussService.updateUserAttentionType(userId, articleId, 3);

        }
    }

    @RequestMapping(value = "/favorite")
    @ResponseBody
    String favorite(Long userId, Long articleId) {
        if (StringUtils.isBlank(articleId, userId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            System.out.println("np");
            //添加用户对应的动态

            return  discussService.updateUserAttentionType(userId, articleId, 4);

        }
    }
}
