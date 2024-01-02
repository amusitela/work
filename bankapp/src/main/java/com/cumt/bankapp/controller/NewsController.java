package com.cumt.bankapp.controller;

import com.cumt.bankapp.domain.News;
import com.cumt.bankapp.service.INewsService;
import com.cumt.bankapp.tools.getData.GetNew;
import com.cumt.bankapp.tools.jwt.BaseContext;
import com.cumt.common.MyResult;
import liquibase.repackaged.net.sf.jsqlparser.expression.StringValue;
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

    @GetMapping("/list")
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
            String userId = BaseContext.getCurrentId();
            iNewsService.updateWatch(id);
            News news = iNewsService.selectNewsById(id);
            news.setLikes(iNewsService.selectLikes(id,""));
            if (iNewsService.selectLikes1(id, userId)==0){
                news.setLike("0");
            }else {
                news.setLike(String.valueOf(iNewsService.selectLikes2(id, userId)));
            }
            System.out.println(news.getLike());
        return MyResult.success(news,"请求成功");
        } catch (Exception e) {
            e.printStackTrace();
            return MyResult.error("返回失败:"+e.getMessage());
        }
    }

    @PostMapping("/like")
    public MyResult updateLikes(@RequestBody News news){
        String id = news.getId();
        String userId = BaseContext.getCurrentId();
        System.out.println(userId);
        String like = news.getLike();
        System.out.println(like);
        try {
            if(iNewsService.selectLikes1(id,userId)==0){
                iNewsService.insertLikes(id,userId);
                System.out.println("插入成功");
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
