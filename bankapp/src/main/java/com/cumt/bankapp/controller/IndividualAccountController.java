package com.cumt.bankapp.controller;

import com.cumt.bankapp.domain.UserInformation;
import com.cumt.bankapp.service.IUserInformationService;
import com.cumt.bankapp.tools.jwt.BaseContext;
import com.cumt.common.MyResult;
import lombok.extern.java.Log;
import net.dreamlu.mica.core.result.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.cumt.bankapp.domain.IndividualAccount;
import com.cumt.bankapp.service.IIndividualAccountService;

import java.math.BigDecimal;
import java.util.List;


/**
 * individual_accountController
 * 
 * @author lyw
 * @date 2023-11-10
 */
@RestController
@RequestMapping("/account")
public class IndividualAccountController
{

    @Autowired
    private IIndividualAccountService individualAccountService;

    @Autowired
    private IUserInformationService uis;


    /**
     * 查询individual_account列表
     */

    @PostMapping("/list")
    @ResponseBody
    public MyResult<List<IndividualAccount>> list(IndividualAccount individualAccount)
    {
        List<IndividualAccount> list = individualAccountService.selectIndividualAccountList(individualAccount);
        return MyResult.success(list);
    }

    /**
     * 导出individual_account总金额
     */

    @PostMapping("/money")
    public MyResult<BigDecimal> getMoneyTotal()
    {
        IndividualAccount individualAccount = new IndividualAccount();
        String ID = BaseContext.getCurrentId();
        individualAccount.setPhoneNumber(ID);
        List<IndividualAccount> list = individualAccountService.selectIndividualAccountList(individualAccount);
        BigDecimal ans = new BigDecimal(0.00);
        for (IndividualAccount i: list
             ) {
            ans =ans.add(i.getBalance());
        }
        return MyResult.success(ans);
    }

    @PostMapping("/card")
    @ResponseBody
    public MyResult<IndividualAccount> getCardInformation(@RequestBody IndividualAccount individualAccount)
    {
        IndividualAccount list = individualAccountService.selectIndividualAccount(individualAccount);

        return MyResult.success(list);
    }


    /**
     * 新增保存individual_account
     */

    @PostMapping("/add")
    @ResponseBody
    public MyResult addSave(@RequestBody IndividualAccount individualAccount)
    {
        individualAccountService.insertIndividualAccount(individualAccount);
        UserInformation userInformation = new UserInformation();
        userInformation.setIdCard(individualAccount.getIdHolder());
        userInformation.setCard(individualAccount.getAccountId());
        userInformation.setPhone(individualAccount.getPhoneNumber());
        uis.updateUserCard(userInformation,1);
        return MyResult.successMsg("添加银行卡成功");
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
    public MyResult editSave(@RequestBody IndividualAccount individualAccount)
    {
        try {
            individualAccountService.updateIndividualAccount(individualAccount);
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
    public MyResult remove(@RequestBody UserInformation userInformation)
    {
        try {
            individualAccountService.deleteIndividualAccountByAccountIds(userInformation.getCard());
            uis.updateUserCard(userInformation,0);
            return MyResult.successMsg("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return MyResult.error("删除失败"+e.getMessage());
        }
    }
}
