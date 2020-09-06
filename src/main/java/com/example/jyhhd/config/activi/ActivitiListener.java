package com.example.jyhhd.config.activi;

import com.example.jyhhd.config.websocket.WebSocketServer;
import lombok.SneakyThrows;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.task.IdentityLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
public class ActivitiListener implements TaskListener, ExecutionListener {


    @Override
    public void notify(DelegateExecution execution) {
        String eventName = execution.getEventName();
        if ("start".equals(eventName)) {
            System.out.println("start=========");
        }else if ("end".equals(eventName)) {
            System.out.println("end=========");
        }
        else if ("take".equals(eventName)) {
            System.out.println("take=========");
        }
    }

    @Override
    public void notify(DelegateTask delegateTask) {
        CopyOnWriteArraySet<WebSocketServer> webSocketSet = WebSocketServer.webSocketSet;
        String eventName = delegateTask.getEventName();
        String id = delegateTask.getId();
        System.err.println(id+"=========任务流程编号");
        String assignee = delegateTask.getAssignee();
        Set<IdentityLink> candidates = delegateTask.getCandidates();//候选人
        if(candidates != null && candidates.size()>0){
            Iterator<IdentityLink> iterator = candidates.iterator();
            while (iterator.hasNext()){
                IdentityLink next = iterator.next();
                String userId = next.getUserId();
                System.err.println(userId+"====候选人===");
            }
        }
        System.err.println(assignee+"=========代理人====");
        if ("create".endsWith(eventName)) {
            System.out.println("create=========");
        }else if ("assignment".endsWith(eventName)) {
            System.out.println("assignment========");
        }else if ("complete".endsWith(eventName)) {
            System.out.println("complete===========");
        }else if ("delete".endsWith(eventName)) {
            System.out.println("delete=============");
        }
        if(candidates==null || candidates.size()==0||assignee==null) {
            System.err.println("=============审核没有代理人===");
            List<String> list = new ArrayList<>();
            try {
                Iterator<WebSocketServer> iterator = webSocketSet.iterator();
                while (iterator.hasNext()) {
                    WebSocketServer next = iterator.next();
                    next.sendMessage("刘欢，那英");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            list.add("刘欢");
            list.add("那英");
           // delegateTask.setVariable("userId", list);
        }
    }
}
