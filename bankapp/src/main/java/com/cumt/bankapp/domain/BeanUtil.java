package com.cumt.bankapp.domain;

import com.cumt.bankapp.mapper.MessageMapper;
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
