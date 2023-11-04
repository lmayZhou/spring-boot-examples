package com.lmaye.cloud.example.logs.service;

import com.lmaye.cloud.starter.elasticsearch.service.IElasticSearchService;
import com.lmaye.cloud.starter.logs.entity.SysLogEntity;

/**
 * -- ServiceLog Service
 *
 * @author lmay.Zhou
 * @date 2020/12/3 14:18
 * @email lmay@lmaye.com
 */
public interface IServiceLogService extends IElasticSearchService<SysLogEntity, Long> {
}
