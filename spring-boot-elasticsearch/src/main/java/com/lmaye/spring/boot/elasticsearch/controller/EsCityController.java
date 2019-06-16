package com.lmaye.spring.boot.elasticsearch.controller;

import com.lmaye.examples.common.common.Response;
import com.lmaye.spring.boot.elasticsearch.entity.EsCityEntity;
import com.lmaye.spring.boot.elasticsearch.param.EsSearchParam;
import com.lmaye.spring.boot.elasticsearch.service.EsCityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Objects;

/**
 * -- ES搜索引擎 Controller
 *
 * @author lmay.Zhou
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since 2019/6/11 23:52 星期二
 */
@RestController
@RequestMapping("/es/city")
@Api(tags = "ES 城市搜索引擎管理", description = "包含新增、删除、搜索等接口")
public class EsCityController {
    /**
     * ES城市 Service
     */
    private final EsCityService cityService;

    public EsCityController(EsCityService cityService) {
        this.cityService = cityService;
    }

    /**
     * 新增
     *
     * @param param 请求参数
     * @return Mono<Response>
     */
    @PostMapping
    @ApiOperation(value = "新增", response = Response.class)
    public Mono<Response> save(@RequestBody @Valid EsCityEntity param) {
        if (Objects.isNull(cityService.saveCity(param))) {
            return Mono.just(Response.failed());
        }
        return Mono.just(Response.success("新增成功"));
    }

    /**
     * 删除
     *
     * @param id 城市编号
     * @return Mono<Response>
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据城市编号删除", response = Response.class)
    public Mono<Response> delete(@ApiParam(required = true, name = "ID", value = "城市编号") @PathVariable Long id) {
        cityService.deleteCityById(id);
        return Mono.just(Response.success("删除成功"));
    }

    /**
     * 搜索
     *
     * @param param 请求参数
     * @return Mono<Response>
     */
    @PostMapping("/search")
    @ApiOperation(value = "搜索分页查询", response = EsCityEntity.class)
    public Mono<Response> search(@RequestBody @Valid EsSearchParam param) {
        return Mono.just(Response.success(cityService.searchCities(param)));
    }
}
