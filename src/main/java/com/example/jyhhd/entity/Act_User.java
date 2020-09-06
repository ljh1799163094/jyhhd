package com.example.jyhhd.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class Act_User {
    @ApiModelProperty(value = "用户编号",name = "id")
    private String id;//用户编号
    @ApiModelProperty(value = "名称",name = "firstName")
    private String firstName;//名字
    @ApiModelProperty(value = "姓氏",name = "lastName")
    private String lastName;//姓氏
    @ApiModelProperty(value = "密码",name = "password")
    private String password;//密码
    @ApiModelProperty(value = "邮箱",name = "email")
    private String email;//邮箱

}
