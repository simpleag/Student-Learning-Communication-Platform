package com.zwp.slcp.sqlserver.mapper;

import com.zwp.slcp.apicommon.entity.Info;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InfoMapper {
    int deleteByPrimaryKey(Long infoId);

    Long insert(Info record);

    int insertSelective(Info record);

    Info selectByPrimaryKey(Long infoId);

    List<Info> selectReceiveUserId(Long infoReceiveUserId);

    List<Info> selectNewInfoReceiveUserId(Long infoReceiveUserId);

    int updateByPrimaryKeySelective(Info record);

    int updateByPrimaryKey(Info record);

    int updateByReceiveUserId(Info record);
}