package com.example.jyhhd.service.activity;

import com.example.jyhhd.Result;

public interface Act_Service {

    //在并行任务中 ，一个并行节点驳回给申请人，
    // 下次申请人再提交还会提交到所有并行点上，但是上一次未驳回的并行节点的任务还在但已经无效果，需要删除
    //总任务Id和节点名称
    void deleteTaskByProcInstIdAndName(String procInstId,String name);

    //根据任务编号查询总任务编号
    String selectProInstIdByID(String id);

    //撤回
    Result withdraw(String taskId,Result result);
}
