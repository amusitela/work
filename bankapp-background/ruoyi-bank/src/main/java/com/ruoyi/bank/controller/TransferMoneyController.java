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
import com.ruoyi.bank.domain.TransferMoney;
import com.ruoyi.bank.service.ITransferMoneyService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * Transfer_moneyController
 * 
 * @author lyw
 * @date 2023-12-15
 */
@RestController
@RequestMapping("/transfer_money/transfer_money")
public class TransferMoneyController extends BaseController
{
    @Autowired
    private ITransferMoneyService transferMoneyService;

    /**
     * 查询Transfer_money列表
     */
    @PreAuthorize("@ss.hasPermi('transfer_money:transfer_money:list')")
    @GetMapping("/list")
    public TableDataInfo list(TransferMoney transferMoney)
    {
        startPage();
        List<TransferMoney> list = transferMoneyService.selectTransferMoneyList(transferMoney);
        return getDataTable(list);
    }

    /**
     * 导出Transfer_money列表
     */
    @PreAuthorize("@ss.hasPermi('transfer_money:transfer_money:export')")
    @Log(title = "Transfer_money", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TransferMoney transferMoney)
    {
        List<TransferMoney> list = transferMoneyService.selectTransferMoneyList(transferMoney);
        ExcelUtil<TransferMoney> util = new ExcelUtil<TransferMoney>(TransferMoney.class);
        util.exportExcel(response, list, "Transfer_money数据");
    }

    /**
     * 获取Transfer_money详细信息
     */
    @PreAuthorize("@ss.hasPermi('transfer_money:transfer_money:query')")
    @GetMapping(value = "/{appointmentTime}")
    public AjaxResult getInfo(@PathVariable("appointmentTime") String appointmentTime)
    {
        return success(transferMoneyService.selectTransferMoneyByAppointmentTime(appointmentTime));
    }

    /**
     * 新增Transfer_money
     */
    @PreAuthorize("@ss.hasPermi('transfer_money:transfer_money:add')")
    @Log(title = "Transfer_money", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TransferMoney transferMoney)
    {
        return toAjax(transferMoneyService.insertTransferMoney(transferMoney));
    }

    /**
     * 修改Transfer_money
     */
    @PreAuthorize("@ss.hasPermi('transfer_money:transfer_money:edit')")
    @Log(title = "Transfer_money", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TransferMoney transferMoney)
    {
        return toAjax(transferMoneyService.updateTransferMoney(transferMoney));
    }

    /**
     * 删除Transfer_money
     */
    @PreAuthorize("@ss.hasPermi('transfer_money:transfer_money:remove')")
    @Log(title = "Transfer_money", businessType = BusinessType.DELETE)
	@DeleteMapping("/{appointmentTimes}")
    public AjaxResult remove(@PathVariable String[] appointmentTimes)
    {
        return toAjax(transferMoneyService.deleteTransferMoneyByAppointmentTimes(appointmentTimes));
    }
}
