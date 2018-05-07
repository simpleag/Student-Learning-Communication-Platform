package com.zwp.slcp.sqlserver.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zwp.slcp.apicommon.entity.*;
import com.zwp.slcp.apicommon.response.FrontApiResponseEntity;
import com.zwp.slcp.apicommon.response.ResponseCode;
import com.zwp.slcp.apicommon.utils.StringUtils;
import com.zwp.slcp.sqlserver.mapper.DiscussCommentMapper;
import com.zwp.slcp.sqlserver.mapper.DiscussMapper;
import com.zwp.slcp.sqlserver.mapper.MapUserDiscussMapper;
import com.zwp.slcp.sqlserver.service.DiscussService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ASUS on 2018/4/22.
 */
@RestController
@CrossOrigin
@RequestMapping("/discuss")
public class DiscussController {
    @Autowired
    private DiscussMapper discussMapper;
    @Autowired
    private DiscussService discussService;
    @Autowired
    private MapUserDiscussMapper mapUserDiscussMapper;
    @Autowired
    private DiscussCommentMapper discussCommentMapper;

    @RequestMapping("/listHomeDiscussByTime")
    @ResponseBody
    PageInfo<HomeDiscuss> listHomeDiscussByTime(Long userId, Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, pageNumber, pageSize)) {
            return null;
        } else {
            PageHelper.startPage(pageNumber, pageSize);
            List<HomeDiscuss> homeDiscussList = discussMapper.selectHomeDiscussOrderByTime(userId);
            PageInfo<HomeDiscuss> homeDiscussPageInfo = new PageInfo<>(homeDiscussList);
            return homeDiscussPageInfo;
        }

    }

    @RequestMapping("/listHomeDiscussByHot")
    @ResponseBody
    PageInfo<HomeDiscuss> listHomeDiscussByHot(Long userId, Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, pageNumber, pageSize)) {
            return null;
        } else {
            PageHelper.startPage(pageNumber, pageSize);
            List<HomeDiscuss> homeDiscussList = discussMapper.selectHomeDiscussOrderByHot(userId);
            PageInfo<HomeDiscuss> homeDiscussPageInfo = new PageInfo<>(homeDiscussList);
            return homeDiscussPageInfo;
        }

    }

    @RequestMapping("/listTagHomeDiscussByHot")
    @ResponseBody
    PageInfo<HomeDiscuss> listTagHomeDiscussByHot(Long userId, Integer tagId, Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, tagId, pageNumber, pageSize)) {
            return null;
        } else {
            PageHelper.startPage(pageNumber, pageSize);
            List<HomeDiscuss> homeDiscussList = discussMapper.selectTagsDiscussOrderByHot(userId, tagId);
            PageInfo<HomeDiscuss> homeDiscussPageInfo = new PageInfo<>(homeDiscussList);
            return homeDiscussPageInfo;
        }

    }

    @RequestMapping("/listTagHomeDiscussByTime")
    @ResponseBody
    PageInfo<HomeDiscuss> listTagHomeDiscussByTime(Long userId, Integer tagId, Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, tagId, pageNumber, pageSize)) {
            return null;
        } else {
            PageHelper.startPage(pageNumber, pageSize);
            List<HomeDiscuss> homeDiscussList = discussMapper.selectTagsDiscussOrderByTime(userId, tagId);
            PageInfo<HomeDiscuss> homeDiscussPageInfo = new PageInfo<>(homeDiscussList);
            return homeDiscussPageInfo;
        }

    }

    @RequestMapping("/findOneById")
    @ResponseBody
    DetailDiscuss findOneById(Long userId, Long discussId) {
        if (StringUtils.isBlank(userId, discussId)) {
            return null;
        } else {
            return discussMapper.selectDiscussDetailById(userId, discussId);
        }
    }
    @RequestMapping("/findOneWithCommentById")
    @ResponseBody
    String findOneWithCommentById(Long userId, Long discussId) {
        if (StringUtils.isBlank(userId, discussId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            DetailDiscuss detailDiscuss = discussMapper.selectDiscussDetailById(userId, discussId);
            PageHelper.startPage(1,10);
            List<DetailDiscussComment> detailDiscussCommentList = discussCommentMapper.selectDetailByDiscussId(userId, discussId);
            PageInfo<DetailDiscussComment > detailDiscussCommentPageInfo = new PageInfo<>(detailDiscussCommentList);
//            System.out.println(detailDiscussCommentPageInfo.getSize());
            return FrontApiResponseEntity.SUCC().data("discuss", detailDiscuss).data("comment", detailDiscussCommentPageInfo).build();
        }
    }

    @RequestMapping("/findOneWithCommentByCommentId")
    @ResponseBody
    String findOneByCommentId(Long userId, Long discussCommentId) {
        if (StringUtils.isBlank(userId, discussCommentId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            DetailDiscuss detailDiscuss = discussMapper.selectDiscussDetailByComment(userId, discussCommentId);
            if (detailDiscuss == null) {
                FrontApiResponseEntity.SYS_ERR().build();
            }
            PageHelper.startPage(1,10);
            List<DetailDiscussComment> detailDiscussCommentList = discussCommentMapper.selectDetailByDiscussId(userId, detailDiscuss.getDiscussId());
            PageInfo<DetailDiscussComment > detailDiscussCommentPageInfo = new PageInfo<>(detailDiscussCommentList);
            return FrontApiResponseEntity.SUCC().data("discuss", detailDiscuss).data("comment", detailDiscussCommentPageInfo).build();
        }
    }

    @RequestMapping("/listUsersDiscuss")
    @ResponseBody
    PageInfo<HomeDiscuss> listUsersDiscuss(Long userId, Long authorId, Integer pageNum, Integer pageSize) {
        if (StringUtils.isBlank(userId, authorId, pageNum, pageSize)) {
            return null;
        }else {
            PageHelper.startPage(pageNum, pageSize);
            List<HomeDiscuss> homeDiscussList = discussMapper.selectHomeDiscussByAuthorId(userId, authorId);
            PageInfo<HomeDiscuss> homeDiscussPageInfo = new PageInfo<>(homeDiscussList);
            return homeDiscussPageInfo;
        }
    }

    @RequestMapping("/listUsersFavoriteDiscuss")
    @ResponseBody
    PageInfo<HomeDiscuss> listUsersFavoriteDiscuss(Long userId, Integer pageNum, Integer pageSize) {
        if (StringUtils.isBlank(userId, pageNum, pageSize)) {
            return null;
        }else {
            PageHelper.startPage(pageNum, pageSize);
            List<HomeDiscuss> homeDiscussList = discussMapper.selectUserFavoriteDiscuss(userId);
            PageInfo<HomeDiscuss> homeDiscussPageInfo = new PageInfo<>(homeDiscussList);
            return homeDiscussPageInfo;
        }
    }

    @RequestMapping("/createDiscuss")
    @ResponseBody
    String createDiscuss(@RequestBody Discuss discuss) {
        discuss.setCreateTime(System.currentTimeMillis());
        discuss.setUpdateTime(System.currentTimeMillis());
        discuss.setDiscussState(1);
        discuss.setDiscussCommentNumber(0);
        if (StringUtils.isBlank(discuss.getDiscussAuthorId())) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            Long discussId = discussService.createDiscuss(discuss);
            System.out.println("discussId"+ discussId);
            if (discussId==null || discussId==0L ) {
                return FrontApiResponseEntity.SYS_ERR().build();
            } else {
                return FrontApiResponseEntity.SUCC().data("discussId", discussId).build();
            }
        }
    }

    @RequestMapping("/updateDiscuss")
    @ResponseBody
    String updateDiscuss(@RequestBody Discuss discuss) {
        discuss.setCreateTime(System.currentTimeMillis());
        if (StringUtils.isBlank(discuss.getDiscussAuthorId())) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            if (discussMapper.updateByPrimaryKeySelective(discuss) == 0) {
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
//        System.out.println("sql"+userId+"+"+discussId+"+"+type);
        Integer sqlUpdateType = 1;
        if (StringUtils.isBlank(userId, discussId, type)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {

            MapUserDiscussKey key = new MapUserDiscussKey(discussId, userId);
            MapUserDiscuss mapUserDiscuss = mapUserDiscussMapper.selectByPrimaryKey(key);
            if (mapUserDiscuss == null) {
                mapUserDiscuss = new MapUserDiscuss(discussId, userId, 0, 0);
                if (type == 3) {
                    mapUserDiscuss.setUserApproveType(1);
                    mapUserDiscuss.setUserFavoriteType(0);
                } else if (type == 4){
                    mapUserDiscuss.setUserApproveType(0);
                    mapUserDiscuss.setUserFavoriteType(1);
                } else {
                    return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
                }
                success = discussService.createUserAttentionType(mapUserDiscuss);

            } else {
                //3为赞同4为收藏
                if (type==3 && mapUserDiscuss.getUserApproveType()==0) {
                    mapUserDiscuss.setUserApproveType(1);
                } else if (type==3 && mapUserDiscuss.getUserApproveType()==1) {
                    mapUserDiscuss.setUserApproveType(0);
                    sqlUpdateType = 2;
                } else if (type==4 && mapUserDiscuss.getUserFavoriteType()==0) {
                    mapUserDiscuss.setUserFavoriteType(1);
                    sqlUpdateType = 3;
                } else if (type==4 && mapUserDiscuss.getUserFavoriteType()==1) {
                    mapUserDiscuss.setUserFavoriteType(0);
                    sqlUpdateType = 4;
                } else {
                    return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
                }
                success = discussService.updateUserAttentionType(mapUserDiscuss, sqlUpdateType);
            }
        }
        if (success) {
            return FrontApiResponseEntity.SUCC().build();
        } else {
            return FrontApiResponseEntity.ERR(ResponseCode.SQLFAIl).build();
        }
    }
}
