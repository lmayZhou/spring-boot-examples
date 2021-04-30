package com.lmaye.activiti.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.models.properties.BinaryProperty;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ModelQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * -- 部署管理
 *
 * @author Lmay Zhou
 * @date 2021/4/26 12:22
 * @email lmay@lmaye.com
 */
@Api(tags = "部署管理", description = "包含部署流程、删除流程等接口")
@RestController
@RequestMapping("/deploy")
public class DeployController {
    /**
     * Repository Service
     */
    private final RepositoryService repositoryService;

    public DeployController(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    /**
     * 部署流程
     * - 根据ModelId
     *
     * @param modelId     模型ID
     * @param processName 流程名称
     * @return Deployment
     */
    @PostMapping("/")
    @ApiOperation(value = "部署流程", notes = "根据modelId部署流程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "modelId", value = "模型ID", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "processName", value = "流程名称", required = true, dataTypeClass = String.class)
    })
    public Deployment deploy(String modelId, String processName) {
        try {
            byte[] sourceBytes = repositoryService.getModelEditorSource(modelId);
            JsonNode editorNode = new ObjectMapper().readTree(sourceBytes);
            BpmnJsonConverter bpmnJsonConverter = new BpmnJsonConverter();
            BpmnModel bpmnModel = bpmnJsonConverter.convertToBpmnModel(editorNode);
            return repositoryService.createDeployment().name("Manual Deployment").enableDuplicateFiltering()
                    .addBpmnModel(processName.concat(".bpmn20.xml"), bpmnModel).deploy();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 部署流程
     * - 根据上传的文件部署流程
     *
     * @param bpmn bpmn文件
     * @param png  png文件
     * @return Deployment
     */
    @PostMapping("/bpmn")
    @ApiOperation(value = "部署流程", notes = "根据上传的文件部署流程")
    public Deployment deployBpmn(@ApiParam(name = "processKey", value = "流程key", required = true) @RequestParam String processKey,
                                 @ApiParam(name = "bpmn", value = "bpmn文件", required = true) @RequestPart MultipartFile bpmn,
                                 @ApiParam(name = "png", value = "png文件", required = true) @RequestPart MultipartFile png) {
        try {
            return repositoryService.createDeployment().name("Manual Deployment").enableDuplicateFiltering().key(processKey)
                    .addInputStream(bpmn.getOriginalFilename(), bpmn.getInputStream())
                    .addInputStream(png.getOriginalFilename(), png.getInputStream()).deploy();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除部署流程
     * - 根据部署ID
     *
     * @param deploymentId 部署ID
     * @return Boolean
     */
    @DeleteMapping("/{deploymentId}")
    @ApiOperation(value = "删除部署流程", notes = "根据部署ID删除部署流程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deploymentId", value = "部署ID", required = true, dataTypeClass = String.class)
    })
    public Boolean delete(@PathVariable String deploymentId) {
        try {
            // 不带级联的删除：只能删除没有启动的流程，如果流程启动，就会抛出异常
            repositoryService.deleteDeployment(deploymentId);
            // 级联删除：不管流程是否启动，都能可以删除
            // repositoryService.deleteDeployment(deploymentId, true);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 查询部署实例列表
     *
     * @param name 模型名称
     * @return List<ProcessInstance>
     */
    @PostMapping("/searchPage")
    @ApiOperation(value = "查询部署实例列表", notes = "查询部署实例")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "模型名称", dataTypeClass = String.class),
    })
    public List<Map<String, Object>> searchPage(String name) {
        try {
            ModelQuery query = repositoryService.createModelQuery();
            List<Model> models;
            if (StringUtils.isNotBlank(name)) {
                models = query.modelNameLike(name).listPage(0, 10);
            } else {
                models = query.listPage(0, 10);
            }
            return models.stream().map(it -> {
                Map<String, Object> data = new HashMap<>(2);
                data.put("id", it.getId());
                data.put("key", it.getKey());
                data.put("name", it.getName());
                data.put("metaInfo", it.getMetaInfo());
                data.put("createTime", it.getCreateTime());
                return data;
            }).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
