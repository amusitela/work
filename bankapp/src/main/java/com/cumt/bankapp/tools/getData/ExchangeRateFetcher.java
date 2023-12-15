package com.cumt.bankapp.tools.getData;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.JSONObject;

/**
 * @author 李易蔚
 * @version 1.0
 */

@SuppressWarnings({"all"})
public class ExchangeRateFetcher {

//    public static void main(String[] args) {
//        exchangeRate("USD","CNY");
//
//        JSONObject json = exchangeRateAll("USD");
//        System.out.println(json.toString(4)); // 美化输出
//    }

public static double exchangeRate(String from, String to) {
    String now = from;
    String requestUrl = "https://api.exchangerate-api.com/v4/latest/" + now;

    try {
        URL url = new URL(requestUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String s = response.toString();
            String[] split = s.split(to);
            String[] split1 = split[1].split("[:,\"]");

            double ans = Double.parseDouble(split1[2]);
            //System.out.println(ans);
            return ans;
        } else {
            System.out.println("GET请求未成功。");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return -1;
}


    public static JSONObject exchangeRateAll(String from) {
        String now = from;
        String requestUrl = "https://api.exchangerate-api.com/v4/latest/" + now;

        try {
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JSONObject jsonResponse = new JSONObject(response.toString());
                return jsonResponse.getJSONObject("rates");

            } else {
                return new JSONObject().put("error", "请求失败，响应码：" + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONObject().put("exception", "请求异常：" + e.getMessage());
        }
    }


}
