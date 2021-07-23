package com.lmaye.spring.boot.api2doc.param;

import com.terran4j.commons.api2doc.annotations.ApiComment;
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
public class SearchParam extends PageParam {
    /**
     * 搜索内容
     */
    @ApiComment(value = "搜索内容", sample = "深圳")
    private String searchContent;
}
