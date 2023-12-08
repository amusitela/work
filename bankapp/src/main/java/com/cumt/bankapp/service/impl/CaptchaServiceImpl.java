package com.cumt.bankapp.service.impl;

import com.cumt.bankapp.service.CaptchaService;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.rowset.CachedRowSet;
import java.util.concurrent.TimeUnit;

/**
 * @author 李易蔚
 * @version 1.0
 */

@Service
public class CaptchaServiceImpl implements CaptchaService {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @Override
    public String createCaptcha(String key) {
        String captcha = defaultKaptcha.createText();
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set(key, captcha, 3, TimeUnit.MINUTES); // 设置验证码5分钟过期
        return captcha;
    }

    @Override
    public boolean validateCaptcha(String key, String captcha) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String storedCaptcha = operations.get(key);
        if(captcha != null && captcha.equals(storedCaptcha)){
            redisTemplate.delete(key);
            return true;
        }
        return false;
    }
}
