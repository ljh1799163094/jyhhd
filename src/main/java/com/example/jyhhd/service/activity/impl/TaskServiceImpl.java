package com.example.jyhhd.service.activity.impl;

import com.example.jyhhd.service.activity.Act_TaskService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.VariableInstance;
import org.activiti.engine.task.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.*;

@Service
public class TaskServiceImpl implements Act_TaskService {

    @Resource
    private TaskService taskService;

    @Override
    public Task newTask() {
        return null;
    }

    @Override
    public Task newTask(String taskId) {
        return null;
    }

    @Override
    public void saveTask(Task task) {

    }

    @Override
    public void deleteTask(String taskId) {

    }

    @Override
    public void deleteTasks(Collection<String> taskIds) {

    }

    @Override
    public void deleteTask(String taskId, boolean cascade) {

    }

    @Override
    public void deleteTasks(Collection<String> taskIds, boolean cascade) {

    }

    @Override
    public void deleteTask(String taskId, String deleteReason) {

    }

    @Override
    public void deleteTasks(Collection<String> taskIds, String deleteReason) {

    }

    @Override
    public void claim(String taskId, String userId) {

    }

    @Override
    public void unclaim(String taskId) {

    }

    @Override
    public void complete(String taskId) {

    }

    @Override
    public void delegateTask(String taskId, String userId) {

    }

    @Override
    public void resolveTask(String taskId) {

    }

    @Override
    public void resolveTask(String taskId, Map<String, Object> variables) {

    }

    @Override
    public void complete(String taskId, Map<String, Object> variables) {

    }

    @Override
    public void complete(String taskId, Map<String, Object> variables, boolean localScope) {

    }

    @Override
    public void setAssignee(String taskId, String userId) {

    }

    @Override
    public void setOwner(String taskId, String userId) {

    }

    @Override
    public List<IdentityLink> getIdentityLinksForTask(String taskId) {
        return null;
    }

    @Override
    public void addCandidateUser(String taskId, String userId) {

    }

    @Override
    public void addCandidateGroup(String taskId, String groupId) {

    }

    @Override
    public void addUserIdentityLink(String taskId, String userId, String identityLinkType) {

    }

    @Override
    public void addGroupIdentityLink(String taskId, String groupId, String identityLinkType) {

    }

    @Override
    public void deleteCandidateUser(String taskId, String userId) {

    }

    @Override
    public void deleteCandidateGroup(String taskId, String groupId) {

    }

    @Override
    public void deleteUserIdentityLink(String taskId, String userId, String identityLinkType) {

    }

    @Override
    public void deleteGroupIdentityLink(String taskId, String groupId, String identityLinkType) {

    }

    @Override
    public void setPriority(String taskId, int priority) {

    }

    @Override
    public void setDueDate(String taskId, Date dueDate) {

    }

    @Override
    public TaskQuery createTaskQuery() {
        return null;
    }

    @Override
    public NativeTaskQuery createNativeTaskQuery() {
        return null;
    }

    @Override
    public void setVariable(String taskId, String variableName, Object value) {

    }

    @Override
    public void setVariables(String taskId, Map<String, ?> variables) {

    }

    @Override
    public void setVariableLocal(String taskId, String variableName, Object value) {

    }

    @Override
    public void setVariablesLocal(String taskId, Map<String, ?> variables) {

    }

    @Override
    public Object getVariable(String taskId, String variableName) {
        return null;
    }

    @Override
    public <T> T getVariable(String taskId, String variableName, Class<T> variableClass) {
        return null;
    }

    @Override
    public boolean hasVariable(String taskId, String variableName) {
        return false;
    }

    @Override
    public Object getVariableLocal(String taskId, String variableName) {
        return null;
    }

    @Override
    public <T> T getVariableLocal(String taskId, String variableName, Class<T> variableClass) {
        return null;
    }

    @Override
    public boolean hasVariableLocal(String taskId, String variableName) {
        return false;
    }

    @Override
    public Map<String, Object> getVariables(String taskId) {
        return null;
    }

    @Override
    public Map<String, Object> getVariablesLocal(String taskId) {
        return null;
    }

    @Override
    public Map<String, Object> getVariables(String taskId, Collection<String> variableNames) {
        return null;
    }

    @Override
    public Map<String, Object> getVariablesLocal(String taskId, Collection<String> variableNames) {
        return null;
    }

    @Override
    public List<VariableInstance> getVariableInstancesLocalByTaskIds(Set<String> taskIds) {
        return null;
    }

    @Override
    public void removeVariable(String taskId, String variableName) {

    }

    @Override
    public void removeVariableLocal(String taskId, String variableName) {

    }

    @Override
    public void removeVariables(String taskId, Collection<String> variableNames) {

    }

    @Override
    public void removeVariablesLocal(String taskId, Collection<String> variableNames) {

    }

    @Override
    public Comment addComment(String taskId, String processInstanceId, String message) {
        return null;
    }

    @Override
    public Comment addComment(String taskId, String processInstanceId, String type, String message) {
        return null;
    }

    @Override
    public Comment getComment(String commentId) {
        return null;
    }

    @Override
    public void deleteComments(String taskId, String processInstanceId) {

    }

    @Override
    public void deleteComment(String commentId) {

    }

    @Override
    public List<Comment> getTaskComments(String taskId) {
        return null;
    }

    @Override
    public List<Comment> getTaskComments(String taskId, String type) {
        return null;
    }

    @Override
    public List<Comment> getCommentsByType(String type) {
        return null;
    }

    @Override
    public List<Event> getTaskEvents(String taskId) {
        return null;
    }

    @Override
    public Event getEvent(String eventId) {
        return null;
    }

    @Override
    public List<Comment> getProcessInstanceComments(String processInstanceId) {
        return null;
    }

    @Override
    public List<Comment> getProcessInstanceComments(String processInstanceId, String type) {
        return null;
    }

    @Override
    public Attachment  createAttachment(String attachmentType, String taskId, String processInstanceId, String attachmentName, String attachmentDescription, InputStream content) {
        return null;
    }

    @Override
    public Attachment createAttachment(String attachmentType, String taskId, String processInstanceId, String attachmentName, String attachmentDescription, String url) {
        return null;
    }

    @Override
    public void saveAttachment(Attachment attachment) {

    }

    @Override
    public Attachment getAttachment(String attachmentId) {
        return null;
    }

    @Override
    public InputStream getAttachmentContent(String attachmentId) {
        return null;
    }

    @Override
    public List<Attachment> getTaskAttachments(String taskId) {
        return null;
    }

    @Override
    public List<Attachment> getProcessInstanceAttachments(String processInstanceId) {
        return null;
    }

    @Override
    public void deleteAttachment(String attachmentId) {

    }

    @Override
    public List<Task> getSubTasks(String parentTaskId) {
        return null;
    }

    @Override
    public void createSonTask(String taskId) {
        Task newtask = taskService.newTask();
        newtask.setAssignee("zl");
        newtask.setName("xinjia");
        newtask.setParentTaskId(taskId);//父任务id
        taskService.saveTask(newtask);
    }
}
