package com.cumt.bankapp.controller;

import com.cumt.bankapp.domain.News;
import com.cumt.bankapp.service.INewsService;
import com.cumt.bankapp.tools.getData.GetNew;
import com.cumt.common.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 李易蔚
 * @version 1.0
 */

@SuppressWarnings({"all"})
@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private INewsService iNewsService;

    @Scheduled(cron = "0 0 * * * ?")
    public void insertNews(){
        try {
            int i1 = iNewsService.deleteAllNews();
            GetNew getNew = new GetNew();
            ArrayList<News> allNews = getNew.getAllNews();
            int i = iNewsService.insertBatchNews(allNews);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("list")
    public MyResult<List<News>> selectAllNews(){
        try {
            return MyResult.success(iNewsService.selectNewsList(new News()),"请求成功");
        } catch (Exception e) {
            e.printStackTrace();
            return MyResult.error("返回失败:"+e.getMessage());
        }
    }

    @GetMapping("/detail")
    public MyResult<News> seleNewsById(String id){
        try {
            iNewsService.updateWatch(id);
            News news = iNewsService.selectNewsById(id);
            news.setLike(iNewsService.selectLikes(id,""));
            return MyResult.success(news,"请求成功");
        } catch (Exception e) {
            e.printStackTrace();
            return MyResult.error("返回失败:"+e.getMessage());
        }
    }

    @PostMapping("/like")
    public MyResult updateLikes(@RequestBody News news){
        String id = news.getId();
        String userId = news.getUserId();
        String like = news.getLike();
        try {
            if(iNewsService.selectLikes(id,userId)==null){
                iNewsService.insertLikes(id,userId);
            }else{
                iNewsService.updateLikes(id,userId,like);
            }
            return MyResult.successMsg("成功");
        } catch (Exception e) {
            e.printStackTrace();
            return MyResult.error("失败");
        }

    }

}
