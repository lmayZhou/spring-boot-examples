package com.lmaye.activiti.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lmaye.activiti.dto.ModelDTO;
import com.lmaye.starter.web.context.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static org.activiti.editor.constants.ModelDataJsonConstants.MODEL_DESCRIPTION;
import static org.activiti.editor.constants.ModelDataJsonConstants.MODEL_NAME;

/**
 * -- Modeler Controller
 *
 * @author lmay.Zhou
 * @date 2021/6/15 10:35
 * @email lmay@lmaye.com
 * @since JDK1.8
 */
@Api(tags = "模型管理", description = "包含创建流程、设计流程模型等接口")
@RestController
@RequestMapping("/modeler")
public class ModelerController {
    /**
     * Repository Service
     */
    @Autowired
    private RepositoryService repositoryService;

    /**
     * Object Mapper
     */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 获取 stencil set json
     *
     * @return String
     */
    @ApiOperation("获取 StencilSet Json")
    @GetMapping("/editor/stencilSet.json")
    public String getStencilSet() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("stencilSet.json")) {
            assert inputStream != null;
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new ActivitiException("Error while loading stencil set", e);
        }
    }

    /**
     * 创建流程模型
     *
     * @param param 请求参数
     * @return ResultVO<Boolean>
     */
    @ApiOperation("创建流程模型")
    @PostMapping("/model/crate")
    public ResultVO<Model> createModel(@RequestBody ModelDTO param) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode editorNode = objectMapper.createObjectNode();
        editorNode.put("id", "canvas");
        editorNode.put("resourceId", "canvas");
        ObjectNode stencilSetNode = objectMapper.createObjectNode();
        stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
        editorNode.set("stencilset", stencilSetNode);
        Model model = repositoryService.newModel();
        ObjectNode modelObjectNode = objectMapper.createObjectNode();
        modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, param.getName());
        modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
        modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, param.getDescription());
        model.setMetaInfo(modelObjectNode.toString());
        // 保存模型
        repositoryService.saveModel(model);
        repositoryService.addModelEditorSource(model.getId(), editorNode.toString().getBytes(StandardCharsets.UTF_8));
        return ResultVO.success(model);
    }

    /**
     * 删除流程模型
     *
     * @param modelId 模型ID
     * @return ResultVO<Boolean>
     */
    @ApiOperation("删除流程模型")
    @DeleteMapping("/model/{modelId}")
    public ResultVO<Boolean> deleteModel(@ApiParam(name = "modelId", value = "模型ID", required = true)
                                         @PathVariable String modelId) {
        repositoryService.deleteModel(modelId);
        return ResultVO.success(true);
    }

    /**
     * 获取流程模型 JSON
     *
     * @param modelId 模版ID
     * @return ResultVO<ObjectNode>
     */
    @ApiOperation("获取流程模型 JSON")
    @GetMapping("/model/{modelId}.json")
    public ResultVO<ObjectNode> getEditorJson(@PathVariable String modelId) {
        Model model = repositoryService.getModel(modelId);
        if (!Objects.isNull(model)) {
            try {
                ObjectNode modelNode;
                if (StringUtils.isNotEmpty(model.getMetaInfo())) {
                    modelNode = (ObjectNode) objectMapper.readTree(model.getMetaInfo());
                } else {
                    modelNode = objectMapper.createObjectNode();
                    modelNode.put("name", model.getName());
                }
                modelNode.put("modelId", model.getId());
                ObjectNode editorJsonNode = (ObjectNode) objectMapper.readTree(new String(
                        repositoryService.getModelEditorSource(model.getId()), StandardCharsets.UTF_8));
                modelNode.set("model", editorJsonNode);
                return ResultVO.success(modelNode);
            } catch (Exception e) {
                throw new ActivitiException("Error creating model JSON", e);
            }
        }
        return ResultVO.success(null);
    }

    /**
     * 保存流程模型
     *
     * @param modelId     模型ID
     * @param name        流程模型名称
     * @param description 描述
     * @param jsonXml     流程文件
     * @param svgXml      图片
     * @return ResultVO<Boolean>
     */
    @ApiOperation("保存流程模型")
    @PutMapping("/model/save/{modelId}")
    public ResultVO<Boolean> saveModel(@PathVariable String modelId, String name, String description,
                                       @RequestParam(name = "json_xml") String jsonXml,
                                       @RequestParam(name = "svg_xml") String svgXml) {
        try (ByteArrayOutputStream outStream = new ByteArrayOutputStream()) {
            Model model = repositoryService.getModel(modelId);
            ObjectNode modelJson = (ObjectNode) objectMapper.readTree(model.getMetaInfo());
            modelJson.put(MODEL_NAME, name);
            modelJson.put(MODEL_DESCRIPTION, description);
            model.setMetaInfo(modelJson.toString());
            model.setName(name);
            JSONObject json = JSONUtil.parseObj(jsonXml);
            JSONObject properties = json.getJSONObject("properties");
            model.setKey(properties.getStr("process_id"));
            repositoryService.saveModel(model);
            repositoryService.addModelEditorSource(model.getId(), jsonXml.getBytes(StandardCharsets.UTF_8));
            InputStream svgStream = new ByteArrayInputStream(svgXml.getBytes(StandardCharsets.UTF_8));
            TranscoderInput input = new TranscoderInput(svgStream);
            PNGTranscoder transcoder = new PNGTranscoder();
            TranscoderOutput output = new TranscoderOutput(outStream);
            transcoder.transcode(input, output);
            repositoryService.addModelEditorSourceExtra(model.getId(), outStream.toByteArray());
            return ResultVO.success(true);
        } catch (Exception e) {
            throw new ActivitiException("Error saving model", e);
        }
    }
}
