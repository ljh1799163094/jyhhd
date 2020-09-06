package com.example.jyhhd.controller.activi;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.zip.ZipInputStream;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Attachment;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
@RequestMapping("/leave")
public class LeaveController {

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    @Resource
    private IdentityService identityService;

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private ProcessEngine processEngine;

    @Resource
    private HistoryService historyService;


    //部署流程资源【第一种方式：classpath】
    @RequestMapping("/deploy1")
    public void deploy1( ){
        Deployment deployment = processEngine.getRepositoryService()//获取流程定义和部署对象相关的Service
                .createDeployment()//创建部署对象
                .name("请假申请审核流程")//声明流程的名称
                .addClasspathResource("processes/leave.bpmn")//加载资源文件，一次只能加载一个文件
                .addClasspathResource("processes/leave.png")//
                .deploy();//完成部署
        System.out.println("部署ID："+deployment.getId());//1
        System.out.println("部署时间："+deployment.getDeploymentTime());

    }

    //部署流程资源【第二种方式：InputStream】
    @RequestMapping("/deploy2")
    public void deploy2( ) throws FileNotFoundException{
        //获取资源相对路径
        String bpmnPath = "processes/leave.bpmn";
        String pngPath = "processes/leave.png";
        //读取资源作为一个输入流
        FileInputStream bpmnfileInputStream = new FileInputStream(bpmnPath);
        FileInputStream pngfileInputStream = new FileInputStream(pngPath);

        Deployment deployment = processEngine.getRepositoryService()//获取流程定义和部署对象相关的Service
                .createDeployment()//创建部署对象
                .addInputStream("leave.bpmn",bpmnfileInputStream)
                .addInputStream("leave.png", pngfileInputStream)
                .deploy();//完成部署
        System.out.println("部署ID："+deployment.getId());//1
        System.out.println("部署时间："+deployment.getDeploymentTime());
    }

    //部署流程资源【第三种方式：InputStream】
    @RequestMapping("/deploy3")
    public void deploy3( ) throws FileNotFoundException{
        //字符串
        String text = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><definitions>...</definitions>";

        Deployment deployment = processEngine.getRepositoryService()//获取流程定义和部署对象相关的Service
                .createDeployment()//创建部署对象
                .addString("leave.bpmn",text)
                .deploy();//完成部署
        System.out.println("部署ID："+deployment.getId());//1
        System.out.println("部署时间："+deployment.getDeploymentTime());

    }

    //部署流程资源【第四种方式：zip/bar格式压缩包方式】
    @RequestMapping("/deploy4")
    public void deploy4( ) throws FileNotFoundException{
        //从classpath路径下读取资源文件
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("processes/leave.zip");
        ZipInputStream zipInputStream = new ZipInputStream(in);
        Deployment deployment = processEngine.getRepositoryService()//获取流程定义和部署对象相关的Service
                .createDeployment()//创建部署对象
                .addZipInputStream(zipInputStream)//使用zip方式部署，将leave.bpmn和leave.png压缩成zip格式的文件
                .deploy();//完成部署
        System.out.println("部署ID："+deployment.getId());//1
        System.out.println("部署时间："+deployment.getDeploymentTime());

    }


    public void test(){
            //3、根据id查询Activiti用户
            User user = identityService.createUserQuery().userId("user1").singleResult();
            System.out.println(user.getId());
            System.out.println(user.getFirstName());
            System.out.println(user.getLastName());
            System.out.println(user.getPassword());
            System.out.println(user.getEmail());

            //添加activiti用户
            User user1 = identityService.newUser("user1");
            user1.setFirstName("张三");
            user1.setLastName("张");
            user1.setPassword("123456");
            user1.setEmail("zhangsan@qq.com");
            identityService.saveUser(user1);

            //创建activiti用户组
            Group group1 = identityService.newGroup("group1");
            group1.setName("员工组");
            group1.setType("员工组");
            identityService.saveGroup(group1);

            //通过用户组id查询Activiti用户组
            Group group = identityService.createGroupQuery().groupId("group1").singleResult();
            System.out.println(group.getId());
            System.out.println(group.getName());
            System.out.println(group.getType());

             //创建Activiti（用户-用户组）关系
            identityService.createMembership("user1", "group1");//user1 在员工阻
            identityService.createMembership("user2", "group2");//user2在总监组
            identityService.createMembership("user3", "group3");//user3在经理组
            identityService.createMembership("user4", "group4");//user4在人力资源组

            //查询属于组group1的用户
            List<User> usersInGroup = identityService.createUserQuery().memberOfGroup("group1").list();
            for (User u : usersInGroup) {
                System.out.println(user.getFirstName());
            }

            //查询user1所属于的组
            List<Group> groupsForUser = identityService.createGroupQuery().groupMember("user1").list();
            for (Group g : groupsForUser) {
                System.out.println(group.getName());
            }

            //查询用户表所有用户
            List<User> list = identityService.createUserQuery().list();


        }

