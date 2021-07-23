package com.lmaye.cloud.example.elasticsearch.controller;

import com.lmaye.cloud.example.elasticsearch.controller.base.BaseEsController;
import com.lmaye.cloud.example.elasticsearch.entity.ServiceLogEntity;
import com.lmaye.cloud.example.elasticsearch.service.IServiceLogService;
import com.lmaye.cloud.starter.web.context.PageResult;
import com.lmaye.cloud.starter.web.context.ResultVO;
import com.lmaye.cloud.starter.web.query.ListQuery;
import com.lmaye.cloud.starter.web.query.PageQuery;
import com.lmaye.cloud.starter.web.query.Query;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * -- ServiceLog Controller
 *
 * @author lmay.Zhou
 * @date 2020/12/3 14:08
 * @email lmay@lmaye.com
 */
@RestController
@RequestMapping("/es/log/service")
@Api(tags = "服务日志ES相关接口")
public class ServiceLogController extends BaseEsController<IServiceLogService, ServiceLogEntity, Long> {
    public ServiceLogController(IServiceLogService service) {
        super(service);
    }

    /**
     * 查询日志All
     *
     * @param query 查询参数
     * @return ResultVO<List < ServiceLogEntity>>
     */
    @PostMapping("/searchAll")
    @ApiOperation("查询日志All")
    public ResultVO<List<ServiceLogEntity>> searchAll(@RequestBody ListQuery query) {
        return ResultVO.success(service.findAll(query, ServiceLogEntity.class));
    }

    /**
     * 查询日志All
     * - 深度
     *
     * @param query 查询参数
     * @return ResultVO<List < ServiceLogEntity>>
     */
    @PostMapping("/searchScrollAll")
    @ApiOperation("查询日志All(深度)")
    public ResultVO<List<ServiceLogEntity>> searchScrollAll(@RequestBody ListQuery query) {
        return ResultVO.success(service.findScrollAll(query, ServiceLogEntity.class));
    }

    /**
     * 分页查询日志
     *
     * @param query 查询参数
     * @return ResultVO<PageResult < ServiceLogEntity>>
     */
    @PostMapping("/searchPage")
    @ApiOperation("分页查询日志")
    public ResultVO<PageResult<ServiceLogEntity>> searchPage(@RequestBody PageQuery query) {
        return ResultVO.success(service.findPage(query, ServiceLogEntity.class));
    }

    /**
     * 分页查询日志
     * - 深度(不支持跨页)
     *
     * @param query 查询参数
     * @return ResultVO<PageResult < ServiceLogEntity>>
     */
    @PostMapping("/searchScrollPage")
    @ApiOperation(value = "分页查询日志(深度)", notes = "不支持跨页")
    public ResultVO<PageResult<ServiceLogEntity>> searchScrollPage(@RequestBody PageQuery query) {
        return ResultVO.success(service.findScrollPage(query, ServiceLogEntity.class));
    }

    /**
     * 查询日志总数
     *
     * @param query 查询参数
     * @return ResultVO<Long>
     */
    @PostMapping("/count")
    @ApiOperation("查询日志总数")
    public ResultVO<Long> count(@RequestBody Query query) {
        return ResultVO.success(service.count(query, ServiceLogEntity.class));
    }
}
