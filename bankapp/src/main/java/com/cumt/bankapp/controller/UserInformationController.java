package com.cumt.bankapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.cumt.bankapp.domain.UserInformation;
import com.cumt.bankapp.service.IUserInformationService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * user_informationController
 * 
 * @author lyw
 * @date 2023-11-10
 */
@RestController
@RequestMapping("/user_information/information")
public class UserInformationController extends BaseController
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
    public AjaxResult showUserCard(@PathVariable("idCard") String idCard){
        System.out.println(userInformationService.displayCard(idCard).toString());
        return AjaxResult.success();
    }



    /**
     * 查询user_information列表
     */

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(UserInformation userInformation)
    {
        startPage();
        List<UserInformation> list = userInformationService.selectUserInformationList(userInformation);
        return getDataTable(list);
    }

    /**
     * 导出user_information列表
     */

    @Log(title = "user_information", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(UserInformation userInformation)
    {
        List<UserInformation> list = userInformationService.selectUserInformationList(userInformation);
        ExcelUtil<UserInformation> util = new ExcelUtil<UserInformation>(UserInformation.class);
        return util.exportExcel(list, "user_information数据");
    }

    /**
     * 新增user_information
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存user_information
     */
    @Log(title = "user_information", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(UserInformation userInformation)
    {
        return toAjax(userInformationService.insertUserInformation(userInformation));
    }

    /**
     * 修改user_information
     */

    @GetMapping("/edit/{idCard}")
    public String edit(@PathVariable("idCard") String idCard, ModelMap mmap)
    {
        UserInformation userInformation = userInformationService.selectUserInformationByIdCard(idCard);
        mmap.put("userInformation", userInformation);
        return prefix + "/edit";
    }

    /**
     * 修改保存user_information
     */

    @Log(title = "user_information", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(UserInformation userInformation)
    {
        return toAjax(userInformationService.updateUserInformation(userInformation));
    }

    /**
     * 删除user_information
     */
    @Log(title = "user_information", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(userInformationService.deleteUserInformationByIdCards(ids));
    }
}
