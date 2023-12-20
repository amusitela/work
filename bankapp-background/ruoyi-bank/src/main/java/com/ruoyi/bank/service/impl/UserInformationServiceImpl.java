package com.ruoyi.bank.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bank.mapper.UserInformationMapper;
import com.ruoyi.bank.domain.UserInformation;
import com.ruoyi.bank.service.IUserInformationService;

/**
 * User_informationService业务层处理
 * 
 * @author lyw
 * @date 2023-12-15
 */
@Service
public class UserInformationServiceImpl implements IUserInformationService 
{
    @Autowired
    private UserInformationMapper userInformationMapper;

    /**
     * 查询User_information
     * 
     * @param useId User_information主键
     * @return User_information
     */
    @Override
    public UserInformation selectUserInformationByUseId(String useId)
    {
        return userInformationMapper.selectUserInformationByUseId(useId);
    }

    /**
     * 查询User_information列表
     * 
     * @param userInformation User_information
     * @return User_information
     */
    @Override
    public List<UserInformation> selectUserInformationList(UserInformation userInformation)
    {
        return userInformationMapper.selectUserInformationList(userInformation);
    }

    /**
     * 新增User_information
     * 
     * @param userInformation User_information
     * @return 结果
     */
    @Override
    public int insertUserInformation(UserInformation userInformation)
    {
        return userInformationMapper.insertUserInformation(userInformation);
    }

    /**
     * 修改User_information
     * 
     * @param userInformation User_information
     * @return 结果
     */
    @Override
    public int updateUserInformation(UserInformation userInformation)
    {
        return userInformationMapper.updateUserInformation(userInformation);
    }

    /**
     * 批量删除User_information
     * 
     * @param useIds 需要删除的User_information主键
     * @return 结果
     */
    @Override
    public int deleteUserInformationByUseIds(String[] useIds)
    {
        return userInformationMapper.deleteUserInformationByUseIds(useIds);
    }

    /**
     * 删除User_information信息
     * 
     * @param useId User_information主键
     * @return 结果
     */
    @Override
    public int deleteUserInformationByUseId(String useId)
    {
        return userInformationMapper.deleteUserInformationByUseId(useId);
    }

    @Override
    public int updateUserCard(UserInformation userInformation, int status) {
        String s = null;
        UserInformation userInformation1 = userInformationMapper.selectUserInformationByIdCard(userInformation.getIdCard());
        if(status==1) {
            if (userInformation1.getCard() != null) {
                s = userInformation.getCard() + "," + userInformation1.getCard();
            } else {
                s = userInformation.getCard();
            }
        }else{
            s=userInformation1.getCard();
            String s1 = userInformation.getCard();
            String[] split = s1.split(",");
            System.out.println(split[0]);
            for (String i:split
            ) {
                i=i+",";
                s=s.replace(i,"");
            }
        }

        userInformation.setCard(s);
        System.out.println(s);

        return userInformationMapper.updateUserInformationByIDCard(userInformation);
    }
}
