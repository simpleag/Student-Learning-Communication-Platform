package com.zwp.slcp.sqlserver.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zwp.slcp.apicommon.entity.*;
import com.zwp.slcp.apicommon.response.FrontApiResponseEntity;
import com.zwp.slcp.apicommon.response.ResponseCode;
import com.zwp.slcp.apicommon.utils.StringUtils;
import com.zwp.slcp.sqlserver.mapper.AnoymousCommentMapper;
import com.zwp.slcp.sqlserver.mapper.AnoymousMapper;
import com.zwp.slcp.sqlserver.mapper.MapUserAnoymousecommentMapper;
import com.zwp.slcp.sqlserver.service.AnoymouseCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ASUS on 2018/4/23.
 */
@RestController
@RequestMapping("/anoymouseComment")
public class AnoymousCommentController {
    @Autowired
    private MapUserAnoymousecommentMapper mapUserAnoymousecommentMapper;
    @Autowired
    private AnoymousCommentMapper anoymousCommentMapper;
    @Autowired
    private AnoymousMapper anoymousMapper;
    @Autowired
    private AnoymouseCommentService anoymouseCommentService;

    @RequestMapping("/listUsersComment")
    @ResponseBody
    PageInfo<AnoymousComment> listUsersComment(Long userId, Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, pageNumber, pageSize)) {
            return null;
        } else {
            PageHelper.startPage(pageNumber, pageSize);
            List<AnoymousComment> anoymousCommentList = anoymousCommentMapper.selectByUserId(userId);
            PageInfo<AnoymousComment> anoymousCommentPageInfo = new PageInfo<>(anoymousCommentList);
            return anoymousCommentPageInfo;
        }
    }

    @RequestMapping("/createComment")
    @ResponseBody
    String createComment(AnoymousComment anoymousComment) {
        if (anoymouseCommentService.createAnoymousComment(anoymousComment)) {
            return FrontApiResponseEntity.SUCC().build();
        } else {
            return FrontApiResponseEntity.ERR(ResponseCode.SQLFAIl).build();
        }
    }

    @RequestMapping("/updateAttentionType")
    @ResponseBody
    String updateAttentionType(Long userId, Long anoymousCommentId) {
        if (StringUtils.isBlank(userId, anoymousCommentId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {

            MapUserAnoymousecommentKey key = new MapUserAnoymousecommentKey(anoymousCommentId, userId);
            MapUserAnoymousecomment mapUserAnoymousecomment = mapUserAnoymousecommentMapper.selectByPrimaryKey(key);
            if (mapUserAnoymousecomment == null) {
                mapUserAnoymousecomment = new MapUserAnoymousecomment(anoymousCommentId, userId, 1);
                mapUserAnoymousecomment.setCreateTime(System.currentTimeMillis());
                if (anoymouseCommentService.createAnoymousCommentApprove(mapUserAnoymousecomment)) {
                    return FrontApiResponseEntity.SUCC().build();
                } else {
                    return FrontApiResponseEntity.ERR(ResponseCode.SQLFAIl).build();
                }
            } else {
                if (mapUserAnoymousecomment.getUserApproveType() == 0) {
                    mapUserAnoymousecomment.setUserApproveType(1);
                } else {
                    mapUserAnoymousecomment.setUserApproveType(0);
                }
                mapUserAnoymousecomment.setUpdateTime(System.currentTimeMillis());

                if (anoymouseCommentService.updateAnoymousCommentApprove(mapUserAnoymousecomment)) {
                    return FrontApiResponseEntity.SUCC().build();
                } else {
                    return FrontApiResponseEntity.ERR(ResponseCode.SQLFAIl).build();
                }
            }
        }
    }
}
