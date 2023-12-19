package com.cumt.bankapp.controller;


import com.cumt.bankapp.service.CaptchaService;
import com.cumt.bankapp.tools.jwt.JwtProperties;
import com.cumt.bankapp.tools.jwt.JwtUtil;
import com.cumt.bankapp.tools.LetterDigit;
import com.cumt.common.MyResult;
import com.cumt.bankapp.domain.UserInformation;
import com.cumt.bankapp.service.IUserInformationService;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

;

@RestController
public class LoginController {

    @Autowired
    private IUserInformationService userInformationService;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @Autowired
    private CaptchaService captchaService;



    @PostMapping("/login")
    public MyResult<String> generateJwt(@RequestBody UserInformation userInformation) {
        String phone = null;
        phone = userInformation.getPhone();
        if (phone==null){
            phone = userInformation.getIdCard();
        }

        if(userInformationService.selectUserInformationName(phone)==null) {
            return MyResult.error("用户名不存在");
        }

        System.out.println(userInformation.getCode());
        if(!validateCaptcha(phone,userInformation.getCode())){
            return MyResult.error("验证码错误");
        }


        String pwd=userInformation.getPswd();
        if (!LetterDigit.isLetterDigit(pwd)){
            return MyResult.error("密码格式错误");
        }


        if(pwd.equals(userInformationService.loginCheck(phone))){
            String secretKey = jwtProperties.getAdminSecretKey(); // 替换为实际的密钥
            long ttlMillis = jwtProperties.getAdminTtl(); // 过期时间，这里设置为1小时
            // 创建Claims
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", phone);
            String jwt= JwtUtil.createJWT(secretKey, ttlMillis, claims);
            return MyResult.success(jwt,"登陆成功");
        }
        else{
            return MyResult.error("密码错误");
        }
    }

    @PostMapping("/registry")
    public MyResult<String> registryUser(@RequestBody UserInformation userInformation){
        String phone = userInformation.getPhone();
        if(!validateCaptcha(phone,userInformation.getCode())){
            return MyResult.error("验证码错误");
        }else {

            if (userInformationService.selectUserInformationName(phone)!=null){
                return MyResult.error("账号已注册");
            }

            String pwd=userInformation.getPswd();

//            if (!LetterDigit.isLetterDigit(pwd)){
//                return MyResult.error("密码格式错误");
//            }

            userInformationService.insertUserInformation(userInformation);

            return MyResult.successMsg("注册成功");
        }
    }

    @GetMapping("/captcha")
    public void getCaptcha(HttpServletResponse response,String key) throws Exception {
        // 生成验证码文本
        String text = captchaService.createCaptcha(key);

        // 使用验证码文本生成图片
        BufferedImage image = defaultKaptcha.createImage(text);

        // 设置响应格式为图片
        response.setContentType("image/jpeg");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        response.getOutputStream().write(outputStream.toByteArray());
//        Base64.getEncoder().encodeToString(outputStream.toByteArray());
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }

    public boolean validateCaptcha(String key, String captcha) {
        return captchaService.validateCaptcha(key, captcha);
    }
}

