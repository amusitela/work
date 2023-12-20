package com.ruoyi.bank.service;

import com.ruoyi.bank.domain.QuestionHistory;

import java.util.List;


/**
 * 历史记录Service接口
 * 
 * @author ljn
 * @date 2023-12-15
 */
public interface IQuestionHistoryService 
{
      public void markAllAsRead();

    public void markAsReadBatch(List<Long> idList);
    /**
     * 查询历史记录
     * 
     * @param id 历史记录主键
     * @return 历史记录
     */

    public QuestionHistory selectQuestionHistoryById(Long id);

    /**
     * 查询历史记录列表
     * 
     * @param questionHistory 历史记录
     * @return 历史记录集合
     */
    public List<QuestionHistory> selectQuestionHistoryList(QuestionHistory questionHistory);

    /**
     * 新增历史记录
     * 
     * @param questionHistory 历史记录
     * @return 结果
     */
    public int insertQuestionHistory(QuestionHistory questionHistory);

    /**
     * 修改历史记录
     * 
     * @param questionHistory 历史记录
     * @return 结果
     */
    public int updateQuestionHistory(QuestionHistory questionHistory);

    /**
     * 批量删除历史记录
     * 
     * @param ids 需要删除的历史记录主键集合
     * @return 结果
     */
    public int deleteQuestionHistoryByIds(Long[] ids);

    /**
     * 删除历史记录信息
     * 
     * @param id 历史记录主键
     * @return 结果
     */
    public int deleteQuestionHistoryById(Long id);
}
