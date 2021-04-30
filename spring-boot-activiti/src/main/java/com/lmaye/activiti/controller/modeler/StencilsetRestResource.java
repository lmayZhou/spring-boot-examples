package com.lmaye.activiti.controller.modeler;

import org.activiti.engine.ActivitiException;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class StencilsetRestResource {
    /**
     * 获取stencilset json
     *
     * @return String
     */
    @GetMapping("/editor/stencilset.json")
    public String getStencilset() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("stencilset.json")) {
            assert inputStream != null;
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new ActivitiException("Error while loading stencil set", e);
        }
    }
}
