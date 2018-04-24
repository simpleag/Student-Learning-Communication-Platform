package com.zwp.slcp.sqlserver.mapper;

import com.zwp.slcp.apicommon.entity.AnoymousComment;
import com.zwp.slcp.apicommon.entity.DetailAnoymousComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
@Mapper
public interface AnoymousCommentMapper {
    int deleteByPrimaryKey(Long anoymousCommentId);

    Long insert(AnoymousComment record);

    int insertSelective(AnoymousComment record);

    AnoymousComment selectByPrimaryKey(Long anoymousCommentId);

    //根据匿名讨论Id查找
    List<DetailAnoymousComment> selectDetailByAnoymousId(@Param("userId") Long userId, @Param("anoymousId") Long anoymousId);

    List<DetailAnoymousComment> selectDetailByAuthorId(@Param("userId") Long userId, @Param("authorId") Long authorId);

    //根据用户ID查找
    List<AnoymousComment> selectByUserId(Long anoymousCommentAuthorId);

    int updateByPrimaryKeySelective(AnoymousComment record);

    int updateByPrimaryKey(AnoymousComment record);
}