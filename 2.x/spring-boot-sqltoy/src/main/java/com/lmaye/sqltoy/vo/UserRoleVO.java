package com.lmaye.sqltoy.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * -- UserRoleVO
 *
 * @author lmayZhou
 * @version 1.0.0
 * @project sqltoy
 */
@Schema(name = "UserRoleVO", description = "用户角色")
@Data
@Accessors(chain = true)
public class UserRoleVO implements Serializable {
    private static final long serialVersionUID = 957018156945049850L;

    /**
     * 主键ID
     */
    @Schema(name = "id", description = "主键ID")
    private Long id;

    /**
     * 用户ID
     */
    @Schema(name = "userId", description = "用户ID")
    private Long userId;

    /**
     * 用户名称
     */
    @Schema(name = "userName", description = "用户名称")
    private Long userName;

    /**
     * 角色ID
     */
    @Schema(name = "roleId", description = "角色ID")
    private Long roleId;

    /**
     * 角色名称
     */
    @Schema(name = "roleName", description = "角色名称")
    private String roleName;

    /**
     * 状态(0. 正常; 1. 停用; 2. 删除;)
     */
    @Schema(name = "status", description = "状态(0. 正常; 1. 停用; 2. 删除;)")
    private Boolean status;

    /**
     * 创建时间
     */
    @Schema(name = "createAt", description = "创建时间")
    private LocalDateTime createAt;

    /**
     * 创建人
     */
    @Schema(name = "createBy", description = "创建人")
    private String createBy;
}
