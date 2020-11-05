package com.example.jyhhd.entity.pms;

import lombok.Data;

import java.util.Date;

@Data
public class CzpEntity {
    private String opermstId;//操作票编号",
    private String opermstTyp;//操作票类型",
    private String unitNo;//机组主键",
    private String unitNam;//机组名称",
    private String elcNam;//设备名称",
    private String elcNo;//设备编码",
    private String fstusrDtm;//申请时间",
    private String ordId;//发令人账号",
    private String ordNam;//发令人姓名",
    private String revordId;//受令人账号",
    private String revordNam;//受令人姓名",
    private Date ordDtm;//受令时间",
    private String fstusrId;//操作人姓名",
    private String fstusrNam;//操作人姓名",
    private String keeperId;//操作人账号",
    private String keeperNam;//监护人姓名",
    private String plaNo;//部门主键",
    private String plaNam;//部门名称",
    private String crwNo;//班组主键",
    private String crwNam;//班组主键",
    private String opermstSht;//操作内容",
    private String opermstSta;//状态编号",
    private String opermstStaNam;//状态名称"
}
