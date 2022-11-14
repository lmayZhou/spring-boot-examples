package com.lmaye.cloud.example.logs.service.impl;

import com.lmaye.cloud.example.logs.repository.FunctionLogRepository;
import com.lmaye.cloud.example.logs.service.IFunctionLogService;
import com.lmaye.cloud.starter.elasticsearch.service.impl.ElasticSearchServiceImpl;
import com.lmaye.cloud.starter.logs.entity.SysLogEntity;
import org.springframework.stereotype.Service;

/**
 * -- FunctionLog Service Impl
 *
 * @author lmay.Zhou
 * @date 2020/12/3 14:21
 * @email lmay@lmaye.com
 */
@Service
public class FunctionLogServiceImpl extends ElasticSearchServiceImpl<FunctionLogRepository, SysLogEntity, Long>
        implements IFunctionLogService {
    public FunctionLogServiceImpl(FunctionLogRepository repository) {
        super(repository);
    }
}
