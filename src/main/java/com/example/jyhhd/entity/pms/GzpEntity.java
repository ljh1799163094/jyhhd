package com.example.jyhhd.entity.pms;

import lombok.Data;

import java.util.Date;

@Data
public class GzpEntity {
     private String tkpSht;//"工作票类型",
     private String ttkId;//工作票编号",
     private String unitNo;//机组主键",
     private String unitNam;//机组名称",
     private String elcNam;//设备名称",
     private String elcId;//设备编码",
     private Date firstDtm;//开票时间",
     private String ttkperId;//负责人账号",
     private String ttkperNam;//负责人姓名",
     private String ttkperDsc;//工作班成员姓名",
     private String ttkDscId;//工作班成员账号",
     private String scratchUsrId;//安全员账号",
     private String scratchUsrNam;//安全员姓名",
     private String plaNo;//部门主键",
     private String plaNam;//部门名称",
     private String otcrwNo;//班组主键",
     private String otcrwNam;//班组名称",
     private Date planbegDtm;//开始时间",
     private Date planenDtm;//结束时间",
     private String adrDsc;//工作地点",
     private String ttkAdr;//工作内容",
     private String ttkSta;//状态id",
     private String ttkStaNam;//状态名称",
     private String ttkNot;//评价信息"
}
