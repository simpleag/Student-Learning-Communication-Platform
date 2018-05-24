package com.zwp.slcp.sqlserver.service;


import com.zwp.slcp.apicommon.entity.*;
import com.zwp.slcp.sqlserver.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * Created by ASUS on 2018/4/22.
 */
@Service
public class ArticleCommentService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleCommentMapper articleCommentMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MapUserArticlecommentMapper mapUserArticlecommentMapper;
    @Autowired
    private InfoMapper infoMapper;

    @Transactional(timeout=36000,rollbackFor=Exception.class)
    public Long createArticleComment(ArticleComment articleComment) {
        Long articleCommentId = 0L;
        try {
            Article article = articleMapper.selectByPrimaryKey(articleComment.getArticleId());
            if (article == null) {
                throw new Exception("数据库操作异常");
            }
            article.setArticleCommentNumber(article.getArticleCommentNumber()+1);
            article.setUpdateTime(System.currentTimeMillis());
            if (articleMapper.updateByPrimaryKeySelective(article) == 0) {
                throw new Exception("数据库操作异常");
            }
            articleComment.setArticleListNumber(article.getArticleCommentNumber());
            articleCommentId = articleCommentMapper.insert(articleComment);
            if (articleCommentId == 0L) {
                throw new Exception("数据库操作异常");
            }
            articleCommentId = articleComment.getArticleCommentId();
            User commentAuthor = new User(articleComment.getArticleCommentAuthorId());
            commentAuthor.setUserCommentNumber(1);
            if (userMapper.updateNumber(commentAuthor) == 0) {
                throw new Exception("数据库操作异常");
            }
            Info info = new Info(article.getArticleAuthorId(), 7, 1, article.getArticleId());
            info.setIntoContent("您的文章"+article.getArticleTitle()+"有一条评论");
            info.setCreateTime(System.currentTimeMillis());
            if (infoMapper.insert(info) == 0) {
                throw new Exception("数据库操作异常");
            }
            if (articleComment.getArticleReplayUserid() != null && articleComment.getArticleReplayUserid() != 0) {
                Info replayInfo = new Info(articleComment.getArticleReplayUserid(),7,1,article.getArticleId());
                replayInfo.setIntoContent("有人回复了您的一条评论");
                replayInfo.setCreateTime(System.currentTimeMillis());
                if (infoMapper.insert(replayInfo) == 0) {
                    throw new Exception("数据库操作异常");
                }
            }

        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        } finally {
            return articleCommentId;
        }
    }

    @Transactional(timeout=36000,rollbackFor=Exception.class)
    public boolean createArticleCommentApprove(MapUserArticlecomment mapUserArticlecomment) {
        boolean isSuccess = false;
        try {
            if (mapUserArticlecommentMapper.insert(mapUserArticlecomment) == 0) {
                throw new Exception("数据库操作异常");
            }
            ArticleComment articleComment = articleCommentMapper.selectByPrimaryKey(mapUserArticlecomment.getArticleCommentId());
            Article article = articleMapper.selectByPrimaryKey(articleComment.getArticleId());
            if (articleComment==null || article==null) {
                throw new Exception("数据库操作异常");
            }
            User targetUser = new User(articleComment.getArticleCommentAuthorId());
            Info info = new Info(articleComment.getArticleCommentAuthorId(), 10, 1, article.getArticleId());
            info.setIntoContent("您的一条文章评论收到了赞");
            info.setCreateTime(System.currentTimeMillis());
            targetUser.setUserGetApproveNumber(1);

            if (infoMapper.insert(info) == 0) {
                throw new Exception("数据库操作异常");
            }
            User user = new User(articleComment.getArticleCommentAuthorId());
            user.setUserApproveNumber(1);
            if (userMapper.updateNumber(user) == 0) {
                throw new Exception("数据库操作异常");
            }
            if (userMapper.updateNumber(targetUser) == 0) {
                throw new Exception("操作异常");
            }
            isSuccess = true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } finally {
            return isSuccess;
        }
    }

    @Transactional(timeout=36000,rollbackFor=Exception.class)
    public boolean updateArticleCommentApprove(MapUserArticlecomment mapUserArticlecomment) {
        boolean isSuccess = false;
        try {
            if (mapUserArticlecommentMapper.updateByPrimaryKeySelective(mapUserArticlecomment) == 0) {
                throw new Exception("数据库操作异常");
            }
            ArticleComment articleComment = articleCommentMapper.selectByPrimaryKey(mapUserArticlecomment.getArticleCommentId());
            User user = new User(articleComment.getArticleCommentAuthorId());
            user.setUserApproveNumber(-1);
            if (userMapper.updateNumber(user) == 0) {
                throw new Exception("数据库操作异常");
            }
            isSuccess = true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } finally {
            return isSuccess;
        }
    }
}
