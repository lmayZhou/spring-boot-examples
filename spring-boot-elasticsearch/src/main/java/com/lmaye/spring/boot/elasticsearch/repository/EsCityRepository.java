package com.lmaye.spring.boot.elasticsearch.repository;

import com.lmaye.spring.boot.elasticsearch.entity.EsCityEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * -- 城市 Repository
 *
 * @author lmay.Zhou
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since 2019/6/11 23:38 星期二
 */
@Repository
public interface EsCityRepository extends ElasticsearchRepository<EsCityEntity, Long> {
}
