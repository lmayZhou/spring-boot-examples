package com.lmaye.spirng.boot.contract.controller;

import com.lmaye.spirng.boot.contract.utils.ITextPdfUtil;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * --
 *
 * @author lmay.Zhou
 * @date 2020/6/10 13:49 星期三
 * @email lmay_zlm@meten.com
 */
@Controller
@RequestMapping("/contract")
@Api(tags = "文件处理")
public class ContractController {
    @GetMapping(value = "/ftlToPdf")
    public void ftlToPdf(HttpServletResponse response) throws Exception {
        Map<String, Object> data = new HashMap<>(1);
        data.put("companyFullName", "寻梦科技有限公司");
        ByteArrayOutputStream out = ITextPdfUtil.processPdf(data, "contract-templates", null, 595.0F, 842.0F);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/pdf");
        OutputStream sOut = response.getOutputStream();
        sOut.flush();
        sOut.write(out.toByteArray());
        sOut.close();
    }

    @GetMapping(value = "/pseToPdf")
    public void pseToPdf(HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("测试.zip", "UTF-8"));
        response.setContentType("application/zip;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        List<ByteArrayOutputStream> outs = new ArrayList<>();
        Map<String, Object> data = new HashMap<>(1);
        data.put("courseNo", "寻梦科技有限公司");
        ByteArrayOutputStream out = ITextPdfUtil.processPdf(data, "pse-template", null, null, null);
        outs.add(out);
        outs.add(out);
        ITextPdfUtil.zipBatchDownload(response.getOutputStream(), outs);
    }
}
