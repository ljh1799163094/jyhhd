package com.example.jyhhd.service.activity;

import org.activiti.engine.impl.persistence.entity.VariableInstance;
import org.activiti.engine.task.*;

import java.io.InputStream;
import java.util.*;

public interface Act_TaskService {
  //  1.创建任务（2个方法）
    //创建与任何流程实例无关的新任务。
    Task newTask();
    //使用用户定义的任务id创建一个新任务。
    Task newTask(String taskId);

//2.存任务（1个方法）
//将给定的任务保存到持久数据存储中。 如果这个任务已经存在于持久化存储中，那么它将被更新。 在保存了新任务之后，传递给该方法的任务实例将使用新创建的任务的id进行更新。
    void saveTask(Task task);

//3.删除任务（6个方法）
    //删除给定的任务，而不是删除与此任务相关的历史信息。
    void deleteTask(String taskId);
    //删除给定集合的所有任务，而不是删除与这些任务相关的历史信息。
    void deleteTasks(Collection<String> taskIds);
    //删除给定的任务。
    void deleteTask(String taskId, boolean cascade);
    //删除给定集合的所有任务
    void deleteTasks(Collection<String> taskIds, boolean cascade);
    //删除给定的任务。
    void deleteTask(String taskId, String deleteReason);
    //删除给定集合的所有任务
    void deleteTasks(Collection<String> taskIds, String deleteReason);


//4.任务操作
//声明对任务的责任:给定的用户被指定为任务的受让人。
    //不检查用户是否被标识组件所知。
    void claim(String taskId, String userId);
    //为了取消任务，使用null用户的{@link #claim}的快捷方式。
    void unclaim(String taskId);
    //当任务成功执行时调用。
    void complete(String taskId);
//将任务委托给另一个用户。
    //这意味着设置了assignee并将委派状态设置为{@link委派状态#PENDING}。
   // 如果没有设置所有者，则所有者将被设置为任务的当前受让人。
    void delegateTask(String taskId, String userId);
//标志着受让人完成了这项任务，并可以将其发回给所有者。
   // 只有当这个任务是{@link DelegationState#PENDING}委托时才能调用。
   // 在此方法返回后，{@link Task# getdelegate state()将被设置为{@link DelegationState#RESOLVED}。
        void resolveTask(String taskId);
//标志着受让人完成了这项任务，提供了所需的变量，并且可以将其发回给所有者。
      //  只有当这个任务是{@link DelegationState#PENDING}委托时才能调用。
       // 在此方法返回后，{@link Task# getdelegate state()将被设置为{@link DelegationState#RESOLVED}。
            void resolveTask(String taskId, Map<String, Object> variables);
//当任务成功执行时调用，
        //    所需的任务参数由最终用户给出。
            void complete(String taskId, Map<String, Object> variables);
//同上
            void complete(String taskId, Map<String, Object> variables, boolean localScope);
//将给定任务的受让人更改为给定的userId。
        //    不检查用户是否被标识组件所知。
            void setAssignee(String taskId, String userId);
//将此任务的所有权转移给另一个用户。
        //不检查用户是否被标识组件所知。
void setOwner(String taskId, String userId);

 //   5.检索
          //检索与给定任务关联的{@link IdentityLink}。
           // 这样的{@link IdentityLink}通知了一个特定的标识(如。
           //         组或用户)与某个任务相关联(如。
           //         作为候选人,受让人,等等)。
   List<IdentityLink> getIdentityLinksForTask(String taskId);
            // 6.候选（候选组，候选用户）的操作（添加与删除）
          //{@link #addUserIdentityLink(String、String、String)}的便利简写;   型{ @link IdentityLinkType #CANDIDATE}
         void addCandidateUser(String taskId, String userId);
         //{@link #addGroupIdentityLink(String、String、String)}的便利简写; 型{ @link IdentityLinkType #CANDIDATE}
         void addCandidateGroup(String taskId, String groupId);
//涉及到有任务的用户。 身份链接的类型由给定的身份链接类型定义。
            void addUserIdentityLink(String taskId, String userId, String identityLinkType);
//涉及到一个有任务的小组。 标识链接的类型由给定的标识链接定义。
            void addGroupIdentityLink(String taskId, String groupId, String identityLinkType);
//{@link #deleteUserIdentityLink(String、String、String)}的便利简写; 型{ @link IdentityLinkType #CANDIDATE}
            void deleteCandidateUser(String taskId, String userId);
//{@link #deleteGroupIdentityLink(String、String、String)}的便利简写; 型{ @link IdentityLinkType #CANDIDATE}
            void deleteCandidateGroup(String taskId, String groupId);
//删除用户与给定身份链接类型的任务之间的关联。
            void deleteUserIdentityLink(String taskId, String userId, String identityLinkType);
//删除一个组与给定身份链接类型的任务之间的关联。
            void deleteGroupIdentityLink(String taskId, String groupId, String identityLinkType);

     //       7.任务修改
//更改任务的优先级。  权限:实际所有者/业务管理员。
            void setPriority(String taskId, int priority);
//更改任务的截止日期。
            void setDueDate(String taskId, Date dueDate);

      //      8.任务查询
//返回可用于动态查询任务的新{@link TaskQuery}。
            TaskQuery createTaskQuery();
//返回任务的新{@link NativeQuery}。
            NativeTaskQuery createNativeTaskQuery();

