package com.zwp.slcp.sqlserver.mapper;

import com.zwp.slcp.apicommon.entity.FullMessage;
import com.zwp.slcp.apicommon.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface MessageMapper {
    int deleteByPrimaryKey(Long messageId);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Long messageId);

    //用户收到的的私信
    List<FullMessage> selectByReceiveUserId(Long messageAuthorId);

    //用户之间的私信
    List<FullMessage> selectByUsersId(@Param("messageReceiveId") Long messageReceiveId, @Param("messageAuthorId") Long messageAuthorId);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);

    //根据接收者用户Id修改
    int updateByReceiveUserId(Message record);
}