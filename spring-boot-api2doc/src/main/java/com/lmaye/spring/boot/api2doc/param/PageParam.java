package com.lmaye.spring.boot.api2doc.param;

import com.terran4j.commons.api2doc.annotations.ApiComment;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * -- 分页参数
 *
 * @author lmay.Zhou
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since 2019/6/12 22:23 星期三
 */
@Data
@ToString(callSuper = true)
public class PageParam implements Serializable {
    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 页码
     */
    @ApiComment(value = "页码", sample = "1")
    private Integer pageNumber = 1;

    /**
     * 页数
     */
    @ApiComment(value = "页数", sample = "10")
    private Integer pageSize = 10;
}
