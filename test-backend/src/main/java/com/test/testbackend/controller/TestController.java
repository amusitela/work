package com.test.testbackend.controller;

import com.test.testbackend.copy.JwtProperties;
import com.test.testbackend.copy.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
@RestController
public class TestController {
        @Autowired
        private JwtProperties jwtProperties;
        @GetMapping("/jwt/{id}")
        public String generateJwt(@PathVariable String id) {
            String secretKey = jwtProperties.getAdminSecretKey(); // 替换为实际的密钥

            long ttlMillis = jwtProperties.getAdminTtl(); // 过期时间，这里设置为1小时

            // 创建Claims
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", id);

            return JwtUtil.createJWT(secretKey, ttlMillis, claims);
        }
}
