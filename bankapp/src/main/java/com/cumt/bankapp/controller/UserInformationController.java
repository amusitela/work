package com.cumt.bankapp.controller;

import java.util.ArrayList;
import java.util.List;

import com.cumt.bankapp.domain.MyResult;
import com.cumt.common.AjaxResult;
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
@RequestMapping("/user_information/information")
public class UserInformationController
{
    private String prefix = "user_information/information";

    @Autowired
    private IUserInformationService userInformationService;


    @GetMapping()
    public String information()
    {
        return prefix + "/information";
    }

    @PostMapping("/updateUserCard")
    @ResponseBody
    public AjaxResult updateUserCard(UserInformation userInformation){

        return AjaxResult.success(userInformationService.updateUserCard(userInformation));
    }

    @GetMapping ("/selectUserCard/{idCard}")
    public String showUserCard(@PathVariable("idCard") String idCard){
        System.out.println(userInformationService.displayCard(idCard).toString());
        return userInformationService.displayCard(idCard).toString();
    }

    @GetMapping("sele")
    public MyResult<List<UserInformation>> seletAll(){
        List<UserInformation> userInformations = new ArrayList<UserInformation>();
        userInformations = userInformationService.selectUserInformationList(new UserInformation());
        System.out.println(userInformations);
        return MyResult.success(userInformations);
    }

    @GetMapping("/loginCheck/{id}")
    public MyResult<String> LoginCheck(@PathVariable(value = "id") String id){
        return MyResult.success(userInformationService.loginCheck(id));
    }





}
