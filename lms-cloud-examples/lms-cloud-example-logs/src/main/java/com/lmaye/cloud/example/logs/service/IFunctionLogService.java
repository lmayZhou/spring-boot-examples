package com.lmaye.cloud.example.logs.service;

import com.lmaye.cloud.starter.elasticsearch.service.IElasticSearchService;
import com.lmaye.cloud.starter.logs.entity.FunctionLogEntity;

/**
 * -- FunctionLog Service
 *
 * @author lmay.Zhou
 * @date 2020/12/3 14:18
 * @email lmay@lmaye.com
 */
public interface IFunctionLogService extends IElasticSearchService<FunctionLogEntity, Long> {
}
