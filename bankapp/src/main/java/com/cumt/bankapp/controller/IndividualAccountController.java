package com.cumt.bankapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.cumt.bankapp.domain.IndividualAccount;
import com.cumt.bankapp.service.IIndividualAccountService;


/**
 * individual_accountController
 * 
 * @author lyw
 * @date 2023-11-10
 */
@RestController
@RequestMapping("/individual_account/Iaccount")
public class IndividualAccountController
{
    private String prefix = "individual_account/Iaccount";

    @Autowired
    private IIndividualAccountService individualAccountService;


    @GetMapping()
    public String Iaccount()
    {
        return prefix + "/Iaccount";
    }

    /**
     * 查询individual_account列表
     */

//    @PostMapping("/list")
//    @ResponseBody
//    public TableDataInfo list(IndividualAccount individualAccount)
//    {
//        startPage();
//        List<IndividualAccount> list = individualAccountService.selectIndividualAccountList(individualAccount);
//        return getDataTable(list);
//    }

    /**
     * 导出individual_account列表
     */

//    @Log(title = "individual_account", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    @ResponseBody
//    public AjaxResult export(IndividualAccount individualAccount)
//    {
//        List<IndividualAccount> list = individualAccountService.selectIndividualAccountList(individualAccount);
//        ExcelUtil<IndividualAccount> util = new ExcelUtil<IndividualAccount>(IndividualAccount.class);
//        return util.exportExcel(list, "individual_account数据");
//    }

    /**
     * 新增individual_account
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存individual_account
     */

//    @Log(title = "individual_account", businessType = BusinessType.INSERT)
//    @PostMapping("/add")
//    @ResponseBody
//    public AjaxResult addSave(IndividualAccount individualAccount)
//    {
//        return toAjax(individualAccountService.insertIndividualAccount(individualAccount));
//    }

    /**
     * 修改individual_account
     */

    @GetMapping("/edit/{accountId}")
    public String edit(@PathVariable("accountId") String accountId, ModelMap mmap)
    {
        IndividualAccount individualAccount = individualAccountService.selectIndividualAccountByAccountId(accountId);
        mmap.put("individualAccount", individualAccount);
        return prefix + "/edit";
    }

    /**
     * 修改保存individual_account
     */

//    @Log(title = "individual_account", businessType = BusinessType.UPDATE)
//    @PostMapping("/edit")
//    @ResponseBody
//    public AjaxResult editSave(IndividualAccount individualAccount)
//    {
//        return toAjax(individualAccountService.updateIndividualAccount(individualAccount));
//    }
//
//    /**
//     * 删除individual_account
//     */
//
//    @Log(title = "individual_account", businessType = BusinessType.DELETE)
//    @PostMapping( "/remove")
//    @ResponseBody
//    public AjaxResult remove(String ids)
//    {
//        return toAjax(individualAccountService.deleteIndividualAccountByAccountIds(ids));
//    }
}
