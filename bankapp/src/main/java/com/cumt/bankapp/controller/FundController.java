package com.cumt.bankapp.controller;

import com.cumt.bankapp.domain.Fund;
import com.cumt.bankapp.domain.FundDetail;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Arrays;

@RestController
public class FundController {

    @GetMapping("/fund")
    public List<Fund> getAllFunds() {
        return Arrays.asList(
                new Fund("10%", "基金A", "1", "高风险高回报"),
                // 更多基金数据...
        );
    }

    @GetMapping("/fund/{fundId}")
    public FundDetail getFundDetail(@PathVariable String fundId) {
        // 在实际应用中，您需要根据 fundId 从数据库或其他服务中获取数据
        // 这里使用静态数据作为示例
        return new FundDetail(
                Arrays.asList("标签1", "标签2"),
                "基金A",
                fundId,
                Arrays.asList(
                        new FundDetail.Rate("5%"),
                        // 更多 Rate 数据...
                )
        );
    }
}

