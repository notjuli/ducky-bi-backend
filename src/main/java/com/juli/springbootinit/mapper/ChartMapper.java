package com.juli.springbootinit.mapper;

import com.juli.springbootinit.model.entity.Chart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
* @author juli
* @description 针对表【chart(图标信息表)】的数据库操作Mapper
* @Entity com.juli.springbootinit.model.entity.Chart
*/
public interface ChartMapper extends BaseMapper<Chart> {
    List<Map<String,Object>> queryChartData(String querySql); //queryChartData与ChartMapper中的id保持一致

}




