package com.mlzj.activity.service;

import java.util.List;
import org.activiti.engine.task.Task;

/**
 * @author yhl
 * @date 2022/7/29
 */
public interface ActivityService {

    void startLeave(String businessKey);

    List<Task> getRunTask(String id);

    /**
     *
     * @param userId 用户id
     * @param approval 审批意见
     */
    void completeTask(String userId, boolean approval);

    void queryProcess(String processId);

}
