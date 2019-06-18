package com.lmaye.spring.boot.api2doc.entity;

import com.terran4j.commons.api2doc.annotations.ApiComment;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * -- ES城市 Entity
 *
 * @author lmay.Zhou
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since 2019/6/11 23:41 星期二
 */
@Data
public class CityEntity implements Serializable {
    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 城市编号
     */
    @NotNull
    @ApiComment(value = "id", sample = "10000")
    private Long id;

    /**
     * 省份编号
     */
    @NotNull
    @ApiComment(value = "省份编号", sample = "10000")
    private Long provinceId;

    /**
     * 城市名称
     */
    @NotBlank
    @ApiComment(value = "城市名称", sample = "深圳")
    private String cityName;

    /**
     * 描述
     */
    @NotBlank
    @ApiComment(value = "描述", sample = "深圳")
    private String description;
}
