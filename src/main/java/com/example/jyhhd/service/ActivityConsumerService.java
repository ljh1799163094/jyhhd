package com.example.jyhhd.service;

import com.example.jyhhd.Result;

public interface ActivityConsumerService {

     boolean startActivityDemo();

    Result getTaskListByUserId(String userId,String key, Result result);

    Result completeTask(String taskId, Result result);
}
