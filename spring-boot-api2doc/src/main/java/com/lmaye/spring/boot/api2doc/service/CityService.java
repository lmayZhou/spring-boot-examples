package com.lmaye.spring.boot.api2doc.service;

import com.lmaye.spring.boot.api2doc.entity.CityEntity;
import com.lmaye.spring.boot.api2doc.param.SearchParam;

import java.util.List;

/**
 * -- ES城市 Service
 *
 * @author lmay.Zhou
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since 2019/6/12 22:13 星期三
 */
public interface CityService {
    /**
     * 保存城市
     *
     * @param entity 实体
     * @return CityEntity
     */
    CityEntity saveCity(CityEntity entity);

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
     * @return CityEntity
     */
    CityEntity searchById(Long id);

    /**
     * 搜索所有的城市
     *
     * @return  List<CityEntity>
     */
    List<CityEntity> searchAll();

    /**
     * 搜索城市
     *
     * @param searchParam 搜索参数
     * @return List<CityEntity>
     */
    List<CityEntity> searchCities(SearchParam searchParam);
}
