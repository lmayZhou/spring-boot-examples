package com.lmaye.spring.boot.api2doc.service.impl;

import com.lmaye.spring.boot.api2doc.entity.CityEntity;
import com.lmaye.spring.boot.api2doc.param.SearchParam;
import com.lmaye.spring.boot.api2doc.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
public class CityServiceImpl implements CityService {
    /**
     * 保存城市
     *
     * @param entity 实体
     * @return CityEntity
     */
    @Override
    public CityEntity saveCity(CityEntity entity) {
        // TODO 测试
        return entity;
    }

    /**
     * 删除城市
     * - 根据ID
     *
     * @param id ID
     */
    @Override
    public void deleteCityById(Long id) {
        // TODO 测试
    }

    /**
     * 搜索城市
     * - 根据ID
     *
     * @param id ID
     * @return CityEntity
     */
    @Override
    public CityEntity searchById(Long id) {
        CityEntity record = new CityEntity();
        record.setId(id);
        record.setProvinceId(10000L);
        record.setCityName("深圳");
        record.setDescription("广东省");
        return record;
    }

    /**
     * 搜索所有的城市
     *
     * @return  List<CityEntity>
     */
    @SuppressWarnings("Duplicates")
    @Override
    public List<CityEntity> searchAll() {
        CityEntity record = new CityEntity();
        record.setId(10000L);
        record.setProvinceId(10000L);
        record.setCityName("深圳");
        record.setDescription("广东省");
        ArrayList<CityEntity> records = new ArrayList<>();
        records.add(record);
        return records;
    }

    /**
     * 搜索城市
     *
     * @param searchParam 搜索参数
     * @return List<CityEntity>
     */
    @SuppressWarnings("Duplicates")
    @Override
    public List<CityEntity> searchCities(SearchParam searchParam) {
        CityEntity record = new CityEntity();
        record.setId(10000L);
        record.setProvinceId(10000L);
        record.setCityName("深圳");
        record.setDescription("广东省");
        ArrayList<CityEntity> records = new ArrayList<>();
        records.add(record);
        return records;
    }
}
