package com.lmaye.activiti.controller;

import com.lmaye.activiti.dto.TaskCompleteDTO;
import com.lmaye.activiti.dto.TaskQueryDTO;
import com.lmaye.activiti.dto.TaskTurnDTO;
import com.lmaye.activiti.vo.TaskVO;
import com.lmaye.app.common.context.ResultCode;
import com.lmaye.app.common.exception.ServiceException;
import com.lmaye.starter.web.context.PageResult;
import com.lmaye.starter.web.context.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
    @Autowired
    private TaskService taskService;

    /**
     * 查询个人任务
     * - 根据流程assignee
     *
     * @param param 请求参数
     * @return ResultVO<List < TaskVO>>
     */
    @PostMapping("/searchPage")
    @ApiOperation(value = "查询个人任务", notes = "根据流程assignee查询个人任务")
    public ResultVO<PageResult<TaskVO>> searchPage(@RequestBody TaskQueryDTO param) {
        try {
            String assignee = param.getAssignee();
            PageResult<TaskVO> pageResult = new PageResult<>();
            Long pageSize = param.getPageSize();
            pageResult.setPageIndex(param.getPageIndex());
            pageResult.setPageSize(pageSize);
            // 任务查询条件
            TaskQuery taskQuery = taskService.createTaskQuery();
            if (StringUtils.isNotBlank(assignee)) {
                taskQuery.taskCandidateOrAssigned(assignee);
            }
            String taskName = param.getTaskName();
            if(StringUtils.isNotBlank(taskName)) {
                taskQuery.taskName(taskName);
            }
            long total = taskQuery.count();
            List<Task> taskList = taskQuery.listPage(param.getPageIndex().intValue() - 1, pageSize.intValue());
            List<TaskVO> records = taskList.stream().map(it -> {
                TaskVO vo = new TaskVO();
                vo.setTaskId(it.getId());
                vo.setTaskName(it.getName());
                vo.setTaskCreateTime(it.getCreateTime());
                vo.setTaskAssignee(it.getAssignee());
                vo.setProcessInstanceId(it.getProcessInstanceId());
                vo.setExecutionId(it.getExecutionId());
                vo.setProcessDefinitionId(it.getProcessDefinitionId());
                return vo;
            }).collect(Collectors.toList());
            pageResult.setRecords(records);
            pageResult.setTotal(total);
            pageResult.setPages(total % pageSize == 0 ? total / pageSize : total / pageSize + 1);
            return ResultVO.success(pageResult);
        } catch (Exception e) {
            throw new ServiceException(ResultCode.FAILURE, e);
        }
    }


    /**
     * 完成任务
     *
     * @param param 请求参数
     * @return ResultVO<Boolean>
     */
    @PostMapping("/complete")
    @ApiOperation(value = "完成任务", notes = "完成任务，任务进入下一个节点")
    public ResultVO<Boolean> complete(@RequestBody TaskCompleteDTO param) {
        try {
            String taskId = param.getTaskId();
            String assignee = param.getAssignee();
            Task task = taskService.createTaskQuery().taskId(taskId).taskCandidateOrAssigned(assignee).singleResult();
            if (Objects.isNull(task)) {
                return ResultVO.success(false);
            }
            // 认领任务
            taskService.claim(taskId, assignee);
            // TODO 动态获取上级审批用户
            Map<String, Object> variables = new HashMap<>(2);
//            variables.put("approve", "ryandawsonuk, erdemedeiros");
            variables.put("approveHR", "other, HR");
            taskService.complete(taskId, variables);
            return ResultVO.success(true);
        } catch (Exception e) {
            throw new ServiceException(ResultCode.FAILURE, e);
        }
    }

    /**
     * 任务转让
     *
     * @param param 请求参数
     * @return ResultVO<Boolean>
     */
    @PostMapping("/turn")
    @ApiOperation(value = "任务转让", notes = "任务转让给指定用户")
    public ResultVO<Boolean> turn(@RequestBody TaskTurnDTO param) {
        try {
            taskService.setAssignee(param.getTaskId(), param.getUserId());
            return ResultVO.success(true);
        } catch (Exception e) {
            throw new ServiceException(ResultCode.FAILURE, e);
        }
    }
}
