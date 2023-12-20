package com.ruoyi.bank.service.impl;

import java.util.List;

import com.ruoyi.bank.domain.QuestionHistory;
import com.ruoyi.bank.mapper.QuestionHistoryMapper;
import com.ruoyi.bank.service.IQuestionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * 历史记录Service业务层处理
 * 
 * @author ljn
 * @date 2023-12-15
 */
@Service
public class QuestionHistoryServiceImpl implements IQuestionHistoryService
{
    @Autowired
    private QuestionHistoryMapper questionHistoryMapper;

    /**
     * 查询历史记录
     * 
     * @param id 历史记录主键
     * @return 历史记录
     */
    @Override
    public QuestionHistory selectQuestionHistoryById(Long id)
    {
        return questionHistoryMapper.selectQuestionHistoryById(id);
    }

    @Override
    public void markAllAsRead(){
        questionHistoryMapper.markAllAsRead();
    }

    @Override
    public void markAsReadBatch(List<Long> idList){
        questionHistoryMapper.markAsReadBatch(idList);
    }
    /**
     * 查询历史记录列表
     * 
     * @param questionHistory 历史记录
     * @return 历史记录
     */
    @Override
    public List<QuestionHistory> selectQuestionHistoryList(QuestionHistory questionHistory)
    {
        return questionHistoryMapper.selectQuestionHistoryList(questionHistory);
    }

    /**
     * 新增历史记录
     * 
     * @param questionHistory 历史记录
     * @return 结果
     */
    @Override
    public int insertQuestionHistory(QuestionHistory questionHistory)
    {
        return questionHistoryMapper.insertQuestionHistory(questionHistory);
    }

    /**
     * 修改历史记录
     * 
     * @param questionHistory 历史记录
     * @return 结果
     */
    @Override
    public int updateQuestionHistory(QuestionHistory questionHistory)
    {
        return questionHistoryMapper.updateQuestionHistory(questionHistory);
    }

    /**
     * 批量删除历史记录
     * 
     * @param ids 需要删除的历史记录主键
     * @return 结果
     */
    @Override
    public int deleteQuestionHistoryByIds(Long[] ids)
    {
        return questionHistoryMapper.deleteQuestionHistoryByIds(ids);
    }

    /**
     * 删除历史记录信息
     * 
     * @param id 历史记录主键
     * @return 结果
     */
    @Override
    public int deleteQuestionHistoryById(Long id)
    {
        return questionHistoryMapper.deleteQuestionHistoryById(id);
    }
}
