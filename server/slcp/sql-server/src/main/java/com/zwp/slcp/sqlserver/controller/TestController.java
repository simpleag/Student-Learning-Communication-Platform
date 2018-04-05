package com.zwp.slcp.sqlserver.controller;

import com.zwp.slcp.apicommon.entity.Tag;
import com.zwp.slcp.sqlserver.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ASUS on 2018/3/31.
 */

@RestController
public class TestController {
    @Autowired
    private TagMapper tagMapper;

    @RequestMapping(value = "/tag/test")
    @ResponseBody
    public String list(){
        Tag tag = tagMapper.selectByPrimaryKey(1);
        return tag.getTagName();
    }
}
