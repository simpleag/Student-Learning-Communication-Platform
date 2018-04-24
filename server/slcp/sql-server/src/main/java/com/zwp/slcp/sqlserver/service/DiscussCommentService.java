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
public class DiscussCommentService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private InfoMapper infoMapper;
    @Autowired
    private MapUserDiscusscommentMapper mapUserDiscusscommentMapper;
    @Autowired
    private DiscussCommentMapper discussCommentMapper;
    @Autowired
    private DiscussMapper discussMapper;

    @Transactional(timeout=36000,rollbackFor=Exception.class)
    public boolean createDiscussComment(DiscussComment discussComment) {
        boolean isSuccess = false;
        try {
            Long dicussCommentId = discussCommentMapper.insert(discussComment);
            if (dicussCommentId == 0L) {
                throw new Exception("数据库操作异常");
            }
            Discuss discuss = discussMapper.selectByPrimaryKey(discussComment.getDiscussId());
            if (discuss == null) {
                throw new Exception("数据库操作异常");
            }
            User commentAuthor = new User(discussComment.getDiscussCommentAuthorId());
            commentAuthor.setUserCommentNumber(1);
            if (userMapper.updateNumber(commentAuthor) == 0) {
                throw new Exception("数据库操作异常");
            }
            Info info = new Info(discuss.getDiscussAuthorId(), 8, 1, dicussCommentId);
            info.setIntoContent("您的文章"+discuss.getDiscussTitle()+"有一条评论");

            if (infoMapper.insert(info) == 0) {
                throw new Exception("数据库操作异常");
            }
            if (discussComment.getDiscussReplayUserid() != null) {
                Info replayInfo = new Info(discussComment.getDiscussReplayUserid(),8,1,dicussCommentId);
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
    public boolean createDiscussCommentApprove(MapUserDiscusscomment mapUserDiscusscomment) {
        boolean isSuccess = false;
        try {
            if (mapUserDiscusscommentMapper.insert(mapUserDiscusscomment) == 0) {
                throw new Exception("数据库操作异常");
            }
            DiscussComment discussComment = discussCommentMapper.selectByPrimaryKey(mapUserDiscusscomment.getDisscussCommentId());
            if (discussComment == null) {
                throw new Exception("数据库操作异常");
            }
            Info info = new Info(discussComment.getDiscussCommentAuthorId(), 11, 1, discussComment.getDiscussCommentId());
            info.setIntoContent("您的一条评论收到了赞");

            if (infoMapper.insert(info) == 0) {
                throw new Exception("数据库操作异常");
            }
            User user = new User(discussComment.getDiscussCommentAuthorId());
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
    public boolean updateDiscussCommentApprove(MapUserDiscusscomment mapUserDiscusscomment) {
        boolean isSuccess = false;
        try {
            if (mapUserDiscusscommentMapper.updateByPrimaryKeySelective(mapUserDiscusscomment) == 0) {
                throw new Exception("数据库操作异常");
            }
            DiscussComment discussComment = discussCommentMapper.selectByPrimaryKey(mapUserDiscusscomment.getDisscussCommentId());
            User user = new User(discussComment.getDiscussCommentAuthorId());
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
