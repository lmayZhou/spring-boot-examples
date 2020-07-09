package com.lmaye.spirng.boot.contract.controller;

import com.lmaye.spirng.boot.contract.utils.ITextPdfUtil;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
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
    public void ftlToPdf(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        String pdfName = "contract-templates";
        Map<String, Object> data = new HashMap<>(1);
        data.put("companyFullName", "寻梦科技有限公司");
        ByteArrayOutputStream out = ITextPdfUtil.processPdf(data, pdfName, null, 595.0F, 842.0F);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/pdf");
        OutputStream sOut = httpServletResponse.getOutputStream();
        sOut.flush();
        sOut.write(out.toByteArray());
        sOut.close();
    }

    @GetMapping(value = "/pseToPdf")
    public void pseToPdf(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        String pdfName = "pse-template";
        Map<String, Object> data = new HashMap<>(1);
        data.put("courseNo", "寻梦科技有限公司");
        ByteArrayOutputStream out = ITextPdfUtil.processPdf(data, pdfName, null, 595.0F, 842.0F);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/pdf");
        OutputStream sOut = httpServletResponse.getOutputStream();
        sOut.flush();
        sOut.write(out.toByteArray());
        sOut.close();
    }
}
