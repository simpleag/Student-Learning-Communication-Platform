package com.zwp.slcp.sqlserver.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zwp.slcp.apicommon.entity.*;
import com.zwp.slcp.apicommon.response.FrontApiResponseEntity;
import com.zwp.slcp.apicommon.response.ResponseCode;
import com.zwp.slcp.apicommon.utils.StringUtils;
import com.zwp.slcp.sqlserver.mapper.DiscussCommentMapper;
import com.zwp.slcp.sqlserver.mapper.DiscussMapper;
import com.zwp.slcp.sqlserver.mapper.MapUserDiscusscommentMapper;
import com.zwp.slcp.sqlserver.service.DiscussCommentService;
import com.zwp.slcp.sqlserver.service.DiscussService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ASUS on 2018/4/23.
 */
@RestController
@RequestMapping("/dc")
public class DiscussCommentController {
    @Autowired
    private DiscussMapper discussMapper;
    @Autowired
    private DiscussCommentMapper discussCommentMapper;
    @Autowired
    private DiscussCommentService discussCommentService;
    @Autowired
    private MapUserDiscusscommentMapper mapUserDiscusscommentMapper;

    @RequestMapping("/listUsersComment")
    @ResponseBody
    PageInfo<DiscussComment> listUsersComment(Long userId, Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, pageNumber, pageSize)) {
            return null;
        } else {
            PageHelper.startPage(pageNumber, pageSize);
            List<DiscussComment> discussCommentList = discussCommentMapper.selectByUserId(userId);
            PageInfo<DiscussComment> discussCommentPageInfo = new PageInfo<>(discussCommentList);
            return discussCommentPageInfo;
        }
    }

    @RequestMapping("/createComment")
    @ResponseBody
    String createComment(DiscussComment discussComment) {
        if (discussCommentService.createDiscussComment(discussComment)) {
            return FrontApiResponseEntity.SUCC().build();
        } else {
            return FrontApiResponseEntity.ERR(ResponseCode.SQLFAIl).build();
        }
    }

    @RequestMapping("/updateAttentionType")
    @ResponseBody
    String updateAttentionType(Long userId, Long discussCommentId) {
        if (StringUtils.isBlank(userId, discussCommentId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {

            MapUserDiscusscommentKey key = new MapUserDiscusscommentKey(discussCommentId, userId);
            MapUserDiscusscomment mapUserDiscusscomment = mapUserDiscusscommentMapper.selectByPrimaryKey(key);
            if (mapUserDiscusscomment == null) {
                mapUserDiscusscomment = new MapUserDiscusscomment(discussCommentId, userId, 1);
                mapUserDiscusscomment.setCreateTime(System.currentTimeMillis());
                if (discussCommentService.createDiscussCommentApprove(mapUserDiscusscomment)) {
                    return FrontApiResponseEntity.SUCC().build();
                } else {
                    return FrontApiResponseEntity.ERR(ResponseCode.SQLFAIl).build();
                }
            } else {
                if (mapUserDiscusscomment.getUserApproveType() == 0) {
                    mapUserDiscusscomment.setUserApproveType(1);
                } else {
                    mapUserDiscusscomment.setUserApproveType(0);
                }
                mapUserDiscusscomment.setUpdateTime(System.currentTimeMillis());

                if (discussCommentService.updateDiscussCommentApprove(mapUserDiscusscomment)) {
                    return FrontApiResponseEntity.SUCC().build();
                } else {
                    return FrontApiResponseEntity.ERR(ResponseCode.SQLFAIl).build();
                }
            }
        }
    }
}
