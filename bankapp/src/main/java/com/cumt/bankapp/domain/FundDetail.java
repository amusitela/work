package com.cumt.bankapp.domain;

import lombok.Data;

import java.util.List;

@Data
public class FundDetail {
    private List<String> tag; // 基金标签
    private String name;      // 基金名
    private String fundId;    // 基金主键id
    private List<Rate> chart; // 涨幅数据

    // 内部类 Rate
    public static class Rate {
        private String rate; // 涨幅

        public Rate(String rate) {
            this.rate = rate;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }


        // 构造函数、getter 和 setter 方法
    }

    public FundDetail(List<String> tag, String name, String fundId, List<Rate> chart) {
        this.tag = tag;
        this.name = name;
        this.fundId = fundId;
        this.chart = chart;
    }


}