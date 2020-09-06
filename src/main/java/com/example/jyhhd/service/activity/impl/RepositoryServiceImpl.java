package com.example.jyhhd.service.activity.impl;

import com.example.jyhhd.service.activity.Act_RepositoryService;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.*;
import org.activiti.engine.task.IdentityLink;
import org.activiti.validation.ValidationError;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@Service
public class RepositoryServiceImpl implements Act_RepositoryService {

    @Resource
    private RepositoryService repositoryService;

    @Override
    public DeploymentBuilder createDeployment() {
        DeploymentBuilder deployment = repositoryService.createDeployment();
        return deployment;
    }

    @Override
    public void deleteDeployment(String s) {
      repositoryService.deleteDeployment(s);
    }

    @Override
    public void deleteDeploymentCascade(String deploymentId) {

    }

    @Override
    public void deleteDeployment(String s, boolean b) {
       repositoryService.deleteDeployment(s,b);
    }

    @Override
    public void setDeploymentCategory(String s, String s1) {
        repositoryService.setDeploymentCategory(s,s1);
    }

    @Override
    public List<String> getDeploymentResourceNames(String s) {
        List<String> deploymentResourceNames = repositoryService.getDeploymentResourceNames(s);
        return deploymentResourceNames;
    }

    @Override
    public InputStream getResourceAsStream(String s, String s1) {
        InputStream resourceAsStream = repositoryService.getResourceAsStream(s, s1);
        return resourceAsStream;
    }

    @Override
    public void changeDeploymentTenantId(String s, String s1) {
        repositoryService.changeDeploymentTenantId(s,s1);

    }

    @Override
    public ProcessDefinitionQuery createProcessDefinitionQuery() {
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        return processDefinitionQuery;
    }

    @Override
    public NativeProcessDefinitionQuery createNativeProcessDefinitionQuery() {
        return repositoryService.createNativeProcessDefinitionQuery();
    }

    @Override
    public DeploymentQuery createDeploymentQuery() {
        return repositoryService.createDeploymentQuery();
    }

    @Override
    public NativeDeploymentQuery createNativeDeploymentQuery() {
        return repositoryService.createNativeDeploymentQuery();
    }

    @Override
    public void suspendProcessDefinitionById(String s) {
        repositoryService.suspendProcessDefinitionById(s);
    }

    @Override
    public void suspendProcessDefinitionById(String s, boolean b, Date date) {
        repositoryService.suspendProcessDefinitionById(s,b,date);
    }

    @Override
    public void suspendProcessDefinitionByKey(String s) {
        repositoryService.suspendProcessDefinitionByKey(s);

    }

    @Override
    public void suspendProcessDefinitionByKey(String s, boolean b, Date date) {
        repositoryService.suspendProcessDefinitionByKey(s,b,date);
    }

    @Override
    public void suspendProcessDefinitionByKey(String s, String s1) {
        repositoryService.suspendProcessDefinitionByKey(s,s1);
    }

    @Override
    public void suspendProcessDefinitionByKey(String s, boolean b, Date date, String s1) {
        repositoryService.suspendProcessDefinitionByKey(s,b,date,s1);

    }

    @Override
    public void activateProcessDefinitionById(String s) {
        repositoryService.activateProcessDefinitionById(s);

    }

    @Override
    public void activateProcessDefinitionById(String s, boolean b, Date date) {
        repositoryService.activateProcessDefinitionById(s,b,date);

    }

    @Override
    public void activateProcessDefinitionByKey(String s) {
        repositoryService.activateProcessDefinitionByKey(s);

    }

    @Override
    public void activateProcessDefinitionByKey(String s, boolean b, Date date) {
        repositoryService.activateProcessDefinitionByKey(s,b,date);

    }

    @Override
    public void activateProcessDefinitionByKey(String s, String s1) {
        repositoryService.activateProcessDefinitionByKey(s,s1);

    }

    @Override
    public void activateProcessDefinitionByKey(String s, boolean b, Date date, String s1) {
        repositoryService.activateProcessDefinitionByKey(s,b,date,s1);

    }

    @Override
    public void setProcessDefinitionCategory(String s, String s1) {
        repositoryService.setProcessDefinitionCategory(s,s1);

    }

    @Override
    public InputStream getProcessModel(String s) {
        return repositoryService.getProcessModel(s);
    }

    @Override
    public InputStream getProcessDiagram(String s) {
        return repositoryService.getProcessDiagram(s);
    }

    @Override
    public ProcessDefinition getProcessDefinition(String s) {
        return repositoryService.getProcessDefinition(s);
    }

    @Override
    public boolean isProcessDefinitionSuspended(String s) {
        return repositoryService.isProcessDefinitionSuspended(s);
    }

    @Override
    public BpmnModel getBpmnModel(String s) {
        return repositoryService.getBpmnModel(s);
    }

    @Override
    public DiagramLayout getProcessDiagramLayout(String s) {
        return repositoryService.getProcessDiagramLayout(s);
    }

    @Override
    public Model newModel() {
        return repositoryService.newModel();
    }

    @Override
    public void saveModel(Model model) {
        repositoryService.saveModel(model);

    }

    @Override
    public void deleteModel(String s) {
        repositoryService.deleteModel(s);

    }

    @Override
    public void addModelEditorSource(String s, byte[] bytes) {
        repositoryService.addModelEditorSource(s,bytes);

    }

    @Override
    public void addModelEditorSourceExtra(String s, byte[] bytes) {
        repositoryService.addModelEditorSourceExtra(s,bytes);

    }

    @Override
    public ModelQuery createModelQuery() {
        return repositoryService.createModelQuery();
    }

    @Override
    public NativeModelQuery createNativeModelQuery() {
        return repositoryService.createNativeModelQuery();
    }

    @Override
    public Model getModel(String s) {
        return repositoryService.getModel(s);
    }

    @Override
    public byte[] getModelEditorSource(String s) {
        return repositoryService.getModelEditorSource(s);
    }

    @Override
    public byte[] getModelEditorSourceExtra(String s) {
        return repositoryService.getModelEditorSourceExtra(s);
    }

    @Override
    public void addCandidateStarterUser(String s, String s1) {
        repositoryService.addCandidateStarterUser(s,s1);

    }

    @Override
    public void addCandidateStarterGroup(String s, String s1) {
        repositoryService.addCandidateStarterGroup(s,s1);

    }

    @Override
    public void deleteCandidateStarterUser(String s, String s1) {
        repositoryService.deleteCandidateStarterUser(s,s1);

    }

    @Override
    public void deleteCandidateStarterGroup(String s, String s1) {
        repositoryService.deleteCandidateStarterGroup(s,s1);

    }

    @Override
    public List<IdentityLink> getIdentityLinksForProcessDefinition(String s) {
        return repositoryService.getIdentityLinksForProcessDefinition(s);
    }

    @Override
    public List<ValidationError> validateProcess(BpmnModel bpmnModel) {
        return repositoryService.validateProcess(bpmnModel);
    }
}
