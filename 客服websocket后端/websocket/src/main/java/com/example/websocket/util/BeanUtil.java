package com.example.websocket.util;

import com.example.websocket.Mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeanUtil {

    private static MessageMapper messageMapper;

    @Autowired
    public BeanUtil(MessageMapper messageMapper) {
        BeanUtil.messageMapper = messageMapper;
    }

    public static MessageMapper getMessageMapper() {
        return messageMapper;
    }
}
