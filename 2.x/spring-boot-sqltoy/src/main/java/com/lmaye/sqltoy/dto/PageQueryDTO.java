package com.lmaye.sqltoy.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
     * 用户ID
     */
    @Schema(name = "userId", description = "用户ID")
    private Long userId;

    /**
     * 角色ID
     */
    @Schema(name = "roleIds", description = "角色ID")
    private List<Long> roleIds;

    /**
     * 用户名
     */
    @Schema(name = "userName", description = "用户名")
    private String userName;

    /**
     * 开始时间
     */
    @Schema(name = "startDate", description = "开始时间")
    private Date startDate;

    /**
     * 结束时间
     */
    @Schema(name = "endDate", description = "结束时间")
    private Date endDate;
}
