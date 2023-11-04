package com.lmaye.cloud.example.logs.service.impl;

import com.lmaye.cloud.example.logs.repository.ServiceLogRepository;
import com.lmaye.cloud.example.logs.service.IServiceLogService;
import com.lmaye.cloud.starter.elasticsearch.service.impl.ElasticSearchServiceImpl;
import com.lmaye.cloud.starter.logs.entity.SysLogEntity;
import org.springframework.stereotype.Service;

/**
 * -- ServiceLog Service Impl
 *
 * @author lmay.Zhou
 * @date 2020/12/3 14:20
 * @email lmay@lmaye.com
 */
@Service
public class ServiceLogServiceImpl extends ElasticSearchServiceImpl<ServiceLogRepository, SysLogEntity, Long>
        implements IServiceLogService {
    public ServiceLogServiceImpl(ServiceLogRepository repository) {
        super(repository);
    }
}
