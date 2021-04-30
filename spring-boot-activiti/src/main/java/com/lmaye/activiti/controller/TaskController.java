package com.lmaye.activiti.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.task.Task;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

/**
 * -- 任务管理
 *
 * @author Lmay Zhou
 * @date 2021/4/25 17:07
 * @email lmay@lmaye.com
 */
@Api(tags = "任务管理", description = "包含部署流程、删除流程等接口")
@RestController
@RequestMapping("/task")
public class TaskController {
    /**
     * Task Service
     */
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * 查询个人任务
     * - 根据流程assignee
     *
     * @param assignee 流程assignee
     * @return List<Map < String, String>>
     */
    @PostMapping("/searchPage")
    @ApiOperation(value = "查询个人任务", notes = "根据流程assignee查询个人任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "assignee", value = "代理人（当前用户）", dataTypeClass = String.class),
    })
    public List<Map<String, String>> searchPage(String assignee) {
        try {
            // 指定个人任务查询
            List<Task> taskList = taskService.createTaskQuery().taskCandidateOrAssigned(assignee).listPage(0, 10);
            if (CollectionUtil.isNotEmpty(taskList)) {
                return taskList.stream().map(it -> {
                    Map<String, String> map = new HashMap<>(2);
                    map.put("taskID", it.getId());
                    map.put("taskName", it.getName());
                    map.put("taskCreateTime", it.getCreateTime().toString());
                    map.put("taskAssignee", it.getAssignee());
                    map.put("processInstanceId", it.getProcessInstanceId());
                    map.put("executionId", it.getExecutionId());
                    map.put("processDefinitionId", it.getProcessDefinitionId());
                    return map;
                }).collect(Collectors.toList());
            }
            return new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    /**
     * 完成任务
     *
     * @param taskId    任务ID
     * @return Boolean
     */
    @PostMapping("/complete")
    @ApiOperation(value = "完成任务", notes = "完成任务，任务进入下一个节点")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "任务ID", dataTypeClass = String.class),
            @ApiImplicitParam(name = "assignee", value = "代理人（当前用户）", dataTypeClass = String.class),
    })
    public Boolean complete(String taskId, String assignee) {
        try {
            Task task = taskService.createTaskQuery().taskId(taskId).taskCandidateOrAssigned(assignee).singleResult();
            if(Objects.isNull(task)) {
                return false;
            }
            // 认领任务
            taskService.claim(taskId, assignee);
            // TODO 动态获取上级审批用户
            Map<String, Object> variables = new HashMap<>(2);
            variables.put("approve1", "A1");
            variables.put("approve2", "A2");
            taskService.complete(taskId, variables);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 任务转让
     *
     * @param taskId  taskId
     * @param userKey 用户Key
     * @return Boolean
     */
    @PostMapping("/turn")
    @ApiOperation(value = "任务转让", notes = "任务转让给指定用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "任务ID", dataTypeClass = String.class),
            @ApiImplicitParam(name = "userKey", value = "用户Key", dataTypeClass = String.class),
    })
    public Boolean turn(String taskId, String userKey) {
        try {
            taskService.setAssignee(taskId, userKey);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
