package com.yupi.springbootinit.manager;

import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.yucongming.dev.client.YuCongMingClient;
import com.yupi.yucongming.dev.common.BaseResponse;
import com.yupi.yucongming.dev.model.DevChatRequest;
import com.yupi.yucongming.dev.model.DevChatResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AiManager {
    @Resource
    private YuCongMingClient yuCongMingClient;

    /**
     * AI 对话
     *
     * @return
     */

    public String doChat(long modelId, String message){
        // 构造请求参数
        DevChatRequest devChatRequest = new DevChatRequest();
        // 设置模型id，后加L，转化成long型
        devChatRequest.setModelId(modelId);
        // 传入消息
        devChatRequest.setMessage(message);
        // 获取响应结果
        BaseResponse<DevChatResponse> response = yuCongMingClient.doChat(devChatRequest);
        System.out.println();
        if(response == null){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"AI响应错误");
        }
        // 返回AI响应文本
        return response.getData().getContent();
    }
}
