package com.cumt.bankapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.cumt.bankapp.domain.Flow;
import com.cumt.bankapp.service.IFlowService;


/**
 * flowController
 * 
 * @author lyw
 * @date 2023-11-10
 */
@RestController
@RequestMapping("/flow/flow")
public class FlowController
{
    private String prefix = "flow/flow";

    @Autowired
    private IFlowService flowService;

    @GetMapping()
    public String flow()
    {
        return prefix + "/flow";
    }


    @GetMapping("/hel")
    public String hello(){
        System.out.println("helo");
        return "helloworld";
    }

//    /**
//     * 查询flow列表
//     */
//    @PostMapping("/list")
//    @ResponseBody
//    public TableDataInfo list(Flow flow)
//    {
//        startPage();
//        List<Flow> list = flowService.selectFlowList(flow);
//        return getDataTable(list);
//    }

    /**
     * 导出flow列表
     */

//    @Log(title = "flow", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    @ResponseBody
//    public AjaxResult export(Flow flow)
//    {
//        List<Flow> list = flowService.selectFlowList(flow);
//        ExcelUtil<Flow> util = new ExcelUtil<Flow>(Flow.class);
//        return util.exportExcel(list, "flow数据");
//    }
//
//    /**
//     * 新增flow
//     */
//    @GetMapping("/add")
//    public String add()
//    {
//        return prefix + "/add";
//    }
//
//    /**
//     * 新增保存flow
//     */
//
//    @Log(title = "flow", businessType = BusinessType.INSERT)
//    @PostMapping("/add")
//    @ResponseBody
//    public AjaxResult addSave(Flow flow)
//    {
//        return toAjax(flowService.insertFlow(flow));
//    }

    /**
     * 修改flow
     */

    @GetMapping("/edit/{transactionId}")
    public String edit(@PathVariable("transactionId") String transactionId, ModelMap mmap)
    {
        Flow flow = flowService.selectFlowByTransactionId(transactionId);
        mmap.put("flow", flow);
        return prefix + "/edit";
    }

    /**
     * 修改保存flow
     */

//    @Log(title = "flow", businessType = BusinessType.UPDATE)
//    @PostMapping("/edit")
//    @ResponseBody
//    public AjaxResult editSave(Flow flow)
//    {
//        return toAjax(flowService.updateFlow(flow));
//    }

    /**
     * 删除flow
     */

//    @Log(title = "flow", businessType = BusinessType.DELETE)
//    @PostMapping( "/remove")
//    @ResponseBody
//    public AjaxResult remove(String ids)
//    {
//        return toAjax(flowService.deleteFlowByTransactionIds(ids));
//    }
}
