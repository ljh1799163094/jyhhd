package com.example.jyhhd.service.impl;

import com.example.jyhhd.Result;
import com.example.jyhhd.service.ActivityConsumerService;
import org.activiti.engine.*;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ActivityConsumerServiceImpl implements ActivityConsumerService {

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private IdentityService identityService;

    @Resource
    private ProcessEngine engine;


    @Override
    public boolean startActivityDemo() {

        RepositoryService repositoryService = engine.getRepositoryService();

        Map<String,Object> map = new HashMap<>();
        map.put("userId","小明");
        map.put("userId2","王刚");
        map.put("userId3","李德华");
        // 部署流程文件
        Deployment deploy = repositoryService.createDeployment().addClasspathResource("processes/my02.bpmn").deploy();
        String id = deploy.getId();
        String key = deploy.getKey();
        System.err.println("key======"+key);
        System.err.println("id========="+id);



        // 启动流程
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess_2");
        // 查询第一个任务
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        System.out.println("当前节点名称" + task.getName());
        System.err.println("taskId========="+task.getId());
        // 完成第一个任务
        taskService.complete(task.getId());
        task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        System.out.println("流程结束后，查找任务：" + task);
        engine.close();
        System.out.println("================================================");
        System.out.println("method startActivityDemo end....");
        return false;
    }

    @Override
    public Result getTaskListByUserId(String userId,String key, Result result) {
        try {
//           Task task = taskService.createTaskQuery()
//                    .processDefinitionKey(key)
//                    .taskAssignee(userId)
//                    .singleResult();

            List<Task> list = taskService.createTaskQuery().processDefinitionKey(key).taskAssignee(userId).list();

            //4.任务列表的展示
            System.err.println("userId======"+userId);
            System.err.println("key=========="+key);
            if(list.size()>0) {
                for(Task task1:list) {
                    System.out.println("流程实例ID:" + task1.getProcessInstanceId());
                    System.out.println("任务ID:" + task1.getId());  //5002
                    System.out.println("任务负责人:" + task1.getAssignee());
                    System.out.println("任务名称:" + task1.getName());
                }
            }
            result.setCode(200);
            result.setMessage("查询成功");
            result.setObj(list);
        }catch (Exception e){
            e.printStackTrace();
            result.setCode(-200);
            result.setMessage("查询失败");
        }
        return result;
    }

    @Override
    public Result completeTask(String taskId, Result result) {

        return null;
    }

    /**
     * 任务领取（签收任务）
     * @param taskId
     * @param userId
     */
    public void claim(String taskId, String userId) {
        this.taskService.claim(taskId, userId);
    }

    /**
     * 放弃签收
     * @param taskId
     */
    public void unclaim(String taskId) {
        this.taskService.unclaim(taskId);
    }

    /**
     * 委托领取（转签）
     * @param taskId
     * @param userId
     */
    public void delegateTask(String taskId, String userId) {
        this.taskService.delegateTask(taskId, userId);
    }

    /**
     * 任务完成
     *
     * @param taskId
     */
    public void complete(String taskId,String userId, Map<String, Object> variables) {
        // 如果该task已经被他人领取，则需先操作unclaim，否则报错
        this.taskService.claim(taskId, userId);
        this.taskService.complete(taskId, variables);
        // 不带变量时，可用如下方法
        // this.taskService.complete(taskId);
    }

    /**
     * 可用于查询个人已签收任务。assignee设置为登录用户ID
     * @param assignee
     */
    public void queryAssigneeTaskList(String assignee){
        //创建任务查询对象
        TaskQuery query = this.taskService.createTaskQuery();
        //指定个人任务查询，指定办理人
        query.taskAssignee(assignee);
        List<Task> list = query.list();
        if(list!=null && list.size()>0){
            for(Task task:list){
                System.out.println("任务ID:"+task.getId());
                System.out.println("任务名称:"+task.getName());
                System.out.println("任务的创建时间:"+task.getCreateTime());
                System.out.println("任务的办理人:"+task.getAssignee());
                System.out.println("流程实例ID："+task.getProcessInstanceId());
                System.out.println("执行对象ID:"+task.getExecutionId());
                System.out.println("流程定义ID:"+task.getProcessDefinitionId());
            }
        }
    }

    /**
     * 可用于查询未签收任务。assignee设置为登录用户ID
     * @param candidateUser
     */
    public void queryCandidateUserTaskList(String candidateUser){
        //创建任务查询对象
        TaskQuery query = this.taskService.createTaskQuery();
        //指定个人任务查询，指定办理人
        query.taskCandidateUser(candidateUser);
        List<Task> list = query.list();
        if(list!=null && list.size()>0){
            for(Task task:list){
                System.out.println("任务ID:"+task.getId());
                System.out.println("任务名称:"+task.getName());
                System.out.println("任务的创建时间:"+task.getCreateTime());
                System.out.println("流程实例ID："+task.getProcessInstanceId());
                System.out.println("执行对象ID:"+task.getExecutionId());
                System.out.println("流程定义ID:"+task.getProcessDefinitionId());
            }
        }
    }

    /**
     * 获取任务中的办理候选人
     *
     * @param taskId
     * @return
     */
    public Set<User> getTaskCandidate(String taskId) {
        Set<org.activiti.engine.identity.User> users = new HashSet<User>();
        List<IdentityLink> identityLinkList = taskService.getIdentityLinksForTask(taskId);
        if (identityLinkList != null && identityLinkList.size() > 0) {
            for (Iterator<IdentityLink> iterator = identityLinkList.iterator(); iterator.hasNext();) {
                IdentityLink identityLink = (IdentityLink) iterator.next();
                if (identityLink.getUserId() != null) {
                    org.activiti.engine.identity.User user = getUser(identityLink.getUserId());
                    if (user != null)
                        users.add(user);
                }
                if (identityLink.getGroupId() != null) {
                    // 根据组获得对应人员
                    List<org.activiti.engine.identity.User> userList = identityService.createUserQuery().memberOfGroup(identityLink.getGroupId()).list();
                    if (userList != null && userList.size() > 0)
                        users.addAll(userList);
                }
            }

        }
        return users;
    }

    private org.activiti.engine.identity.User getUser(String userId) {
        org.activiti.engine.identity.User user = identityService.createUserQuery().userId(userId).singleResult();
        return user;
    }

    /**
     * 某个task添加候选人
     * @param taskId
     * @param candidateUser
     */
    public void addCandidateUser(String taskId,String candidateUser) {
        taskService.addCandidateUser(taskId, candidateUser);
    }

    /**
     * 某个task添加候选人组
     * @param taskId
     * @param candidateGroup
     */
    public void addCandidateGroup(String taskId,String candidateGroup) {
        taskService.addCandidateGroup(taskId, candidateGroup);
    }

    /**
     * 查询个人代办事项
     * @param processDefinitionKey
     * @param userId
     */
    public void queryMyTodoTask(String processDefinitionKey,String userId) {
        TaskQuery taskQuery = taskService.createTaskQuery();
        if (processDefinitionKey != null) {
            taskQuery = taskQuery.processDefinitionKey(processDefinitionKey);
        }
        List<String> groupList = getGroupListByUserId(userId);
        if (groupList != null && groupList.size() > 0) {
            // 由于task既可以属于用户，也可以属于组，所以需或的关系来查询
            taskQuery = taskQuery.or().taskCandidateOrAssigned(userId).taskCandidateGroupIn(groupList).endOr();
        } else {// 如果该用户没有任何用户组
            taskQuery = taskQuery.taskCandidateOrAssigned(userId);
        }
        taskQuery.listPage(0, 10);
    }

    public List<String> getGroupListByUserId(String userId) {

        return null;
    }


    //候选人（组）：代表有权限对任务进行操作的人。
    //持有人：指当前任务的执行人，他此时持有该任务。
    //代理人：当一个人持有人不方便处理任务时，可以将任务指给另一人，被指的人即为代理人。

    //任务和候选人的关系是一个中间表(act_ru_identitylink)来保存的，所有任务和候选人的关系是多对多。
    //给任务设置候选人
    //taskService.addCandidateUser(taskId,userId);
    //List<Task> list = taskService.createTaskQuery().taskCandidateUser(userId).list();

   // 一个人可以持有多个任务，一个任务只能有一个持有人，因此持有人和任务的关系是一对多。
    // 持有人关系表现在任务表act_ru_task的OWNER字段中。
    ////给任务设置持有人
    //taskService.setOwner(taskId,userId);
    //反向查询任务：根据持有人查询任务
    //List<Task> list = taskService.createTaskQuery().taskOwner(userId).list();

    // 一个人可以是多个任务的代理人，一个任务只能有一个代理人，因此代理人和任务的关系是一对多
    //区别：taskService.setAssignee()和taskService.claim()都可以给任务设置代理人，区别：
    //setAssignee方法可以随时任意为任务指定代理人（可多次调用），
    //而claim方法给任务指定一次代理人后，如果再次调用claim来指定代理人，则会抛出异常：ActivitiTaskAlreadyClaimedException: Task ‘b7ec485a-a4cd-4bdc-8117-8beb76a81c2f’ is already claimed by someone else.
    //taskService.claim(taskId,userId);

}
