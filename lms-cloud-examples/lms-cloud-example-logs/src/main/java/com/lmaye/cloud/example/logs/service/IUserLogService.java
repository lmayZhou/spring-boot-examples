package com.lmaye.cloud.example.logs.service;

import com.lmaye.cloud.starter.elasticsearch.service.IElasticSearchService;
import com.lmaye.cloud.starter.logs.entity.SysLogEntity;

/**
 * -- UserLog Service
 *
 * @author lmay.Zhou
 * @date 2020/12/2 12:28
 * @email lmay@lmaye.com
 */
public interface IUserLogService extends IElasticSearchService<SysLogEntity, Long> {
}
