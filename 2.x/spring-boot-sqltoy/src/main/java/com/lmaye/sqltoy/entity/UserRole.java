package com.lmaye.sqltoy.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.sagacity.sqltoy.config.annotation.Column;
import org.sagacity.sqltoy.config.annotation.Entity;
import org.sagacity.sqltoy.config.annotation.Id;

import java.util.Date;
import java.util.List;

/**
 * -- UserRole
 *
 * @author lmayZhou
 * @version 1.0.0
 * @project sqltoy
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Entity(tableName = "user_role", comment = "用户信息", pk_constraint = "PRIMARY")
public class UserRole extends BaseEntity {
    private static final long serialVersionUID = 3004357668182873641L;

    /**
     * 主键ID
     */
    @Id(strategy = "generator", generator = "org.sagacity.sqltoy.plugins.id.impl.DefaultIdGenerator")
    @Column(name = "id", comment = "主键ID", length = 19L, type = java.sql.Types.BIGINT, nullable = false)
    private Long id;

    /**
     * 用户ID
     */
    @Column(name = "user_id", comment = "用户ID", length = 19L, defaultValue = "0", type = java.sql.Types.BIGINT, nullable = false)
    private Long userId;

    /**
     * 角色ID
     */
    @Column(name = "role_id", comment = "角色ID", length = 19L, defaultValue = "0", type = java.sql.Types.BIGINT, nullable = false)
    private Long roleId;

    /**
     * 用户名称
     */
    private Long userName;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色ID
     */
    private List<Long> roleIds;

    /**
     * 开始时间
     */
    private Date startDate;

    /**
     * 结束时间
     */
    private Date endDate;
}
