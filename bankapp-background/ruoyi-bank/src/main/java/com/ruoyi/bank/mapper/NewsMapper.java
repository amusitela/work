package com.ruoyi.bank.mapper;

import java.util.List;
import com.ruoyi.bank.domain.News;

/**
 * NewsMapper接口
 * 
 * @author lyw
 * @date 2023-12-15
 */
public interface NewsMapper 
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
     * 删除News
     * 
     * @param id News主键
     * @return 结果
     */
    public int deleteNewsById(String id);

    /**
     * 批量删除News
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNewsByIds(String[] ids);
}
