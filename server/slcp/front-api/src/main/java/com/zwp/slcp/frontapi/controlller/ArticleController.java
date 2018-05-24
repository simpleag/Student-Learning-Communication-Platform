package com.zwp.slcp.frontapi.controlller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.zwp.slcp.apicommon.constant.MyConstant;
import com.zwp.slcp.apicommon.entity.*;
import com.zwp.slcp.apicommon.response.FrontApiResponseEntity;
import com.zwp.slcp.apicommon.response.ResponseCode;
import com.zwp.slcp.apicommon.utils.StringUtils;
import com.zwp.slcp.frontapi.service.ActiveService;
import com.zwp.slcp.frontapi.service.ArticleCommentService;
import com.zwp.slcp.frontapi.service.ArticleService;
import com.zwp.slcp.frontapi.service.UploadFileToOssService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Created by ASUS on 2018/4/26.
 */
@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleCommentService articleCommentService;
    @Autowired
    private ActiveService activeService;
    @Autowired
    private UploadFileToOssService uploadFileToOssService;

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);


    @RequestMapping(value = "/uploadPicFile")
    @ResponseBody
    String uploadPicFile(@RequestParam("file") MultipartFile file) {

        logger.debug("传入的文件参数：{}", JSON.toJSONString(file, true));

        System.out.println("File: " + file == null);
        System.out.println(file.getOriginalFilename());
        String result = FrontApiResponseEntity.SYS_ERR().build();
        try {
            if (file != null) {
                // 取得当前上传文件的文件名称
                String fileName = file.getOriginalFilename();
                //image/jpeg  image/png
                String fileType = file.getContentType();
                if (!fileType.equals("image/jpeg") && !fileType.equals("image/png")) {
                    return FrontApiResponseEntity.SYS_ERR().message("图片类型只允许JPG和PNG").build();
                }
//                if (file.getContentType())
                // 如果名称不为空,说明该文件存在，否则说明该文件不存在
                if (fileName.trim() != "") {
                    File newFile = new File(fileName);
                    FileOutputStream outStream = new FileOutputStream(newFile); // 文件输出流用于将数据写入文件
                    outStream.write(file.getBytes());
                    outStream.close(); // 关闭文件输出流
                    file.transferTo(newFile);
                    // 上传到阿里云
//                            files.add(uploadFileToOssService.upload(newFile));
                    String path = uploadFileToOssService.upload(newFile);
                    System.out.println(path);
                    newFile.delete();
                    result = FrontApiResponseEntity.SUCC().data("imgUrl", path).build();
                }
            }


        } catch (Exception e) {
            result = FrontApiResponseEntity.SYS_ERR().message(e.getMessage()).build();
        } finally {
            return result;
        }
    }

    @RequestMapping(value = "/listOrderByTime")
    @ResponseBody
    String listOrderByTime(Long userId, Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, pageNumber, pageSize)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            PageInfo<HomeArticle> homeArticlePageInfo = articleService.listHomeArticlesByTime(userId, pageNumber, pageSize);
            if (homeArticlePageInfo == null) {
                return FrontApiResponseEntity.SYS_ERR().build();
            } else {
                return FrontApiResponseEntity.SUCC().data("articleList", homeArticlePageInfo).build();
            }
        }
    }

    @RequestMapping(value = "/listOrderByHot")
    @ResponseBody
    String listOrderByHot(Long userId, Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, pageNumber, pageSize)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            PageInfo<HomeArticle> homeArticlePageInfo = articleService.listHomeArticlesByHot(userId, pageNumber, pageSize);
            if (homeArticlePageInfo == null) {
                return FrontApiResponseEntity.SYS_ERR().build();
            } else {
                return FrontApiResponseEntity.SUCC().data("articleList", homeArticlePageInfo).build();
            }
        }
    }

    @RequestMapping(value = "/listTagsArticleOrderByHot")
    @ResponseBody
    String listTagsArticleOrderByHot(Long userId, Integer tagId, Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, tagId, pageNumber, pageSize)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            PageInfo<HomeArticle> homeArticlePageInfo = articleService.listTagsHomeArticlesByHot(userId, tagId, pageNumber, pageSize);
            if (homeArticlePageInfo == null) {
                return FrontApiResponseEntity.SYS_ERR().build();
            } else {
                return FrontApiResponseEntity.SUCC().data("articleList", homeArticlePageInfo).build();
            }
        }
    }

    @RequestMapping(value = "/listTagsArticleOrderByTime")
    @ResponseBody
    String listTagsArticleOrderByTime(Long userId, Integer tagId, Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, tagId, pageNumber, pageSize)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            PageInfo<HomeArticle> homeArticlePageInfo = articleService.listTagsHomeArticlesByTime(userId, tagId, pageNumber, pageSize);
            if (homeArticlePageInfo == null) {
                return FrontApiResponseEntity.SYS_ERR().build();
            } else {
                return FrontApiResponseEntity.SUCC().data("articleList", homeArticlePageInfo).build();
            }
        }
    }

    @RequestMapping(value = "/detail")
    @ResponseBody
    String detail(Long userId, Long articleId) {
        if (StringUtils.isBlank(userId, articleId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            return articleService.findOneWithCommentById(userId, articleId);
        }
    }

    @RequestMapping(value = "/findByCommentId")
    @ResponseBody
    String findByCommentId(Long userId, Long commentId) {
        if (StringUtils.isBlank(userId, commentId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {

            return articleService.findOneByCommentId(userId, commentId);
        }
    }

    @RequestMapping(value = "/authors")
    @ResponseBody
    String authors(Long userId, Long authorId, Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, authorId, pageNumber, pageSize)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            PageInfo<HomeArticle> homeArticlePageInfo = articleService.listUsersArticles(userId, authorId, pageNumber, pageSize);
            if (homeArticlePageInfo == null) {
                return FrontApiResponseEntity.SYS_ERR().build();
            }
            return FrontApiResponseEntity.SUCC().data("articleList", homeArticlePageInfo).build();
        }
    }

    @RequestMapping(value = "/userFavoriteArticle")
    @ResponseBody
    String userFavoriteArticle(Long userId, Long targetUserId, Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId, targetUserId, pageNumber, pageSize)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            PageInfo<HomeArticle> homeArticlePageInfo = articleService.listUsersFavoriteArticles(targetUserId, pageNumber, pageSize);
            if (homeArticlePageInfo == null) {
                return FrontApiResponseEntity.SYS_ERR().build();
            }
            return FrontApiResponseEntity.SUCC().data("articleList", homeArticlePageInfo).build();
        }
    }

    @RequestMapping(value = "/createArticle")
    @ResponseBody
    String createArticle(Long userId, String articleTiltle, String articleContent, Integer tagId, String articlePicUrl) {
        logger.info("startCreate");
        logger.info("userId: "+ userId+ "Title: "+ articleTiltle+" conetent: "+ articleContent+" tagId: "+ tagId+ "picUrl: "+ articlePicUrl);
        if (StringUtils.isBlank(userId, articleContent, articlePicUrl, articleTiltle, tagId, articleTiltle)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            Article article = new Article(userId, articleTiltle, articleContent, tagId, articlePicUrl);
            JSONObject jsonObject = JSON.parseObject(articleService.createArticle(article));
            logger.info(jsonObject.toJSONString());
            if (jsonObject.getString("code").equals("200")) {
                Long articleId = jsonObject.getLong("articleId");
                String content = "用户创建了" + articleTiltle + "文章";
                activeService.createActive(MyConstant.MONGODB_COLL_NAME, userId, articleId, 1, content);
            } else {
            }
            logger.info(jsonObject.toJSONString());
            return jsonObject.toJSONString();
        }
    }

    @RequestMapping(value = "/updateArticleState")
    @ResponseBody
    String updateArticleState(Long articleId, Integer state) {
        if (StringUtils.isBlank(articleId, state)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            Article article = new Article(articleId);
            article.setArticleState(state);
            return articleService.updateArticle(article);
        }
    }

    @RequestMapping(value = "/baseData")
    @ResponseBody
    String baseData(Long userId, Long articleId) {
        if (StringUtils.isBlank(articleId, userId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            DetailArticle detailArticle = articleService.findOneById(userId, articleId);
            if (detailArticle == null) {
                return FrontApiResponseEntity.SYS_ERR().build();
            }
            return FrontApiResponseEntity.SUCC().data("detail", detailArticle).build();
        }
    }

    @RequestMapping(value = "/userApprove")
    @ResponseBody
    String userApprove(Long userId, Long articleId) {
        if (StringUtils.isBlank(articleId, userId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            System.out.println("np");
            //添加用户对应的动态
            String content = "用户点赞了一篇文章";
            activeService.createActive(MyConstant.MONGODB_COLL_NAME, userId, articleId, 2, content);
            return articleService.updateUserAttentionType(userId, articleId, 1);
        }
    }

    @RequestMapping(value = "/userFavorite")
    @ResponseBody
    String userFavorite(Long userId, Long articleId) {
        if (StringUtils.isBlank(articleId, userId)) {
            return FrontApiResponseEntity.ERR(ResponseCode.PARAMERROR).build();
        } else {
            //添加用户对应的动态

            String content = "用户收藏了一篇文章";
            activeService.createActive(MyConstant.MONGODB_COLL_NAME, userId, articleId, 3, content);
            return articleService.updateUserAttentionType(userId, articleId, 2);

        }
    }


}
