package com.cumt.bankapp.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cumt.bankapp.domain.IndividualAccount;
import com.cumt.bankapp.mapper.IndividualAccountMapper;
import com.cumt.bankapp.service.IIndividualAccountService;
import com.cumt.common.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cumt.bankapp.mapper.UserInformationMapper;
import com.cumt.bankapp.domain.UserInformation;
import com.cumt.bankapp.service.IUserInformationService;


/**
 * user_informationService业务层处理
 *
 * @author lyw
 * @date 2023-11-10
 */
@Service
public class UserInformationServiceImpl implements IUserInformationService
{
    @Autowired
    private UserInformationMapper userInformationMapper;

    @Autowired
    private IndividualAccountMapper iam;

    /**
     * 查询user_information
     *
     * @param idCard user_information主键
     * @return user_information
     */
    @Override
    public UserInformation selectUserInformationByIdCard(String idCard)
    {
        return userInformationMapper.selectUserInformationByIdCard(idCard);
    }

    /**
     * 查询user_information列表
     *
     * @param userInformation user_information
     * @return user_information
     */
    @Override
    public List<UserInformation> selectUserInformationList(UserInformation userInformation)
    {
        return userInformationMapper.selectUserInformationList(userInformation);
    }

    /**
     * 查询user_information
     *
     * @param id
     * @return user_information
     */
    @Override
    public String selectUserInformationName(String id) {
        return userInformationMapper.selectUserInformationName(id);
    }

    /**
     * 查询user_information
     *
     * @param userInformation
     * @return user_information
     */
    @Override
    public UserInformation selectUserInformation(UserInformation userInformation) {
        return userInformationMapper.selectUserInformation(userInformation);
    }

    /**
     * 新增user_information
     *
     * @param userInformation user_information
     * @return 结果
     */
    @Override
    public int insertUserInformation(UserInformation userInformation)
    {
        return userInformationMapper.insertUserInformation(userInformation);
    }

    /**
     * 修改user_information
     *
     * @param userInformation user_information
     * @return 结果
     */
    @Override
    public int updateUserInformation(UserInformation userInformation)
    {
        return userInformationMapper.updateUserInformation(userInformation);
    }

    /**
     * 批量删除user_information
     *
     * @param idCards 需要删除的user_information主键
     * @return 结果
     */
    @Override
    public int deleteUserInformationByIdCards(String idCards)
    {
        return userInformationMapper.deleteUserInformationByIdCards(Convert.toStrArray(idCards));
    }

    /**
     * 删除user_information信息
     *
     * @param idCard user_information主键
     * @return 结果
     */
    @Override
    public int deleteUserInformationByIdCard(String idCard)
    {
        return userInformationMapper.deleteUserInformationByIdCard(idCard);
    }

    @Override
    public int updateUserCard(UserInformation userInformation, int status) {
        String s = null;
        UserInformation userInformation1 = userInformationMapper.selectUserInformationByIdCard(userInformation.getIdCard());
        if(status==1) {
            if (userInformation1.getCard() != null) {
                s = userInformation1.getCard() + "," + userInformation.getCard();
            } else {
                s = userInformation.getCard();
            }
        }else{
            s=userInformation.getCard();
            String[] split = s.split(",");
            for (String i:split
            ) {
                i=i+",";
                s=s.replace(i,"");
            }
        }

        userInformation.setCard(s);

        return userInformationMapper.updateUserInformation(userInformation);
    }

    @Override
    public List<IndividualAccount> displayCard(String idCard) {

        List<IndividualAccount> individualAccounts = new ArrayList<IndividualAccount>();
        //查询用户的银行卡信息
        UserInformation userInformation = userInformationMapper.selectUserInformationByIdCard(idCard);

        String card = userInformation.getCard();

        String[] split = card.split(",");

        for (String i:split
        ) {
            IndividualAccount individualAccount = iam.selectIndividualAccountByAccountId(i);
            individualAccounts.add(individualAccount);
        }
        return individualAccounts;
    }

    @Override
    public String loginCheck(String id) {
        return userInformationMapper.selectUserInformationPSWDById(id);
    }

    @Override
    public String getPay(String phone) {
        return userInformationMapper.getPay(phone);
    }

    @Override
    public String seletPayPSWD(String phone) {
        return userInformationMapper.selectUserInformationPayPswdById(phone);
    }



}
