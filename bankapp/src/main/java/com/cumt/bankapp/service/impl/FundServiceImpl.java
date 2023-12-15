package com.cumt.bankapp.service.impl;

import com.cumt.bankapp.domain.Fund;
import com.cumt.bankapp.mapper.FundMapper;
import com.cumt.bankapp.service.IFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 李易蔚
 * @version 1.0
 */

@SuppressWarnings({"all"})
@Service
public class FundServiceImpl implements IFundService {
    @Autowired
    private FundMapper fundMapper;

    /**
     *查询基金列表
     * @param fund
     * @return List<Fund>
     * */
    public List<Fund> selectFundList(Fund fund){
        return fundMapper.selectFundList(fund);
    }

    /**
     *查询详细基金
     * @param id
     * @return  Fund
     * */
    public Fund selectFundById(String id){
        return fundMapper.selectFundById(id);
    }

    /**
     *批量插入基金
     * @param fund
     * @return  int
     * */
    public int insertBatchFunds(List<Fund> fund){
        return fundMapper.insertBatchFunds(fund);
    }

    /**
     *删除基金
     * @param
     * @return int
     * */
    public int deleteAllFund(){
        return fundMapper.deleteAllFund();
    }
}
