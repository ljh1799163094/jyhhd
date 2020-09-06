package com.example.jyhhd.controller.activi;

import com.example.jyhhd.Result;
import com.example.jyhhd.entity.Act_Group;
import com.example.jyhhd.entity.Act_User;
import com.example.jyhhd.entity.TaskEntity;
import com.example.jyhhd.service.activity.Act_Service;
import io.swagger.annotations.*;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.cmd.NeedsActiveTaskCmd;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntityManagerImpl;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@RestController
@RequestMapping("/activiti")
@Api(value = "工作流接口",description = "工作流接口")
public class Act_Controller {

    @Resource
    protected RepositoryService repositoryService;
    @Resource
    protected RuntimeService runtimeService;
    @Resource
    protected TaskService taskService;
    @Resource
    protected ProcessEngine processEngine;
    @Resource
    protected HistoryService historyService;
    @Resource
    protected IdentityService identityService;

    @Resource
    private FormService formService;

    @Resource
    private ManagementService managementService;

    @Autowired
    private Act_Service act_service;


    /**
     * 部署工作流流程定义
     *
     * @throws IOException
     */
    @PostMapping("/deploy")
    @ApiOperation("部署工作流流程定义")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "流程图文件", required = true, paramType="file")
    })
    public Result deploy(@RequestParam(value = "file") MultipartFile multipartFile) {
        Result result = new Result();
        try {
            if(multipartFile != null && !multipartFile.isEmpty()) {
                String fileName = multipartFile.getOriginalFilename();//获取文件名加后缀
                InputStream inputStream = multipartFile.getInputStream();
                if (StringUtils.isEmpty(fileName) || !fileName.contains(".")) {
                    fileName = "1.bpmn";
                }
                String name = fileName.split("\\.")[0];
                //部署文件
               // processEngine.getRepositoryService()//获取流程定义和部署对象相关的Service
                repositoryService .createDeployment()//创建部署对象
                        .name(name)//声明流程的名称
                        .addInputStream(fileName,inputStream)//加载资源文件，一次只能加载一个文件
                        .deploy();//完成部署
                result.setCode(200);
                result.setMessage("部署成功");
            }else {
                result.setMessage("文件不能为空");
                result.setCode(-200);
            }

        }catch (Exception e){
            e.printStackTrace();
            result.setCode(-200);
            result.setMessage("部署失败");
        }

      return result;
    }

    /**
     * 根据流程图KEY启动流程定义
     * @param key
     * @return
     */
    @GetMapping("/startProcessInstanceByKey")
    @ApiOperation("根据流程图KEY启动流程定义")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "流程图KEY", required = true, paramType="query")
    })
    public Result startProcessInstanceByKey(@RequestParam("key") String key,@RequestParam("userId") String userId){
        Result result = new Result();
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("userId",userId);
            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key,map);
            result.setCode(200);
            result.setMessage("启动成功");
        }catch (RuntimeException e){
            e.printStackTrace();
            result.setCode(-200);
            result.setMessage("启动失败");
        }
        return result;
    }


    /**
     *根据用户编号查询当前任务
     * @return
     */
    @GetMapping("/getTaskListByUserId")
    @ApiOperation("根据用户查询当前任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户编号", required = true, paramType="query")
    })
    public Result getTaskListByUserId(@RequestParam("userId") String userId){
        Result result = new Result();
        try {
            List<Task> list = processEngine.getTaskService()//与正在执行的任务管理相关的Service
                    .createTaskQuery()//创建任务查询对象
                    /**查询条件（where部分）*/
                    .taskAssignee(userId).orderByTaskCreateTime().asc()//使用创建时间的升序排列
                    /**返回结果集*/
                    .list();//返回列表
            List<TaskEntity> objects = new ArrayList<>();
            if (list.size() > 0) {
                for (Task task : list) {
                    TaskEntity taskEntity = new TaskEntity();
                    BeanUtils.copyProperties(task, taskEntity);
                    task.getDelegationState();
                    System.err.println(task.toString());
                    objects.add(taskEntity);
                }
            }
            result.setCode(200);
            result.setMessage("查询成功");
            result.setObj(objects);
        }catch (RuntimeException e){
            e.printStackTrace();
            result.setMessage("查询失败");
            result.setCode(-200);
        }
        return result;
    }

    /**
     * 添加工作流用户
     * @return
     */
    @PostMapping("/addUser")
    @ApiOperation("添加用户")
    public Result addUser(@ApiParam("user") @RequestBody Act_User user){
        Result result = new Result();
        try {
            if (user != null && StringUtils.isEmpty(user.getId())) {
                User user1 = identityService.newUser("user1");
                BeanUtils.copyProperties(user, user1);
                identityService.saveUser(user1);
                result.setCode(200);
                result.setMessage("添加成功");
            } else {
                result.setCode(-200);
                result.setMessage("用户编号不能为空");
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            result.setCode(-200);
            result.setMessage("添加失败");
        }
        return result;
    }

    /**
     * 添加工作流组
     * @return
     */
    @PostMapping("/addGroup")
    @ApiOperation("添加工作流组")
    public Result addGroup(@ApiParam("user") @RequestBody Act_Group group){
        Result result = new Result();
        try {
            if (group != null) {
                Group group1 = identityService.newGroup("group1");
                BeanUtils.copyProperties(group, group1);
                group1.setId(UUID.randomUUID().toString().replace("-",""));
                identityService.saveGroup(group1);
                result.setCode(200);
                result.setMessage("添加成功");
            } else {
                result.setCode(-200);
                result.setMessage("组名不能为空");
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            result.setCode(-200);
            result.setMessage("添加失败");
        }
        return result;
    }


    /**
     * 建立工作流人员和组的关系
     * @return
     */
    @PostMapping("/addGroup_User")
    @ApiOperation("建立工作流人员和组的关系")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户编号", required = true, paramType="query"),
            @ApiImplicitParam(name = "groupId", value = "组编号", required = true, paramType="query")
    })
    public Result addGroup_User(@RequestParam("userId") String userId,@RequestParam("groupId") String groupId){
        Result result = new Result();
        try {
            if (!StringUtils.isEmpty(userId) && !StringUtils.isEmpty(groupId)) {
                identityService.createMembership(userId, groupId);
                result.setCode(200);
                result.setMessage("添加成功");
            } else {
                result.setCode(-200);
                result.setMessage("人员和组编号不能为空");
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            result.setCode(-200);
            result.setMessage("添加失败");
        }
        return result;
    }


    /**
     * 获取用户的候选任务列表
     * @param userId
     * @return
     */
    @GetMapping("/getCandidateTaskListByUser")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户编号",name = "userId",required = true,paramType = "query")
    })
    public Result getTaskListByUser(@RequestParam("userId") String userId){
        Result result = new Result();
        List<Task> list = taskService.createTaskQuery().taskCandidateUser(userId).list();
        for (Task task : list) {
            TaskEntity taskEntity = new TaskEntity();
            BeanUtils.copyProperties(task, taskEntity);
            task.getDelegationState();
            System.err.println(task.toString());
        }
        return result;
    }


    /**
     * 获取用户的候选任务和个人任务列表
     * @param userId
     * @return
     */
    @GetMapping("/getAllTaskListByUser")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户编号",name = "userId",required = true,paramType = "query")
    })
    public Result getAllTaskListByUser(@RequestParam("userId") String userId){
        Result result = new Result();
        List<Task> list = taskService.createTaskQuery().taskCandidateOrAssigned(userId).list();
        for (Task task : list) {
            TaskEntity taskEntity = new TaskEntity();
            BeanUtils.copyProperties(task, taskEntity);
            task.getDelegationState();
            System.err.println(task.toString());
        }
        return result;
    }

    /**
     * 完成任务
     * @param taskId 任务编号
     * @return
     */
    @GetMapping("/completeMyPersonalTask")
    @ApiOperation("完成任务")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "任务编号",name = "taskId",required = true, paramType="query" ),
            @ApiImplicitParam(value = "状态",name = "status",required = true, paramType="query" ),
            @ApiImplicitParam(value = "审核人",name = "userId",required = true, paramType="query" )
    })
    public Result completeMyPersonalTask(@RequestParam("taskId") String taskId,@RequestParam("status") Integer status,@RequestParam("userId") String userId) {

        Result result = new Result();
        try {
           if(StringUtils.isEmpty(taskId)){
               result.setMessage("任务不能为空");
               result.setCode(-200);
               return result;
           }
            //taskService.claim(taskId,userId);//任务认领(代理，此任务只可以被代理一次)
            Map<String, Object> map = new HashMap<>();
            map.put("status",status);
            if(!StringUtils.isEmpty(userId)) {
                map.put("userId",userId);
            }
            String comment="WERWRWRWREWERWERWERWEWR";
            Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
            String processInstanceId = task.getProcessInstanceId();
            taskService.addComment(taskId,processInstanceId,comment);//添加意见
            taskService.complete(taskId,map);
            System.out.println("完成任务：任务ID：" + taskId);
            result.setMessage("操作成功");
            result.setCode(200);
        }catch (RuntimeException e){
            e.printStackTrace();
            result.setCode(-200);
            result.setMessage("操作失败");
        }
        return result;
    }

    /**
     * 给任务添加候选人
     * @param users
     * @param taskId
     * @return
     */
    @GetMapping("/addCandidateUser")
    @ApiOperation("给任务添加候选人")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "候选人数组",name = "users",required = true,paramType = "query"),
            @ApiImplicitParam(value = "任务编号",name = "taskId",required = true,paramType = "query")
    })
    public Result addCandidateUser(@RequestParam("users") String users,@RequestParam("taskId") String taskId){
        Result result = new Result();
        if(!StringUtils.isEmpty(users)) {
            taskService.addCandidateUser(taskId, users);
            //taskService.setAssignee(taskId,users);//替换代理人

            result.setMessage("添加成功");
            result.setCode(200);
        }else {
            result.setCode(-200);
            result.setMessage("候选人不能为空");
        }
        return result;
    }

    /**
     * 回退/撤回
     * @param taskId
     * @return
     */
    @GetMapping("/backProcess")
    @ApiOperation("回退")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "任务编号",name = "taskId",required = true, paramType="query")
    })
    public Result backProcess(@RequestParam("taskId") String taskId){
        Result result = new Result();
        //获取当前任务对象
        Task currentTask =taskService.createTaskQuery().taskId(taskId).singleResult();
        if(currentTask==null){
            throw new ActivitiException("当前任务不存在或已被办理完成，回退失败！");
        }
        //获取流程定义id
        String processDefinitionId = currentTask.getProcessDefinitionId();
        //获取bpmn模板
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        //获取节点编号
        Result re = act_service.withdraw(taskId, result);
        String nodeNum="";
        if(re.getCode()==200){
            nodeNum = (String)re.getObj();
        }else {
            return re;
        }
        //获取目标节点定义
        FlowNode targetNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(nodeNum);//节点id
        //删除当前运行任务
        String executionEntityId = managementService.executeCommand(new DeleteTaskCmd(currentTask.getId()));
        //流程执行到目标节点
        managementService.executeCommand(new SetFLowNodeAndGoCmd(targetNode, executionEntityId));
        return result;
    }

    /**
     *  删除当前运行时任务命令，并返回当前任务的执行对象id
     *  这里继承了NeedsActiveTaskCmd，主要时很多跳转业务场景下，要求不能是挂起任务。可以直接继承Command即可
     */
    public class DeleteTaskCmd extends NeedsActiveTaskCmd<String> {
        public DeleteTaskCmd(String taskId){
            super(taskId);
        }

        @Override
        protected String execute(CommandContext commandContext, org.activiti.engine.impl.persistence.entity.TaskEntity taskEntity) {
            //获取所需服务
            TaskEntityManagerImpl taskEntityManager = (TaskEntityManagerImpl)commandContext.getTaskEntityManager();
            //获取当前任务的来源任务及来源节点信息
            ExecutionEntity executionEntity = taskEntity.getExecution();
            //删除当前任务,来源任务
            taskEntityManager.deleteTask(taskEntity, "jumpReason", false, false);
            return executionEntity.getId();
        }

        @Override
        public String getSuspendedTaskException() {
            return "挂起的任务不能跳转";
        }
    }

    /**
     *  根据提供节点和执行对象id，进行跳转命令
     */
    public class SetFLowNodeAndGoCmd implements Command<Void> {
        private FlowNode flowElement;
        private String executionId;

        public SetFLowNodeAndGoCmd(FlowNode flowElement, String executionId) {
            this.flowElement = flowElement;
            this.executionId = executionId;
        }

        @Override
        public Void execute(CommandContext commandContext) {
            //获取目标节点的来源连线
            List<SequenceFlow> flows = flowElement.getIncomingFlows();
            if (flows == null || flows.size() < 1) {
                throw new ActivitiException("回退错误，目标节点没有来源连线");
            }
            //随便选一条连线来执行，当前执行计划为，从连线流转到目标节点，实现跳转
            ExecutionEntity executionEntity = commandContext.getExecutionEntityManager().findById(executionId);
            executionEntity.setCurrentFlowElement(flows.get(0));
            commandContext.getAgenda().planTakeOutgoingSequenceFlowsOperation(executionEntity, true);
            return null;
        }
    }
    /**
     * 查询历史任务
     * @return
     */
    @GetMapping("/getHistoryByUserId")
    @ApiOperation("查询历史任务")
    public Result getHistoryByUserId(){
        Result result = new Result();
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .orderByHistoricTaskInstanceStartTime()
                .desc()
                .list();
        for(HistoricTaskInstance hti:list){
            System.out.println("任务ID:"+hti.getId());
            System.out.println("流程实例ID:"+hti.getProcessInstanceId());
            System.out.println("任务名称："+hti.getName());

            System.out.println("getProcessDefinitionId："+hti.getProcessDefinitionId());
            System.out.println("getParentTaskId："+hti.getParentTaskId());
            System.out.println("hti.getDeleteReason()："+hti.getDeleteReason());


            System.out.println("办理人："+hti.getAssignee());
            System.out.println("开始时间："+hti.getStartTime());
            System.out.println("结束时间："+hti.getEndTime());
            System.out.println("=================================");
        }
        return result;
    }


    @DeleteMapping("/removeHistoryByProcessInstance")
    @ApiOperation("根据流程实例删除历史记录")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "流程实例",name = "processInstance",required = true,paramType ="query" )
    })
    public Result removeHistoryByProcessInstance(@RequestParam("processInstance") String processInstance){
        Result result = new Result();
        if(StringUtils.isEmpty(processInstance)){
            result.setCode(-200);
            result.setMessage("流程实例不能为空");
            return result;
        }
        try {
            runtimeService.deleteProcessInstance(processInstance,"");
            //historyService.deleteHistoricProcessInstance(processInstance,"");
            result.setCode(200);
            result.setMessage("删除成功");
        }catch (RuntimeException e){
            e.printStackTrace();
            result.setCode(-200);
            result.setMessage("删除失败");
        }
        return result;
    }


        /**
         *撤回上一个节点
         */
        @GetMapping("/rollback")
        @ApiOperation("撤回上一个节点")
        @ApiImplicitParams({
                @ApiImplicitParam(name = "taskId",value = "任务编号",required = true,paramType = "query"),
                @ApiImplicitParam(name = "userId",value = "当前人",required = true,paramType = "query")
        })
        public void rollback(@RequestParam("taskId") String taskId,@RequestParam("userId") String userId) throws Exception{
            //当前任务
//            Task task = taskService.createTaskQuery()
//                    .taskAssignee("lisi")
//                    .singleResult();

            Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
            if(task==null) {
                throw new RuntimeException("流程未启动或已执行完成，无法撤回");
            }
            List<HistoricTaskInstance> htlist = historyService.createHistoricTaskInstanceQuery()
                    .processDefinitionId(task.getProcessDefinitionId())
                    .list();

            String myTaskId = null;
            HistoricTaskInstance myTask = null;

            for (HistoricTaskInstance hti : htlist) {
                //回退到zhangsan也就是任务A,业务中这里就是当前登录的用户名
                if(hti.getAssignee().equals(userId)){
                    myTaskId=hti.getId();
                    myTask=hti;
                    break;
                }
            }

            if(myTask==null){
                throw new RuntimeException("该任务非当前用户提交，无法撤回");
            }
            String processDefinitionId = myTask.getProcessDefinitionId();
            BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
            String myActivityId = null;
            List<HistoricActivityInstance> haiList =
                    historyService
                            .createHistoricActivityInstanceQuery()
                            .executionId(myTask.getExecutionId())
                            .finished()
                            .list();
            for (HistoricActivityInstance hai : haiList) {
                if(myTaskId.equals(hai.getTaskId())) {
                    myActivityId = hai.getActivityId();
                    break;
                }
            }

            FlowNode myFlowNode =
                    (FlowNode) bpmnModel.getMainProcess().getFlowElement(myActivityId);

            Execution execution = runtimeService.createExecutionQuery()
                    .executionId(task.getExecutionId()).singleResult();

            String activityId = execution.getActivityId();

            System.out.println(activityId);

            FlowNode flowNode = (FlowNode) bpmnModel.getMainProcess()
                    .getFlowElement(activityId);

            //记录原活动方向
            List<SequenceFlow> oriSequenceFlows = new ArrayList<SequenceFlow>();
            oriSequenceFlows.addAll(flowNode.getOutgoingFlows());

            //清理活动方向
            flowNode.getOutgoingFlows().clear();

            //建立新方向
            List<SequenceFlow> newSequenceFlowList = new ArrayList<SequenceFlow>();
            SequenceFlow newSequenceFlow = new SequenceFlow();
            newSequenceFlow.setId("newSequenceFlowId");
            newSequenceFlow.setSourceFlowElement(flowNode);
            newSequenceFlow.setTargetFlowElement(myFlowNode);
            newSequenceFlowList.add(newSequenceFlow);
            flowNode.setOutgoingFlows(newSequenceFlowList);

            Authentication.setAuthenticatedUserId(userId);
            taskService.addComment(task.getId(), task.getProcessInstanceId(), "撤回");

            //完成任务
            taskService.complete(task.getId());
            //恢复原方向
            flowNode.setOutgoingFlows(oriSequenceFlows);
        }


    /**
     * 获取由此人发起的任务列表
     * @param userId
     * @return
     */
    @GetMapping("/getTaskByStartUser")
    @ApiOperation("获取由此人发起的任务列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户编号",required = true,paramType = "query")
    })
    public Result getTaskByStartUser(@RequestParam("userId") String userId){
            Result result = new Result();
        List<HistoricProcessInstance> historicProcessInstanceList = historyService.createHistoricProcessInstanceQuery().startedBy(userId).list();
        for(HistoricProcessInstance hi:historicProcessInstanceList){
            System.err.println("id====="+hi.getId());
            System.err.println("BusinessKey====="+hi.getBusinessKey());
            System.err.println("ProcessDefinitionId====="+hi.getProcessDefinitionId());
            System.err.println("ProcessDefinitionKey====="+hi.getProcessDefinitionKey());
            System.err.println("ProcessDefinitionName====="+hi.getProcessDefinitionName());
            System.err.println("StartUserId====="+hi.getStartUserId());
            System.err.println("Name====="+hi.getName());
            System.err.println("EndActivityId====="+hi.getEndActivityId());
            System.err.println("StartActivityId====="+hi.getStartActivityId());
        }
        return result;
        }
}
