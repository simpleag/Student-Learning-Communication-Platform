package com.zwp.slcp.frontapi.service.failCallBack;

import com.github.pagehelper.PageInfo;
import com.zwp.slcp.apicommon.entity.Info;
import com.zwp.slcp.frontapi.service.InfoService;
import org.springframework.stereotype.Component;

/**
 * Created by ASUS on 2018/4/23.
 */
@Component
public class InfoFailCallBack implements InfoService{
    @Override
    public PageInfo<Info> listUserReceiveInfo(Long userId, Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public Long listUserNewReceiveCount(Long userId) {
        return null;
    }
}
