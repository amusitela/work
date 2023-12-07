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
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

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
    public MyResult<String> generateJwt(@RequestBody UserInformation userInformation,String code) {
        String phone = null;
        try {
            phone = userInformation.getPhone();
            if (phone==null){phone = userInformation.getIdCard();}
        } catch (Exception e) {
            e.printStackTrace();
            return MyResult.error("用户名不存在");
        }
        if(!validateCaptcha(phone,code)){
            return MyResult.error("验证码错误");
        }else {

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
            String id=userInformationService.getId(phone);
            claims.put("id", id);
            String jwt= JwtUtil.createJWT(secretKey, ttlMillis, claims);
            return MyResult.success(jwt);
        }
        else{
            return MyResult.error("密码错误");
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
    }

    public boolean validateCaptcha(String key, String captcha) {
        return captchaService.validateCaptcha(key, captcha);
    }
}
