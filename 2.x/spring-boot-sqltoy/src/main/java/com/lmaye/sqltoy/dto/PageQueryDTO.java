package com.lmaye.sqltoy.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * -- QueryDTO
 *
 * @author Lmay Zhou
 * @date 2022/11/14 16:32
 * @email lmay@lmaye.com
 * @since JDK17
 */
@Schema(name = "SysUserDTO", description = "搜索信息")
@Data
@Accessors(chain = true)
public class PageQueryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 当前页码(默认: 1)
     */
    @Min(1)
    @Schema(description = "当前页码(默认: 1)", defaultValue = "1")
    private Long pageIndex = 1L;

    /**
     * 每页显示页数(默认: 10)
     */
    @Range(min = 1, max = 10000)
    @Schema(description = "每页显示页数(默认: 10)", defaultValue = "10")
    private Long pageSize = 10L;

    /**
     * 用户名
     */
    @Schema(name = "userName", description = "用户名")
    private String userName;
}
