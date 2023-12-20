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
import com.ruoyi.bank.domain.Chart;
import com.ruoyi.bank.service.IChartService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * chartController
 * 
 * @author lyw
 * @date 2023-12-15
 */
@RestController
@RequestMapping("/Chart/chart")
public class ChartController extends BaseController
{
    @Autowired
    private IChartService chartService;

    /**
     * 查询chart列表
     */
    @PreAuthorize("@ss.hasPermi('Chart:chart:list')")
    @GetMapping("/list")
    public TableDataInfo list(Chart chart)
    {
        startPage();
        List<Chart> list = chartService.selectChartList(chart);
        return getDataTable(list);
    }

    /**
     * 导出chart列表
     */
    @PreAuthorize("@ss.hasPermi('Chart:chart:export')")
    @Log(title = "chart", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Chart chart)
    {
        List<Chart> list = chartService.selectChartList(chart);
        ExcelUtil<Chart> util = new ExcelUtil<Chart>(Chart.class);
        util.exportExcel(response, list, "chart数据");
    }

    /**
     * 获取chart详细信息
     */
    @PreAuthorize("@ss.hasPermi('Chart:chart:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(chartService.selectChartById(id));
    }

    /**
     * 新增chart
     */
    @PreAuthorize("@ss.hasPermi('Chart:chart:add')")
    @Log(title = "chart", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Chart chart)
    {
        return toAjax(chartService.insertChart(chart));
    }

    /**
     * 修改chart
     */
    @PreAuthorize("@ss.hasPermi('Chart:chart:edit')")
    @Log(title = "chart", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Chart chart)
    {
        return toAjax(chartService.updateChart(chart));
    }

    /**
     * 删除chart
     */
    @PreAuthorize("@ss.hasPermi('Chart:chart:remove')")
    @Log(title = "chart", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(chartService.deleteChartByIds(ids));
    }
}