 //9.任务变量（添加，获取，检测，移除）
//在任务上设置变量。 如果变量不是已经存在，那么它将在最外层的范围内创建。 这意味着在此任务与执行相关的情况下，流程实例。
            void setVariable(String taskId, String variableName, Object value);
//同上
            void setVariables(String taskId, Map<String, ? extends Object> variables);
//在任务上设置变量。如果变量不是已经存在，那么它将在任务中创建。
            void setVariableLocal(String taskId, String variableName, Object value);
//同上
            void setVariablesLocal(String taskId, Map<String, ? extends Object> variables);
//获取任务范围内的变量和搜索，如果还有执行范围的话。
            Object getVariable(String taskId, String variableName);
//获取任务范围内的变量和搜索，如果还有执行范围的话。
<T> T getVariable(String taskId, String variableName, Class<T> variableClass);
//检查任务是否具有给定名称的变量，在任务范围内，如果还有执行范围。
            boolean hasVariable(String taskId, String variableName);
//检查任务是否有定义为给定名称的变量。
            Object getVariableLocal(String taskId, String variableName);
//同上
<T> T getVariableLocal(String taskId, String variableName, Class<T> variableClass);
//检查任务是否有定义为给定名称的变量，只有本地任务范围。
            boolean hasVariableLocal(String taskId, String variableName);
//获取任务范围内的所有变量和搜索，
// 如果还有执行范围的话。 如果您有很多变量，而且只需要一些变量，那么可以考虑使用{@link #getVariables(String, Collection)}来获得更好的性能。
            Map<String, Object> getVariables(String taskId);
//获取所有变量并只在任务范围内搜索。
           // 如果您有许多任务局部变量，而且只需要一些，请考虑使用{@link #getVariablesLocal(String, Collection)}来获得更好的性能。
            Map<String, Object> getVariablesLocal(String taskId);
//获取所有给定变量的值，并只在任务范围内搜索。
            Map<String, Object> getVariables(String taskId, Collection<String> variableNames);
//在任务上获取一个变量。
            Map<String, Object> getVariablesLocal(String taskId, Collection<String> variableNames);
//获取所有变量并只在任务范围内搜索。
            List<VariableInstance> getVariableInstancesLocalByTaskIds(Set<String> taskIds);
//从任务中移除变量。
// 当变量不存在时，什么都不会发生。
            void removeVariable(String taskId, String variableName);
//从任务中移除变量(不考虑父作用域)。
// 当变量不存在时，什么都不会发生。
            void removeVariableLocal(String taskId, String variableName);
//从任务中移除给定集合中的所有变量。
// 不存在的变量名完全被忽略。
            void removeVariables(String taskId, Collection<String> variableNames);
//从任务中删除给定集合中的所有变量(不考虑父范围)。
// 不存在的变量名完全被忽略。
            void removeVariablesLocal(String taskId, Collection<String> variableNames);
//向任务和/或流程实例添加注释。
            Comment addComment(String taskId, String processInstanceId, String message);
//将注释添加到一个任务和/或具有自定义类型的流程实例。
            Comment addComment(String taskId, String processInstanceId, String type, String message);
//返回带有给定id的单个注释。如果给定id没有注释，则返回null。
            Comment getComment(String commentId);
//从提供的任务和/或流程实例中删除所有注释。
            void deleteComments(String taskId, String processInstanceId);
//用给定的id删除单个注释。
            void deleteComment(String commentId);
//与给定任务相关的注释。
            List<Comment> getTaskComments(String taskId);
//与给定类型的给定任务相关的注释。
            List<Comment> getTaskComments(String taskId, String type);
//给定类型的所有注释。
            List<Comment> getCommentsByType(String type);

//            11.事件
//所有与给定任务相关的Event。
            List<Event> getTaskEvents(String taskId);
//返回给定id的单个事件。如果给定id不存在事件，则返回null。
            Event getEvent(String eventId);
//与给定流程实例相关的注释。
            List<Comment> getProcessInstanceComments(String processInstanceId);
//同上
            List<Comment> getProcessInstanceComments(String processInstanceId, String type);
  //12.附件
//向任务和/或流程实例添加新的附件，并使用输入流提供内容。
            Attachment createAttachment(String attachmentType, String taskId, String processInstanceId, String attachmentName, String attachmentDescription, InputStream content);
//向任务和/或流程实例添加新的附件，并使用url作为内容。
            Attachment createAttachment(String attachmentType, String taskId, String processInstanceId, String attachmentName, String attachmentDescription, String url);
//更新附件的名称和描述。
            void saveAttachment(Attachment attachment);
//检索一个特定的附件
            Attachment getAttachment(String attachmentId);
//检索特定附件的流内容。
            InputStream getAttachmentContent(String attachmentId);
//与任务相关的附件列表。
            List<Attachment> getTaskAttachments(String taskId);
//与流程实例相关联的附件列表。
            List<Attachment> getProcessInstanceAttachments(String processInstanceId);
//删除附件
            void deleteAttachment(String attachmentId);

//13.任务列表获取
//父任务的子任务列表。
            List<Task> getSubTasks(String parentTaskId);


            //创建子任务
            void createSonTask(String taskId);
}
