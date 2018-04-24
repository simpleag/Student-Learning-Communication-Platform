package com.zwp.slcp.sqlserver.service;

import com.zwp.slcp.apicommon.entity.*;
import com.zwp.slcp.sqlserver.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * Created by ASUS on 2018/4/23.
 */
@Service
public class AnoymouseCommentService {
    @Autowired
    private AnoymousCommentMapper anoymousCommentMapper;
    @Autowired
    private MapUserAnoymousecommentMapper mapUserAnoymousecommentMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private InfoMapper infoMapper;
    @Autowired
    private AnoymousMapper anoymousMapper;

    @Transactional(timeout=36000,rollbackFor=Exception.class)
    public boolean createAnoymousComment(AnoymousComment anoymousComment) {
        boolean isSuccess = false;
        try {
            Long anoymousCommentId = anoymousCommentMapper.insert(anoymousComment);
            if (anoymousCommentId == 0L) {
                throw new Exception("数据库操作异常");
            }
            Anoymous anoymous = anoymousMapper.selectByPrimaryKey(anoymousComment.getAnoymousId());
            if (anoymous == null) {
                throw new Exception("数据库操作异常");
            }
            User commentAuthor = new User(anoymousComment.getAnoymousCommentAuthorId());
            commentAuthor.setUserCommentNumber(1);
            if (userMapper.updateNumber(commentAuthor) == 0) {
                throw new Exception("数据库操作异常");
            }
            Info info = new Info(anoymous.getAnoymousAuthorId(), 9, 1, anoymousCommentId);
            info.setIntoContent("您的文章"+anoymous.getAnoymousTitle()+"有一条评论");

            if (infoMapper.insert(info) == 0) {
                throw new Exception("数据库操作异常");
            }
            if (anoymousComment.getAnoymouseReplayUserid() != null) {
                Info replayInfo = new Info(anoymousComment.getAnoymouseReplayUserid(),9,1,anoymousCommentId);
                replayInfo.setIntoContent("有人回复了您的一条评论");
                if (infoMapper.insert(replayInfo) == 0) {
                    throw new Exception("数据库操作异常");
                }
            }
            isSuccess = true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } finally {
            return isSuccess;
        }
    }

    @Transactional(timeout=36000,rollbackFor=Exception.class)
    public boolean createAnoymousCommentApprove(MapUserAnoymousecomment mapUserAnoymousecomment) {
        boolean isSuccess = false;
        try {
            if (mapUserAnoymousecommentMapper.insert(mapUserAnoymousecomment) == 0) {
                throw new Exception("数据库操作异常");
            }
            AnoymousComment anoymousComment = anoymousCommentMapper.selectByPrimaryKey(mapUserAnoymousecomment.getAnoymouseCommentId());
            if (anoymousComment == null) {
                throw new Exception("数据库操作异常");
            }
            Info info = new Info(anoymousComment.getAnoymousCommentAuthorId(), 12, 1, anoymousComment.getAnoymousCommentId());
            info.setIntoContent("您的一条评论收到了赞");

            if (infoMapper.insert(info) == 0) {
                throw new Exception("数据库操作异常");
            }
            User user = new User(anoymousComment.getAnoymousCommentAuthorId());
            user.setUserApproveNumber(1);
            if (userMapper.updateNumber(user) == 0) {
                throw new Exception("数据库操作异常");
            }

        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } finally {
            return isSuccess;
        }
    }

    @Transactional(timeout=36000,rollbackFor=Exception.class)
    public boolean updateAnoymousCommentApprove(MapUserAnoymousecomment mapUserAnoymousecomment) {
        boolean isSuccess = false;
        try {
            if (mapUserAnoymousecommentMapper.updateByPrimaryKeySelective(mapUserAnoymousecomment) == 0) {
                throw new Exception("数据库操作异常");
            }
            AnoymousComment anoymousComment = anoymousCommentMapper.selectByPrimaryKey(mapUserAnoymousecomment.getAnoymouseCommentId());
            User user = new User(anoymousComment.getAnoymousCommentAuthorId());
            user.setUserApproveNumber(-1);
            if (userMapper.updateNumber(user) == 0) {
                throw new Exception("数据库操作异常");
            }

        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } finally {
            return isSuccess;
        }
    }
}
