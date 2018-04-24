package com.zwp.slcp.sqlserver.service;

import com.zwp.slcp.apicommon.entity.*;
import com.zwp.slcp.apicommon.response.FrontApiResponseEntity;
import com.zwp.slcp.sqlserver.mapper.MapUserMapper;
import com.zwp.slcp.sqlserver.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 * Created by ASUS on 2018/4/5.
 */

@Service
public class UserService {

    //必须加入注解，不然会发生空指针
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MapUserMapper mapUserMapper;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    @Transactional(timeout=36000,rollbackFor=Exception.class)
    public String createUserAttention(MapUserKey key){
        MapUser mapUser = new MapUser();
        logger.info(key.getUser2Id().toString());
        boolean success = false;
        //如果没有关联
        if (null == mapUserMapper.selectByPrimaryKey(key)) {
            mapUser = new MapUser(key.getUserId(), key.getUser2Id());
            User user = new User(key.getUserId());
            user.setUserAttentionNumber(1);
            User user2 = new User(key.getUser2Id());
            user2.setUserFollowNumber(1);
            try {
                if (mapUserMapper.insert(mapUser) == 0) {
                    throw new Exception("操作异常");
                }
                if (userMapper.updateNumber(user) == 0) {
                    throw new Exception("操作异常");
                }
                //测试事务回滚
//                int i = 1/0;
                if (userMapper.updateNumber(user2) == 0) {
                    throw new Exception("操作异常");
                }
                success = true;
            } catch (Exception e) {
                logger.info(e.toString());
                //执行事务回滚
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            } finally {
              return success ? FrontApiResponseEntity.SUCC().build():FrontApiResponseEntity.SYS_ERR().message("数据库操作异常").build();
            }

        } else {
            return FrontApiResponseEntity.SYS_ERR().build();
        }

    }

    @Transactional(timeout=36000,rollbackFor=Exception.class)
    public String removeUserAttention(MapUserKey key){
        MapUser mapUser = new MapUser();
        logger.info(key.getUser2Id().toString());
        boolean success = false;
        //如果没有关联
        if (null != mapUserMapper.selectByPrimaryKey(key)) {
            mapUser = new MapUser(key.getUserId(), key.getUser2Id());
            User user = new User(key.getUserId());
            user.setUserAttentionNumber(-1);
            User user2 = new User(key.getUser2Id());
            user2.setUserFollowNumber(-1);
            try {
                if (mapUserMapper.deleteByPrimaryKey(mapUser) == 0) {
                    throw new Exception("操作异常");
                }
                if (userMapper.updateNumber(user) == 0) {
                    throw new Exception("操作异常");
                }
                //测试事务回滚
//                int i = 1/0;

                if (userMapper.updateNumber(user2) == 0) {
                    throw new Exception("操作异常");
                }
                success = true;
            } catch (Exception e) {
                logger.info(e.toString());
                //执行事务回滚
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            } finally {
                return success ? FrontApiResponseEntity.SUCC().build():FrontApiResponseEntity.SYS_ERR().message("数据库操作异常").build();
            }

        } else {
            return FrontApiResponseEntity.SYS_ERR().build();
        }

    }


}
