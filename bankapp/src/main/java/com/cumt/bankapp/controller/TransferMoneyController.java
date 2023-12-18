package com.cumt.bankapp.controller;

import java.math.BigDecimal;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.cumt.bankapp.mapper.IndividualAccountMapper;
import com.cumt.bankapp.service.IIndividualAccountService;
import com.cumt.bankapp.service.IUserInformationService;
import com.cumt.bankapp.tools.jwt.BaseContext;
import com.cumt.common.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.cumt.bankapp.domain.TransferMoney;
import com.cumt.bankapp.service.ITransferMoneyService;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.transform.Result;

/**
 * transfer_moneyController
 *
 * @author lyw
 * @date 2023-11-10
 */
@RestController
@RequestMapping("/transfer1")
public class TransferMoneyController
{
    @Autowired
    private ITransferMoneyService transferMoneyService;

    @Autowired
    private IIndividualAccountService iIndividualAccountService;

    @Autowired
    IUserInformationService uis;

    @PostMapping("/transfer")
    @ResponseBody
    public MyResult<String> transfer(@RequestBody TransferMoney transferMoney)  {
        String currentId = BaseContext.getCurrentId();

        String result = null;

        String privateKeyString = "-----BEGIN PRIVATE KEY-----\n" +
                "MIIBVwIBADANBgkqhkiG9w0BAQEFAASCAUEwggE9AgEAAkEAvxPv5b7W/b7oa4HZ\n" +
                "x9RVm9q2fM4ICpLYN9PXPgDFRYwJ5da8MljjQjlt75r6p2x44FE2mZYsHx1g5BJI\n" +
                "gu9JbQIDAQABAkEAtjnoTr3KwSHg/C5RKJ8aqdFSugGvb44NwGg6XPpVKHW4MeOB\n" +
                "yrcc/fLlMbzU3yiAbQV0Z+eFbmFyXhuwZo77DQIhAONxfTgZ2Z1tJwGYmVFGlFNI\n" +
                "JlcDoB7x+DY9qU7uwPjjAiEA1xGV64iUJZjYhs4h7lZl7eh020NayRcMUU4hUYzg\n" +
                "VW8CIQDFcBBOcgo5obZZqVl2d3ls7lXx9UC7fDuHAnIGBjZYFwIhALY7jWfNC5Kr\n" +
                "51SaVz/nb2jeHh4n/UYExIgkBrFeCDv7AiEAg9tnXc7pXZiytoZuP86FoXjuGO04\n" +
                "6TMZefLw9tnNNNc=\n" +
                "-----END PRIVATE KEY";

        // 替换为您的加密数据（Base64编码）
        String encryptedData =transferMoney.getPay();
        System.out.println(encryptedData);
        String decryptedString = null;

        try {

            privateKeyString = privateKeyString.replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY", "")
                    .replaceAll("\\s", "");

            // 将Base64编码的字符串转换为PrivateKey对象
            byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyString);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

            // 创建Cipher对象并初始化为解密模式
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            // 对数据进行解密
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
            decryptedString = new String(decryptedBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }

        System.out.println(decryptedString);
        System.out.println(uis.getPay(currentId));
        System.out.println(uis.seletPayPSWD(currentId));


        if (!uis.getPay(currentId).equals(decryptedString)){
            return MyResult.error("支付密码错误");
        }

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

    @GetMapping("/flow")
    public MyResult<List<TransferMoney>> getFlow(){
        String id = BaseContext.getCurrentId();
        List<TransferMoney> transferMoneyList=new ArrayList<>();
         transferMoneyList=transferMoneyService.selectAllFlow(id);
        return  MyResult.success(transferMoneyList);
    }



}
