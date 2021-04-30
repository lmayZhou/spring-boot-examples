package com.lmaye.activiti.controller.modeler;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * --
 *
 * @author Lmay Zhou
 * @date 2021/4/25 12:04
 * @email lmay@lmaye.com
 */
@RestController
@RequestMapping("/modeler")
public class ModelSaveRestResource implements ModelDataJsonConstants {
    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 保存流程
     *
     * @param modelId     模型ID
     * @param name        流程模型名称
     * @param description 描述
     * @param jsonXml     流程文件
     * @param svgXml      图片
     */
    @PutMapping("/model/save/{modelId}")
    public void saveModel(@PathVariable String modelId, String name, String description,
                          @RequestParam(name = "json_xml") String jsonXml, @RequestParam(name = "svg_xml") String svgXml) {
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
        } catch (Exception e) {
            throw new ActivitiException("Error saving model", e);
        }
    }
}
