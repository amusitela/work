package com.cumt.bankapp.mapper;

import java.io.File;
import java.util.List;
import com.cumt.bankapp.domain.UserInformation;
import liquibase.pro.packaged.P;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * user_informationMapper接口
 * 
 * @author lyw
 * @date 2023-11-10
 */
@Repository
public interface UserInformationMapper 
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
     * 查询user_information姓名
     *
     * @param id
     * @return user_information集合
     */
    public String selectUserInformationName(String id);

    /**
     * 精确查询user_information
     *
     * @param userInformation
     * @return user_information集合
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
     * 删除user_information
     * 
     * @param idCard user_information主键
     * @return 结果
     */
    public int deleteUserInformationByIdCard(String idCard);

    /**
     * 批量删除user_information
     * 
     * @param idCards 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserInformationByIdCards(String[] idCards);

    /**
     * 验证用户密码是否正确
     * @param id
     *   @return pswd
     * */
    public String selectUserInformationPSWDById(String id);

    /**
     * 获取用户支付密码
     * @param id
     *   @return pswd
     * */
    public String selectUserInformationPayPswdById(String id);
    /**
     * 获取银行卡号
     * @param phone
     *   @return card id
     * */
    public String getPay(String phone);
    /**
     * 获取银行卡号
     * @param phone
     *   @return card id
     * */
    public byte[] selectImg(String phone);
    /**
     * 获取银行卡号
     * @param phone
     *   @return card id
     * */
    public int updateImg(@Param("phone") String phone, @Param("file") byte[] flie);



}
