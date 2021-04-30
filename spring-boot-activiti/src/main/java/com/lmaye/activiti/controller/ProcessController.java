package com.lmaye.activiti.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * -- 流程管理
 *
 * @author Lmay Zhou
 * @date 2021/4/25 15:22
 * @email lmay@lmaye.com
 */
@Api(tags = "流程管理", description = "包含启动流程、查询流程、删除流程等接口")
@RestController
@RequestMapping("/process")
public class ProcessController {
    /**
     * Runtime Service
     */
    private final RuntimeService runtimeService;

    /**
     * Repository Service
     */
    private final RepositoryService repositoryService;

    public ProcessController(RuntimeService runtimeService, RepositoryService repositoryService) {
        this.runtimeService = runtimeService;
        this.repositoryService = repositoryService;
    }

    /**
     * 启动流程
     * - 根据流程key
     *
     * @param processKey 流程key
     * @param startUser  启动流程的用户
     * @return ProcessInstance
     */
    @PostMapping("/start")
    @ApiOperation(value = "启动流程", notes = "每一个流程有对应的一个key这个是某一个流程内固定的写在bpmn内的")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processKey", value = "流程key", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "startUser", value = "启动流程的用户", required = true, dataTypeClass = String.class)
    })
    public String start(String processKey, String startUser) {
        try {
            Map<String, Object> variables = new HashMap<>(1);
            variables.put("startUser", startUser);
            // 业务关联id(businessKey)
            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey, "", variables);
            return processInstance.getProcessDefinitionId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询流程实例列表
     *
     * @param processDefinitionKey 流程key
     * @return List<ProcessInstance>
     */
    @PostMapping("/searchPage")
    @ApiOperation(value = "查询流程实例列表", notes = "查询流程实例")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processDefinitionKey", value = "流程key", dataTypeClass = String.class),
    })
    public List<Map<String, Object>> searchPage(String processDefinitionKey) {
        try {
            ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
            List<ProcessInstance> instances;
            if (StringUtils.isNotBlank(processDefinitionKey)) {
                instances = processInstanceQuery.processDefinitionKey(processDefinitionKey).listPage(0, 10);
            } else {
                instances = processInstanceQuery.listPage(0, 10);
            }
            return instances.stream().map(it -> {
                Map<String, Object> data = new HashMap<>(2);
                data.put("processId", it.getId());
                data.put("startUser", it.getStartUserId());
                data.put("startTime", it.getStartTime());
                data.put("processDefinitionKey", it.getProcessDefinitionId());
                return data;
            }).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * 查询流程实例
     * - 根据流程ID
     *
     * @param processId 流程实例ID
     * @return Map<String, Object>
     */
    @GetMapping("/searchById/{processId}")
    @ApiOperation(value = "根据流程ID查询流程实例", notes = "查询流程实例")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processId", value = "流程实例ID", dataTypeClass = String.class),
    })
    public Map<String, Object> searchById(@PathVariable String processId) {
        try {
            Map<String, Object> result = new HashMap<>(2);
            ProcessInstance instance = runtimeService.createProcessInstanceQuery().processInstanceId(processId).singleResult();
            if (Objects.isNull(instance)) {
                return result;
            }
            result.put("processId", instance.getId());
            result.put("startUser", instance.getStartUserId());
            result.put("startTime", instance.getStartTime());
            result.put("processDefinitionKey", instance.getProcessDefinitionId());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除流程实例
     * - 根据流程实例ID
     *
     * @param processId 流程实例ID
     * @return Boolean
     */
    @DeleteMapping("/{processId}")
    @ApiOperation(value = "删除流程实例", notes = "根据流程实例ID删除流程实例")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processId", value = "流程实例ID", required = true, dataTypeClass = String.class)
    })
    public Boolean delete(@PathVariable String processId) {
        try {
            runtimeService.deleteProcessInstance(processId, "根据流程实例ID删除流程实例");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除流程实例
     * - 根据流程实例key
     *
     * @param processDefinitionKey 流程实例key
     * @return Boolean
     */
    @DeleteMapping("/byKey/{processDefinitionKey}")
    @ApiOperation(value = "删除流程实例", notes = "根据流程实例key删除流程实例")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processDefinitionKey", value = "流程实例Key", required = true, dataTypeClass = String.class),
    })
    public Boolean deleteByKey(@PathVariable String processDefinitionKey) {
        try {
            ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
            List<ProcessInstance> runningList = processInstanceQuery.processDefinitionKey(processDefinitionKey).list();
            if (CollectionUtil.isNotEmpty(runningList)) {
                runningList.forEach(s -> runtimeService.deleteProcessInstance(s.getId(), "根据流程实例key删除流程实例"));
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 流程图预览
     *
     * @param response          HttpServletResponse
     * @param processInstanceId 流程实例ID
     */
    @GetMapping("/preview/{processInstanceId}")
    @ApiOperation(value = "流程图预览", notes = "根据流程实例ID生成流程图")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processInstanceId", value = "流程实例ID", required = true, dataTypeClass = String.class),
    })
    public void preview(HttpServletResponse response, @PathVariable String processInstanceId) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();
        response.setContentType("image/png; charset=utf-8");
        BpmnModel model = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
        if (!Objects.isNull(model) && model.getLocationMap().size() > 0) {
            ProcessDiagramGenerator generator = new DefaultProcessDiagramGenerator();
            // 生成流程图, 高亮
            try (InputStream is = generator.generateDiagram(model, runtimeService.getActiveActivityIds(processInstanceId),
                    new ArrayList<>(), "黑体", "黑体", "黑体");
                 OutputStream os = response.getOutputStream()) {
                Transcoder transcoder = new PNGTranscoder();
                TranscoderInput input = new TranscoderInput(is);
                TranscoderOutput output = new TranscoderOutput(os);
                transcoder.transcode(input, output);
                byte[] bytes = new byte[1024];
                while (is.read(bytes) != -1) {
                    os.write(bytes);
                }
                os.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
