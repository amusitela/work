package com.cumt.bankapp.tools;

/**
 * @author 李易蔚
 * @version 1.0
 */

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.context.annotation.Bean;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Configuration;
import java.util.Properties;

@SuppressWarnings({"all"})

@Configuration
public class KaptchaConfig {

    @Bean
    public DefaultKaptcha getDefaultKaptcha() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        // 配置属性，例如边框、颜色等
        properties.setProperty("kaptcha.border", "yes");
        properties.setProperty("kaptcha.border.color", "105,179,90");
        // ... 其他属性
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
