package com.ruoyi.bank.mapper;


import com.ruoyi.bank.domain.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper {
    @Insert("INSERT INTO question_history(sender, receiver, content, time,ifread) VALUES(#{senderId}, #{receiverId}, #{content}, #{timestamp},#{ifread})")
    void insertMessage(Message message);
}
