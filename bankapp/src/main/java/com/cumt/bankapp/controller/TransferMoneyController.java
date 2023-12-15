package com.cumt.bankapp.controller;

import java.math.BigDecimal;
import java.util.List;

import com.cumt.bankapp.mapper.IndividualAccountMapper;
import com.cumt.bankapp.service.IIndividualAccountService;
import com.cumt.common.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.cumt.bankapp.domain.TransferMoney;
import com.cumt.bankapp.service.ITransferMoneyService;
/**
 * transfer_moneyController
 *
 * @author lyw
 * @date 2023-11-10
 */
@RestController
@RequestMapping("/transfer_money")
public class TransferMoneyController
{
    @Autowired
    private ITransferMoneyService transferMoneyService;

    @Autowired
    private IIndividualAccountService iIndividualAccountService;

    @PostMapping("/transfer")
    @ResponseBody
    public MyResult<String> transfer(@RequestBody TransferMoney transferMoney){

        String result = null;

        try {

            TransferMoney money = new TransferMoney();

            money.setAmount(transferMoney.getAmount());
            money.setDescription(transferMoney.getDescription());
            money.setFromAccount(transferMoney.getFromAccount());
            money.setToAccount(transferMoney.getToAccount());
            if (transferMoney.getAppointmentTime()!= null){
                money.setAppointmentTime(transferMoney.getAppointmentTime());
            }else{
                if(transferMoney.getAmount().doubleValue()> 20000.00){
                    return MyResult.error("数额大于两万，请预约转账");
                }
            }

            result = iIndividualAccountService.transfer(transferMoney.getFromAccount(),transferMoney.getToAccount(),transferMoney.getAmount().doubleValue());


            transferMoneyService.insertTransferMoney(money);

            return MyResult.successMsg(result);
        } catch (Exception e) {
            e.printStackTrace();
            return MyResult.error(result);
        }
    }




}
