package com.example.jyhhd.entity;

import lombok.Data;

/**
 * 实时监督坑口excl的内容
 */
@Data
public class SSJDKK implements Cloneable{
    private String dcName;//电厂名
    private String zy;//专业
    private String jdxm;//监督项目
    private String jz;//机组
    private String kks;//设备kks码
    private String elcName;//设备名称
    private String cdmc;//测点名称
    private String cdmc_old;//修改前的测点名称
    private String cdms;//测点描述
    private String dw;//单位
    private String cdbds;//测点表达式
    private String cxcxjfz;//持续超限几分钟
    private String gjmc;//告警名称
    private String gjxz;//告警限值
    private String gjbds;//告警表达式
    private String gjms;//告警描述
    private String gjdj;//告警等级

}
