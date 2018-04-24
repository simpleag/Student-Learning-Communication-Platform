package com.zwp.slcp.sqlserver.service;

import com.zwp.slcp.apicommon.entity.MapUserArticle;
import com.zwp.slcp.apicommon.entity.MapUserTag;
import com.zwp.slcp.apicommon.entity.User;
import com.zwp.slcp.sqlserver.mapper.MapUserTagMapper;
import com.zwp.slcp.sqlserver.mapper.TagMapper;
import com.zwp.slcp.sqlserver.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * Created by ASUS on 2018/4/22.
 */
@Service
public class TagService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private MapUserTagMapper mapUserTagMapper;

    @Transactional(timeout=36000,rollbackFor=Exception.class)
    public boolean updateUserAttentionTagType(MapUserTag mapUserTag) {
        boolean isSuccess = false;
        try {
            User user = new User(mapUserTag.getUserId());
            if (mapUserTag.getUserAttentionType() == 0) {
                user.setUserAttentionNumber(-1);
            } else {
                user.setUserAttentionNumber(1);
            }
            if (userMapper.updateNumber(user) == 0) {
                throw new Exception("数据库操作异常");
            }
            if (mapUserTagMapper.updateByPrimaryKeySelective(mapUserTag) == 0) {
                throw new Exception("数据库操作异常");
            }
            isSuccess = true;


        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

        } finally {
            return isSuccess;
        }

    }

    @Transactional(timeout=36000,rollbackFor=Exception.class)
    public boolean createUserAttentionTagType(MapUserTag mapUserTag) {
        boolean success = false;
        try {

            User user = new User(mapUserTag.getUserId());
            user.setUserAttentionNumber(1);
            if (userMapper.updateNumber(user) == 0) {
                throw new Exception("操作异常");
            }
            if (mapUserTagMapper.insert(mapUserTag) == 0) {
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
