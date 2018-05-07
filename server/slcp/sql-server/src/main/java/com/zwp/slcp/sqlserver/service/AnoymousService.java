package com.zwp.slcp.sqlserver.service;

import com.zwp.slcp.apicommon.entity.*;
import com.zwp.slcp.apicommon.response.FrontApiResponseEntity;
import com.zwp.slcp.apicommon.utils.StringUtils;
import com.zwp.slcp.sqlserver.mapper.AnoymousMapper;
import com.zwp.slcp.sqlserver.mapper.InfoMapper;
import com.zwp.slcp.sqlserver.mapper.MapUserAnoymouseMapper;
import com.zwp.slcp.sqlserver.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * Created by ASUS on 2018/4/22.
 */
@Service
public class AnoymousService {
    @Autowired
    private AnoymousMapper anoymousMapper;
    @Autowired
    private MapUserAnoymouseMapper mapUserAnoymouseMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private InfoMapper infoMapper;

    @Transactional(timeout=36000,rollbackFor=Exception.class)
    public Long createAnoymous(Anoymous anoymous) {
        Long anoymousId = 0L;
        try {
            anoymousId = anoymousMapper.insert(anoymous);
            if (anoymousId == 0) {
                throw new Exception("操作异常");
            }
            User user = new User(anoymous.getAnoymousAuthorId());
            user.setUserAnoymouseNumber(1);
            if (userMapper.updateNumber(user) == 0) {
                throw new Exception("操作异常");
            }
        } catch (Exception e) {
            //执行事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new Exception("操作异常");
        } finally {
            return anoymousId;
        }
    }

    @Transactional(timeout=36000,rollbackFor=Exception.class)
    public boolean updateUserAttentionType(MapUserAnoymouse mapUserAnoymouse, Integer sqlUpdateType) {
        boolean success = false;
        try {
            MapUserAnoymouseKey key = new MapUserAnoymouseKey(mapUserAnoymouse.getAnoymouseId(), mapUserAnoymouse.getUserId());
            User user = new User(mapUserAnoymouse.getUserId());
            Info info = null;
            Anoymous anoymous = anoymousMapper.selectByPrimaryKey(mapUserAnoymouse.getAnoymouseId());
            if (StringUtils.isBlank(anoymous.getAnoymousId(), anoymous.getAnoymousAuthorId())) {
                throw new Exception("操作异常");
            }
            info = new Info(anoymous.getAnoymousAuthorId(), sqlUpdateType, 5, anoymous.getAnoymousId());
            info.setCreateTime(System.currentTimeMillis());
            if (sqlUpdateType == 1) {
                user.setUserApproveNumber(1);
                info.setInfoType(5);
                info.setIntoContent("您的"+anoymous.getAnoymousTitle()+"匿名讨论收到了一条赞");
            } else if (sqlUpdateType == 2) {
                user.setUserApproveNumber(-1);
            } else if (sqlUpdateType == 3) {
                user.setUserFavoriteNumber(1);
                info.setInfoType(6);
                info.setIntoContent("您的"+anoymous.getAnoymousTitle()+"匿名讨论被一名用户收藏");
            } else if (sqlUpdateType == 4) {
                user.setUserFavoriteNumber(-1);
            }
            if (infoMapper.insert(info) == 0) {
                throw new Exception("操作异常");
            }
            if (userMapper.updateNumber(user) == 0) {
                throw new Exception("操作异常");
            }
            if (mapUserAnoymouseMapper.updateByPrimaryKeySelective(mapUserAnoymouse) == 0) {
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
    public boolean createUserAttentionType(MapUserAnoymouse mapUserAnoymouse) {
        boolean success = false;
        try {

            User user = new User(mapUserAnoymouse.getUserId());
            user.setUserFavoriteNumber(mapUserAnoymouse.getUserFavoriteType());
            user.setUserApproveNumber(mapUserAnoymouse.getUserApproveType());
            Anoymous anoymous = anoymousMapper.selectByPrimaryKey(mapUserAnoymouse.getAnoymouseId());
            if (StringUtils.isBlank(anoymous.getAnoymousId(), anoymous.getAnoymousAuthorId())) {
                throw new Exception("操作异常");
            }
            Info info = new Info(anoymous.getAnoymousAuthorId(), 0, 1, anoymous.getAnoymousId());
            info.setCreateTime(System.currentTimeMillis());
            if (mapUserAnoymouse.getUserFavoriteType() == 1) {
                info.setInfoType(5);
                info.setIntoContent("您的"+anoymous.getAnoymousTitle()+"匿名讨论收到了一条赞");
            } else if (mapUserAnoymouse.getUserApproveType() == 1) {
                info.setInfoType(6);
                info.setIntoContent("您的"+anoymous.getAnoymousTitle()+"匿名讨论被一名用户收藏");
            } else {
                throw new Exception("操作异常");
            }
            if (infoMapper.insert(info) == 0) {
                throw new Exception("操作异常");
            }
            if (userMapper.updateNumber(user) == 0) {
                throw new Exception("操作异常");
            }
            if (mapUserAnoymouseMapper.insert(mapUserAnoymouse) == 0) {
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
