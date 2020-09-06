package com.example.jyhhd.service.activity;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.repository.*;
import org.activiti.engine.task.IdentityLink;
import org.activiti.validation.ValidationError;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public interface Act_RepositoryService {

    //创建部署
    //开始创建一个新的部署。
    DeploymentBuilder createDeployment();

    //删除部署（3个）
    //删除给定的部署。
    void deleteDeployment(String deploymentId);

    //将给定的部署和级联删除删除到流程实例、历史流程实例和作业。
    void deleteDeploymentCascade(String deploymentId);
    //同上
    void deleteDeployment(String deploymentId, boolean cascade);

    //设置部署
    //设置部署的类别。
    //可以按类别查询部署:参见{@link DeploymentQuery#deploymentCategory(String)}。
    void setDeploymentCategory(String deploymentId, String category);

    //获取部署
    //检索给定部署的部署资源列表，按字母顺序排列。
    List<String> getDeploymentResourceNames(String deploymentId);
    //通过一个字节流提供对部署资源的访问。
    InputStream getResourceAsStream(String deploymentId, String resourceName);
    //实验功能 更改部署的租户标识符，以匹配给定的租户标识符。 这一变化将波及任何相关实体:
    void changeDeploymentTenantId(String deploymentId, String newTenantId);

    //创建
    //查询过程定义。
    ProcessDefinitionQuery createProcessDefinitionQuery();
    //为流程定义返回一个新{@link org.activiti.engine.query.NativeQuery}。
    NativeProcessDefinitionQuery createNativeProcessDefinitionQuery();
    //查询部署。
    DeploymentQuery createDeploymentQuery();
    //返回一个新的部署{@link org.activiti.engine.query.NativeQuery}
    NativeDeploymentQuery createNativeDeploymentQuery();

    //暂停流程
    //用给定的id暂停流程定义。
    void suspendProcessDefinitionById(String processDefinitionId);
    //同上
    void suspendProcessDefinitionById(String processDefinitionId, boolean suspendProcessInstances, Date suspensionDate);
    //用给定的键(= id in the bpmn20.xml file)挂起所有的进程定义。xml文件)。
    void suspendProcessDefinitionByKey(String processDefinitionKey);
    //同上
    void suspendProcessDefinitionByKey(String processDefinitionKey, boolean suspendProcessInstances, Date suspensionDate);
    //类似{@link #suspendProcessDefinitionByKey(String)}但只适用于给定的租户标识符。
    void suspendProcessDefinitionByKey(String processDefinitionKey, String tenantId);
    //类似{@link #suspendProcessDefinitionByKey(String, boolean, Date)}但只适用于给定的租户标识符。
    void suspendProcessDefinitionByKey(String processDefinitionKey, boolean suspendProcessInstances, Date suspensionDate, String tenantId);
    //激活流程 用给定的id激活流程定义。
    void activateProcessDefinitionById(String processDefinitionId);
    //同上
    void activateProcessDefinitionById(String processDefinitionId, boolean activateProcessInstances, Date activationDate);
    //用给定的key(=id in the bpmn20.xml file)激活流程定义。
    void activateProcessDefinitionByKey(String processDefinitionKey);
    //同上
    void activateProcessDefinitionByKey(String processDefinitionKey, boolean activateProcessInstances, Date activationDate);
    //类似于{@link #activateProcessDefinitionByKey(String)}但只适用于给定的租户标识符。
    void activateProcessDefinitionByKey(String processDefinitionKey, String tenantId);
    //类似于{@link #activateProcessDefinitionByKey(String, boolean, Date)}但只适用于给定的租户标识符。
    void activateProcessDefinitionByKey(String processDefinitionKey, boolean activateProcessInstances, Date activationDate, String tenantId);
    //设置流程定义的类别。 流程定义可以查询类别:看到{ @link ProcessDefinitionQuery # processDefinitionCategory(String)}。
    void setProcessDefinitionCategory(String processDefinitionId, String category);

    //访问已部署流程
    //提供对已部署过程模型的访问，例如，一个BPMN 2.0 XML文件，通过一个字节流。
    InputStream getProcessModel(String processDefinitionId);
    //提供对已部署流程图的访问，例如PNG图像，通过一个字节流。
    InputStream getProcessDiagram(String processDefinitionId);
    //返回{@link ProcessDefinition}，包括所有BPMN信息，如附加属性(如文档)。
    ProcessDefinition getProcessDefinition(String processDefinitionId);
    //使用提供的流程定义id返回与流程定义对应的{@link BpmnModel}。
    BpmnModel getBpmnModel(String processDefinitionId);

    //检测流程状态
    //检查流程定义是否被挂起。
    boolean isProcessDefinitionSuspended(String processDefinitionId);
    //在流程关系图中提供元素的位置和维度，如{@link RepositoryService#getProcessDiagram(String)}所提供的。
    DiagramLayout getProcessDiagramLayout(String processDefinitionId);

    //模型的增删改查
    //创建一个新的model，他的模型是暂时的，必须使用{@link #saveModel(模型)}来保存。
    Model newModel();
    //保存模型。 如果模型已经存在，那么模型就会被更新，否则就会创建一个新的模型。
    void saveModel(Model model);
    //删除model
    void deleteModel(String modelId);
    //为模型保存模型编辑器源。
    void addModelEditorSource(String modelId, byte[] bytes);
    //同上
    void addModelEditorSourceExtra(String modelId, byte[] bytes);
    //创建查询模型
    ModelQuery createModelQuery();
    //为流程定义返回一个新{@link org.activiti.engine.query.NativeQuery}
    NativeModelQuery createNativeModelQuery();
    //返回{@link Model}
    Model getModel(String modelId);
    //以字节数组的形式返回模型编辑器源。
    byte[] getModelEditorSource(String modelId);
    //同上
    byte[] getModelEditorSourceExtra(String modelId);

    //授权候选用户进行流程定义。
    void addCandidateStarterUser(String processDefinitionId, String userId);
    //授权候选用户组进行流程定义。
    void addCandidateStarterGroup(String processDefinitionId, String groupId);
    //删除候选用户对流程定义的授权。
    void deleteCandidateStarterUser(String processDefinitionId, String userId);
    //删除候选用户组对流程定义的授权。
    void deleteCandidateStarterGroup(String processDefinitionId, String groupId);

    //检索与给定进程定义关联的{@link IdentityLink}。
    List<IdentityLink> getIdentityLinksForProcessDefinition(String processDefinitionId);
    //根据对Activiti引擎执行流程定义的规则，验证给定的流程定义。
    List<ValidationError> validateProcess(BpmnModel bpmnModel);
}
