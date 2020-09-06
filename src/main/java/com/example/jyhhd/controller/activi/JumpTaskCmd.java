package com.example.jyhhd.controller.activi;

import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.ActivitiEngineAgenda;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ExecutionEntityManager;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntityManager;
import org.activiti.engine.impl.util.ProcessDefinitionUtil;
import org.activiti.engine.task.Comment;
import org.springframework.stereotype.Component;

/**
 * Created by 陈书山 on 2016/10/19.
 */
public class JumpTaskCmd implements Command<Comment> {

    /**
     * 当前TaskId
     */
    private String currentTaskId;

    /**
     * 目标流程定义节点Id
     */
    private String targetNodeId;

    public JumpTaskCmd(String currentTaskId,String targetNodeId) {
        this.currentTaskId = currentTaskId;
        this.targetNodeId = targetNodeId;
    }

    public String getCurrentTaskId() {
        return currentTaskId;
    }

    public void setCurrentTaskId(String currentTaskId) {
        this.currentTaskId = currentTaskId;
    }

    public String getTargetNodeId() {
        return targetNodeId;
    }

    public void setTargetNodeId(String targetNodeId) {
        this.targetNodeId = targetNodeId;
    }

    @Override
    public Comment execute(CommandContext commandContext) {
        ExecutionEntityManager executionEntityManager = commandContext.getExecutionEntityManager();
        TaskEntityManager taskEntityManager = commandContext.getTaskEntityManager();
        TaskEntity taskEntity = taskEntityManager.findById(this.currentTaskId);
        ExecutionEntity executionEntity = executionEntityManager.findById(taskEntity.getExecutionId());
        Process process = ProcessDefinitionUtil.getProcess(executionEntity.getProcessDefinitionId());
        taskEntityManager.deleteTask(taskEntity, "移动节点", true, true);
        FlowElement targetFlowElement = process.getFlowElement(targetNodeId);
        executionEntity.setCurrentFlowElement(targetFlowElement);
        ActivitiEngineAgenda agenda = commandContext.getAgenda();
        agenda.planContinueProcessInCompensation(executionEntity);
        return null;
    }

   /* //审批驳回
    payApplyStatus = EnumPayApplyStatus.审批驳回.getIndex();
    TaskServiceImpl taskServiceImpl = (TaskServiceImpl) taskService;
    taskServiceImpl.getCommandExecutor().execute(new JumpTaskCmd(taskId, "end"));*/
}