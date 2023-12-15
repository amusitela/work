package com.ruoyi.bank.service;

import java.util.List;
import com.ruoyi.bank.domain.News;

/**
 * NewsService接口
 * 
 * @author lyw
 * @date 2023-12-15
 */
public interface INewsService 
{
    /**
     * 查询News
     * 
     * @param id News主键
     * @return News
     */
    public News selectNewsById(String id);

    /**
     * 查询News列表
     * 
     * @param news News
     * @return News集合
     */
    public List<News> selectNewsList(News news);

    /**
     * 新增News
     * 
     * @param news News
     * @return 结果
     */
    public int insertNews(News news);

    /**
     * 修改News
     * 
     * @param news News
     * @return 结果
     */
    public int updateNews(News news);

    /**
     * 批量删除News
     * 
     * @param ids 需要删除的News主键集合
     * @return 结果
     */
    public int deleteNewsByIds(String[] ids);

    /**
     * 删除News信息
     * 
     * @param id News主键
     * @return 结果
     */
    public int deleteNewsById(String id);
}
