package com.cumt.bankapp.controller;

import java.util.ArrayList;
import java.util.List;

import com.cumt.common.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cumt.bankapp.domain.UserInformation;
import com.cumt.bankapp.service.IUserInformationService;



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
    public MyResult<String> showUserCard(@RequestParam("idCard") String idCard){
        return  MyResult.success(userInformationService.displayCard(idCard).toString());
    }

    /**
     * 查找所有数据库信息
     *
     * */
    @GetMapping("sele")
    public MyResult<List<UserInformation>> seletAll(){
        List<UserInformation> userInformations = new ArrayList<UserInformation>();
        userInformations = userInformationService.selectUserInformationList(new UserInformation());
        System.out.println(userInformations);
        return MyResult.success(userInformations);
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
