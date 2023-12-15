package com.cumt.bankapp.mapper;

import com.cumt.bankapp.domain.Fund;
import com.cumt.bankapp.domain.News;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 李易蔚
 * @version 1.0
 */

@SuppressWarnings({"all"})
@Repository
public interface FundMapper {
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
