package com.cumt.bankapp.service;

import com.cumt.bankapp.domain.Chart;

/**
 * @author 李易蔚
 * @version 1.0
 */

@SuppressWarnings({"all"})
public interface IChartService {
    /**
     *查询详细图标
     * @param id
     * @return  Chart
     * */
    public Chart selectNewsById(String id);
}
