package com.ruoyi.bank.mapper;

import java.util.List;
import com.ruoyi.bank.domain.UserInformation;

/**
 * User_informationMapper接口
 * 
 * @author lyw
 * @date 2023-12-15
 */
public interface UserInformationMapper 
{
    /**
     * 查询User_information
     * 
     * @param useId User_information主键
     * @return User_information
     */
    public UserInformation selectUserInformationByUseId(String useId);

    /**
     * 查询User_information列表
     * 
     * @param userInformation User_information
     * @return User_information集合
     */
    public List<UserInformation> selectUserInformationList(UserInformation userInformation);

    /**
     * 新增User_information
     * 
     * @param userInformation User_information
     * @return 结果
     */
    public int insertUserInformation(UserInformation userInformation);

    /**
     * 修改User_information
     * 
     * @param userInformation User_information
     * @return 结果
     */
    public int updateUserInformation(UserInformation userInformation);

    /**
     * 修改User_information
     *
     * @param userInformation User_information
     * @return 结果
     */
    public int updateUserInformationByIDCard(UserInformation userInformation);

    /**
     * 删除User_information
     * 
     * @param useId User_information主键
     * @return 结果
     */
    public int deleteUserInformationByUseId(String useId);

    /**
     * 批量删除User_information
     * 
     * @param useIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserInformationByUseIds(String[] useIds);

    /**
     * 查询user_information
     *
     * @param idCard user_information主键
     * @return user_information
     */
    public UserInformation selectUserInformationByIdCard(String idCard);

}
