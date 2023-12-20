package com.ruoyi.bank.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bank.mapper.ChartMapper;
import com.ruoyi.bank.domain.Chart;
import com.ruoyi.bank.service.IChartService;

/**
 * chartService业务层处理
 * 
 * @author lyw
 * @date 2023-12-15
 */
@Service
public class ChartServiceImpl implements IChartService 
{
    @Autowired
    private ChartMapper chartMapper;

    /**
     * 查询chart
     * 
     * @param id chart主键
     * @return chart
     */
    @Override
    public Chart selectChartById(String id)
    {
        return chartMapper.selectChartById(id);
    }

    /**
     * 查询chart列表
     * 
     * @param chart chart
     * @return chart
     */
    @Override
    public List<Chart> selectChartList(Chart chart)
    {
        return chartMapper.selectChartList(chart);
    }

    /**
     * 新增chart
     * 
     * @param chart chart
     * @return 结果
     */
    @Override
    public int insertChart(Chart chart)
    {
        return chartMapper.insertChart(chart);
    }

    /**
     * 修改chart
     * 
     * @param chart chart
     * @return 结果
     */
    @Override
    public int updateChart(Chart chart)
    {
        return chartMapper.updateChart(chart);
    }

    /**
     * 批量删除chart
     * 
     * @param ids 需要删除的chart主键
     * @return 结果
     */
    @Override
    public int deleteChartByIds(String[] ids)
    {
        return chartMapper.deleteChartByIds(ids);
    }

    /**
     * 删除chart信息
     * 
     * @param id chart主键
     * @return 结果
     */
    @Override
    public int deleteChartById(String id)
    {
        return chartMapper.deleteChartById(id);
    }
}
