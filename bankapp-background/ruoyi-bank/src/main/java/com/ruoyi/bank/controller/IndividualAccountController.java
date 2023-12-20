package com.ruoyi.bank.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.bank.domain.UserInformation;
import com.ruoyi.bank.service.IUserInformationService;
import com.ruoyi.bank.service.impl.UserInformationServiceImpl;
import org.apache.catalina.User;
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
import com.ruoyi.bank.domain.IndividualAccount;
import com.ruoyi.bank.service.IIndividualAccountService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * AccountController
 * 
 * @author lyw
 * @date 2023-12-15
 */
@RestController
@RequestMapping("/bank/account")
public class IndividualAccountController extends BaseController
{
    @Autowired
    private IIndividualAccountService individualAccountService;

    @Autowired
    private IUserInformationService uis;

    /**
     * 查询Account列表
     */
    @PreAuthorize("@ss.hasPermi('bank:account:list')")
    @GetMapping("/list")
    public TableDataInfo list(IndividualAccount individualAccount)
    {
        startPage();
        List<IndividualAccount> list = individualAccountService.selectIndividualAccountList(individualAccount);
        return getDataTable(list);
    }

    /**
     * 导出Account列表
     */
    @PreAuthorize("@ss.hasPermi('bank:account:export')")
    @Log(title = "Account", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, IndividualAccount individualAccount)
    {
        List<IndividualAccount> list = individualAccountService.selectIndividualAccountList(individualAccount);
        ExcelUtil<IndividualAccount> util = new ExcelUtil<IndividualAccount>(IndividualAccount.class);
        util.exportExcel(response, list, "Account数据");
    }

    /**
     * 获取Account详细信息
     */
    @PreAuthorize("@ss.hasPermi('bank:account:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(individualAccountService.selectIndividualAccountById(id));
    }

    /**
     * 新增Account
     */
    @PreAuthorize("@ss.hasPermi('bank:account:add')")
    @Log(title = "Account", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody IndividualAccount individualAccount)
    {
        UserInformation userInformation = new UserInformation();
        userInformation.setIdCard(individualAccount.getIdHolder());
        userInformation.setCard(individualAccount.getAccountId());
        userInformation.setPhone(individualAccount.getPhoneNumber());
        uis.updateUserCard(userInformation,1);
        return toAjax(individualAccountService.insertIndividualAccount(individualAccount));
    }

    /**
     * 修改Account
     */
    @PreAuthorize("@ss.hasPermi('bank:account:edit')")
    @Log(title = "Account", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody IndividualAccount individualAccount)
    {
        return toAjax(individualAccountService.updateIndividualAccount(individualAccount));
    }

    /**
     * 删除Account
     */
    @PreAuthorize("@ss.hasPermi('bank:account:remove')")
    @Log(title = "Account", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        UserInformation userInformation = new UserInformation();
        IndividualAccount individualAccount = individualAccountService.selectIndividualAccountById(ids[0]);
        String card = "";
        for (int i = 0; i < ids.length; i++) {
            card+=(individualAccountService.selectIndividualAccountById(ids[i]).getAccountId()+",");
        }
        System.out.println("--------------------------------------------------");
        System.out.println(card);
        userInformation.setIdCard(individualAccount.getIdHolder());
        userInformation.setPhone(individualAccount.getPhoneNumber());
        userInformation.setCard(card);
        uis.updateUserCard(userInformation,0);
        return toAjax(individualAccountService.deleteIndividualAccountByIds(ids));
    }
}
