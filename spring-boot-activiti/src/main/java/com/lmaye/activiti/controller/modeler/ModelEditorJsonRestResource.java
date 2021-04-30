package com.lmaye.activiti.controller.modeler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * --
 *
 * @author Lmay Zhou
 * @date 2021/4/25 12:04
 * @email lmay@lmaye.com
 */
@RestController
@RequestMapping("/modeler")
public class ModelEditorJsonRestResource implements ModelDataJsonConstants {
    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/model/{modelId}.json")
    public ObjectNode getEditorJson(@PathVariable String modelId) {
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
                return modelNode;
            } catch (Exception e) {
                throw new ActivitiException("Error creating model JSON", e);
            }
        }
        return null;
    }
}
