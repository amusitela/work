package com.ruoyi.bank.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.bank.domain.UserInformation;
import com.ruoyi.bank.service.IUserInformationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * User_informationController
 * 
 * @author lyw
 * @date 2023-12-15
 */
@RestController
@RequestMapping("/user_information/user_information")
public class UserInformationController extends BaseController
{
    @Autowired
    private IUserInformationService userInformationService;

    /**
     * 查询User_information列表
     */
    @PreAuthorize("@ss.hasPermi('user_information:user_information:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserInformation userInformation)
    {
        startPage();
        List<UserInformation> list = userInformationService.selectUserInformationList(userInformation);
        return getDataTable(list);
    }

    /**
     * 导出User_information列表
     */
    @PreAuthorize("@ss.hasPermi('user_information:user_information:export')")
    @Log(title = "User_information", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserInformation userInformation)
    {
        List<UserInformation> list = userInformationService.selectUserInformationList(userInformation);
        ExcelUtil<UserInformation> util = new ExcelUtil<UserInformation>(UserInformation.class);
        util.exportExcel(response, list, "User_information数据");
    }

    /**
     * 获取User_information详细信息
     */
    @PreAuthorize("@ss.hasPermi('user_information:user_information:query')")
    @GetMapping(value = "/{nm}")
    public AjaxResult getInfo(@PathVariable("nm") String nm)
    {
        return success(userInformationService.selectUserInformationByNm(nm));
    }

    /**
     * 新增User_information
     */
    @PreAuthorize("@ss.hasPermi('user_information:user_information:add')")
    @Log(title = "User_information", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserInformation userInformation)
    {
        return toAjax(userInformationService.insertUserInformation(userInformation));
    }

    /**
     * 修改User_information
     */
    @PreAuthorize("@ss.hasPermi('user_information:user_information:edit')")
    @Log(title = "User_information", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserInformation userInformation)
    {
        return toAjax(userInformationService.updateUserInformation(userInformation));
    }

    /**
     * 删除User_information
     */
    @PreAuthorize("@ss.hasPermi('user_information:user_information:remove')")
    @Log(title = "User_information", businessType = BusinessType.DELETE)
	@DeleteMapping("/{nms}")
    public AjaxResult remove(@PathVariable String[] nms)
    {
        return toAjax(userInformationService.deleteUserInformationByNms(nms));
    }
}
