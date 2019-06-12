package com.lmaye.spring.boot.elasticsearch.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

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
@ApiModel("ES城市")
@Document(indexName = "cityIndex", type = "city_entity")
public class EsCityEntity implements Serializable {
    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 城市编号
     */
    @Id
    @NotNull
    @ApiModelProperty(value = "城市编号", required = true, dataType = "Long")
    private Long id;

    /**
     * 省份编号
     */
    @NotNull
    @ApiModelProperty(value = "省份编号", required = true, dataType = "Long")
    private Long provinceId;

    /**
     * 城市名称
     */
    @NotBlank
    @Field(type = FieldType.Keyword)
    @ApiModelProperty(value = "城市名称", required = true, dataType = "String")
    private String cityName;

    /**
     * 描述
     */
    @NotBlank
    @ApiModelProperty(value = "描述", dataType = "String")
    private String description;
}
