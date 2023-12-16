package com.example.websocket.Mapper;

import com.example.websocket.Entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Insert;

@Mapper
public interface MessageMapper {
    @Insert("INSERT INTO question_history(sender, receiver, content, time,ifread) VALUES(#{senderId}, #{receiverId}, #{content}, #{timestamp},#{ifread})")
    void insertMessage(Message message);
}
