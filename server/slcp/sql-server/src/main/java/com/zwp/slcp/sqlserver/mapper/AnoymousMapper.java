package com.zwp.slcp.sqlserver.mapper;

import com.zwp.slcp.apicommon.entity.Anoymous;
import com.zwp.slcp.apicommon.entity.DetailAnoymous;
import com.zwp.slcp.apicommon.entity.DetailDiscuss;
import com.zwp.slcp.apicommon.entity.HomeAnoymous;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AnoymousMapper {
    int deleteByPrimaryKey(Long anoymousId);

    Long insert(Anoymous record);

    int insertSelective(Anoymous record);

    Anoymous selectByPrimaryKey(Long anoymousId);

    //查看主页匿名讨论
    List<HomeAnoymous> selectHomeAnoymousOrderByHot(Long userId);

    List<HomeAnoymous> selectHomeAnoymousByTime(Long userId);

    //查看用户发表的匿名讨论 当前用户id和作者
    List<HomeAnoymous> selectHomeAnoumousByAuthorId(@Param("userId") Long userId, @Param("authorId") Long authorId);

    List<HomeAnoymous> selectUserFavoriteAnoymous(Long userId);

    DetailAnoymous selectAnoymousDetailById(@Param("userId") Long userId, @Param("anoymousId") Long anoymousId);

    DetailAnoymous selectAnoumousDetailByComment(@Param("userId") Long userId, @Param("anoymousCommentId") Long anoymousCommentId);

    int updateByPrimaryKeySelective(Anoymous record);

    int updateByPrimaryKey(Anoymous record);

    int updateCommentNumber(@Param("updateTime") Long updateTime, @Param("anoymousId") Long anoymousId);
}