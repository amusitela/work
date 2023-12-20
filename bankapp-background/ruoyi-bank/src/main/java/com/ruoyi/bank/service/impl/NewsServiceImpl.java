package com.ruoyi.bank.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bank.mapper.NewsMapper;
import com.ruoyi.bank.domain.News;
import com.ruoyi.bank.service.INewsService;

/**
 * NewsService业务层处理
 * 
 * @author lyw
 * @date 2023-12-15
 */
@Service
public class NewsServiceImpl implements INewsService 
{
    @Autowired
    private NewsMapper newsMapper;

    /**
     * 查询News
     * 
     * @param id News主键
     * @return News
     */
    @Override
    public News selectNewsById(String id)
    {
        return newsMapper.selectNewsById(id);
    }

    /**
     * 查询News列表
     * 
     * @param news News
     * @return News
     */
    @Override
    public List<News> selectNewsList(News news)
    {
        return newsMapper.selectNewsList(news);
    }

    /**
     * 新增News
     * 
     * @param news News
     * @return 结果
     */
    @Override
    public int insertNews(News news)
    {
        return newsMapper.insertNews(news);
    }

    /**
     * 修改News
     * 
     * @param news News
     * @return 结果
     */
    @Override
    public int updateNews(News news)
    {
        return newsMapper.updateNews(news);
    }

    /**
     * 批量删除News
     * 
     * @param ids 需要删除的News主键
     * @return 结果
     */
    @Override
    public int deleteNewsByIds(String[] ids)
    {
        return newsMapper.deleteNewsByIds(ids);
    }

    /**
     * 删除News信息
     * 
     * @param id News主键
     * @return 结果
     */
    @Override
    public int deleteNewsById(String id)
    {
        return newsMapper.deleteNewsById(id);
    }
}
