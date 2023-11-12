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
import com.cumt.bankapp.domain.Flow;
import com.cumt.bankapp.service.IFlowService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * flowController
 * 
 * @author lyw
 * @date 2023-11-10
 */
@Controller
@RequestMapping("/flow/flow")
public class FlowController extends BaseController
{
    private String prefix = "flow/flow";

    @Autowired
    private IFlowService flowService;

    @RequiresPermissions("flow:flow:view")
    @GetMapping()
    public String flow()
    {
        return prefix + "/flow";
    }

    /**
     * 查询flow列表
     */
    @RequiresPermissions("flow:flow:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Flow flow)
    {
        startPage();
        List<Flow> list = flowService.selectFlowList(flow);
        return getDataTable(list);
    }

    /**
     * 导出flow列表
     */
    @RequiresPermissions("flow:flow:export")
    @Log(title = "flow", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Flow flow)
    {
        List<Flow> list = flowService.selectFlowList(flow);
        ExcelUtil<Flow> util = new ExcelUtil<Flow>(Flow.class);
        return util.exportExcel(list, "flow数据");
    }

    /**
     * 新增flow
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存flow
     */
    @RequiresPermissions("flow:flow:add")
    @Log(title = "flow", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Flow flow)
    {
        return toAjax(flowService.insertFlow(flow));
    }

    /**
     * 修改flow
     */
    @RequiresPermissions("flow:flow:edit")
    @GetMapping("/edit/{transactionId}")
    public String edit(@PathVariable("transactionId") Integer transactionId, ModelMap mmap)
    {
        Flow flow = flowService.selectFlowByTransactionId(transactionId);
        mmap.put("flow", flow);
        return prefix + "/edit";
    }

    /**
     * 修改保存flow
     */
    @RequiresPermissions("flow:flow:edit")
    @Log(title = "flow", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Flow flow)
    {
        return toAjax(flowService.updateFlow(flow));
    }

    /**
     * 删除flow
     */
    @RequiresPermissions("flow:flow:remove")
    @Log(title = "flow", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(flowService.deleteFlowByTransactionIds(ids));
    }
}
