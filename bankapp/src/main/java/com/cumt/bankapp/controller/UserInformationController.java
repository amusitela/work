package com.cumt.bankapp.controller;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.cumt.bankapp.tools.jwt.BaseContext;
import com.cumt.common.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cumt.bankapp.domain.UserInformation;
import com.cumt.bankapp.service.IUserInformationService;

import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.servlet.http.HttpServletResponse;


/**
 * user_informationController
 * 
 * @author lyw
 * @date 2023-11-10
 */
@RestController
@RequestMapping("/user_information")
public class UserInformationController
{

    @Autowired
    private IUserInformationService userInformationService;

//    /**
//     * 更新卡号
//     *
//     * */
//    @PostMapping("/updateUserCard")
//    @ResponseBody
//    public MyResult updateUserCard(UserInformation userInformation){
//        try {
//            userInformationService.updateUserCard(userInformation);
//            return MyResult.successMsg("更新成功");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return MyResult.error("更新失败");
//        }
//
//    }

    /**
     * 查找使用者的卡列表
     *
     * */
    @PostMapping ("/selectUserCard")
    public MyResult<String> showUserCard(){
        String idCard= BaseContext.getCurrentId();
        return  MyResult.success(userInformationService.displayCard(idCard).toString());
    }

    /**
     * 查找所有数据库信息
     *
     * */
    @GetMapping("/sele")
    public MyResult<List<UserInformation>> seletAll(){
        List<UserInformation> userInformations = new ArrayList<UserInformation>();
        userInformations = userInformationService.selectUserInformationList(new UserInformation());
        System.out.println(userInformations);
        return MyResult.success(userInformations);
    }


    /**
     * 上传头像
     *
     * */
    @PostMapping("/proflie")
    public  MyResult<UserInformation> selectProfile(@RequestBody Map<String,Object> map){
        String id =BaseContext.getCurrentId();
        String oldpsw = (String)map.get("oldpsw");
        String newpsw = (String)map.get("newpsw");
        String userName = (String)map.get("userName");
        File file = (File)map.get("file");

        UserInformation userInformation= null;
        try {
            userInformation = new UserInformation();
            userInformation=userInformationService.selectUserInformationByIdCard(id);
            userInformation.setPayPswd(null);
            userInformation.setPswd(null);
            if (oldpsw.equals(userInformation.getPswd())){
                userInformation.setPswd(newpsw);
            }
            if(userName!=null){
                userInformation.setNm(userName);
            }
            if (file.exists()){
                byte[] bytes = convertFileToByteArray(file);
                userInformation.setImg(bytes);
            }
            userInformationService.updateUserInformation(userInformation);

            return MyResult.success(userInformation);
        } catch (Exception e) {
            e.printStackTrace();
            return MyResult.error("修改失败:"+e.getMessage());
        }


    }

    /**
     * 查找
     *
     * */
    @GetMapping("/seletImg")
    public  MyResult<List<String>> selectImg(HttpServletResponse response){


        String id =BaseContext.getCurrentId();

        byte[] bytes = userInformationService.selectImg(id);
        File file = convertByteArrayToFile(bytes);

        ArrayList<String> strings = new ArrayList<>();
        try {
            // 使用验证码文本生成图片
            BufferedImage image =ImageIO.read(file);
            // 设置响应格式为图片
            response.setContentType("image/jpeg");
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", outputStream);
            response.getOutputStream().write(outputStream.toByteArray());
            response.getOutputStream().flush();
            response.getOutputStream().close();
            String s = userInformationService.selectUserInformationName(id);
            strings.add(id);
            strings.add(s);
            return MyResult.success(strings);
        } catch (IOException e) {
            e.printStackTrace();
            return MyResult.error("错误:"+e.getMessage());
        }
    }

    public byte[] convertFileToByteArray(File file) {

        byte[] fileData = new byte[(int) file.length()];

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            fileInputStream.read(fileData);
        } catch (IOException e) {
        }

        return fileData;
    }

    public File convertByteArrayToFile(byte[] fileData) {
        File file = new File("");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(fileData);
        } catch (IOException e) {
        }
        return file;
    }


//    /**
//     * 验证密码
//     *
//     * */
//    @PostMapping("/loginCheck")
//    public MyResult<String> LoginCheck(@RequestBody  String id){
//        return MyResult.success(userInformationService.loginCheck(id));
//    }





}
