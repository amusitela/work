package com.test.testbackend.controller.user;

import com.test.testbackend.copy.JwtProperties;
import com.test.testbackend.copy.JwtUtil;
import com.test.testbackend.copy.MyResult;

import com.test.testbackend.entity.LoginRequest;
import com.test.testbackend.service.LoginService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

;

@RestController
@Api
public class LoginController {
        @Autowired
        private JwtProperties jwtProperties;
        @Autowired
        private LoginService loginService;
        @PostMapping("/login")
        public MyResult<String> generateJwt(@RequestBody LoginRequest loginRequest) {

            Long phone =loginRequest.getAccount();
            String pwd=loginRequest.getPassword();
            System.out.println(pwd);
            Boolean a=loginService.comparePwd(phone,pwd);
            if(a){
                String secretKey = jwtProperties.getAdminSecretKey(); // 替换为实际的密钥
                long ttlMillis = jwtProperties.getAdminTtl(); // 过期时间，这里设置为1小时
                // 创建Claims
                Map<String, Object> claims = new HashMap<>();
                Long id=loginService.getId(phone);
                claims.put("id", id);
                String jwt=JwtUtil.createJWT(secretKey, ttlMillis, claims);
                return MyResult.success(jwt);
            }
            else{
                return MyResult.error("密码错误");
            }


        }
    @GetMapping("/login2")
    public String generateJwt2() {


        return "2123";
    }
}
