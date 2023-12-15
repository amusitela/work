package com.cumt.bankapp.service;

import com.cumt.bankapp.domain.News;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 李易蔚
 * @version 1.0
 */

@SuppressWarnings({"all"})
public interface INewsService  {
        /**
         *查询新闻列表
         * @param news
         * @return List<News>
         * */
        public List<News> selectNewsList(News news);

        /**
         *插入新闻
         * @param news
         * @return  int
         * */
        public int insertNews(News news);


    /**
     *批量插入新闻
     * @param news
     * @return  int
     * */
     public int insertBatchNews(List<News> news);

    /**
     *删除新闻
     * @param
     * @return int
     * */
     public int deleteAllNews();

    /**
     *更新浏览量
     * @param id
     * @return  int
     * */
    public int updateWatch(String id);

    /**
     *查询新闻详细
     * @param id
     * @return News
     * */
    public News selectNewsById(String id);
}
