package com.cumt.bankapp.service;

import com.cumt.bankapp.domain.Fund;

import java.util.List;

/**
 * @author 李易蔚
 * @version 1.0
 */

@SuppressWarnings({"all"})
public interface IFundService {
    /**
     *查询基金列表
     * @param fund
     * @return List<Fund>
     * */
    public List<Fund> selectFundList(Fund fund);

    /**
     *查询详细基金
     * @param id
     * @return  Fund
     * */
    public Fund selectFundById(String id);

    /**
     *批量插入基金
     * @param fund
     * @return  int
     * */
    public int insertBatchFunds(List<Fund> fund);

    /**
     *删除基金
     * @param
     * @return int
     * */
    public int deleteAllFund();
}
