package com.juli.springbootinit.utils;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Excel相关工具类
 */
@Slf4j
public class ExcelUtils {
    /**
     * excel转csv
     * @param multipartFile
     * @return
     */
    //MultipartFile允许你在Java应用程序中接收、处理和保存来自客户端的文件数据。
    public static String excelToCsv(MultipartFile multipartFile) {
        // 1. 读取数据
        List<Map<Integer, String>> list = null;
        try {
            list = EasyExcel.read(multipartFile.getInputStream())
                    .excelType(ExcelTypeEnum.XLSX)
                    .sheet()
                    .headRowNumber(0)
                    .doReadSync();
        } catch (IOException e) {
            log.error("csv转化错误");
        }

        // 2. 数据清洗
        if(CollUtil.isEmpty(list)){
            return "";
        }

        // 3. 转化为csv文件
        StringBuilder stringBuilder =new StringBuilder();
        // 读取表头
        LinkedHashMap<Integer,String> headMap = (LinkedHashMap)list.get(0);
        List<String> headerList = headMap.values().stream().filter(header -> ObjectUtils.isNotEmpty(header)).collect(Collectors.toList());
        stringBuilder.append(StringUtils.join(headerList,",")).append("\n");

        // 读取数据
        for(int i = 1;i < list.size();i++){
            LinkedHashMap<Integer,String> dataMap = (LinkedHashMap)list.get(i);
            List<String> dataList = dataMap.values().stream().filter(header -> ObjectUtils.isNotEmpty(header)).collect(Collectors.toList());
            stringBuilder.append(StringUtils.join(dataList,",")).append("\n");
        }
        return stringBuilder.toString();
    }
}

//测试输出list、LinkedHashMap和LinkedHashMap。value的代码
//    public static String excelToCsv(MultipartFile multipartFile) {
////MultipartFile允许你在Java应用程序中接收、处理和保存来自客户端的文件数据。
//        File file = null;
//        try {
//            file = ResourceUtils.getFile("classpath:网站数据.xlsx");
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        // 读取数据
//        List<Map<Integer, String>> list = EasyExcel.read(file)
//                .excelType(ExcelTypeEnum.XLSX)
//                .sheet()
//                .headRowNumber(0)
//                .doReadSync();
//        System.out.println("数据的list:" + list);
//        System.out.println();
//        // 数据清洗
//        if(CollUtil.isEmpty(list)){
//            return "";
//        }
//        // 转化为csv文件
//        // 读取数据
//        System.out.println("数据的LinkedHashMap:");
//        for(int i = 0;i < list.size();i++){
//            LinkedHashMap<Integer,String> dataMap = (LinkedHashMap)list.get(i);
//            System.out.println(dataMap);
//        }
//
//        System.out.println();
//        System.out.println("数据的values:");
//        for(int i = 0;i < list.size();i++){
//            LinkedHashMap<Integer,String> dataMap = (LinkedHashMap)list.get(i);
//            System.out.println(StringUtils.join(dataMap.values(),","));
//        }
//
//        System.out.println();
//        return "";
//    }
//}
