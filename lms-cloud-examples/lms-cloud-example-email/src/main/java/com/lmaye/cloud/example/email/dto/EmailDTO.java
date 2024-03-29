package com.lmaye.cloud.example.email.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * -- Email
 *
 * @author Lmay Zhou
 * @date 2021/4/14 16:52
 * @email lmay@lmaye.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "EmailDTO", description = "邮件信息")
public class EmailDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 邮件发送者
     * - 未指定: 随机或默认邮箱账号
     */
    @ApiModelProperty("邮件发送者(未指定: 随机或默认邮箱账号)")
    private String mailSender;

    /**
     * 发件人名称
     * - 未指定: 默认邮箱账号名称
     */
    @ApiModelProperty("发件人名称(未指定: 默认邮箱账号名称)")
    private String senderName;

    /**
     * 邮件接收人
     */
    @NotNull
    @ApiModelProperty(value = "邮件接收人", required = true)
    private String[] recipient;

    /**
     * 抄送
     */
    @ApiModelProperty("抄送")
    private String[] cc;

    /**
     * 密送
     */
    @ApiModelProperty("密送")
    private String[] bcc;

    /**
     * 邮件主题
     */
    @NotBlank
    @ApiModelProperty(value = "邮件主题", required = true)
    private String subject;

    /**
     * 发送邮件格式为 HTML
     */
    @ApiModelProperty(value = "是否HTML格式", example = "false")
    private Boolean html = false;

    /**
     * 邮件内容
     * <pre>
     *     HTML模版填充示例:
     *
     *     import freemarker.template.Configuration;
     *     ...
     *     FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate("ResetPassword.ftl"), GsonUtils.fromJson(data, Map.class))
     * </pre>
     */
    @NotBlank
    @ApiModelProperty(value = "邮件内容", required = true)
    private String content;
}
