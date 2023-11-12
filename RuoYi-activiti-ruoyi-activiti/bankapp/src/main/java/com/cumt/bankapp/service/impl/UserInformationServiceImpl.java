package com.cumt.bankapp.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cumt.bankapp.mapper.UserInformationMapper;
import com.cumt.bankapp.domain.UserInformation;
import com.cumt.bankapp.service.IUserInformationService;
import com.ruoyi.common.core.text.Convert;

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
}
