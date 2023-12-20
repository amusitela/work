package com.cumt.bankapp.controller;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import com.cumt.bankapp.tools.jwt.BaseContext;
import com.cumt.common.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cumt.bankapp.domain.UserInformation;
import com.cumt.bankapp.service.IUserInformationService;
import org.springframework.web.multipart.MultipartFile;

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
    @PostMapping("/proflie1")
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
    @PostMapping("/profile")
    public  MyResult<UserInformation> updateProfile(
            @RequestParam(value = "oldpsw", required = false) String oldPsw,
            @RequestParam(value = "newpsw", required = false) String newPsw,
            @RequestParam(value = "userName", required = false) String userName,
            @RequestParam(value = "file", required = false) MultipartFile file) {
        System.out.println("file");
        String id =BaseContext.getCurrentId();

        try {
            UserInformation userInformation = new UserInformation();
            userInformation=userInformationService.selectUserInformationByIdCard(id);
            userInformation.setPayPswd(null);
            userInformation.setPswd(null);
            if (userInformation.getPswd()==oldPsw){
                userInformation.setPswd(newPsw);
            }
            if(userName!=null){
                userInformation.setNm(userName);
            }
            if (file != null && !file.isEmpty()){
                byte[] bytes = file.getBytes();
                userInformation.setImg(bytes);}

            userInformationService.updateUserInformation(userInformation);

            return MyResult.success(userInformation);
        } catch (Exception e) {
            e.printStackTrace();
            return MyResult.error("修改失败:"+e.getMessage());
        }



    }
//    /**
//     * 查找
//     *
//     * */
//    @GetMapping("/proflie")
//    public  MyResult<Map<String,String>> selectImg(){
//
//        String id =BaseContext.getCurrentId();
//
//        HashMap<String, String> strings = new HashMap<>();
//
//        try {
//            String s = userInformationService.selectUserInformationName(id);
//            strings.put("phone",id);
//            strings.put("nm",s);
//            return MyResult.success(strings);
//        } catch (Exception e) {
//            return MyResult.error("错误:"+e.getMessage());
//        }
//
//
//
//    }
    @GetMapping("/profile2/{url}")
    public void getImg(HttpServletResponse response, @PathVariable("url") String url) {
        UserInformation userInformation = userInformationService.selectImg(url);
        byte[] bytes = userInformation.getImg();

        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            // 使用字节数组生成图像
            BufferedImage image = ImageIO.read(inputStream);

            // 设置响应格式为图片
            response.setContentType("image/jpeg");

            // 将图像数据写入响应流
            ImageIO.write(image, "jpg", outputStream);
            response.getOutputStream().write(outputStream.toByteArray());

            // 添加一些关于文件名和其他信息的头信息，可以根据需要调整
            String s = userInformationService.selectUserInformationName(url);
            response.setHeader("Content-Disposition", "inline; filename=" + url);
            response.setHeader("User-Information-Name", s);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @GetMapping("/profile2/{url}")
//    public  void getImg(HttpServletResponse response,@PathVariable("url")String url){
//
//        byte[] bytes = userInformationService.selectImg(url);
//        File file = convertByteArrayToFile(bytes);
//
//        ArrayList<String> strings = new ArrayList<>();
//        try {
//            // 使用验证码文本生成图片
//            BufferedImage image =ImageIO.read(file);
//            // 设置响应格式为图片
//            response.setContentType("image/jpeg");
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            ImageIO.write(image, "jpg", outputStream);
//            response.getOutputStream().write(outputStream.toByteArray());
//            String s = userInformationService.selectUserInformationName(url);
//            strings.add(url);
//            strings.add(s);
//            response.getOutputStream().flush();
//            response.getOutputStream().close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

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
