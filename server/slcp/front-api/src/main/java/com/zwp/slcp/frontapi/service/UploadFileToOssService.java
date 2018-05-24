package com.zwp.slcp.frontapi.service;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import com.zwp.slcp.frontapi.controlller.ArticleController;
import org.apache.poi.ss.usermodel.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by ASUS on 2018/4/30.
 */
@Service
public class UploadFileToOssService {

    @Value("${slcp.upload.rootpath}")
    String uploadRootpath;

    @Value("${slcp.upload.oss.accesskeyid}")
    String accesskeyid;

    @Value("${slcp.upload.oss.accesskeysecret}")
    String accesskeysecret;

    @Value("${slcp.upload.oss.endpoint}")
    String endpoint;

    @Value("${slcp.upload.oss.bucket}")
    String bucket;

    private final Logger logger = LoggerFactory.getLogger(UploadFileToOssService.class);

    public  String upload(File file) {
        if (file == null) {
            return null;
        }
        // 创建OSS客户端
        OSSClient ossClient = new OSSClient(endpoint, accesskeyid, accesskeysecret);
        try {
            // 判断文件容器是否存在，不存在则创建
            if (!ossClient.doesBucketExist(bucket)) {
                ossClient.createBucket(bucket);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucket);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(createBucketRequest);
            }
            // 创建文件路径

            String fileUrl = bucket+ "/"+ UUID.randomUUID().toString().replace("-","")+"-"+file.getName();
            String path = uploadRootpath+ "/"+ fileUrl;
            // 上传文件
            PutObjectResult result = ossClient.putObject(new PutObjectRequest(bucket, fileUrl, file));
            if (null != result) {
                return path;
            }
        } catch (OSSException oe) {
//            log.error(oe.getMessage());
        } catch (ClientException ce) {
//            log.error(ce.getMessage());
        } finally {
            // 关闭OSS服务，一定要关闭
            ossClient.shutdown();
        }
        return null;
    }
//    public String upload(MultipartFile upfile) {
//        if(upfile==null|| upfile.isEmpty()) {
//            return null;
//        }
//
//        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
//        String key = "adminupload/" + df.format(new Date()) + "/"
//                + UUID.randomUUID().toString().replaceAll("-", "")
//                +upfile.getOriginalFilename();
//        //  + upfile.getOriginalFilename().substring(upfile.getOriginalFilename().indexOf(".")+1);
//
//        return upload(key, upfile);
//    }
//
//    public String upload(String key,MultipartFile upfile){
//        boolean success=true;
//        String msg="";
//
//        OSSClient ossClient = new OSSClient(endpoint, accesskeyid, accesskeysecret);
//        // 创建上传Object的Metadata
//        ObjectMetadata meta = new ObjectMetadata();
//        // 设置上传文件长度
//        meta.setContentLength(upfile.getSize());
//        meta.setContentType(upfile.getContentType());
//        // 上传文件
//        try {
//            ossClient.putObject(
//                    bucket,
//                    key,
//                    upfile.getInputStream(),
//                    meta
//            );
//        }
//        catch (Exception e){
//            e.printStackTrace();
//            success=false;
//            msg=e.getMessage();
////             关闭clien
//        }
//        ossClient.shutdown();
//
//        String path = key;
//        //this.imgUpload(upfile).getData();
//        String config ="";
//        if(success){
//            config=uploadRootpath+path;
//        }
//        else{
//            config=null;
//        }
//        return config;
//    }

    public String generateUrl(String key){
        Date expiration = new Date(System.currentTimeMillis() + 1800 * 1000);
        OSSClient ossClient = new OSSClient(endpoint, accesskeyid, accesskeysecret);

        return ossClient.generatePresignedUrl(bucket, key, expiration).toString();
    }

    public static void main(String[] args) {
        UploadFileToOssService uploadFileToOssService = new UploadFileToOssService();

        uploadFileToOssService.upload(null);
    }
}
