package com.lmaye.spring.boot.skywalking.controller;

import com.lmaye.cloud.starter.web.context.ResultVO;
import com.lmaye.spring.boot.skywalking.entity.EsCityEntity;
import com.lmaye.spring.boot.skywalking.param.EsSearchParam;
import com.lmaye.spring.boot.skywalking.service.EsCityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.properties.LongProperty;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.ArrayList;
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
    @ApiOperation(value = "新增", notes = "新增城市", response = ResultVO.class)
    public Mono<ResultVO<String>> save(@RequestBody @Valid EsCityEntity param) {
        if (Objects.isNull(cityService.saveCity(param))) {
            return Mono.just(ResultVO.failed());
        }
        return Mono.just(ResultVO.success("新增成功"));
    }

    /**
     * 删除
     *
     * @param id 城市编号
     * @return Mono<ResultVO>
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据城市编号删除", notes = "根据城市编号删除", response = ResultVO.class)
    public Mono<ResultVO<String>> delete(@ApiParam(required = true, name = "id", value = "城市编号", type = LongProperty.TYPE, example = "1")
                                     @PathVariable long id) {
        cityService.deleteCityById(id);
        return Mono.just(ResultVO.success("删除成功"));
    }

    /**
     * 搜索城市
     * - 根据ID
     *
     * @param id ID
     * @return ResultVO<EsCityEntity>
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "根据城市编号搜索", notes = "根据城市编号搜索城市")
    public ResultVO<EsCityEntity> searchById(@ApiParam(required = true, name = "id", value = "城市编号", type = LongProperty.TYPE, example = "1")
                                             @PathVariable long id) {
        return ResultVO.success(cityService.searchById(id));
    }

    /**
     * 搜索所有的城市
     *
     * @return ResultVO<ArrayList<EsCityEntity>>
     */
    @GetMapping("/all")
    @ApiOperation(value = "搜索所有的城市", notes = "搜索所有的城市")
    public ResultVO<ArrayList<EsCityEntity>> searchAll() {
        return ResultVO.success(cityService.searchAll());
    }

    /**
     * 搜索
     *
     * @param param 请求参数
     * @return ResultVO<Page<EsCityEntity>>
     */
    @PostMapping("/search")
    @ApiOperation(value = "搜索分页查询", notes = "搜索分页查询")
    public ResultVO<Page<EsCityEntity>> search(@RequestBody @Valid EsSearchParam param) {
        return ResultVO.success(cityService.searchCities(param));
    }
}
