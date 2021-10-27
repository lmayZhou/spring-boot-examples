package com.lmaye.cloud.example.drools.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * <p>
 * 规则DRL
 * </p>
 *
 * @author lmayZhou
 * @since 2021-10-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "RuleDrlDTO", description = "规则DRL")
public class RuleDrlDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 规则名称
     */
    @NotEmpty
    @Length(min = 1, max = 128)
    @ApiModelProperty(value = "规则名称", required = true)
    private String ruleName;

    /**
     * 规则DRL
     */
    @NotEmpty
    @ApiModelProperty(value = "规则DRL内容", required = true)
    private String ruleDrl;
}