package com.zwp.slcp.sqlserver.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zwp.slcp.apicommon.entity.FullMessage;
import com.zwp.slcp.apicommon.entity.Info;
import com.zwp.slcp.apicommon.utils.StringUtils;
import com.zwp.slcp.sqlserver.mapper.InfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ASUS on 2018/4/22.
 */
@RestController
@CrossOrigin
@RequestMapping("/info")
public class InfoController {
    @Autowired
    private InfoMapper infoMapper;

    @RequestMapping("/listUserReceiveInfo")
    @ResponseBody
    PageInfo<Info> listUserReceiveInfo(Long userId, Integer pageNumber, Integer pageSize, Integer isRead) {
        if (StringUtils.isBlank(userId, pageNumber, pageSize)) {
            return null;
        } else {
            PageHelper.startPage(pageNumber, pageSize);
            List<Info> infoList = infoMapper.selectReceiveUserId(userId);
            Info info = new Info();
            info.setUpdateTime(System.currentTimeMillis());
            info.setInfoReceiveUserId(userId);
            info.setInfoState(0);
            if (isRead==1) {
                infoMapper.updateByReceiveUserId(info);
            }
            PageInfo<Info> infoPageInfo = new PageInfo<>(infoList);
            return infoPageInfo;
        }
    }

    @RequestMapping("/listUserNewReceiveCount")
    @ResponseBody
    Long listUserNewReceiveCount(Long userId, Integer pageNumber, Integer pageSize) {
        if (StringUtils.isBlank(userId)) {
            return null;
        } else {
            try{

//                PageHelper.startPage(pageNumber, pageSize);
                List<Info> infoList = infoMapper.selectNewInfoReceiveUserId(userId);
                if (infoList ==null || infoList.size()==0) {
                    return 0L;
                }
//                PageInfo<Info> infoPageInfo = new PageInfo<>(infoList);
                if (infoList != null && infoList.size()!=0) {

                    return Long.valueOf(infoList.size());
                }else {
                    return 0L;
                }
            }catch (Exception e) {
                return 0L;
            }
        }
    }



}
