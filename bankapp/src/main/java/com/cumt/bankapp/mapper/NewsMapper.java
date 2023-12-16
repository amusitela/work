package com.cumt.bankapp.mapper;

import com.cumt.bankapp.domain.News;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 李易蔚
 * @version 1.0
 */

@SuppressWarnings({"all"})
@Repository
public interface NewsMapper {
   /**
    *查询新闻列表
    * @param news
    * @return List<News>
    * */
   public List<News> selectNewsList(News news);

    /**
     *查询新闻详细
     * @param id
     * @return News
     * */
    public News selectNewsById(String id);

    /**
     *插入新闻
     * @param news
     * @return  int
     * */
    public int insertNews(News news);


    /**
     *更新浏览量
     * @param id
     * @return  int
     * */
    public int updateWatch(String id);

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
    *插入点赞
    * @param useId newId
    * @return int
    * */
   public int insertLikes(@Param("newsId") String newsId, @Param("userId") String userId);

 /**
  *更新点赞
  * @param useId newId
  * @return int
  * */
 public int updateLikes(@Param("newsId") String newsId,@Param("userId") String userId,@Param("like") String like);

 /**
  *更新点赞
  * @param useId newId
  * @return int
  * */
 public String selectLikes(@Param("newsId") String newsId,@Param("userId") String userId);
}
