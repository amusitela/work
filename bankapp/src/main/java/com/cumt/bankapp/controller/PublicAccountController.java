package com.cumt.bankapp.controller;

import java.math.BigDecimal;
import java.util.List;

import com.cumt.bankapp.domain.IndividualAccount;
import com.cumt.bankapp.domain.UserInformation;
import com.cumt.common.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.cumt.bankapp.domain.PublicAccount;
import com.cumt.bankapp.service.IPublicAccountService;

/**
 * public_accountController
 *
 * @author lyw
 * @date 2023-11-10
 */
@RestController
@RequestMapping("/public_account")
public class PublicAccountController
{
    @Autowired
    private IPublicAccountService publicAccountService;

    /**
     * 查询individual_account列表
     */

    @PostMapping("/list")
    @ResponseBody
    public MyResult<List<PublicAccount>> list(@RequestBody PublicAccount publicAccount)
    {
        List<PublicAccount> list = publicAccountService.selectPublicAccountList(publicAccount);
        return MyResult.success(list);
    }


    @PostMapping("/card")
    @ResponseBody
    public MyResult<PublicAccount> getCardInformation(@RequestBody PublicAccount publicAccount)
    {
        PublicAccount list = publicAccountService.selectPublicAccount(publicAccount);

        return MyResult.success(list);
    }


    /**
     * 新增保存individual_account
     */

    @PostMapping("/add")
    @ResponseBody
    public MyResult addSave(@RequestBody PublicAccount publicAccount)
    {
        publicAccountService.insertPublicAccount(publicAccount);
        return MyResult.successMsg("添加公户成功");
    }

//    /**
//     * 修改individual_account
//     */
//
//    @GetMapping("/edit/{accountId}")
//    public String edit(@PathVariable("accountId") String accountId, ModelMap mmap)
//    {
//        IndividualAccount individualAccount = individualAccountService.selectIndividualAccountByAccountId(accountId);
//        mmap.put("individualAccount", individualAccount);
//        return "/edit";
//    }

    /**
     * 修改保存individual_account
     */
    @PostMapping("/edit")
    @ResponseBody
    public MyResult editSave(@RequestBody PublicAccount publicAccount)
    {
        try {
            publicAccountService.updatePublicAccount(publicAccount);
            return MyResult.successMsg("修改成功");
        } catch (Exception e) {
            return MyResult.error("修改失败"+e.getMessage());
        }

    }

    /**
     * 删除individual_account
     */

    @PostMapping( "/remove")
    @ResponseBody
    public MyResult remove(@RequestBody PublicAccount publicAccount)
    {
        try {
            publicAccountService.deletePublicAccountByAccountId(publicAccount.getAccountId());
            return MyResult.successMsg("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return MyResult.error("删除失败"+e.getMessage());
        }
    }

}
