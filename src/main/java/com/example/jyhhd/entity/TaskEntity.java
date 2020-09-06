package com.example.jyhhd.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * 任务实体类
 */
@Data
public class TaskEntity {
    private String id;//任务编号
    private String comment;//意见
    private String name;//任务名称
    private Date createTime;//任务的创建时间
    private String assignee;//任务的办理人
    private String processInstanceId;//流程实例ID
    private String executionId;//执行对象ID
    private String processDefinitionId;//流程定义ID
}
