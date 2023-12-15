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
import com.ruoyi.bank.domain.PublicAccount;
import com.ruoyi.bank.service.IPublicAccountService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * PaccountController
 * 
 * @author lyw
 * @date 2023-12-15
 */
@RestController
@RequestMapping("/paccount/paccount")
public class PublicAccountController extends BaseController
{
    @Autowired
    private IPublicAccountService publicAccountService;

    /**
     * 查询Paccount列表
     */
    @PreAuthorize("@ss.hasPermi('paccount:paccount:list')")
    @GetMapping("/list")
    public TableDataInfo list(PublicAccount publicAccount)
    {
        startPage();
        List<PublicAccount> list = publicAccountService.selectPublicAccountList(publicAccount);
        return getDataTable(list);
    }

    /**
     * 导出Paccount列表
     */
    @PreAuthorize("@ss.hasPermi('paccount:paccount:export')")
    @Log(title = "Paccount", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PublicAccount publicAccount)
    {
        List<PublicAccount> list = publicAccountService.selectPublicAccountList(publicAccount);
        ExcelUtil<PublicAccount> util = new ExcelUtil<PublicAccount>(PublicAccount.class);
        util.exportExcel(response, list, "Paccount数据");
    }

    /**
     * 获取Paccount详细信息
     */
    @PreAuthorize("@ss.hasPermi('paccount:paccount:query')")
    @GetMapping(value = "/{accountId}")
    public AjaxResult getInfo(@PathVariable("accountId") String accountId)
    {
        return success(publicAccountService.selectPublicAccountByAccountId(accountId));
    }

    /**
     * 新增Paccount
     */
    @PreAuthorize("@ss.hasPermi('paccount:paccount:add')")
    @Log(title = "Paccount", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PublicAccount publicAccount)
    {
        return toAjax(publicAccountService.insertPublicAccount(publicAccount));
    }

    /**
     * 修改Paccount
     */
    @PreAuthorize("@ss.hasPermi('paccount:paccount:edit')")
    @Log(title = "Paccount", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PublicAccount publicAccount)
    {
        return toAjax(publicAccountService.updatePublicAccount(publicAccount));
    }

    /**
     * 删除Paccount
     */
    @PreAuthorize("@ss.hasPermi('paccount:paccount:remove')")
    @Log(title = "Paccount", businessType = BusinessType.DELETE)
	@DeleteMapping("/{accountIds}")
    public AjaxResult remove(@PathVariable String[] accountIds)
    {
        return toAjax(publicAccountService.deletePublicAccountByAccountIds(accountIds));
    }
}
