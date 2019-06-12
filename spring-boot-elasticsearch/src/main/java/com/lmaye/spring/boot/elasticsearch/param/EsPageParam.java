package com.lmaye.spring.boot.elasticsearch.param;

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
public class EsPageParam implements Serializable {
    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 页码
     */
    private Integer pageNumber = 1;

    /**
     * 页数
     */
    private Integer pageSize = 10;
}
