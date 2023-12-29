package com.cumt.bankapp.controller;

import java.math.BigDecimal;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.*;


import com.cumt.bankapp.domain.UserInformation;
import com.cumt.bankapp.websocket.WebSocketChatHandler;
import liquibase.pro.packaged.I;
import org.flowable.dmn.engine.impl.hitpolicy.EvaluateRuleValidityBehavior;
import org.json.JSONArray;
import org.json.JSONObject;
import com.cumt.bankapp.domain.IndividualAccount;
import com.cumt.bankapp.mapper.IndividualAccountMapper;
import com.cumt.bankapp.service.IFlowDefinitionService;
import com.cumt.bankapp.service.IIndividualAccountService;
import com.cumt.bankapp.service.IUserInformationService;
import com.cumt.bankapp.service.impl.FlowDefinitionServiceImpl;
import com.cumt.bankapp.tools.jwt.BaseContext;
import com.cumt.common.MyResult;
import org.json.JSONObject;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private IFlowDefinitionService flowDefinitionService;

    @Autowired
    private IUserInformationService uis;


    @PostMapping("/transfer")
    @ResponseBody
    public MyResult<String> transfer(@RequestBody TransferMoney transferMoney)  {
        String currentId = BaseContext.getCurrentId();

        String result = null;

//        //私钥解密，可以写入配置文件中
//        String privateKeyString = "-----BEGIN PRIVATE KEY-----\n" +
//                "MIIBVwIBADANBgkqhkiG9w0BAQEFAASCAUEwggE9AgEAAkEAvxPv5b7W/b7oa4HZ\n" +
//                "x9RVm9q2fM4ICpLYN9PXPgDFRYwJ5da8MljjQjlt75r6p2x44FE2mZYsHx1g5BJI\n" +
//                "gu9JbQIDAQABAkEAtjnoTr3KwSHg/C5RKJ8aqdFSugGvb44NwGg6XPpVKHW4MeOB\n" +
//                "yrcc/fLlMbzU3yiAbQV0Z+eFbmFyXhuwZo77DQIhAONxfTgZ2Z1tJwGYmVFGlFNI\n" +
//                "JlcDoB7x+DY9qU7uwPjjAiEA1xGV64iUJZjYhs4h7lZl7eh020NayRcMUU4hUYzg\n" +
//                "VW8CIQDFcBBOcgo5obZZqVl2d3ls7lXx9UC7fDuHAnIGBjZYFwIhALY7jWfNC5Kr\n" +
//                "51SaVz/nb2jeHh4n/UYExIgkBrFeCDv7AiEAg9tnXc7pXZiytoZuP86FoXjuGO04\n" +
//                "6TMZefLw9tnNNNc=\n" +
//                "-----END PRIVATE KEY";
//
//        // 替换为Base64编码
//        String encryptedData =transferMoney.getPay();
//        //System.out.println(encryptedData);
//        String decryptedString = null;
//
//        try {
//
//            privateKeyString = privateKeyString.replace("-----BEGIN PRIVATE KEY-----", "")
//                    .replace("-----END PRIVATE KEY", "")
//                    .replaceAll("\\s", "");
//
//            // 将Base64编码的字符串转换为PrivateKey对象
//            byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyString);
//            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
//            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
//
//            // 创建Cipher对象并初始化为解密模式
//            Cipher cipher = Cipher.getInstance("RSA");
//            cipher.init(Cipher.DECRYPT_MODE, privateKey);
//
//            // 对数据进行解密
//            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
//            decryptedString = new String(decryptedBytes);
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (InvalidKeySpecException e) {
//            e.printStackTrace();
//        } catch (NoSuchPaddingException e) {
//            e.printStackTrace();
//        } catch (IllegalBlockSizeException e) {
//            e.printStackTrace();
//        } catch (BadPaddingException | InvalidKeyException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(decryptedString);
        //判断支付密码是否正确
        if (!uis.getPay(currentId).equals(transferMoney.getPay())){
            return MyResult.error("支付密码错误");
        }

        //有数据库操作，注意异常
        try {

            //获取转账信息
            TransferMoney money = new TransferMoney();

                money.setAmount(transferMoney.getAmount());
                money.setDescription(transferMoney.getDescription());
                money.setFromAccount(transferMoney.getFromAccount());
                money.setToAccount(transferMoney.getToAccount());

            if (transferMoney.getIs()){
                // 获取当前时间戳
                long currentTimestampMillis = System.currentTimeMillis();

                // 将当前时间戳加上24小时
                long newTimestampMillis = currentTimestampMillis + (24 * 60 * 60 * 1000);

                // 创建 Date 对象
                Date currentDate = new Date(currentTimestampMillis);
                Date newDate = new Date(newTimestampMillis);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                //设置时间格式
                String formattedDate = dateFormat.format(newDate);
                Date parsedDate = dateFormat.parse(formattedDate);

                //发起流程
                money.setAppointmentTime(parsedDate);
                Map<String, Object> trans = trans(money);
                flowDefinitionService.startProcessInstanceById("transferApprovalProcess:26:110016",trans);

            }else{
                if(transferMoney.getAmount().doubleValue()> 20000.00){
                    return MyResult.error("数额大于两万，请预约转账");
                }
            }

            //操作数据库，更新数据
            result = iIndividualAccountService.transfer(transferMoney.getFromAccount(),transferMoney.getToAccount(),transferMoney.getAmount().doubleValue());
//            transferMoneyService.insertTransferMoney(money);


            //发送转账成功信息,设置信息
//            IndividualAccount individualAccount = iIndividualAccountService.selectIndividualAccountByAccountId(transferMoney.getToAccount());

//                UserInformation userInformation = new UserInformation();
//                userInformation.setIdCard(individualAccount.getIdHolder());
//                userInformation.setPhone(individualAccount.getPhoneNumber());
//                UserInformation userInformation1 = uis.selectUserInformation(userInformation);
//                String receiverId = userInformation1.getId();
            //发送信息
//            WebSocketChatHandler.onTransferSuccess(receiverId, String.valueOf(money.getAmount()));

            return MyResult.successMsg(result);
        } catch (Exception e) {
            e.printStackTrace();
            return MyResult.error(result);
        }
    }

    @GetMapping("/flow")
    public MyResult<List<TransferMoney>> getFlow(){
        String id = BaseContext.getCurrentId();
        List<IndividualAccount> individualAccounts = uis.displayCard(id);
        List<String> cards = new ArrayList<String>();
        for (int i = 0; i < individualAccounts.size(); i++) {
            cards.add(individualAccounts.get(i).getAccountId()) ;
        }
        List<TransferMoney> transferMoneyAllList=new ArrayList<>();
        for (int i = 0; i < individualAccounts.size(); i++) {
            List<TransferMoney> transferMoneyList=new ArrayList<>();
            transferMoneyList=transferMoneyService.selectAllFlow(cards.get(i));
            transferMoneyAllList.addAll(transferMoneyList);
        }
        ArrayList<TransferMoney> answer = new ArrayList<>();
        for (TransferMoney i:transferMoneyAllList
             ) {
            if(cards.contains(i.getFromAccount())){
                i.setStatus("支出");
            }else{
                i.setStatus("收入");
            }
            System.out.println(i);
            answer.add(i);
        }

        return  MyResult.success(answer);
    }

    @PostMapping("addCard")
    public MyResult<String> addCard(@RequestBody IndividualAccount individualAccount){

        try {
            Map<String, Object> trans = transAdd(individualAccount);
            flowDefinitionService.startProcessInstanceById("insertCard:1:180044",trans);
            return MyResult.successMsg("申请成功，静候佳音");
        } catch (Exception e) {
            e.printStackTrace();
            return MyResult.error("申请失败");
        }
    }

    @PostMapping("/getInActCard")
    public MyResult<List<String>> getInActCard(){
        try {
            List<String> strings = iIndividualAccountService.selectInActCard();
            return MyResult.success(strings,"成功");
        } catch (Exception e) {
            e.printStackTrace();
            return MyResult.error("查找失败");
        }
    }

    public static Map<String, Object> trans(TransferMoney transferMoney) {

        Map<String, Object> variablesMap = new HashMap<String, Object>();
        String jsonString = "{\"fields\":[{\"__config__\":{\"label\":\"转账人\",\"showLabel\":true,\"changeTag\":true,\"tag\":\"el-input\",\"tagIcon\":\"input\",\"required\":true,\"layout\":\"colFormItem\",\"span\":24,\"document\":\"https://element.eleme.cn/#/zh-CN/component/input\",\"regList\":[{\"required\":true,\"message\":\"请输入转账人转账人\",\"trigger\":\"blur\"}],\"formId\":101,\"renderKey\":\"1011702627522851\",\"defaultValue\":\""+ transferMoney.getFromAccount()+"\"},\"__slot__\":{\"prepend\":\"\",\"append\":\"\"},\"placeholder\":\"请输入转账人转账人\",\"style\":{\"width\":\"100%\"},\"clearable\":true,\"prefix-icon\":\"\",\"suffix-icon\":\"\",\"show-word-limit\":false,\"readonly\":false,\"disabled\":false,\"__vModel__\":\"fromAccount\"},{\"__config__\":{\"label\":\"转账对象\",\"showLabel\":true,\"changeTag\":true,\"tag\":\"el-input\",\"tagIcon\":\"input\",\"required\":true,\"layout\":\"colFormItem\",\"span\":24,\"document\":\"https://element.eleme.cn/#/zh-CN/component/input\",\"regList\":[{\"required\":true,\"message\":\"请输入转账对象转账对象\",\"trigger\":\"blur\"}],\"formId\":101,\"renderKey\":\"1011702705566833\",\"defaultValue\":\""+ transferMoney.getToAccount()+"\"},\"__slot__\":{\"prepend\":\"\",\"append\":\"\"},\"placeholder\":\"请输入转账对象转账对象\",\"style\":{\"width\":\"100%\"},\"clearable\":true,\"prefix-icon\":\"\",\"suffix-icon\":\"\",\"show-word-limit\":false,\"readonly\":false,\"disabled\":false,\"__vModel__\":\"toAccount\"},{\"__config__\":{\"label\":\"转账金额\",\"showLabel\":true,\"changeTag\":true,\"tag\":\"el-input\",\"tagIcon\":\"input\",\"required\":true,\"layout\":\"colFormItem\",\"span\":24,\"document\":\"https://element.eleme.cn/#/zh-CN/component/input\",\"regList\":[{\"required\":true,\"message\":\"请输入转账金额\",\"trigger\":\"blur\"}],\"formId\":102,\"renderKey\":\"1021702705567651\",\"defaultValue\":\""+transferMoney.getAmount()+"\"},\"__slot__\":{\"prepend\":\"\",\"append\":\"\"},\"placeholder\":\"请输入转账金额\",\"style\":{\"width\":\"100%\"},\"clearable\":true,\"prefix-icon\":\"\",\"suffix-icon\":\"\",\"show-word-limit\":false,\"readonly\":false,\"disabled\":false,\"__vModel__\":\"amount\"},{\"__config__\":{\"label\":\"转账方式\",\"showLabel\":true,\"tag\":\"el-select\",\"tagIcon\":\"select\",\"layout\":\"colFormItem\",\"span\":24,\"required\":true,\"regList\":[{\"required\":true,\"message\":\"请选择转账方式\",\"trigger\":\"change\"}],\"changeTag\":true,\"document\":\"https://element.eleme.cn/#/zh-CN/component/select\",\"formId\":111,\"renderKey\":\"1111702705777575\",\"defaultValue\":3},\"__slot__\":{\"options\":[{\"label\":\"公户对公户\",\"value\":1},{\"label\":\"公户对个人\",\"value\":2},{\"label\":\"个人对个人\",\"value\":3}]},\"placeholder\":\"请选择转账方式\",\"style\":{\"width\":\"100%\"},\"clearable\":true,\"disabled\":false,\"filterable\":false,\"multiple\":false,\"__vModel__\":\"type\"},{\"__config__\":{\"label\":\"描述\",\"showLabel\":true,\"changeTag\":true,\"tag\":\"el-input\",\"tagIcon\":\"input\",\"required\":true,\"layout\":\"colFormItem\",\"span\":24,\"document\":\"https://element.eleme.cn/#/zh-CN/component/input\",\"regList\":[{\"required\":true,\"message\":\"请输入单行文本描述\",\"trigger\":\"blur\"}],\"formId\":101,\"renderKey\":\"1011702891630323\",\"defaultValue\":\""+transferMoney.getDescription()+"\"},\"__slot__\":{\"prepend\":\"\",\"append\":\"\"},\"placeholder\":\"请输入单行文本描述\",\"style\":{\"width\":\"100%\"},\"clearable\":true,\"prefix-icon\":\"\",\"suffix-icon\":\"\",\"show-word-limit\":false,\"readonly\":false,\"disabled\":false,\"__vModel__\":\"description\"},{\"__config__\":{\"label\":\"预约时间\",\"tag\":\"el-time-picker\",\"tagIcon\":\"time\",\"defaultValue\":\""+transferMoney.getAppointmentTime()+"\",\"span\":24,\"showLabel\":true,\"layout\":\"colFormItem\",\"required\":true,\"regList\":[{\"required\":true,\"message\":\"请选择预约时间预约时间\",\"trigger\":\"change\"}],\"changeTag\":true,\"document\":\"https://element.eleme.cn/#/zh-CN/component/time-picker\",\"formId\":103,\"renderKey\":\"1031702627563350\"},\"placeholder\":\"请选择预约时间预约时间\",\"style\":{\"width\":\"100%\"},\"disabled\":false,\"clearable\":true,\"picker-options\":{\"selectableRange\":\"00:00:00-23:59:59\"},\"format\":\"HH:mm:ss\",\"value-format\":\"HH:mm:ss\",\"__vModel__\":\"time\"}],\"formRef\":\"elForm\",\"formModel\":\"formData\",\"size\":\"medium\",\"labelPosition\":\"left\",\"labelWidth\":100,\"formRules\":\"rules\",\"gutter\":15,\"disabled\":true,\"span\":24,\"formBtns\":false}";
        // 将 JSON 字符串解析为 JSONObject
        JSONObject jsonObject = new JSONObject(jsonString);

        // 将 JSONObject 转换为 Map
        Map<String, Object> map = jsonToMap(jsonObject);

        variablesMap.put("fromAccount",transferMoney.getFromAccount());
        variablesMap.put("toAccount",transferMoney.getToAccount());
        variablesMap.put("amount",transferMoney.getAmount());
        variablesMap.put("description",transferMoney.getDescription());
        variablesMap.put("time",transferMoney.getAppointmentTime());
        variablesMap.put("variables",map);
        variablesMap.put("type",3);

        return variablesMap;
    }

    public static Map<String, Object> transAdd(IndividualAccount individualAccount) {

        Map<String, Object> variablesMap = new HashMap<String, Object>();
        String jsonString = "{\"fields\":[{\"__config__\":{\"label\":\"手机号\",\"showLabel\":true,\"changeTag\":true,\"tag\":\"el-input\",\"tagIcon\":\"input\",\"required\":true,\"layout\":\"colFormItem\",\"span\":24,\"document\":\"https://element.eleme.cn/#/zh-CN/component/input\",\"regList\":[{\"required\":true,\"message\":\"请输入手机号\",\"trigger\":\"blur\"}],\"formId\":101,\"renderKey\":\"1011703077864419\",\"defaultValue\":\""+individualAccount.getPhoneNumber()+"\"},\"__slot__\":{\"prepend\":\"\",\"append\":\"\"},\"placeholder\":\"请输入手机号\",\"style\":{\"width\":\"100%\"},\"clearable\":true,\"prefix-icon\":\"\",\"suffix-icon\":\"\",\"show-word-limit\":false,\"readonly\":false,\"disabled\":false,\"__vModel__\":\"phone\"},{\"__config__\":{\"label\":\"身份证号\",\"showLabel\":true,\"changeTag\":true,\"tag\":\"el-input\",\"tagIcon\":\"input\",\"required\":true,\"layout\":\"colFormItem\",\"span\":24,\"document\":\"https://element.eleme.cn/#/zh-CN/component/input\",\"regList\":[{\"required\":true,\"message\":\"请输入身份证号\",\"trigger\":\"blur\"}],\"formId\":102,\"renderKey\":\"1021703077868355\",\"defaultValue\":\""+individualAccount.getIdHolder()+"\"},\"__slot__\":{\"prepend\":\"\",\"append\":\"\"},\"placeholder\":\"请输入身份证号\",\"style\":{\"width\":\"100%\"},\"clearable\":true,\"prefix-icon\":\"\",\"suffix-icon\":\"\",\"show-word-limit\":false,\"readonly\":false,\"disabled\":false,\"__vModel__\":\"idCard\"},{\"__config__\":{\"label\":\"邮箱\",\"showLabel\":true,\"changeTag\":true,\"tag\":\"el-input\",\"tagIcon\":\"input\",\"required\":true,\"layout\":\"colFormItem\",\"span\":24,\"document\":\"https://element.eleme.cn/#/zh-CN/component/input\",\"regList\":[{\"required\":true,\"message\":\"请输入邮箱\",\"trigger\":\"blur\"}],\"formId\":102,\"renderKey\":\"1021703078634263\",\"defaultValue\":\""+individualAccount.getEmail()+"\"},\"__slot__\":{\"prepend\":\"\",\"append\":\"\"},\"placeholder\":\"请输入邮箱\",\"style\":{\"width\":\"100%\"},\"clearable\":true,\"prefix-icon\":\"\",\"suffix-icon\":\"\",\"show-word-limit\":false,\"readonly\":false,\"disabled\":false,\"__vModel__\":\"email\"},{\"__config__\":{\"label\":\"银行卡号\",\"showLabel\":true,\"changeTag\":true,\"tag\":\"el-input\",\"tagIcon\":\"input\",\"required\":true,\"layout\":\"colFormItem\",\"span\":24,\"document\":\"https://element.eleme.cn/#/zh-CN/component/input\",\"regList\":[{\"required\":true,\"message\":\"请输入银行卡号\",\"trigger\":\"blur\"}],\"formId\":103,\"renderKey\":\"1031703077869787\",\"defaultValue\":\""+individualAccount.getAccountId()+"\"},\"__slot__\":{\"prepend\":\"\",\"append\":\"\"},\"placeholder\":\"请输入银行卡号\",\"style\":{\"width\":\"100%\"},\"clearable\":true,\"prefix-icon\":\"\",\"suffix-icon\":\"\",\"show-word-limit\":false,\"readonly\":false,\"disabled\":false,\"__vModel__\":\"card\"},{\"__config__\":{\"label\":\"地址\",\"showLabel\":true,\"changeTag\":true,\"tag\":\"el-input\",\"tagIcon\":\"input\",\"required\":true,\"layout\":\"colFormItem\",\"span\":24,\"document\":\"https://element.eleme.cn/#/zh-CN/component/input\",\"regList\":[{\"required\":true,\"message\":\"请输入单行文本地址\",\"trigger\":\"blur\"}],\"formId\":101,\"renderKey\":\"1011703078631364\",\"defaultValue\":\""+individualAccount.getAddress()+"\"},\"__slot__\":{\"prepend\":\"\",\"append\":\"\"},\"placeholder\":\"请输入单行文本地址\",\"style\":{\"width\":\"100%\"},\"clearable\":true,\"prefix-icon\":\"\",\"suffix-icon\":\"\",\"show-word-limit\":false,\"readonly\":false,\"disabled\":false,\"__vModel__\":\"address\"}],\"formRef\":\"elForm\",\"formModel\":\"formData\",\"size\":\"medium\",\"labelPosition\":\"left\",\"labelWidth\":100,\"formRules\":\"rules\",\"gutter\":15,\"disabled\":true,\"span\":24,\"formBtns\":false}";
        // 将 JSON 字符串解析为 JSONObject
        JSONObject jsonObject = new JSONObject(jsonString);

        // 将 JSONObject 转换为 Map
        Map<String, Object> map = jsonToMap(jsonObject);

        variablesMap.put("phone",individualAccount.getPhoneNumber());
        variablesMap.put("idCard",individualAccount.getIdHolder());
        variablesMap.put("email",individualAccount.getEmail());
        variablesMap.put("card",individualAccount.getAccountId());
        variablesMap.put("address",individualAccount.getAddress());
        variablesMap.put("variables",map);

        return variablesMap;
    }



    // 递归将 JSONObject 转换为 Map
    private static Map<String, Object> jsonToMap(JSONObject json) {
        Map<String, Object> map = new HashMap<>();

        // 遍历 JSONObject 中的每个键
        for (String key : json.keySet()) {
            Object value = json.get(key);

            // 如果值是嵌套的 JSONObject，则递归转换
            if (value instanceof JSONObject) {
                value = jsonToMap((JSONObject) value);
            }

            // 如果值是嵌套的 JSONArray，则递归转换
            else if (value instanceof JSONArray) {
                value = jsonToList((JSONArray) value);
            }

            // 将键值对放入 Map
            map.put(key, value);
        }

        return map;
    }

    // 将 JSONArray 转换为 List
    private static Object jsonToList(JSONArray array) {
        // 这里您可以根据需要进行进一步处理，例如将 JSONArray 转换为 List<Object>
        // 这里只是简单地返回 JSONArray 本身
        return array.toList();
    }


    @GetMapping("/total/bill")
    public  MyResult<Map<String,Double>> getToTalBill(){
        Map<String,Double> t=new HashMap<>();
        String id = BaseContext.getCurrentId();
        Double pay=transferMoneyService.getPay(id);
        Double receive=transferMoneyService.getRecive(id);
        t.put("pay",pay);
        t.put("recive",receive);
        return MyResult.success(t);

    }

    @PostMapping("/getRead")
    public MyResult<List<TransferMoney>> getRead(){
        String currentId = BaseContext.getCurrentId();
        try {
            List<IndividualAccount> individualAccounts = uis.displayCard(currentId);
            ArrayList<String> strings = new ArrayList<>();
            for (int i = 0; i < individualAccounts.size(); i++) {
                strings.add(individualAccounts.get(i).getAccountId());
            }
            ArrayList<TransferMoney> transferMonies = new ArrayList<>();
            for (String i: strings
                 ) {
                List<TransferMoney> read = transferMoneyService.getRead(i);
                transferMonies.addAll(read);
            }
            for (TransferMoney i:transferMonies
                 ) {
                i.setRead(1);
                transferMoneyService.updateTransferMoney(i);
            }
            return MyResult.success(transferMonies,"查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            return MyResult.error("查询失败");
        }
    }

}
