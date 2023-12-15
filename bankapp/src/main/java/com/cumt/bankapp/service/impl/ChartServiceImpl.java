package com.cumt.bankapp.service.impl;

import com.cumt.bankapp.domain.Chart;
import com.cumt.bankapp.mapper.ChartMapper;
import com.cumt.bankapp.service.IChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 李易蔚
 * @version 1.0
 */

@SuppressWarnings({"all"})
@Service
public class ChartServiceImpl implements IChartService {

    @Autowired
    private ChartMapper chartMapper;

    /**
     *查询详细图标
     * @param id
     * @return  Chart
     * */
    @Override
    public Chart selectNewsById(String id) {
        return chartMapper.selectNewsById(id);
    }
}
