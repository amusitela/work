package com.cumt.bankapp.service;

import org.springframework.stereotype.Service;

/**
 * @author 李易蔚
 * @version 1.0
 */

@SuppressWarnings({"all"})
public interface CaptchaService {
    /**
     * 生成验证码
     * @param key
     * return String
     * */
    public String createCaptcha(String key);

    /**
     * 校验验证码
     * @param key，captcha
     * return boolean
     * */
    public boolean validateCaptcha(String key, String captcha);
}
