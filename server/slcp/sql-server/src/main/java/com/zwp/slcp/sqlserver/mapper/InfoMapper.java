package com.zwp.slcp.sqlserver.mapper;

import com.zwp.slcp.apicommon.entity.Info;

import java.util.List;

public interface InfoMapper {
    int deleteByPrimaryKey(Long infoId);

    int insert(Info record);

    int insertSelective(Info record);

    Info selectByPrimaryKey(Long infoId);

    List<Info> selectByUserId(Long infoReceiveUserId);

    int updateByPrimaryKeySelective(Info record);

    int updateByPrimaryKey(Info record);
}