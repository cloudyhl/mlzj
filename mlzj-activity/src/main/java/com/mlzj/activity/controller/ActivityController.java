package com.mlzj.activity.controller;

import com.mlzj.activity.model.dto.UserAddDto;
import com.mlzj.activity.service.ActivityService;
import com.mlzj.activity.service.UserService;
import java.util.List;
import javax.annotation.Resource;
import org.activiti.engine.task.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Resource
    private UserService userService;

    @Resource
    private ActivityService activityService;

    @PostMapping("/addUser")
    public void addUser(@RequestBody UserAddDto userAddDto) {
        userService.addUser(userAddDto);
    }

    @GetMapping("/startUserLeave")
    public void startUserLeave(@RequestParam String businessKey) {
        activityService.startLeave(businessKey);
    }

    @GetMapping("/getMyApproval")
    public List<Task> getMyApproval(@RequestParam String id) {
        return activityService.getRunTask(id);
    }

    @GetMapping("/approval")
    public void approval(@RequestParam String userId, @RequestParam boolean approval) {
        activityService.completeTask(userId, approval);
    }

    @GetMapping("/queryProcess")
    public void queryProcess(@RequestParam String processId) {
        activityService.queryProcess(processId);
    }

}
