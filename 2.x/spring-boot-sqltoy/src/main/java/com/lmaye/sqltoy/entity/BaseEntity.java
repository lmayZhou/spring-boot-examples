package com.lmaye.sqltoy.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.sagacity.sqltoy.config.annotation.Column;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * -- BaseEntity
 *
 * @author Lmay Zhou
 * @date 2022/11/29 16:50
 * @email lmay@lmaye.com
 * @since JDK17
 */
@Data
@Accessors(chain = true)
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 状态(0. 正常; 1. 停用; 2. 删除;)
     */
    @Column(name = "status", comment = "状态(0. 正常; 1. 停用; 2. 删除;)", length = 1L, defaultValue = "0", type = java.sql.Types.BIT, nullable = false)
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "create_at", comment = "创建时间", length = 19L, defaultValue = "CURRENT_TIMESTAMP", type = java.sql.Types.DATE, nullable = false)
    private LocalDateTime createAt;

    /**
     * 创建人
     */
    @Column(name = "create_by", comment = "创建人", length = 30L, defaultValue = "0", type = java.sql.Types.VARCHAR, nullable = false)
    private String createBy;

    /**
     * 更新时间
     */
    @Column(name = "update_at", comment = "更新时间", length = 19L, type = java.sql.Types.DATE, nullable = true)
    private LocalDateTime updateAt;

    /**
     * 更新人
     */
    @Column(name = "update_by", comment = "更新人", length = 30L, type = java.sql.Types.VARCHAR, nullable = true)
    private String updateBy;

    /**
     * 备注
     */
    @Column(name = "remark", comment = "备注", length = 500L, type = java.sql.Types.VARCHAR, nullable = true)
    private String remark;

    /**
     * 拓展字段
     */
    @Column(name = "ext", comment = "拓展字段", length = 500L, type = java.sql.Types.VARCHAR, nullable = true)
    private String ext;

    /**
     * 版本号
     */
    @Column(name = "version", comment = "版本号", length = 10L, defaultValue = "1", type = java.sql.Types.INTEGER, nullable = false)
    private Integer version;
}
