package com.zwp.slcp.sqlserver.service;

import com.zwp.slcp.apicommon.entity.*;
import com.zwp.slcp.apicommon.response.FrontApiResponseEntity;
import com.zwp.slcp.apicommon.utils.StringUtils;
import com.zwp.slcp.sqlserver.mapper.ArticleMapper;
import com.zwp.slcp.sqlserver.mapper.InfoMapper;
import com.zwp.slcp.sqlserver.mapper.MapUserArticleMapper;
import com.zwp.slcp.sqlserver.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * Created by ASUS on 2018/4/20.
 */
@Service
public class ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MapUserArticleMapper mapUserArticleMapper;
    @Autowired
    private InfoMapper infoMapper;
    private static final Logger logger = LoggerFactory.getLogger(ArticleService.class);

    @Transactional(timeout=36000,rollbackFor=Exception.class)
    public String createArticle(Article article) {
        boolean success = false;
        try {
            if (articleMapper.insert(article) == 0) {
                throw new Exception("操作异常");
            }
            User user = new User(article.getArticleAuthorId());
            user.setUserArticleNumber(1);
            if (userMapper.updateNumber(user) == 0) {
                throw new Exception("操作异常");
            }
            success = true;
        } catch (Exception e) {
            //执行事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new Exception("操作异常");
        } finally {
            return success ? FrontApiResponseEntity.SUCC().build():FrontApiResponseEntity.SYS_ERR().message("数据库操作异常").build();
        }
    }

    @Transactional(timeout=36000,rollbackFor=Exception.class)
    public boolean updateUserAttentionType(MapUserArticle mapUserArticle, Integer sqlUpdateType) {
        boolean success = false;
        try {
            MapUserArticleKey key = new MapUserArticleKey(mapUserArticle.getArticleId(), mapUserArticle.getUserId());
            User user = new User(mapUserArticle.getUserId());
            Info info = null;
            Article article = articleMapper.selectByPrimaryKey(mapUserArticle.getArticleId());
            if (StringUtils.isBlank(article.getArticleId(), article.getArticleAuthorId())) {
                throw new Exception("操作异常");
            }
            info = new Info(article.getArticleAuthorId(), sqlUpdateType, 1, article.getArticleId());
            info.setCreateTime(System.currentTimeMillis());
            if (sqlUpdateType == 1) {
                user.setUserApproveNumber(1);
                info.setInfoType(1);
                info.setIntoContent("您的"+article.getArticleTitle()+"文章收到了一条赞");
            } else if (sqlUpdateType == 2) {
                user.setUserApproveNumber(-1);
            } else if (sqlUpdateType == 3) {
                user.setUserFavoriteNumber(1);
                info.setInfoType(2);
                info.setIntoContent("您的"+article.getArticleTitle()+"文章被一名用户收藏");
            } else if (sqlUpdateType == 4) {
                user.setUserFavoriteNumber(-1);
            }
            if (infoMapper.insert(info) == 0) {
                throw new Exception("操作异常");
            }
            if (userMapper.updateNumber(user) == 0) {
                throw new Exception("操作异常");
            }
            if (mapUserArticleMapper.updateByPrimaryKey(mapUserArticle) == 0) {
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
    public boolean createUserAttentionType(MapUserArticle mapUserArticle) {
        boolean success = false;
        try {

            User user = new User(mapUserArticle.getUserId());
            user.setUserFavoriteNumber(mapUserArticle.getUserFavoriteType());
            user.setUserApproveNumber(mapUserArticle.getUserApproveType());
            Article article = articleMapper.selectByPrimaryKey(mapUserArticle.getArticleId());
            if (StringUtils.isBlank(article.getArticleId(), article.getArticleAuthorId())) {
                throw new Exception("操作异常");
            }
            Info info = new Info(article.getArticleAuthorId(), 1, 1, article.getArticleId());
            info.setCreateTime(System.currentTimeMillis());
            if (mapUserArticle.getUserFavoriteType() == 1) {
                info.setIntoContent("您的"+article.getArticleTitle()+"文章收到了一条赞");
            } else if (mapUserArticle.getUserApproveType() == 1) {
                info.setInfoType(2);
                info.setIntoContent("您的"+article.getArticleTitle()+"文章被一名用户收藏");
            } else {
                throw new Exception("操作异常");
            }
            if (infoMapper.insert(info) == 0) {
                throw new Exception("操作异常");
            }
            if (userMapper.updateNumber(user) == 0) {
                throw new Exception("操作异常");
            }
            if (mapUserArticleMapper.insert(mapUserArticle) == 0) {
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
