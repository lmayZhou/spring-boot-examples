package com.lmaye.spring.boot.api2doc.controller;

import com.lmaye.spring.boot.api2doc.common.Response;
import com.lmaye.spring.boot.api2doc.entity.CityEntity;
import com.lmaye.spring.boot.api2doc.param.SearchParam;
import com.lmaye.spring.boot.api2doc.service.CityService;
import com.terran4j.commons.api2doc.annotations.Api2Doc;
import com.terran4j.commons.api2doc.annotations.ApiComment;
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
@RequestMapping("/city")
@ApiComment(seeClass = CityEntity.class)
@Api2Doc(id = "city", name = "ES 城市搜索引擎管理")
public class CityController {
    /**
     * ES城市 Service
     */
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    /**
     * 新增
     *
     * @param param 请求参数
     * @return Mono<Response>
     */
    @PostMapping
    @ApiComment("新增")
    public Mono<Response> save(@RequestBody @Valid @ApiComment("城市实体") CityEntity param) {
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
    @ApiComment("根据城市编号删除")
    public Mono<Response> delete(@ApiComment(value = "城市编号") @PathVariable long id) {
        cityService.deleteCityById(id);
        return Mono.just(Response.success("删除成功"));
    }

    /**
     * 搜索城市
     * - 根据ID
     *
     * @param id ID
     * @return Mono<Response>
     */
    @GetMapping("/{id}")
    @ApiComment("根据城市编号搜索")
    public Mono<Response> searchById(@ApiComment("城市编号") @PathVariable long id) {
        return Mono.just(Response.success(cityService.searchById(id)));
    }

    /**
     * 搜索所有的城市
     *
     * @return Mono<Response>
     */
    @GetMapping("/all")
    @ApiComment("搜索所有的城市")
    public Mono<Response> searchAll() {
        return Mono.just(Response.success(cityService.searchAll()));
    }

    /**
     * 搜索
     *
     * @param param 请求参数
     * @return Mono<Response>
     */
    @PostMapping("/search")
    @ApiComment("搜索分页查询")
    public Mono<Response> search(@RequestBody @Valid @ApiComment("搜索参数") SearchParam param) {
        return Mono.just(Response.success(cityService.searchCities(param)));
    }
}
