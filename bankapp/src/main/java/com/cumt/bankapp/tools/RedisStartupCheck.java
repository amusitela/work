package com.cumt.bankapp.tools;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisStartupCheck implements ApplicationListener<ApplicationReadyEvent> {


    private final RedisTemplate<String, Object> redisTemplate;

    public RedisStartupCheck(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            Boolean isConnected = redisTemplate.getConnectionFactory().getConnection().ping() != null;
                if (isConnected) {
                    System.out.println("Redis连接成功....");
                } else {
                    System.out.println("Redis连接失败...");
                }
            } catch (Exception e) {
                System.out.println("未开启Redis..." );
                // 可以选择在这里停止应用
                System.exit(1);
            }
    }
}
