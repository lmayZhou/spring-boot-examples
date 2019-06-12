package com.lmaye.spring.boot.elasticsearch.param;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * -- ES搜索分页参数
 *
 * @author lmay.Zhou
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since 2019/6/12 22:28 星期三
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class EsSearchParam extends EsPageParam {
    /**
     * 搜索内容
     */
    private String searchContent;
}
