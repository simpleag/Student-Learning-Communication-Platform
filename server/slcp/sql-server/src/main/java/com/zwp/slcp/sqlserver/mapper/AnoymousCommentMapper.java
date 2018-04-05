package com.zwp.slcp.sqlserver.mapper;

import com.zwp.slcp.apicommon.entity.AnoymousComment;
import com.zwp.slcp.apicommon.entity.DetailAnoymousComment;

import java.util.List;

public interface AnoymousCommentMapper {
    int deleteByPrimaryKey(Long anoymousCommentId);

    int insert(AnoymousComment record);

    int insertSelective(AnoymousComment record);

    AnoymousComment selectByPrimaryKey(Long anoymousCommentId);

    //根据匿名讨论Id查找
    List<DetailAnoymousComment> selectDetailByAnoymousId(Long userId, Long anoymousId);

    //根据用户ID查找
    List<AnoymousComment> selectByUserId(Long anoymousCommentAuthorId);

    int updateByPrimaryKeySelective(AnoymousComment record);

    int updateByPrimaryKey(AnoymousComment record);
}