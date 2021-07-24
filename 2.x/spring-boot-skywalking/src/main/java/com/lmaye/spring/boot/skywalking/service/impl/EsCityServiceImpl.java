package com.lmaye.spring.boot.skywalking.service.impl;

import com.google.common.collect.Lists;
import com.lmaye.spring.boot.skywalking.entity.EsCityEntity;
import com.lmaye.spring.boot.skywalking.param.EsSearchParam;
import com.lmaye.spring.boot.skywalking.repository.EsCityRepository;
import com.lmaye.spring.boot.skywalking.service.EsCityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * -- ES城市 Service 实现类
 *
 * @author lmay.Zhou
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since 2019/6/12 22:15 星期三
 */
@Slf4j
@Service
public class EsCityServiceImpl implements EsCityService {
    /**
     * ES城市 Repository
     */
    private final EsCityRepository cityRepository;

    public EsCityServiceImpl(EsCityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    /**
     * 保存城市
     *
     * @param entity 实体
     * @return EsCityEntity
     */
    @Override
    public EsCityEntity saveCity(EsCityEntity entity) {
        return cityRepository.save(entity);
    }

    /**
     * 删除城市
     * - 根据ID
     *
     * @param id ID
     */
    @Override
    public void deleteCityById(Long id) {
        cityRepository.deleteById(id);
    }

    /**
     * 搜索城市
     * - 根据ID
     *
     * @param id ID
     * @return EsCityEntity
     */
    @Override
    public EsCityEntity searchById(Long id) {
        return cityRepository.findById(id).orElse(null);
    }

    /**
     * 搜索所有的城市
     *
     * @return ArrayList<EsCityEntity>
     */
    @Override
    public ArrayList<EsCityEntity> searchAll() {
        return Lists.newArrayList(cityRepository.findAll().iterator());
    }

    /**
     * 搜索城市
     * <pre>
     * ES设置分页源码:
     * if (query.getPageable().isPaged()) {
     *      // 起始页码 = 当前页码 * 页面大小（故: 页码传入时 - 1）
     *      startRecord = query.getPageable().getPageNumber() * query.getPageable().getPageSize();
     *      // 页码大小
     *      searchRequestBuilder.setSize(query.getPageable().getPageSize());
     * }
     * searchRequestBuilder.setFrom(startRecord);
     * </pre>
     *
     * @param searchParam 搜索参数
     * @return Page<EsCityEntity>
     */
    @Override
    public Page<EsCityEntity> searchCities(EsSearchParam searchParam) {
        String searchContent = searchParam.getSearchContent();
        // 分页参数（传入页码 - 1）
        Pageable pageable = PageRequest.of(searchParam.getPageNumber() - 1, searchParam.getPageSize());
        EsCityEntity query = new EsCityEntity();
        query.setCityName(searchContent);
        query.setDescription(searchContent);
        return cityRepository.searchSimilar(query, new String[]{"cityName", "description"}, pageable);
    }
}
