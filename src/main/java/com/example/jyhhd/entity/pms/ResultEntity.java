package com.example.jyhhd.entity.pms;

import lombok.Data;

@Data
public class ResultEntity {
    private String flg;//状态码
    private String msg;//状态信息
    private String total;//数量
    private Object rows;//返回结果集
}
