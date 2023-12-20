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
 * paccountController
 * 
 * @author lyw
 * @date 2023-12-15
 */
@RestController
@RequestMapping("/bank/paccount")
public class PublicAccountController extends BaseController
{
    @Autowired
    private IPublicAccountService publicAccountService;

    /**
     * 查询paccount列表
     */
    @PreAuthorize("@ss.hasPermi('bank:paccount:list')")
    @GetMapping("/list")
    public TableDataInfo list(PublicAccount publicAccount)
    {
        startPage();
        List<PublicAccount> list = publicAccountService.selectPublicAccountList(publicAccount);
        return getDataTable(list);
    }

    /**
     * 导出paccount列表
     */
    @PreAuthorize("@ss.hasPermi('bank:paccount:export')")
    @Log(title = "paccount", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PublicAccount publicAccount)
    {
        List<PublicAccount> list = publicAccountService.selectPublicAccountList(publicAccount);
        ExcelUtil<PublicAccount> util = new ExcelUtil<PublicAccount>(PublicAccount.class);
        util.exportExcel(response, list, "paccount数据");
    }

    /**
     * 获取paccount详细信息
     */
    @PreAuthorize("@ss.hasPermi('bank:paccount:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(publicAccountService.selectPublicAccountById(id));
    }

    /**
     * 新增paccount
     */
    @PreAuthorize("@ss.hasPermi('bank:paccount:add')")
    @Log(title = "paccount", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PublicAccount publicAccount)
    {
        return toAjax(publicAccountService.insertPublicAccount(publicAccount));
    }

    /**
     * 修改paccount
     */
    @PreAuthorize("@ss.hasPermi('bank:paccount:edit')")
    @Log(title = "paccount", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PublicAccount publicAccount)
    {
        return toAjax(publicAccountService.updatePublicAccount(publicAccount));
    }

    /**
     * 删除paccount
     */
    @PreAuthorize("@ss.hasPermi('bank:paccount:remove')")
    @Log(title = "paccount", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(publicAccountService.deletePublicAccountByIds(ids));
    }
}
