package com.example.jyhhd.entity;

import lombok.Data;

@Data
public class Table2 {
    private String xh;//序号
    private String lineName;//线路或机组名称
    private Integer jdbhsjts;//继电保护实际套数
    private Integer sjtrts;//实际投入套数
    private String trl;//投入率
    private String node;//备注
}
