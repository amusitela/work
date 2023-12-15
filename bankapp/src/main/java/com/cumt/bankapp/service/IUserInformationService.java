package com.cumt.bankapp.service;

import java.util.List;

import com.cumt.bankapp.domain.IndividualAccount;
import com.cumt.bankapp.domain.UserInformation;

/**
 * user_informationService接口
 * 
 * @author lyw
 * @date 2023-11-10
 */
public interface IUserInformationService 
{
    /**
     * 查询user_information
     * 
     * @param idCard user_information主键
     * @return user_information
     */
    public UserInformation selectUserInformationByIdCard(String idCard);

    /**
     * 查询user_information列表
     * 
     * @param userInformation user_information
     * @return user_information集合
     */
    public List<UserInformation> selectUserInformationList(UserInformation userInformation);

    /**
     * 查询user_information
     *
     * @param id
     * @return user_information
     */
    public String selectUserInformationName(String id);

    /**
     * 查询user_information
     *
     * @param userInformation
     * @return user_information
     */
    public UserInformation selectUserInformation(UserInformation userInformation);

    /**
     * 新增user_information
     * 
     * @param userInformation user_information
     * @return 结果
     */
    public int insertUserInformation(UserInformation userInformation);

    /**
     * 修改user_information
     * 
     * @param userInformation user_information
     * @return 结果
     */
    public int updateUserInformation(UserInformation userInformation);

    /**
     * 批量删除user_information
     * 
     * @param idCards 需要删除的user_information主键集合
     * @return 结果
     */
    public int deleteUserInformationByIdCards(String idCards);

    /**
     * 删除user_information信息
     * 
     * @param idCard user_information主键
     * @return 结果
     */
    public int deleteUserInformationByIdCard(String idCard);

    /**
     * 修改用户银行卡信息
     *
     * @param userInformation
     * @return 结果
     */
    public int updateUserCard(UserInformation userInformation,int status);

    /**
     * 查询用户银行卡信息
     *
     * @param idCard
     * @return 结果
     */
    public List<IndividualAccount> displayCard(String idCard);


    /**
     * 验证用户密码是否正确
     * @param id
     *   @return pswd
    * */
    public String loginCheck(String id);

    public String getId(String phone);
}
