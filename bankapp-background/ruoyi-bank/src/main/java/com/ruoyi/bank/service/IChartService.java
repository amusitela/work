package com.ruoyi.bank.service;

import java.util.List;
import com.ruoyi.bank.domain.Chart;

/**
 * chartService接口
 * 
 * @author lyw
 * @date 2023-12-15
 */
public interface IChartService 
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
     * 批量删除chart
     * 
     * @param ids 需要删除的chart主键集合
     * @return 结果
     */
    public int deleteChartByIds(String[] ids);

    /**
     * 删除chart信息
     * 
     * @param id chart主键
     * @return 结果
     */
    public int deleteChartById(String id);
}
