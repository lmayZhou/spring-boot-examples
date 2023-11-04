package com.lmaye.cloud.example.logs.service.impl;

import com.lmaye.cloud.example.logs.repository.UserLogRepository;
import com.lmaye.cloud.example.logs.service.IUserLogService;
import com.lmaye.cloud.starter.elasticsearch.service.impl.ElasticSearchServiceImpl;
import com.lmaye.cloud.starter.logs.entity.SysLogEntity;
import org.springframework.stereotype.Service;

/**
 * -- UserLog Service Impl
 *
 * @author lmay.Zhou
 * @date 2020/12/2 12:29
 * @email lmay@lmaye.com
 */
@Service
public class UserLogServiceImpl extends ElasticSearchServiceImpl<UserLogRepository, SysLogEntity, Long>
        implements IUserLogService {
    public UserLogServiceImpl(UserLogRepository repository) {
        super(repository);
    }
}
