package com.cumt.bankapp.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.cumt.bankapp.domain.PublicAccount;
import com.cumt.bankapp.service.IPublicAccountService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * public_accountController
 * 
 * @author lyw
 * @date 2023-11-10
 */
@Controller
@RequestMapping("/public_account/Paccount")
public class PublicAccountController extends BaseController
{
    private String prefix = "public_account/Paccount";

    @Autowired
    private IPublicAccountService publicAccountService;

    @RequiresPermissions("public_account:Paccount:view")
    @GetMapping()
    public String Paccount()
    {
        return prefix + "/Paccount";
    }

    /**
     * 查询public_account列表
     */
    @RequiresPermissions("public_account:Paccount:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PublicAccount publicAccount)
    {
        startPage();
        List<PublicAccount> list = publicAccountService.selectPublicAccountList(publicAccount);
        return getDataTable(list);
    }

    /**
     * 导出public_account列表
     */
    @RequiresPermissions("public_account:Paccount:export")
    @Log(title = "public_account", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PublicAccount publicAccount)
    {
        List<PublicAccount> list = publicAccountService.selectPublicAccountList(publicAccount);
        ExcelUtil<PublicAccount> util = new ExcelUtil<PublicAccount>(PublicAccount.class);
        return util.exportExcel(list, "public_account数据");
    }

    /**
     * 新增public_account
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存public_account
     */
    @RequiresPermissions("public_account:Paccount:add")
    @Log(title = "public_account", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PublicAccount publicAccount)
    {
        return toAjax(publicAccountService.insertPublicAccount(publicAccount));
    }

    /**
     * 修改public_account
     */
    @RequiresPermissions("public_account:Paccount:edit")
    @GetMapping("/edit/{accountId}")
    public String edit(@PathVariable("accountId") Integer accountId, ModelMap mmap)
    {
        PublicAccount publicAccount = publicAccountService.selectPublicAccountByAccountId(accountId);
        mmap.put("publicAccount", publicAccount);
        return prefix + "/edit";
    }

    /**
     * 修改保存public_account
     */
    @RequiresPermissions("public_account:Paccount:edit")
    @Log(title = "public_account", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PublicAccount publicAccount)
    {
        return toAjax(publicAccountService.updatePublicAccount(publicAccount));
    }

    /**
     * 删除public_account
     */
    @RequiresPermissions("public_account:Paccount:remove")
    @Log(title = "public_account", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(publicAccountService.deletePublicAccountByAccountIds(ids));
    }
}
