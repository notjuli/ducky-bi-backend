package com.juli.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.juli.springbootinit.service.ChartService;
import com.juli.springbootinit.model.entity.Chart;
import com.juli.springbootinit.mapper.ChartMapper;
import org.springframework.stereotype.Service;

/**
* @author paranoid
* @description 针对表【chart(图标信息表)】的数据库操作Service实现
* @createDate 2023-11-02 22:26:28
*/
@Service
public class ChartServiceImpl extends ServiceImpl<ChartMapper, Chart>
    implements ChartService {

}




