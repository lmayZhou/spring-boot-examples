package com.lmaye.cloud.example.drools.service;

import com.lmaye.cloud.example.drools.dto.RuleDrlDTO;
import com.lmaye.cloud.example.drools.entity.RuleDrl;
import com.lmaye.cloud.example.drools.vo.RuleDrlVO;
import com.lmaye.cloud.starter.web.service.IRestConverter;
import org.mapstruct.Mapper;

/**
 * -- RuleDrl Converter
 *
 * @author lmay.Zhou
 * @date 2021/6/24 16:46
 * @email lmay@lmaye.com
 * @since JDK1.8
 */
@Mapper(componentModel = "spring")
public interface RuleDrlRestConverter extends IRestConverter<RuleDrl, RuleDrlVO, RuleDrlDTO> {
}
