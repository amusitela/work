package com.cumt.bankapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.cumt.bankapp.domain.PublicAccount;
import com.cumt.bankapp.service.IPublicAccountService;

/**
 * public_accountController
 *
 * @author lyw
 * @date 2023-11-10
 */
@RestController
@RequestMapping("/public_account/Paccount")
public class PublicAccountController
{
    private String prefix = "public_account/Paccount";

    @Autowired
    private IPublicAccountService publicAccountService;


}
