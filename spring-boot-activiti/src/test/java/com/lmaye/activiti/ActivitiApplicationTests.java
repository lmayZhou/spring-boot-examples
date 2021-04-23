package com.lmaye.activiti;

import com.lmaye.activiti.config.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.model.payloads.GetTasksPayload;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@SpringBootTest
class ActivitiApplicationTests {
    @Autowired
    private ProcessRuntime processRuntime;

    @Autowired
    private TaskRuntime taskRuntime;

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private SecurityUtil securityUtil;

    @Test
    void contextLoads() {

    }

    /**
     * 流程定义的部署
     * <p>
     * activiti 表:
     * act_re_deployment  部署信息
     * act_re_procdef     流程定义的一些信息
     * act_ge_bytearray   流程定义的bpmn文件及png文件
     */
    @Test
    public void createDeploy() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("processes/demo2.bpmn20.xml")
//                .addClasspathResource("processes/demo2.png")
                .name("审批流程")
                .deploy();
        log.info("流程部署id:" + deployment.getName());
        log.info("流程部署名称:" + deployment.getId());
    }

    /**
     * 查看流程
     */
    @Test
    void loads() {
        Page<ProcessDefinition> definitions = processRuntime.processDefinitions(Pageable.of(0, 10));
        log.info("已部署的流程个数：{}", definitions.getTotalItems());
        for (ProcessDefinition definition : definitions.getContent()) {
            log.info("流程定义：{}", definition);
        }
    }

    /**
     * 启动流程
     * <p>
     * act_hi_actinst     已完成的活动信息
     * act_hi_identitylink   参与者信息
     * act_hi_procinst   流程实例
     * act_hi_taskinst   任务实例
     * act_ru_execution   执行表
     * act_ru_identitylink   参与者信息
     * act_ru_task  任务
     */
    @Test
    void startInstance() {
        securityUtil.logInAs("ryandawsonuk");
        RuntimeService runtimeService = processEngine.getRuntimeService();
        // 设置assignee
        Map<String, Object> assignees = new HashMap<>();
        assignees.put("apply", "salaboy");
        //启动流程实例
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("demo2", assignees);
//        ProcessInstance instance = processRuntime.start(ProcessPayloadBuilder.start().withProcessDefinitionKey("demo2").build());
        log.info("流程定义ID：{}", instance.getProcessDefinitionId());
        log.info("流程实例ID：{}", instance.getId());
    }

    /**
     * 获取任务，认领任务，并且执行
     */
    @Test
    void getTask() {
        securityUtil.logInAs("salaboy");
        GetTasksPayload payload = new GetTasksPayload();
        Page<Task> tasks = taskRuntime.tasks(Pageable.of(0, 10), payload);
        log.info("任务数量：{}", tasks.getTotalItems());
        if (tasks.getTotalItems() > 0) {
            for (Task task : tasks.getContent()) {
                log.info("--------------------------------------------------");
                log.info("流程实例ID: {}", task.getProcessInstanceId());
                log.info("任务ID: {}", task.getId());
                log.info("任务负责人: {}", task.getAssignee());
                log.info("任务名称: {}", task.getName());
                // 设置assignee
                Map<String, Object> assignees = new HashMap<>();
                assignees.put("approve", "erdemedeiros");
                // 认领任务
//                taskRuntime.claim(TaskPayloadBuilder.claim().withTaskId(task.getId()).build());
                // 执行任务
                taskRuntime.complete(TaskPayloadBuilder.complete().withTaskId(task.getId()).withVariables(assignees).build());
            }
        }

        /*TaskService taskService = processEngine.getTaskService();
        // 根据流程定义的key,负责人assignee来实现当前用户的任务列表查询
        List<org.activiti.engine.task.Task> taskList = taskService.createTaskQuery().processDefinitionKey("demo2").taskAssignee("salaboy").list();
        for (org.activiti.engine.task.Task task : taskList) {
            log.info("--------------------------------------------------");
            log.info("流程实例ID: {}", task.getProcessInstanceId());
            log.info("任务ID: {}", task.getId());
            log.info("任务负责人: {}", task.getAssignee());
            log.info("任务名称: {}", task.getName());
        }*/
    }

    /**
     * 处理当前用户的任务
     * <p>
     * act_hi_actinst
     * act_hi_identitylink
     * act_hi_taskinst
     * act_ru_identitylink
     * act_ru_task
     */
    @Test
    public void completeTask() {
        TaskService taskService = processEngine.getTaskService();
//        taskService.setAssignee();
//        taskService.claim();
        org.activiti.engine.task.Task task = taskService.createTaskQuery().processDefinitionKey("demo2")
                .taskAssignee("salaboy").singleResult();
        if (!Objects.isNull(task)) {
            // 处理任务,结合当前用户任务列表的查询操作的话
            taskService.complete(task.getId());
            log.info("处理完成当前用户的任务");
        } else {
            log.info("当前用户暂无任务");
        }
    }

    @Test
    public void queryHistory() {
        HistoryService historyService = processEngine.getHistoryService();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 查询流程定义
        ProcessDefinitionQuery definitionQuery = repositoryService.createProcessDefinitionQuery();
        // 遍历查询结果
        org.activiti.engine.repository.ProcessDefinition process = definitionQuery.processDefinitionKey("demo2")
                .orderByProcessDefinitionVersion().desc().singleResult();
        if (!Objects.isNull(process)) {
            HistoricActivityInstanceQuery query = historyService.createHistoricActivityInstanceQuery();
            // 排序StartTime
            List<HistoricActivityInstance> instances = query.processDefinitionId(process.getId())
                    .orderByHistoricActivityInstanceStartTime().asc().list();
            for (HistoricActivityInstance it : instances) {
                System.out.println(it.getActivityId());
                System.out.println(it.getActivityName());
                System.out.println(it.getProcessDefinitionId());
                System.out.println(it.getProcessInstanceId());
                System.out.println("--------------------------------------------------");
            }
        }
    }
}
