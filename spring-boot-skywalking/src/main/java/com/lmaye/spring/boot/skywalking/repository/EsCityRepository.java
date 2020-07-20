package com.lmaye.spring.boot.skywalking.repository;

import com.lmaye.spring.boot.skywalking.entity.EsCityEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * -- ES城市 Repository
 *
 * @author lmay.Zhou
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since 2019/6/11 23:38 星期二
 */
@Repository
public interface EsCityRepository extends ElasticsearchRepository<EsCityEntity, Long> {
}
