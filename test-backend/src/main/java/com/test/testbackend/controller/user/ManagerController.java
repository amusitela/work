package com.test.testbackend.controller.user;

import com.test.testbackend.copy.JwtProperties;
import com.test.testbackend.copy.JwtUtil;
import com.test.testbackend.entity.LoginRequest;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@RestController
@Api
public class ManagerController {
        @Autowired
        private JwtProperties jwtProperties;
        @PostMapping("/login")
        public String generateJwt(@RequestBody LoginRequest loginRequest) {
            String secretKey = jwtProperties.getAdminSecretKey(); // 替换为实际的密钥

            long ttlMillis = jwtProperties.getAdminTtl(); // 过期时间，这里设置为1小时
            System.out.println(loginRequest.password);
            // 创建Claims
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginRequest.account);

            return JwtUtil.createJWT(secretKey, ttlMillis, claims);
        }
    @GetMapping("/login2")
    public String generateJwt2() {


        return "2123";
    }
}
