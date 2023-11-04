package com.lmaye.cloud.example.logs.repository;

import com.lmaye.cloud.starter.elasticsearch.repository.IElasticSearchRepository;
import com.lmaye.cloud.starter.logs.entity.SysLogEntity;
import org.springframework.stereotype.Repository;

/**
 * -- UserLog Repository
 *
 * @author lmay.Zhou
 * @date 2020/12/2 12:26
 * @email lmay@lmaye.com
 */
@Repository
public interface UserLogRepository extends IElasticSearchRepository<SysLogEntity, Long> {
}
