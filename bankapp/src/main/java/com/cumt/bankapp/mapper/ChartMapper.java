package com.cumt.bankapp.mapper;

import com.cumt.bankapp.domain.Chart;
import org.springframework.stereotype.Repository;

/**
 * @author 李易蔚
 * @version 1.0
 */

@SuppressWarnings({"all"})
@Repository
public interface ChartMapper {
    /**
     *查询详细图标
     * @param id
     * @return  Chart
     * */
    public Chart selectNewsById(String id);
}
