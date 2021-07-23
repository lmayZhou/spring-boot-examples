package com.lmaye.spring.boot.elasticsearch.service;

import com.lmaye.spring.boot.elasticsearch.entity.EsCityEntity;
import com.lmaye.spring.boot.elasticsearch.param.EsSearchParam;
import org.springframework.data.domain.Page;

import java.util.ArrayList;

/**
 * -- ES城市 Service
 *
 * @author lmay.Zhou
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since 2019/6/12 22:13 星期三
 */
public interface EsCityService {
    /**
     * 保存城市
     *
     * @param entity 实体
     * @return EsCityEntity
     */
    EsCityEntity saveCity(EsCityEntity entity);

    /**
     * 删除城市
     * - 根据ID
     *
     * @param id ID
     */
    void deleteCityById(Long id);

    /**
     * 搜索城市
     * - 根据ID
     *
     * @param id ID
     * @return EsCityEntity
     */
    EsCityEntity searchById(Long id);

    /**
     * 搜索所有的城市
     *
     * @return ArrayList<EsCityEntity>
     */
    ArrayList<EsCityEntity> searchAll();

    /**
     * 搜索城市
     *
     * @param searchParam 搜索参数
     * @return Page<EsCityEntity>
     */
    Page<EsCityEntity> searchCities(EsSearchParam searchParam);
}
