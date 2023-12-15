package com.cumt.bankapp.controller;


import com.cumt.bankapp.domain.Chart;
import com.cumt.bankapp.domain.Fund;
import com.cumt.bankapp.service.IChartService;
import com.cumt.bankapp.service.IFundService;
import com.cumt.bankapp.tools.getData.RequestFund;
import com.cumt.common.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/fund")
public class FundController {

    @Autowired
    private IFundService iFundService;

    @Autowired
    private IChartService iChartService;

    @GetMapping("/a")
    public int insertAllFund(){
        RequestFund requestFund = new RequestFund();
        List<Fund> fund = requestFund.getFund();
        return  iFundService.insertBatchFunds(fund);
    }

    @GetMapping("list")
    public MyResult<List<Fund>> selectAllFund(){
        try {
            return MyResult.success(iFundService.selectFundList(new Fund()),"请求成功");
        } catch (Exception e) {
            e.printStackTrace();
            return MyResult.error("返回失败:"+e.getMessage());
        }
    }

    @GetMapping("/detail")
    public MyResult<Fund> seleFundById(String id){
        try {
            return MyResult.success(iFundService.selectFundById(id),"请求成功");
        } catch (Exception e) {
            e.printStackTrace();
            return MyResult.error("返回失败:"+e.getMessage());
        }
    }

    @PostMapping("/chart")
    public MyResult<Chart> getChart(@RequestBody String id){
        try {
            return MyResult.success(iChartService.selectNewsById(id),"请求成功");
        } catch (Exception e) {
            e.printStackTrace();
            return MyResult.error("请求失败");
        }
    }



}