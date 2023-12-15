package com.cumt.bankapp.tools.getData;

import com.cumt.bankapp.domain.News;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * @author 李易蔚
 * @version 1.0
 */

@SuppressWarnings({"all"})
public  class GetNew {


public static ArrayList<News> getAllNews() {
    ArrayList<News> news = new ArrayList<>();
    HashMap<String, String> ids = getId();
    Set<Map.Entry<String, String>> entries = ids.entrySet();
    for (Map.Entry<String,String> id : entries
         ) {
        News aNew = getNew(id.getKey());
        aNew.setAuthor(id.getValue());
        news.add(aNew);
    }
    return news;
}

public static HashMap<String,String> getId() {
    String requestUrl = "https://openapi.boc.cn/unlogin/ezcms/get_strategy_infor_list";
    String payload = "{\"lastId\":\"1108744\",\"lastInforType\":\"11001\",\"channelId\":\"110000\",\"readerId\":\"\",\"loadType\":\"1\",\"needFullLifeCycle\":false,\"ruleId\":\"10331\",\"pagesize\":\"50\"}";
    HashMap<String, String> map = new HashMap<>();
    try {
        URL url = new URL(requestUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // 设置方法为post
        connection.setRequestMethod("POST");

        // 设置请求头
        Map<String, String> headers = new HashMap<>();
        headers.put("Host", "openapi.boc.cn");
        headers.put("Connection", "keep-alive");
        headers.put("Content-Length", "153");
        headers.put("Pragma", "no-cache");
        headers.put("Cache-Control", "no-cache");
        headers.put("timeout", "100000");
        headers.put("clentid", "548");
        headers.put("Content-Type", "application/json;charset=UTF-8");
        headers.put("Accept", "application/json, text/plain, */*");
        headers.put("clientid", "548");
        headers.put("userid", "");
        headers.put("User-Agent", "Mozilla/5.0 (Linux; Android 11; 21051182C Build/RKQ1.200826.002; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/94.0.4606.85 Mobile Safari/537.36/bocapp(8.3.0);lsta:0;;sessionId:94129ad5-249b-43ae-8cab-1a8006a3afec;cifNumber:;appSequence:22200;");
        headers.put("trantm", "143205");
        headers.put("uuid", "GZ6lATGQn0kvts0G73miQLRHUBXqhorS");
        headers.put("trandt", "20231213");
        headers.put("Origin", "https://openapi.boc.cn");
        headers.put("X-Requested-With", "com.chinamworld.bocmbci");
        headers.put("Sec-Fetch-Site", "same-origin");
        headers.put("Sec-Fetch-Mode", "cors");
        headers.put("Sec-Fetch-Dest", "empty");
        headers.put("Referer", "https://openapi.boc.cn/inews/index.html");
        headers.put("Accept-Encoding", "gzip, deflate");
        headers.put("Accept-Language", "zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7");
        headers.put("Cookie", "sajssdk_2015_cross_bocop-app-infonew_user=1; sensorsdata2015jssdkcrossbocop-app-info=%7B%22distinct_id%22%3A%22bocop-app-info%22%2C%22first_id%22%3A%22%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiYm9jb3AtYXBwLWluZm8ifQ%3D%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%22%2C%22value%22%3A%22%22%7D%2C%22%24device_id%22%3A%22bocop-app-info%22%7D");

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            connection.setRequestProperty(entry.getKey(), entry.getValue());
        }

        // 打开流
        connection.setDoOutput(true);

        // 写入荷载
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = payload.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        //读响应
        int responseCode = connection.getResponseCode();
//        System.out.println("Response Code: " + responseCode);

        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

        try {
            JSONObject jsonObject = new JSONObject(response.toString());
            JSONArray list = jsonObject.getJSONArray("list");

            for (int i = 0; i < list.length(); i++) {
                JSONObject item = list.getJSONObject(i);
                String id = item.getString("id");
                String authorName = item.getString("authorName");
                //将id值存入数组
                map.put(id,authorName);
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return map;
}

public static News getNew(String ID) {
    String requestUrl = "https://openapi.boc.cn/unlogin/query_article_detail";
    ArrayList<News> news = new ArrayList<>();

    try {
        URL url = new URL(requestUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        //设置为post请求
        connection.setRequestMethod("POST");

        //设置请求头
        Map<String, String> headers = new HashMap<>();
        headers.put("Host", "openapi.boc.cn");
        headers.put("Connection", "keep-alive");
        headers.put("Content-Length", "84");
        headers.put("Pragma", "no-cache");
        headers.put("Cache-Control", "no-cache");
        headers.put("timeout", "100000");
        headers.put("clentid", "548");
        headers.put("Content-Type", "application/json;charset=UTF-8");
        headers.put("Accept", "application/json, text/plain, */*");
        headers.put("clientid", "548");
        headers.put("userid", "");
        headers.put("User-Agent", "Mozilla/5.0 (Linux; Android 11; 21051182C Build/RKQ1.200826.002; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/94.0.4606.85 Mobile Safari/537.36/bocapp(8.3.0);lsta:0;;sessionId:840888a4-23a6-4eca-b9a4-31f0ce47aa68;cifNumber:;appSequence:22200;");
        headers.put("trantm", "133941");
        headers.put("uuid", "r0gl3s3eO7HtrJzev8tqyP3tlNZv1Toa");
        headers.put("trandt", "20231213");
        headers.put("Origin", "https://openapi.boc.cn");
        headers.put("X-Requested-With", "com.chinamworld.bocmbci");
        headers.put("Sec-Fetch-Site", "same-origin");
        headers.put("Sec-Fetch-Mode", "cors");
        headers.put("Sec-Fetch-Dest", "empty");
        headers.put("Referer", "https://openapi.boc.cn/inews/index.html");
        headers.put("Accept-Encoding", "gzip, deflate");
        headers.put("Accept-Language", "zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7");
        headers.put("Cookie", "sajssdk_2015_cross_bocop-app-infonew_user=1; sensorsdata2015jssdkcrossbocop-app-info=%7B%22distinct_id%22%3A%22bocop-app-info%22%2C%22first_id%22%3A%22%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiYm9jb3AtYXBwLWluZm8ifQ%3D%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%22%2C%22value%22%3A%22%22%7D%2C%22%24device_id%22%3A%22bocop-app-info%22%7D");

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            connection.setRequestProperty(entry.getKey(), entry.getValue());
        }

        // 设置json
        String jsonData = "{\"channelId\":\"110000\",\"readerId\":\"\",\"articleId\":\""+ID+"\",\"needFullLifeCycle\":false}";

        //打开流
        connection.setDoOutput(true);

        //读请求数据
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonData.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        // 读响应
        int responseCode = connection.getResponseCode();
//        System.out.println("Response Code: " + responseCode);

        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            try {
                JSONObject item = new JSONObject(response.toString());
                String id = item.getString("id");
                String title = item.getString("name");
                String content = item.getString("content");
                String image = item.getString("imageUrl");
                News news1 = new News();
                news1.setId(id);
                news1.setTitle(title);
                news1.setContent(content);
                news1.setImage(image);

             return news1;
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
  }
}

