package com.ruoyi.bank.mapper;

import java.util.List;
import com.ruoyi.bank.domain.Chart;

/**
 * chartMapper接口
 * 
 * @author lyw
 * @date 2023-12-15
 */
public interface ChartMapper 
{
    /**
     * 查询chart
     * 
     * @param id chart主键
     * @return chart
     */
    public Chart selectChartById(String id);

    /**
     * 查询chart列表
     * 
     * @param chart chart
     * @return chart集合
     */
    public List<Chart> selectChartList(Chart chart);

    /**
     * 新增chart
     * 
     * @param chart chart
     * @return 结果
     */
    public int insertChart(Chart chart);

    /**
     * 修改chart
     * 
     * @param chart chart
     * @return 结果
     */
    public int updateChart(Chart chart);

    /**
     * 删除chart
     * 
     * @param id chart主键
     * @return 结果
     */
    public int deleteChartById(String id);

    /**
     * 批量删除chart
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteChartByIds(String[] ids);
}
