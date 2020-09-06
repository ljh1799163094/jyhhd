package com.example.jyhhd.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface Act_Mapper {

    //根据总任务Id和节点名称删除任务
    void deleteTaskByProcInstIdAndName(@Param("procInstId") String procInstId, @Param("name") String name);

    //根据任务编号查询总任务编号
    String selectProInstIdByID(@Param("id") String id);

    //根据任务编号查询已完成的任务历史记录编号
    String selectFinishHistoryByTask(@Param("taskId") String taskId);

    //根据任务编号查询未完成任务的历史记录
    List<String> selectNotFinishHistoryByTask(@Param("taskId") String taskId);
}
