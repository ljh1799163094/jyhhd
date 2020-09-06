package com.example.jyhhd.entity;

import lombok.Data;

import java.util.Date;

/**
 * 220kV及以上变电设备色谱（09）月报表
 */
@Data
public class Table1 {
    private String id;//编号
    private String  sbName;//设备名称
    private String voltageKind;//电压等级
    private Date cyrq;//采样日期
    private Date syrq;//试验日期
    private Double ch4;//
    private Double c2h6;//
    private Double c2h4;
    private Double c2h2;
    private Double zj;//总烃
    private Double h2;
    private Double co;
    private Double co2;
    private String jl;//结论
    private String bz;//备注

}
