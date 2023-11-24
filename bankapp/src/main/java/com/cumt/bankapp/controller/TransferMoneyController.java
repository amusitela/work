//package com.cumt.bankapp.controller;
//
//import java.util.List;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import com.ruoyi.common.annotation.Log;
//import com.ruoyi.common.enums.BusinessType;
//import com.cumt.bankapp.domain.TransferMoney;
//import com.cumt.bankapp.service.ITransferMoneyService;
//import com.ruoyi.common.core.controller.BaseController;
//import com.ruoyi.common.core.domain.AjaxResult;
//import com.ruoyi.common.utils.poi.ExcelUtil;
//import com.ruoyi.common.core.page.TableDataInfo;
//
///**
// * transfer_moneyController
// *
// * @author lyw
// * @date 2023-11-10
// */
//@Controller
//@RequestMapping("/transfer_money/money")
//public class TransferMoneyController extends BaseController
//{
//    private String prefix = "transfer_money/money";
//
//    @Autowired
//    private ITransferMoneyService transferMoneyService;
//
//    @RequiresPermissions("transfer_money:money:view")
//    @GetMapping()
//    public String money()
//    {
//        return prefix + "/money";
//    }
//
//    /**
//     * 查询transfer_money列表
//     */
//    @RequiresPermissions("transfer_money:money:list")
//    @PostMapping("/list")
//    @ResponseBody
//    public TableDataInfo list(TransferMoney transferMoney)
//    {
//        startPage();
//        List<TransferMoney> list = transferMoneyService.selectTransferMoneyList(transferMoney);
//        return getDataTable(list);
//    }
//
//    /**
//     * 导出transfer_money列表
//     */
//    @RequiresPermissions("transfer_money:money:export")
//    @Log(title = "transfer_money", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    @ResponseBody
//    public AjaxResult export(TransferMoney transferMoney)
//    {
//        List<TransferMoney> list = transferMoneyService.selectTransferMoneyList(transferMoney);
//        ExcelUtil<TransferMoney> util = new ExcelUtil<TransferMoney>(TransferMoney.class);
//        return util.exportExcel(list, "transfer_money数据");
//    }
//
//    /**
//     * 新增transfer_money
//     */
//    @GetMapping("/add")
//    public String add()
//    {
//        return prefix + "/add";
//    }
//
//    /**
//     * 新增保存transfer_money
//     */
//    @RequiresPermissions("transfer_money:money:add")
//    @Log(title = "transfer_money", businessType = BusinessType.INSERT)
//    @PostMapping("/add")
//    @ResponseBody
//    public AjaxResult addSave(TransferMoney transferMoney)
//    {
//        return toAjax(transferMoneyService.insertTransferMoney(transferMoney));
//    }
//
//    /**
//     * 修改transfer_money
//     */
//    @RequiresPermissions("transfer_money:money:edit")
//    @GetMapping("/edit/{transferId}")
//    public String edit(@PathVariable("transferId") String transferId, ModelMap mmap)
//    {
//        TransferMoney transferMoney = transferMoneyService.selectTransferMoneyByTransferId(transferId);
//        mmap.put("transferMoney", transferMoney);
//        return prefix + "/edit";
//    }
//
//    /**
//     * 修改保存transfer_money
//     */
//    @RequiresPermissions("transfer_money:money:edit")
//    @Log(title = "transfer_money", businessType = BusinessType.UPDATE)
//    @PostMapping("/edit")
//    @ResponseBody
//    public AjaxResult editSave(TransferMoney transferMoney)
//    {
//        return toAjax(transferMoneyService.updateTransferMoney(transferMoney));
//    }
//
//    /**
//     * 删除transfer_money
//     */
//    @RequiresPermissions("transfer_money:money:remove")
//    @Log(title = "transfer_money", businessType = BusinessType.DELETE)
//    @PostMapping( "/remove")
//    @ResponseBody
//    public AjaxResult remove(String ids)
//    {
//        return toAjax(transferMoneyService.deleteTransferMoneyByTransferIds(ids));
//    }
//}
