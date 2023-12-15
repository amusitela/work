package com.cumt.bankapp.domain;

import lombok.Data;

/**
 * @author 李易蔚
 * @version 1.0
 */

@SuppressWarnings({"all"})

@Data
public class Fund {
    private String dayIncomeRatio;
    private String product_name;
    private String fundTypeName;
    private String productRiskLevel;
    private String accumulativeNav;
    private String productId;

    @Override
    public String toString() {
        return "Fund{" +
                "dayIncomeRatio='" + dayIncomeRatio + '\'' +
                ", product_name='" + product_name + '\'' +
                ", fundTypeName='" + fundTypeName + '\'' +
                ", productRiskLevel='" + productRiskLevel + '\'' +
                ", accumulativeNav='" + accumulativeNav + '\'' +
                ", productId='" + productId + '\'' +
                '}';
    }


}
