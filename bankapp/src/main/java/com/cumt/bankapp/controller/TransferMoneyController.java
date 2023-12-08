package com.cumt.bankapp.controller;

import java.util.List;

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
@RequestMapping("/transfer_money/money")
public class TransferMoneyController
{
    private String prefix = "transfer_money/money";

    @Autowired
    private ITransferMoneyService transferMoneyService;

    @GetMapping()
    public String money()
    {
        return prefix + "/money";
    }



}
