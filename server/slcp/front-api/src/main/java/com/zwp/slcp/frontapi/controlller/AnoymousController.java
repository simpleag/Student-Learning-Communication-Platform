package com.zwp.slcp.frontapi.controlller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.zwp.slcp.apicommon.entity.*;
import com.zwp.slcp.apicommon.entity.HomeAnoymous;
import com.zwp.slcp.apicommon.response.FrontApiResponseEntity;
import com.zwp.slcp.apicommon.response.ResponseCode;
import com.zwp.slcp.apicommon.utils.StringUtils;
import com.zwp.slcp.frontapi.service.ActiveService;
import com.zwp.slcp.frontapi.service.AnoymousCommentService;
import com.zwp.slcp.frontapi.service.AnoymousService;
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
@RequestMapping("/anoymous")
public class AnoymousController {
    @Autowired
    private AnoymousService anoymousService;
    @Autowired
    private AnoymousCommentService anoymousCommentService;
    @Autowired
    private ActiveService activeService;


    private final Logger logger = LoggerFactory.getLogger(AnoymousController.class);
    private final static String COLL_NAME = "active";

    @RequestMapping(value = "/listOrderByTime")
    @ResponseBody
    String listOrderByTime(Long userId, Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, pageNumber, pageSize)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            PageInfo<HomeAnoymous> homePageInfo = anoymousService.listHomeAnoymouseByTime(userId, pageNumber, pageSize);
            if (homePageInfo == null) {
                return FrontApiResponseEntity.SYS_ERR().build();
            } else {
                return FrontApiResponseEntity.SUCC().data("anoymousList", homePageInfo).build();
            }
        }
    }

    @RequestMapping(value = "/listOrderByHot")
    @ResponseBody
    String listOrderByHot(Long userId, Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, pageNumber, pageSize)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            PageInfo<HomeAnoymous> homeDiscussPageInfo = anoymousService.listHomeAnoymouseByHot(userId, pageNumber, pageSize);
            if (homeDiscussPageInfo == null) {
                return FrontApiResponseEntity.SYS_ERR().build();
            } else {
                return FrontApiResponseEntity.SUCC().data("anoymousList", homeDiscussPageInfo).build();
            }
        }
    }



    @RequestMapping(value = "/detail")
    @ResponseBody
    String detail(Long userId, Long articleId) {
        if (StringUtils.isBlank(userId, articleId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            return anoymousService.findOneWithCommentById(userId, articleId);
        }
    }

    @RequestMapping(value = "/findByCommentId")
    @ResponseBody
    String findByCommentId(Long userId, Long commentId) {
        if (StringUtils.isBlank(userId, commentId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {

            return anoymousService.findOneByCommentId(userId, commentId);
        }
    }

    @RequestMapping(value = "/listByAuthorId")
    @ResponseBody
    String authors(Long userId, Long authorId, Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, authorId, pageNumber, pageSize)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            PageInfo<HomeAnoymous> homeDiscussPageInfo =  anoymousService.listUsersAnoymous(userId, authorId, pageNumber, pageSize);
            if (homeDiscussPageInfo == null) {
                return FrontApiResponseEntity.SYS_ERR().build();
            }
            return FrontApiResponseEntity.SUCC().data("anoymousList", homeDiscussPageInfo).build();
        }
    }

    @RequestMapping(value = "/userFavorite")
    @ResponseBody
    String userFavorite(Long userId, Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, pageNumber, pageSize)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            PageInfo<HomeAnoymous> homeDiscussPageInfo =  anoymousService.listUsersFavoriteAnoymous(userId, pageNumber, pageSize);
            if (homeDiscussPageInfo == null) {
                return FrontApiResponseEntity.SYS_ERR().build();
            }
            return FrontApiResponseEntity.SUCC().data("anoymousList", homeDiscussPageInfo).build();
        }
    }

    @RequestMapping(value = "/create")
    @ResponseBody
    String create(Long userId, String tiltle, String content) {
        if (StringUtils.isBlank(userId, tiltle, content)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            Anoymous anoymous = new Anoymous(userId, tiltle, content);
            JSONObject json = JSON.parseObject(anoymousService.createAnoymous(anoymous));
            return json.toJSONString();
        }
    }

    @RequestMapping(value = "/updateState")
    @ResponseBody
    String updateState(Long anoymousId, Integer state) {
        if (StringUtils.isBlank(anoymousId, state)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            Anoymous anoymous = new Anoymous(anoymousId);
            anoymous.setAnoymousState(state);
            return anoymousService.updateAnoymous(anoymous);
        }
    }

    @RequestMapping(value = "/baseData")
    @ResponseBody
    String baseData(Long userId, Long anoymousId) {
        if (StringUtils.isBlank(anoymousId, userId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            DetailAnoymous detailAnoymous = anoymousService.findOneById(userId, anoymousId);
            if (detailAnoymous == null) {
                return FrontApiResponseEntity.SYS_ERR().build();
            }
            return FrontApiResponseEntity.SUCC().data("detail", detailAnoymous).build();
        }
    }

    @RequestMapping(value = "/approve")
    @ResponseBody
    String approve(Long userId, Long articleId) {
        if (StringUtils.isBlank(articleId, userId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            //添加用户对应的动态
            return  anoymousService.updateUserAttentionType(userId, articleId, 1);

        }
    }

    @RequestMapping(value = "/favorite")
    @ResponseBody
    String favorite(Long userId, Long articleId) {
        if (StringUtils.isBlank(articleId, userId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            //添加用户对应的动态

            return  anoymousService.updateUserAttentionType(userId, articleId, 2);

        }
    }
}
