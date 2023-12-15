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
     * @param nm User_information主键
     * @return User_information
     */
    @Override
    public UserInformation selectUserInformationByNm(String nm)
    {
        return userInformationMapper.selectUserInformationByNm(nm);
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
     * @param nms 需要删除的User_information主键
     * @return 结果
     */
    @Override
    public int deleteUserInformationByNms(String[] nms)
    {
        return userInformationMapper.deleteUserInformationByNms(nms);
    }

    /**
     * 删除User_information信息
     * 
     * @param nm User_information主键
     * @return 结果
     */
    @Override
    public int deleteUserInformationByNm(String nm)
    {
        return userInformationMapper.deleteUserInformationByNm(nm);
    }
}
