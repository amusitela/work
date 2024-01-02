package com.cumt.bankapp.service.impl;

import com.cumt.bankapp.domain.News;
import com.cumt.bankapp.mapper.NewsMapper;
import com.cumt.bankapp.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 李易蔚
 * @version 1.0
 */

@SuppressWarnings({"all"})
@Service
public class NewsServiceIpml implements INewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public List<News> selectNewsList(News news) {
        return newsMapper.selectNewsList(news);
    }

    @Override
    public int insertNews(News news) {
        return newsMapper.insertNews(news);
    }

    @Override
    public int insertBatchNews(List<News> news) {
        return newsMapper.insertBatchNews(news);
    }

    @Override
    public int deleteAllNews() {
        return newsMapper.deleteAllNews();
    }

    @Override
    public int updateWatch(String id) {
        return newsMapper.updateWatch(id);
    }

    @Override
    public News selectNewsById(String id) {
        return newsMapper.selectNewsById(id);
    }

    @Override
    public int insertLikes(String newsId, String userId) {
         newsMapper.insertLikes(newsId,userId);
         return newsMapper.updateAddLike(newsId);
    }

    @Override
    public int updateLikes(String newsId, String userId,String like) {
         newsMapper.updateAddLikeA(newsId,userId,like);
         return newsMapper.updateLikes(newsId,userId,like);
    }

    @Override
    public String selectLikes(String newsId, String userId) {
        return newsMapper.selectLikes(newsId,userId);
    }

    @Override
    public int selectLikes1(String newsId, String userId) {
        return newsMapper.selectLikes1(newsId,userId);
    }

    @Override
    public String selectLikes2(String newsId, String userId) {
        return newsMapper.selectLikes2(newsId ,userId);
    }
}
