package com.zwp.slcp.sqlserver.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zwp.slcp.apicommon.entity.*;
import com.zwp.slcp.apicommon.response.FrontApiResponseEntity;
import com.zwp.slcp.apicommon.response.ResponseCode;
import com.zwp.slcp.apicommon.utils.StringUtils;
import com.zwp.slcp.sqlserver.mapper.AnoymousCommentMapper;
import com.zwp.slcp.sqlserver.mapper.AnoymousMapper;
import com.zwp.slcp.sqlserver.mapper.MapUserAnoymouseMapper;
import com.zwp.slcp.sqlserver.service.AnoymousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ASUS on 2018/4/22.
 */
@RestController
@CrossOrigin
@RequestMapping("/anoymous")
public class AnoymousController {
    @Autowired
    private AnoymousMapper anoymousMapper;
    @Autowired
    private MapUserAnoymouseMapper mapUserAnoymouseMapper;
    @Autowired
    private AnoymousService anoymousService;
    @Autowired
    private AnoymousCommentMapper anoymousCommentMapper;

    @RequestMapping("/listHomeAnoymouseByTime")
    @ResponseBody
    PageInfo<HomeAnoymous> listHomeAnoymouseByTime(Long userId, Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, pageNumber, pageSize)) {
            return null;
        } else {
            PageHelper.startPage(pageNumber, pageSize);
            List<HomeAnoymous> homeAnoymousList = anoymousMapper.selectHomeAnoymousByTime(userId);
            PageInfo<HomeAnoymous> homeAnoymousPageInfo = new PageInfo<>(homeAnoymousList);
            return homeAnoymousPageInfo;
        }

    }

    @RequestMapping("/listHomeAnoymouseByHot")
    @ResponseBody
    PageInfo<HomeAnoymous> listHomeAnoymouseByHot(Long userId, Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, pageNumber, pageSize)) {
            return null;
        } else {
            PageHelper.startPage(pageNumber, pageSize);
            List<HomeAnoymous> homeAnoymousList = anoymousMapper.selectHomeAnoymousOrderByHot(userId);
            PageInfo<HomeAnoymous> homeAnoymousPageInfo = new PageInfo<>(homeAnoymousList);
            return homeAnoymousPageInfo;
        }

    }


    @RequestMapping("/findOneById")
    @ResponseBody
    DetailAnoymous findOneById(Long userId, Long anoymousId) {
        if (StringUtils.isBlank(userId, anoymousId)) {
            return null;
        } else {
            return anoymousMapper.selectAnoymousDetailById(userId, anoymousId);
        }
    }

    @RequestMapping("/findOneWithCommentById")
    @ResponseBody
    String findOneWithCommentById(Long userId, Long anoymousId) {
        if (StringUtils.isBlank(userId, anoymousId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            DetailAnoymous detailAnoymous = anoymousMapper.selectAnoymousDetailById(userId, anoymousId);
            PageHelper.startPage(1,10);
            List<DetailAnoymousComment> detailAnoymousComments = anoymousCommentMapper.selectDetailByAnoymousId(userId, anoymousId);
            PageInfo<DetailAnoymousComment > detailAnoymousCommentPageInfo = new PageInfo<>(detailAnoymousComments);
            return FrontApiResponseEntity.SUCC().data("anoymous", detailAnoymous).data("comment", detailAnoymousCommentPageInfo).build();
        }
    }

    @RequestMapping("/findOneWithCommentByCommentId")
    @ResponseBody
    String findOneWithCommentByCommentId(Long userId, Long anoumousCommentId) {
        if (StringUtils.isBlank(userId, anoumousCommentId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            DetailAnoymous detailAnoymous = anoymousMapper.selectAnoumousDetailByComment(userId, anoumousCommentId);
            if (detailAnoymous == null) {
                return FrontApiResponseEntity.SYS_ERR().build();
            }
            PageHelper.startPage(1,10);
            List<DetailAnoymousComment> detailAnoymousComments = anoymousCommentMapper.selectDetailByAnoymousId(userId, detailAnoymous.getAnoymousId());
            PageInfo<DetailAnoymousComment > detailAnoymousCommentPageInfo = new PageInfo<>(detailAnoymousComments);
            return FrontApiResponseEntity.SUCC().data("anoymous", detailAnoymous).data("comment", detailAnoymousCommentPageInfo).build();
        }
    }

    @RequestMapping("/listUsersAnoymous")
    @ResponseBody
    PageInfo<HomeAnoymous> listUsersAnoymous(Long userId, Long authorId, Integer pageNum, Integer pageSize) {
        if (StringUtils.isBlank(userId, authorId, pageNum, pageSize)) {
            return null;
        }else {
            PageHelper.startPage(pageNum, pageSize);
            List<HomeAnoymous> homeAnoymousList = anoymousMapper.selectHomeAnoumousByAuthorId(userId, authorId);
            PageInfo<HomeAnoymous> homeAnoymousPageInfo = new PageInfo<>(homeAnoymousList);
            return homeAnoymousPageInfo;
        }
    }

    @RequestMapping("/listUsersFavoriteAnoymous")
    @ResponseBody
    PageInfo<HomeAnoymous> listUsersFavoriteAnoymous(Long userId, Integer pageNum, Integer pageSize) {
        if (StringUtils.isBlank(userId, pageNum, pageSize)) {
            return null;
        }else {
            PageHelper.startPage(pageNum, pageSize);
            List<HomeAnoymous> homeAnoymousList = anoymousMapper.selectUserFavoriteAnoymous(userId);
            PageInfo<HomeAnoymous> homeAnoymousPageInfo = new PageInfo<>(homeAnoymousList);
            return homeAnoymousPageInfo;
        }
    }

    @RequestMapping("/createAnoymous")
    @ResponseBody
    String createAnoymous(@RequestBody Anoymous anoymous) {
        anoymous.setCreateTime(System.currentTimeMillis());
        anoymous.setUpdateTime(System.currentTimeMillis());
        anoymous.setAnoymousState(1);
        if (StringUtils.isBlank(anoymous.getAnoymousAuthorId())) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            Long anoymousId = anoymousService.createAnoymous(anoymous);
            if (anoymousId == 0L) {
                return FrontApiResponseEntity.SYS_ERR().build();
            } else {
                return FrontApiResponseEntity.SUCC().data("anoymousId", anoymousId).build();
            }
        }
    }

    @RequestMapping("/updateAnoymous")
    @ResponseBody
    String updateAnoymous(@RequestBody Anoymous anoymous) {
        anoymous.setCreateTime(System.currentTimeMillis());
        if (StringUtils.isBlank(anoymous.getAnoymousAuthorId())) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            if (anoymousMapper.updateByPrimaryKeySelective(anoymous) == 0) {
                return FrontApiResponseEntity.ERR(ResponseCode.SQLFAIl).build();
            } else {
                return FrontApiResponseEntity.SUCC().build();
            }
        }
    }

    @RequestMapping("/updateUserAttentionType")
    @ResponseBody
    String updateUserAttentionType (Long userId, Long discussId, Integer type) {
        boolean success = false;
        Integer sqlUpdateType = 1;
        if (StringUtils.isBlank(userId, discussId, type)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {

            MapUserAnoymouseKey key = new MapUserAnoymouseKey(discussId, userId);
            MapUserAnoymouse mapUserAnoymouse = mapUserAnoymouseMapper.selectByPrimaryKey(key);
            if (mapUserAnoymouse == null) {
                mapUserAnoymouse = new MapUserAnoymouse(discussId, userId, 0, 0);
                if (type == 5) {
                    mapUserAnoymouse.setUserApproveType(1);
                    mapUserAnoymouse.setUserFavoriteType(0);
                } else if (type == 6){
                    mapUserAnoymouse.setUserApproveType(0);
                    mapUserAnoymouse.setUserFavoriteType(1);
                } else {
                    return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
                }
                success = anoymousService.createUserAttentionType(mapUserAnoymouse);

            } else {
                //3为赞同4为收藏
                if (type==5 && mapUserAnoymouse.getUserApproveType()==0) {
                    mapUserAnoymouse.setUserApproveType(1);
                } else if (type==5 && mapUserAnoymouse.getUserApproveType()==1) {
                    mapUserAnoymouse.setUserApproveType(0);
                    sqlUpdateType = 2;
                } else if (type==6 && mapUserAnoymouse.getUserFavoriteType()==0) {
                    mapUserAnoymouse.setUserFavoriteType(1);
                    sqlUpdateType = 3;
                } else if (type==6 && mapUserAnoymouse.getUserFavoriteType()==1) {
                    mapUserAnoymouse.setUserFavoriteType(0);
                    sqlUpdateType = 4;
                } else {
                    return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
                }
                success = anoymousService.updateUserAttentionType(mapUserAnoymouse, sqlUpdateType);
            }
        }
        if (success) {
            return FrontApiResponseEntity.SUCC().build();
        } else {
            return FrontApiResponseEntity.ERR(ResponseCode.SQLFAIl).build();
        }
    }
}
