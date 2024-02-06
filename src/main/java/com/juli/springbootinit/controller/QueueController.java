package com.juli.springbootinit.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import com.juli.springbootinit.annotation.AuthCheck;
import com.juli.springbootinit.common.BaseResponse;
import com.juli.springbootinit.common.DeleteRequest;
import com.juli.springbootinit.common.ErrorCode;
import com.juli.springbootinit.common.ResultUtils;
import com.juli.springbootinit.constant.CommonConstant;
import com.juli.springbootinit.constant.UserConstant;
import com.juli.springbootinit.exception.BusinessException;
import com.juli.springbootinit.exception.ThrowUtils;
import com.juli.springbootinit.manager.AiManager;
import com.juli.springbootinit.manager.RedisLimiterManager;
import com.juli.springbootinit.model.dto.chart.*;
import com.juli.springbootinit.model.entity.Chart;
import com.juli.springbootinit.model.entity.User;
import com.juli.springbootinit.model.vo.BiResponse;
import com.juli.springbootinit.service.ChartService;
import com.juli.springbootinit.service.UserService;
import com.juli.springbootinit.utils.ExcelUtils;
import com.juli.springbootinit.utils.SqlUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 队列测试
 *
 * @author Julii
 */
@RestController
@RequestMapping("/queue")
@Slf4j
public class QueueController {
    @Resource
    private ThreadPoolExecutor threadPoolExecutor;

    @GetMapping("/add")
    // 将任务添加到线程池
    public void add(String name){
        CompletableFuture.runAsync(()->{
            System.out.println("执行任务中：" + name +"，执行人：" + Thread.currentThread().getName());
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },threadPoolExecutor);
    }

    @GetMapping("/get")
    // 获取线程池的状态信息
    public String get(){
        Map<String,Object> map = new HashMap<>();
        int size = threadPoolExecutor.getQueue().size();
        map.put("队列长度",size);
        long taskCount = threadPoolExecutor.getTaskCount();
        map.put("任务总数",taskCount);
        long completeTaskCount = threadPoolExecutor.getCompletedTaskCount();
        map.put("已完成任务数",completeTaskCount);
        long activeCount = threadPoolExecutor.getActiveCount();
        map.put("正在工作的线程数",activeCount);
        return JSONUtil.toJsonStr(map);
    }
}