package com.example.jyhhd.service.activity.impl;

import com.example.jyhhd.Result;
import com.example.jyhhd.mapper.Act_Mapper;
import com.example.jyhhd.service.activity.Act_Service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Act_ServiceImpl implements Act_Service {


    @Resource
    private Act_Mapper act_mapper;

    @Override
   public void deleteTaskByProcInstIdAndName(String procInstId,String name){
        act_mapper.deleteTaskByProcInstIdAndName(procInstId,name);
    }

    @Override
    public String selectProInstIdByID(String id) {
        String proInstId=act_mapper.selectProInstIdByID(id);
        return proInstId;
    }

    @Override
    public Result withdraw(String taskId,Result result) {
        //根据任务编号查询已完成任务的历史记录
        String id=act_mapper.selectFinishHistoryByTask(taskId);
        if(!StringUtils.isEmpty(id)){
            result.setCode(-200);
            result.setMessage("此任务已经执行完不可以撤回");
            return result;
        }
        //根据任务编号查询未完成任务的历史记录
        List<String> list=act_mapper.selectNotFinishHistoryByTask(taskId);
        if(list.size()>0){
            result.setObj(list.get(0));
            result.setCode(200);
            result.setMessage("获取成功");
        }else {
            result.setCode(-200);
            result.setMessage("你还未提交，不能撤回");
        }
        return result;
    }


}
