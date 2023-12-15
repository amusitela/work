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
import com.ruoyi.bank.domain.Fund;
import com.ruoyi.bank.service.IFundService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * FundController
 * 
 * @author lyw
 * @date 2023-12-15
 */
@RestController
@RequestMapping("/fund/fund")
public class FundController extends BaseController
{
    @Autowired
    private IFundService fundService;

    /**
     * 查询Fund列表
     */
    @PreAuthorize("@ss.hasPermi('fund:fund:list')")
    @GetMapping("/list")
    public TableDataInfo list(Fund fund)
    {
        startPage();
        List<Fund> list = fundService.selectFundList(fund);
        return getDataTable(list);
    }

    /**
     * 导出Fund列表
     */
    @PreAuthorize("@ss.hasPermi('fund:fund:export')")
    @Log(title = "Fund", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Fund fund)
    {
        List<Fund> list = fundService.selectFundList(fund);
        ExcelUtil<Fund> util = new ExcelUtil<Fund>(Fund.class);
        util.exportExcel(response, list, "Fund数据");
    }

    /**
     * 获取Fund详细信息
     */
    @PreAuthorize("@ss.hasPermi('fund:fund:query')")
    @GetMapping(value = "/{dayIncomeRatio}")
    public AjaxResult getInfo(@PathVariable("dayIncomeRatio") String dayIncomeRatio)
    {
        return success(fundService.selectFundByDayIncomeRatio(dayIncomeRatio));
    }

    /**
     * 新增Fund
     */
    @PreAuthorize("@ss.hasPermi('fund:fund:add')")
    @Log(title = "Fund", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Fund fund)
    {
        return toAjax(fundService.insertFund(fund));
    }

    /**
     * 修改Fund
     */
    @PreAuthorize("@ss.hasPermi('fund:fund:edit')")
    @Log(title = "Fund", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Fund fund)
    {
        return toAjax(fundService.updateFund(fund));
    }

    /**
     * 删除Fund
     */
    @PreAuthorize("@ss.hasPermi('fund:fund:remove')")
    @Log(title = "Fund", businessType = BusinessType.DELETE)
	@DeleteMapping("/{dayIncomeRatios}")
    public AjaxResult remove(@PathVariable String[] dayIncomeRatios)
    {
        return toAjax(fundService.deleteFundByDayIncomeRatios(dayIncomeRatios));
    }
}
