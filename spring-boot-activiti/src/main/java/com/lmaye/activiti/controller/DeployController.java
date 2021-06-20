package com.lmaye.activiti.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lmaye.activiti.dto.ModelQueryDTO;
import com.lmaye.activiti.vo.ModelVO;
import com.lmaye.app.common.context.ResultCode;
import com.lmaye.app.common.exception.ServiceException;
import com.lmaye.starter.web.context.PageResult;
import com.lmaye.starter.web.context.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ModelQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
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
     * @return ResultVO<Deployment>
     */
    @PostMapping("/")
    @ApiOperation(value = "部署流程", notes = "根据modelId部署流程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "modelId", value = "模型ID", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "processName", value = "流程名称", required = true, dataTypeClass = String.class)
    })
    public ResultVO<Deployment> deploy(String modelId, String processName) {
        try {
            byte[] sourceBytes = repositoryService.getModelEditorSource(modelId);
            JsonNode editorNode = new ObjectMapper().readTree(sourceBytes);
            BpmnJsonConverter bpmnJsonConverter = new BpmnJsonConverter();
            BpmnModel bpmnModel = bpmnJsonConverter.convertToBpmnModel(editorNode);
            Deployment deployment = repositoryService.createDeployment().name("Manual Deployment").enableDuplicateFiltering()
                    .addBpmnModel(processName.concat(".bpmn20.xml"), bpmnModel).deploy();
            return ResultVO.success(deployment);
        } catch (Exception e) {
            throw new ServiceException(ResultCode.FAILURE, e);
        }
    }

    /**
     * 部署流程
     * - 根据上传的文件部署流程
     *
     * @param bpmn bpmn文件
     * @param png  png文件
     * @return ResultVO<Deployment>
     */
    @PostMapping("/bpmn")
    @ApiOperation(value = "部署流程", notes = "根据上传的文件部署流程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processKey", value = "流程key", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "bpmn", value = "bpmn文件", required = true, dataTypeClass = MultipartFile.class),
            @ApiImplicitParam(name = "png", value = "png文件", required = true, dataTypeClass = MultipartFile.class),
    })
    public ResultVO<Deployment> deployBpmn(@RequestParam String processKey, @RequestPart MultipartFile bpmn,
                                           @RequestPart MultipartFile png) {
        try {
            Deployment deployment = repositoryService.createDeployment().name("Manual Deployment")
                    .enableDuplicateFiltering().key(processKey)
                    .addInputStream(bpmn.getOriginalFilename(), bpmn.getInputStream())
                    .addInputStream(png.getOriginalFilename(), png.getInputStream()).deploy();
            return ResultVO.success(deployment);
        } catch (Exception e) {
            throw new ServiceException(ResultCode.FAILURE, e);
        }
    }

    /**
     * 删除部署流程
     * - ResultVO<Boolean>
     *
     * @param deploymentId 部署ID
     * @return Boolean
     */
    @DeleteMapping("/{deploymentId}")
    @ApiOperation(value = "删除部署流程", notes = "根据部署ID删除部署流程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deploymentId", value = "部署ID", required = true, dataTypeClass = String.class)
    })
    public ResultVO<Boolean> delete(@PathVariable String deploymentId) {
        try {
            // 不带级联的删除：只能删除没有启动的流程，如果流程启动，就会抛出异常
            repositoryService.deleteDeployment(deploymentId);
            // 级联删除：不管流程是否启动，都能可以删除
            // repositoryService.deleteDeployment(deploymentId, true);
            return ResultVO.success(true);
        } catch (Exception e) {
            throw new ServiceException(ResultCode.FAILURE, e);
        }
    }

    /**
     * 查询部署实例列表
     *
     * @param param 请求参数
     * @return ResultVO<List<Map<String, Object>>>
     */
    @PostMapping("/searchPage")
    @ApiOperation(value = "查询部署实例列表", notes = "查询部署实例")
    public ResultVO<PageResult<ModelVO>> searchPage(@RequestBody ModelQueryDTO param) {
        try {
            PageResult<ModelVO> pageResult = new PageResult<>();
            Long pageSize = param.getPageSize();
            pageResult.setPageIndex(param.getPageIndex());
            pageResult.setPageSize(pageSize);
            ModelQuery modelQuery = repositoryService.createModelQuery();
            String name = param.getName();
            if (StringUtils.isNotBlank(name)) {
                modelQuery.modelNameLike(name);
            }
            long total = modelQuery.count();
            List<Model> models = modelQuery.listPage(param.getPageIndex().intValue() - 1, pageSize.intValue());
            List<ModelVO> records = models.stream().map(it -> {
                ModelVO vo = new ModelVO();
                vo.setId(it.getId());
                vo.setKey(it.getKey());
                vo.setName(it.getName());
                vo.setMetaInfo(it.getMetaInfo());
                vo.setCreateTime(it.getCreateTime());
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
}
