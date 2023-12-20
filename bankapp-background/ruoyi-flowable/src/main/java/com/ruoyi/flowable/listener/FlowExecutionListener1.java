package com.ruoyi.flowable.listener;

import com.ruoyi.bank.domain.IndividualAccount;
import com.ruoyi.bank.domain.UserInformation;
import com.ruoyi.bank.service.IIndividualAccountService;
import com.ruoyi.bank.service.IUserInformationService;
import lombok.extern.slf4j.Slf4j;
import org.flowable.common.engine.api.delegate.Expression;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;
import org.flowable.http.common.api.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
@Component
public class FlowExecutionListener1 implements ExecutionListener {
    /**
     * 流程设计器添加的参数
     */
    private Expression param;
    @Autowired
    private IIndividualAccountService individualAccountService;

    @Autowired
    private IUserInformationService uis;

    @Override
    @Transactional
    public void notify(DelegateExecution execution) {
        String phone = (String) execution.getVariable("phone");
        String idCard = (String) execution.getVariable("idCard");
        String card = (String) execution.getVariable("card");
        String address = (String) execution.getVariable("address");
        String email = (String) execution.getVariable("email");
        IndividualAccount individualAccount = new IndividualAccount();
        individualAccount.setAccountId(card);
        String id = individualAccountService.selectIndividualAccountList(individualAccount).get(0).getId();

        individualAccount.setIdHolder(idCard);
        individualAccount.setPhoneNumber(phone);
        individualAccount.setAddress(address);
        individualAccount.setEmail(email);
        individualAccount.setIsActive("1");
        individualAccount.setId(id);

        UserInformation userInformation = new UserInformation();

        userInformation.setIdCard(individualAccount.getIdHolder());
        userInformation.setCard(individualAccount.getAccountId());
        userInformation.setPhone(individualAccount.getPhoneNumber());

        UserInformation userInformation1 = new UserInformation();
        userInformation1.setIdCard("410482200211111111");
        userInformation1.setCard(individualAccount.getAccountId());
        userInformation1.setPhone("1");

        individualAccountService.updateIndividualAccount(individualAccount);
        uis.updateUserCard(userInformation,1);
        uis.updateUserCard(userInformation1,0);

    }
}
