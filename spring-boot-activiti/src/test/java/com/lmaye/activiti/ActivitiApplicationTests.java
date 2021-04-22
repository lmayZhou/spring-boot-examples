package com.lmaye.activiti;

import com.lmaye.activiti.config.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.runtime.TaskRuntime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class ActivitiApplicationTests {
    @Autowired
    private ProcessRuntime processRuntime;

    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    private TaskRuntime taskRuntime;

    @Test
    void contextLoads() {

    }

    /**
     * 查看流程
     */
    @Test
    void loads() {
        securityUtil.logInAs("salaboy");
        Page<ProcessDefinition> definitions = processRuntime.processDefinitions(Pageable.of(0, 10));
        log.info("已部署的流程个数：{}", definitions.getTotalItems());
        for (ProcessDefinition definition : definitions.getContent()) {
            log.info("流程定义：{}", definition);
        }
    }

    /**
     * 启动流程
     */
    @Test
    void startInstance() {
        securityUtil.logInAs("salaboy");
        ProcessInstance instance = processRuntime.start(ProcessPayloadBuilder.start().withProcessDefinitionKey("demo").build());
        log.info("流程实例ID：{}", instance.getId());
    }

    /**
     * 获取任务，拾取任务，并且执行
     */
    @Test
    void getTask() {
        // 指定组内任务人
        securityUtil.logInAs("salaboy");
        Page<Task> tasks = taskRuntime.tasks(Pageable.of(0, 10));
        if (tasks.getTotalItems() > 0) {
            for (Task task : tasks.getContent()) {
                log.info("任务名称：{}", task.getName());
                //拾取任务
                taskRuntime.claim(TaskPayloadBuilder.claim().withTaskId(task.getId()).build());
                //执行任务
                taskRuntime.complete(TaskPayloadBuilder.complete().withTaskId(task.getId()).build());
            }
        }
    }
}
