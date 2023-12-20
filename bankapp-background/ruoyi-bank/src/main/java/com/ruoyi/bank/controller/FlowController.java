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
import com.ruoyi.bank.domain.Flow;
import com.ruoyi.bank.service.IFlowService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * FlowController
 * 
 * @author cbk
 * @date 2023-12-15
 */
@RestController
@RequestMapping("/flow/flow")
public class FlowController extends BaseController
{
    @Autowired
    private IFlowService flowService;

    /**
     * 查询Flow列表
     */
    @PreAuthorize("@ss.hasPermi('flow:flow:list')")
    @GetMapping("/list")
    public TableDataInfo list(Flow flow)
    {
        startPage();
        List<Flow> list = flowService.selectFlowList(flow);
        return getDataTable(list);
    }

    /**
     * 导出Flow列表
     */
    @PreAuthorize("@ss.hasPermi('flow:flow:export')")
    @Log(title = "Flow", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Flow flow)
    {
        List<Flow> list = flowService.selectFlowList(flow);
        ExcelUtil<Flow> util = new ExcelUtil<Flow>(Flow.class);
        util.exportExcel(response, list, "Flow数据");
    }

    /**
     * 获取Flow详细信息
     */
    @PreAuthorize("@ss.hasPermi('flow:flow:query')")
    @GetMapping(value = "/{transactionId}")
    public AjaxResult getInfo(@PathVariable("transactionId") String transactionId)
    {
        return success(flowService.selectFlowByTransactionId(transactionId));
    }

    /**
     * 新增Flow
     */
    @PreAuthorize("@ss.hasPermi('flow:flow:add')")
    @Log(title = "Flow", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Flow flow)
    {
        return toAjax(flowService.insertFlow(flow));
    }

    /**
     * 修改Flow
     */
    @PreAuthorize("@ss.hasPermi('flow:flow:edit')")
    @Log(title = "Flow", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Flow flow)
    {
        return toAjax(flowService.updateFlow(flow));
    }

    /**
     * 删除Flow
     */
    @PreAuthorize("@ss.hasPermi('flow:flow:remove')")
    @Log(title = "Flow", businessType = BusinessType.DELETE)
	@DeleteMapping("/{transactionIds}")
    public AjaxResult remove(@PathVariable String[] transactionIds)
    {
        return toAjax(flowService.deleteFlowByTransactionIds(transactionIds));
    }
}
