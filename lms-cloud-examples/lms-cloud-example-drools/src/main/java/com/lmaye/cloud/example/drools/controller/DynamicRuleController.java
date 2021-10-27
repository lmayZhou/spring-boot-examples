package com.lmaye.cloud.example.drools.controller;

import com.lmaye.cloud.example.drools.dto.RuleDrlDTO;
import com.lmaye.cloud.example.drools.entity.Order;
import com.lmaye.cloud.example.drools.entity.RuleDrl;
import com.lmaye.cloud.example.drools.service.IRuleDrlService;
import com.lmaye.cloud.example.drools.service.RuleDrlRestConverter;
import com.lmaye.cloud.example.drools.vo.RuleDrlVO;
import com.lmaye.cloud.starter.web.context.ResultVO;
import com.lmaye.cloud.starter.web.service.impl.RestConverterImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * -- Dynamic Rule Controller
 *
 * @author Lmay Zhou
 * @date 2021/10/25 15:26
 * @email lmay@lmaye.com
 * @since JDK1.8
 */
@AllArgsConstructor
@RestController
@RequestMapping("/dynamicRule")
@Api(tags = "动态规则相关接口")
public class DynamicRuleController extends RestConverterImpl<RuleDrlRestConverter, RuleDrl, RuleDrlVO, RuleDrlDTO> {
    /**
     * Rule Drl Service
     */
    private final IRuleDrlService ruleDrlService;

    /**
     * 保存
     *
     * @param param 请求参数
     * @return Mono<ResultVO<Boolean>>
     */
    @PostMapping("/save")
    @ApiOperation("保存")
    public Mono<ResultVO<Boolean>> save(@RequestBody @Validated RuleDrlDTO param) {
        return Mono.just(ResultVO.success(ruleDrlService.save(restConverter.convertDtoToEntity(param))));
    }

    /**
     * 查询
     *
     * @param id 主键ID
     * @return Mono<ResultVO<RuleDrlVO>>
     */
    @GetMapping("/{id}")
    @ApiOperation("查询规则")
    public Mono<ResultVO<RuleDrlVO>> query(@PathVariable @ApiParam(value = "主键ID", required = true) Integer id) {
        return Mono.just(ResultVO.success(restConverter.convertEntityToVo(ruleDrlService.getById(id))));
    }

    /**
     * 执行规则
     *
     * @param ruleId 规则ID
     * @param originalPrice 原价
     * @return Mono<ResultVO<Order>>
     */
    @GetMapping("/execOrder/{ruleId}/{originalPrice}")
    @ApiOperation("执行规则")
    public Mono<ResultVO<Order>> execOrder(@PathVariable @ApiParam(value = "规则ID", required = true) Integer ruleId,
                                           @PathVariable @ApiParam(value = "原价", required = true) Double originalPrice) {
        return Mono.just(ResultVO.success(ruleDrlService.execOrder(ruleId, originalPrice)));
    }
}
