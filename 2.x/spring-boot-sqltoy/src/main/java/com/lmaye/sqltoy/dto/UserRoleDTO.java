package com.lmaye.sqltoy.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * -- UserRoleDTO
 *
 * @author lmayZhou
 * @version 1.0.0
 * @project sqltoy
 */
@Schema(name = "UserRoleDTO", description = "用户角色")
@Data
@Accessors(chain = true)
public class UserRoleDTO implements Serializable {
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
     * 角色ID
     */
    @Schema(name = "roleId", description = "角色ID")
    private Long roleId;

    /**
     * 状态(0. 正常; 1. 停用; 2. 删除;)
     */
    @Schema(name = "status", description = "状态(0. 正常; 1. 停用; 2. 删除;)")
    private Boolean status;
}
