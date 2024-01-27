package com.yupi.springbootinit.model.vo;

import lombok.Data;

@Data
public class BiResponse {
    private long chartId;
    private String genChart;

    private String genResult;
}
