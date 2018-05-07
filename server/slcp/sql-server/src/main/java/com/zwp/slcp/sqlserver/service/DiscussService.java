package com.zwp.slcp.sqlserver.service;

import com.zwp.slcp.apicommon.entity.*;
import com.zwp.slcp.apicommon.response.FrontApiResponseEntity;
import com.zwp.slcp.apicommon.utils.StringUtils;
import com.zwp.slcp.sqlserver.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * Created by ASUS on 2018/4/22.
 */
@Service
public class DiscussService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DiscussMapper discussMapper;
    @Autowired
    private MapUserDiscussMapper mapUserDiscussMapper;
    @Autowired
    private InfoMapper infoMapper;

    @Transactional(timeout=36000,rollbackFor=Exception.class)
    public Long createDiscuss(Discuss discuss) {
        Long discussId = 0L;
        try {
            discussId = discussMapper.insert(discuss);
            if (discussId == 0L) {
                throw new Exception("操作异常");
            }
            discussId = discuss.getDiscussId();
            User user = new User(discuss.getDiscussAuthorId());
            user.setUserDiscussNumber(1);
            Integer result = userMapper.updateNumber(user);
            if (result == 0) {
                throw new Exception("操作异常");
            }

        } catch (Exception e) {
            //执行事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            discussId = 0L;
            throw new Exception("操作异常");
        } finally {
            return discussId;
        }
    }

    @Transactional(timeout=36000,rollbackFor=Exception.class)
    public boolean updateUserAttentionType(MapUserDiscuss mapUserDiscuss, Integer sqlUpdateType) {
        boolean success = false;
        try {
            MapUserDiscussKey key = new MapUserDiscussKey(mapUserDiscuss.getDiscussId(), mapUserDiscuss.getUserId());
            User user = new User(mapUserDiscuss.getUserId());
            Info info = null;
            Discuss discuss = discussMapper.selectByPrimaryKey(mapUserDiscuss.getDiscussId());
            if (StringUtils.isBlank(discuss.getDiscussId(), discuss.getDiscussAuthorId())) {
                throw new Exception("操作异常");
            }
            info = new Info(discuss.getDiscussAuthorId(), sqlUpdateType, 1, discuss.getDiscussId());
            info.setCreateTime(System.currentTimeMillis());
            if (sqlUpdateType == 1) {
                user.setUserApproveNumber(1);
                info.setInfoType(3);
                info.setIntoContent("您的"+discuss.getDiscussTitle()+"讨论收到了一条赞");
            } else if (sqlUpdateType == 2) {
                user.setUserApproveNumber(-1);
            } else if (sqlUpdateType == 3) {
                user.setUserFavoriteNumber(1);
                info.setInfoType(4);
                info.setIntoContent("您的"+discuss.getDiscussTitle()+"讨论被一名用户收藏");
            } else if (sqlUpdateType == 4) {
                user.setUserFavoriteNumber(-1);
            }
            if (infoMapper.insert(info) == 0) {
                throw new Exception("操作异常");
            }
            if (userMapper.updateNumber(user) == 0) {
                throw new Exception("操作异常");
            }
            if (mapUserDiscussMapper.updateByPrimaryKey(mapUserDiscuss) == 0) {
                throw new Exception("操作异常");
            }
            success = true;
        } catch (Exception e) {
            //执行事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } finally {
            return success;
        }
    }

    @Transactional(timeout=36000,rollbackFor=Exception.class)
    public boolean createUserAttentionType(MapUserDiscuss mapUserDiscuss) {
        boolean success = false;
        try {

            User user = new User(mapUserDiscuss.getUserId());
            user.setUserFavoriteNumber(mapUserDiscuss.getUserFavoriteType());
            user.setUserApproveNumber(mapUserDiscuss.getUserApproveType());
            Discuss discuss = discussMapper.selectByPrimaryKey(mapUserDiscuss.getDiscussId());
            if (StringUtils.isBlank(discuss.getDiscussId(), discuss.getDiscussAuthorId())) {
                throw new Exception("操作异常");
            }
            Info info = new Info(discuss.getDiscussAuthorId(), 1, 1, discuss.getDiscussId());
            info.setCreateTime(System.currentTimeMillis());
            if (mapUserDiscuss.getUserFavoriteType() == 1) {
                info.setInfoType(3);
                info.setIntoContent("您的"+discuss.getDiscussTitle()+"讨论收到了一条赞");
            } else if (mapUserDiscuss.getUserApproveType() == 1) {
                info.setInfoType(4);
                info.setIntoContent("您的"+discuss.getDiscussTitle()+"讨论被一名用户收藏");
            } else {
                throw new Exception("操作异常");
            }
            if (infoMapper.insert(info) == 0) {
                throw new Exception("操作异常");
            }
            if (userMapper.updateNumber(user) == 0) {
                throw new Exception("操作异常");
            }
            if (mapUserDiscussMapper.insert(mapUserDiscuss) == 0) {
                throw new Exception("操作异常");
            }
            success = true;
        } catch (Exception e) {
            //执行事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } finally {
            return success;
        }
    }
}
