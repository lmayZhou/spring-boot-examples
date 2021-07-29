package com.lmaye.cloud.example.email.controller;

import com.lmaye.cloud.example.email.converter.EmailRestConverter;
import com.lmaye.cloud.example.email.dto.EmailDTO;
import com.lmaye.cloud.example.email.vo.EmailVO;
import com.lmaye.cloud.starter.email.entity.Email;
import com.lmaye.cloud.starter.email.service.EmailSendService;
import com.lmaye.cloud.starter.web.context.ResultVO;
import com.lmaye.cloud.starter.web.service.impl.RestConverterImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

/**
 * -- Email Send Controller
 *
 * @author lmay.Zhou
 * @date 2021/6/28 23:26
 * @email lmay@lmaye.com
 * @since JDK1.8
 */
@AllArgsConstructor
@RestController
@RequestMapping("/email")
@Api(tags = "发送Email相关接口")
public class EmailSendController extends RestConverterImpl<EmailRestConverter, Email, EmailVO, EmailDTO> {
    /**
     * Email Send Service
     */
    private final EmailSendService emailSendService;

    /**
     * 发送邮件
     *
     * @param param 请求参数
     * @return ResultVO<Boolean>
     */
    @PostMapping("/send")
    @ApiOperation("发送邮件")
    public Mono<ResultVO<Boolean>> sendEmail(@RequestBody @Validated EmailDTO param) {
        return Mono.just(ResultVO.success(emailSendService.sendMail(restConverter.convertDtoToEntity(param))));
    }

    /**
     * 发送邮件
     *
     * @param param 请求参数
     * @return ResultVO<Boolean>
     */
    @PostMapping(value = "/sendA", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ApiOperation("发送邮件-附件")
    public Mono<ResultVO<Boolean>> sendEmailA(@RequestPart("files") MultipartFile[] files, @Validated EmailDTO param) {
        Email entity = restConverter.convertDtoToEntity(param);
        entity.setFiles(files);
        return Mono.just(ResultVO.success(emailSendService.sendMail(entity)));
    }

    /**
     * 发送邮件
     * - 动态发件者
     *
     * @param param 请求参数
     * @return ResultVO<Boolean>
     */
    @PostMapping("/sendDynamic")
    @ApiOperation("发送邮件 - 动态发件者")
    public Mono<ResultVO<Boolean>> sendDynamicEmail(@RequestBody EmailDTO param) {
        return Mono.just(ResultVO.success(emailSendService.dynamicSendMail(restConverter.convertDtoToEntity(param))));
    }
}
