package com.example.jyhhd.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class Act_Group {
    @ApiModelProperty(value = "编号",name = "id")
    private String id;//编号
    @ApiModelProperty(value = "组名",name = "name")
    private String name;//组名
    @ApiModelProperty(value = "类型",name = "type")
    private String type;//类型
}
