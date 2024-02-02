package com.juli.springbootinit.manager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class AiManagerTest {

    @Resource
    private AiManager aiManager;

    @Test
    void doChat() {
        String answer = aiManager.doChat(1721129941246390274L,"分析需求：\n" +
                "分析景点的客流量\n" +
                "原始数据：\n" +
                "日期,客流量\n" +
                "1号,1000\n" +
                "2号,5000\n" +
                "3号,5500");
        System.out.println(answer);
    }
}