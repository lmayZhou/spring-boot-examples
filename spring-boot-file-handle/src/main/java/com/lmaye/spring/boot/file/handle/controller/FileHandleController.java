package com.lmaye.spring.boot.file.handle.controller;

import com.lmaye.examples.common.utils.FileUtils;
import io.swagger.annotations.Api;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * -- 文件处理
 * - Controller
 *
 * @author lmay.Zhou
 * @date 2020/6/2 11:47 星期二
 * @email lmay_zlm@meten.com
 */
@RestController
@RequestMapping("/file")
@Api(tags = "文件处理")
public class FileHandleController {
    /**
     * 批量下载
     *
     * @param response response
     */
    @GetMapping("/downloadBatch")
    public void downloadBatch(HttpServletResponse response) throws Exception {
        // 需要编码否则中文乱码
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("测试.zip", "UTF-8"));
        response.setContentType("application/zip;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        FileUtils.zipBatchDownload(response.getOutputStream(), ResourceUtils.getFile("classpath:"));
    }
}
