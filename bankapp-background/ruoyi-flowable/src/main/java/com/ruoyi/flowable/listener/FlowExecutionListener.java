package com.ruoyi.flowable.listener;

import com.ruoyi.bank.controller.Listener;
import lombok.extern.slf4j.Slf4j;
import org.flowable.common.engine.api.delegate.Expression;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;
import org.flowable.http.common.api.HttpRequest;
import org.flowable.http.common.api.HttpResponse;
import org.springframework.stereotype.Component;

import com.ruoyi.bank.domain.TransferMoney;
import com.ruoyi.bank.service.IPublicAccountService;
import com.ruoyi.bank.service.ITransferMoneyService;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 执行监听器
 *
 * 执行监听器允许在执行过程中执行Java代码。
 * 执行监听器可以捕获事件的类型：
 * 流程实例启动，结束
 * 输出流捕获
 * 获取启动，结束
 * 路由开始，结束
 * 中间事件开始，结束
 * 触发开始事件，触发结束事件
 *
 * @author Tony
 * @date 2022/12/16
 */
@Slf4j
@Component("flowExecutionListener")
public class FlowExecutionListener implements ExecutionListener {
    /**
     * 流程设计器添加的参数
     */
    private Expression param;
    private static final Logger logger = LoggerFactory.getLogger(Listener.class);



    @Autowired
    private IPublicAccountService pas;

    @Autowired
    private ITransferMoneyService tms;
    @Override
    public void notify(DelegateExecution execution) {
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
