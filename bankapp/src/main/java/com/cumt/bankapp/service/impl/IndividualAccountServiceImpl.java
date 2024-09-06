package com.cumt.bankapp.service.impl;

import java.util.List;


import com.cumt.common.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cumt.bankapp.mapper.IndividualAccountMapper;
import com.cumt.bankapp.domain.IndividualAccount;
import com.cumt.bankapp.service.IIndividualAccountService;
import org.springframework.transaction.annotation.Transactional;


/**
 * individual_accountService业务层处理
 * 
 * @author lyw
 * @date 2023-11-10
 */
@Service
public class IndividualAccountServiceImpl implements IIndividualAccountService 
{
    @Autowired
    private IndividualAccountMapper individualAccountMapper;

    private final Map<String, Object> accountLocks = new ConcurrentHashMap<>();

    /**
     * 查询individual_account
     * 
     * @param accountId individual_account主键
     * @return individual_account
     */
    @Override
    public IndividualAccount selectIndividualAccountByAccountId(String accountId)
    {
        return individualAccountMapper.selectIndividualAccountByAccountId(accountId);
    }

    @Override
    public int selectIndividualAccountByAccountIdTotal(String id) {
        return individualAccountMapper.selectIndividualAccountByAccountIdTotal(id);
    }

    /**
     * 查询individual_account列表
     * 
     * @param individualAccount individual_account
     * @return individual_account
     */
    @Override
    public List<IndividualAccount> selectIndividualAccountList(IndividualAccount individualAccount)
    {
        return individualAccountMapper.selectIndividualAccountList(individualAccount);
    }

    /**
     * 查询individual_account
     *
     * @param individualAccount individual_account
     * @return individual_account
     */
    @Override
    public IndividualAccount selectIndividualAccount(IndividualAccount individualAccount) {
        return individualAccountMapper.selectIndividualAccount(individualAccount);
    }

    /**
     * 新增individual_account
     * 
     * @param individualAccount individual_account
     * @return 结果
     */
    @Override
    public int insertIndividualAccount(IndividualAccount individualAccount)
    {
        return individualAccountMapper.insertIndividualAccount(individualAccount);
    }

    /**
     * 修改individual_account
     * 
     * @param individualAccount individual_account
     * @return 结果
     */
    @Override
    public int updateIndividualAccount(IndividualAccount individualAccount)
    {
        return individualAccountMapper.updateIndividualAccount(individualAccount);
    }

    /**
     * 批量删除individual_account
     * 
     * @param accountIds 需要删除的individual_account主键
     * @return 结果
     */
    @Override
    public int deleteIndividualAccountByAccountIds(String accountIds)
    {
        return individualAccountMapper.deleteIndividualAccountByAccountIds(Convert.toStrArray(accountIds));
    }

    /**
     * 删除individual_account信息
     * 
     * @param accountId individual_account主键
     * @return 结果
     */
    @Override
    public int deleteIndividualAccountByAccountId(String accountId)
    {
        return individualAccountMapper.deleteIndividualAccountByAccountId(accountId);
    }

    /**
     * 执行转账操作
     *
     * @param fromAccountId toAccountId amount
     * @return 结果
     */
    @Override
    @Transactional
    public String transfer(String fromAccountId, String toAccountId, Double amount) {
        // 确保锁的顺序一致，避免死锁问题
        String firstLock = fromAccountId.compareTo(toAccountId) < 0 ? fromAccountId : toAccountId;
        String secondLock = fromAccountId.compareTo(toAccountId) < 0 ? toAccountId : fromAccountId;

        // 获取或创建锁对象
        Object firstAccountLock = accountLocks.computeIfAbsent(firstLock, key -> new Object());
        Object secondAccountLock = accountLocks.computeIfAbsent(secondLock, key -> new Object());

        // 锁定第一个账户
        synchronized (firstAccountLock) {
            // 锁定第二个账户
            synchronized (secondAccountLock) {
                // 执行转账操作
                try {
                // 查询转出账户信息
                System.out.println(fromAccountId);
                IndividualAccount fromAccount = individualAccountMapper.selectIndividualAccountByAccountId(fromAccountId);
                if (fromAccount == null) {
                    return "转出账户不存在";
                }

                // 查询转入账户信息
                IndividualAccount toAccount = individualAccountMapper.selectIndividualAccountByAccountId(toAccountId);
                if (toAccount == null) {
                    return "转入账户不存在";
                }

                // 检查余额是否足够
                if (fromAccount.getBalance()<=0 || fromAccount.getBalance() < amount) {
                    return "余额不足";
                }


                    individualAccountMapper.withdraw(fromAccountId, amount); // 扣减转出账户余额
                    individualAccountMapper.deposit(toAccountId, amount);    // 增加转入账户余额
                    return "转账成功";
                } catch (Exception e) {
                    e.printStackTrace();
                    return "转账失败: " + e.getMessage();
                } finally {
                    // 移除锁对象，避免内存占用，如果并发访问频繁，可以不移除锁对象
                    accountLocks.remove(firstLock);
                    accountLocks.remove(secondLock);
                }
            }
        }
    }

    @Override
    public List<String> selectInActCard() {
        return individualAccountMapper.selectInActCard();
    }
}
