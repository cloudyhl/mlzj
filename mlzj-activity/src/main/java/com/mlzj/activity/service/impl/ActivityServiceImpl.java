package com.mlzj.activity.service.impl;

import com.mlzj.activity.service.ActivityService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;

/**
 * assignee属性: 直接把用户任务分配给指定用户(和使用humanPerformer 效果完全一样)
 * candidateUsers属性: 为任务设置候选人(和使用potentialOwner效果完全一样,不需要像使用potentialOwner通过user(kermit)声明,这个属性只能用于人员)
 * candidateGroups属性: 为任务设置候选组(和使用potentialOwner效果完全一样,不需要像使用potentialOwner通过group(management)声明,这个属性只能用于群组)
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private HistoryService historyService;


    @PostConstruct
    public void init(){
        repositoryService.createDeployment().addClasspathResource("processes/userLeave.bpmn20.xml").deploy();
    }

    @Override
    public void startLeave(String businessKey) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("superior", "100002");
        paramMap.put("manager", "100003");
        Authentication.setAuthenticatedUserId("100001");
        ProcessInstance userLeave = runtimeService.startProcessInstanceByKey("userLeave", businessKey, paramMap);
        System.out.println(userLeave);
    }

    @Override
    public List<Task> getRunTask(String id) {
        return taskService.createTaskQuery().taskAssignee(id).list();
    }

    @Override
    public void completeTask(String userId, boolean approval) {
        List<Task> list = taskService.createTaskQuery().taskAssignee(userId).list();
        for (Task task : list) {
            Map<String, Object> paramMap = new HashMap<>();
            if (userId.equals("100002")) {
                paramMap.put("vacationApproved", approval);
            } else {
                paramMap.put("hVacationApproved", approval);
            }
            taskService.complete(task.getId(), paramMap);
        }
    }

    @Override
    public void queryProcess(String processId) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processId).singleResult();
        System.out.println(processInstance);
    }
}
