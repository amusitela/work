package com.ruoyi.bank.controller;

import com.ruoyi.bank.domain.TransferMoney;
import com.ruoyi.bank.service.IPublicAccountService;
import com.ruoyi.bank.service.ITransferMoneyService;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component("listener")
public class Listener implements JavaDelegate {

    private static final Logger logger = LoggerFactory.getLogger(Listener.class);



    @Autowired
    private IPublicAccountService pas;

    @Autowired
    private ITransferMoneyService tms;

    public void execute(DelegateExecution execution) {
        try {
            // 假设所有变量都是必需的，如果任何一个变量缺失，将抛出异常
            String fromAccount = (String) execution.getVariable("fromAccount");
            String toAccount = (String) execution.getVariable("toAccount");
            Double amount = Double.valueOf((String) execution.getVariable("amount"));
            int type = (int) execution.getVariable("type");
            String time = (String) execution.getVariable("time");
            String description = (String) execution.getVariable("description");

            TransferMoney transferMoney = new TransferMoney();
            transferMoney.setAmount(amount.toString());
            transferMoney.setAppointmentTime(time);
            transferMoney.setFromAccount(fromAccount);
            transferMoney.setToAccount(toAccount);
            transferMoney.setDescription(description);

            logger.info("Executing transfer: {}", transferMoney);
            try {
                tms.insertTransferMoney(transferMoney);

                switch (type) {
                    case 1:
                        pas.transferPtoP(fromAccount, toAccount, amount);
                        break;
                    case 2:
                        pas.transferPtoI(fromAccount, toAccount, amount);
                        break;
                    default:
                        String transfer = pas.transfer(fromAccount, toAccount, amount);
                        logger.info("Transfer result: {}", transfer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (NumberFormatException e) {
            logger.error("Error parsing number", e);
            // 可以抛出自定义异常或进行其他错误处理
        } catch (Exception e) {
            logger.error("Error executing delegate", e);
            // 可以抛出自定义异常或进行其他错误处理
        }
    }
}
