package com.zwp.slcp.sqlserver.mapper;

import com.zwp.slcp.apicommon.entity.MapUser;
import com.zwp.slcp.apicommon.entity.MapUserKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface MapUserMapper {
    int deleteByPrimaryKey(MapUserKey key);

    int insert(MapUser record);

    int insertSelective(MapUser record);

    MapUser selectByPrimaryKey(MapUserKey key);

    //查找两个用户之间的关系
    List<MapUser> selectByUserId(MapUserKey key);

    int updateByPrimaryKeySelective(MapUser record);

    int updateByPrimaryKey(MapUser record);
}