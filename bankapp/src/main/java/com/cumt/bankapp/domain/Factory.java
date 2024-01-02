package com.cumt.bankapp.domain;

import com.cumt.bankapp.service.ITransferMoneyService;
import com.cumt.bankapp.service.IUserInformationService;
import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Getter
public class Factory {

    @Resource
    protected ITransferMoneyService tms;

    @Resource
    protected IUserInformationService uis;

}