   //任务列表查询
    public void findMyPersonalTask(){
        String assignee = "user1";
        List<Task> list = processEngine.getTaskService()//与正在执行的任务管理相关的Service
                .createTaskQuery()//创建任务查询对象
                /**查询条件（where部分）*/
                .taskAssignee(assignee)//指定个人任务查询，指定办理人
//						.taskCandidateUser(candidateUser)//组任务的办理人查询
//						.processDefinitionId(processDefinitionId)//使用流程定义ID查询
//						.processInstanceId(processInstanceId)//使用流程实例ID查询
//						.executionId(executionId)//使用执行对象ID查询
                /**排序*/
                .orderByTaskCreateTime().asc()//使用创建时间的升序排列
                /**返回结果集*/
//						.singleResult()//返回惟一结果集
//						.count()//返回结果集的数量
//						.listPage(firstResult, maxResults);//分页查询
                .list();//返回列表
        if(list!=null && list.size()>0){
            for(Task task:list){
                System.out.println("任务ID:"+task.getId());
                System.out.println("任务名称:"+task.getName());
                System.out.println("任务的创建时间:"+task.getCreateTime());
                System.out.println("任务的办理人:"+task.getAssignee());
                System.out.println("流程实例ID："+task.getProcessInstanceId());
                System.out.println("执行对象ID:"+task.getExecutionId());
                System.out.println("流程定义ID:"+task.getProcessDefinitionId());
                System.out.println("########################################################");
            }
        }
    }

    //13、完成我的任务
    public void completeMyPersonalTask() {
        // 任务ID
        String taskId = "15";
        //完成任务的同时，设置流程变量，根据流程变量的结果来节点进入哪一个节点任务
        Map<String, Object> args = new HashMap<>();
        args.put("time", 2);

        processEngine.getTaskService()// 与正在执行的任务管理相关的Service
                .complete(taskId, args);
        System.out.println("完成任务：任务ID：" + taskId);

    }

    //查看流程图
    @RequestMapping(value = "/image", method = RequestMethod.GET)
    public void image(HttpServletResponse response,
                      @RequestParam String processInstanceId) {
        try {
            InputStream is = getDiagram(processInstanceId);
            if (is == null)
                return;

            response.setContentType("image/png");

            BufferedImage image = ImageIO.read(is);
            OutputStream out = response.getOutputStream();
            ImageIO.write(image, "png", out);

            is.close();
            out.close();
        } catch (Exception ex) {
            log.error("查看流程图失败", ex);
        }
    }


    public InputStream getDiagram(String processInstanceId) {
        System.err.println("=====processInstanceId===="+processInstanceId);
        //获得流程实例
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();
        String processDefinitionId = StringUtils.EMPTY;
        if (processInstance == null) {
            //查询已经结束的流程实例
            HistoricProcessInstance processInstanceHistory =
                    historyService.createHistoricProcessInstanceQuery()
                            .processInstanceId(processInstanceId).singleResult();
            if (processInstanceHistory == null)
                return null;
            else
                processDefinitionId = processInstanceHistory.getProcessDefinitionId();
        } else {
            processDefinitionId = processInstance.getProcessDefinitionId();
        }

        //使用宋体
        String fontName = "宋体";
        //获取BPMN模型对象
        BpmnModel model = repositoryService.getBpmnModel(processDefinitionId);
        //获取流程实例当前的节点，需要高亮显示
        List<String> currentActs = Collections.EMPTY_LIST;
        if (processInstance != null)
            currentActs = runtimeService.getActiveActivityIds(processInstance.getId());

        return processEngine.getProcessEngineConfiguration()
                .getProcessDiagramGenerator()
                .generateDiagram(model, "png", currentActs, new ArrayList<String>(),
                        fontName, fontName, fontName, null, 1.0);
    }


    //添加意见（taskId 任务编号，processInstanceId 流程实例编号，message 意见）
    public void addComment(String taskId,String processInstanceId, String message){
        taskService.addComment(taskId,processInstanceId,message);
    }

    /**
     *根据流程实例查询意见
     * @param processInstanceId
     */
    public void getProcessInstanceComments(String processInstanceId){
        List<Comment> processInstanceComments = taskService.getProcessInstanceComments(processInstanceId);
    }


    /**
     *根据任务编号查询意见
     * @param taskId
     */
    public void getTaskAttachments(String taskId){
        List<Attachment> taskAttachments = taskService.getTaskAttachments(taskId);
    }
}



