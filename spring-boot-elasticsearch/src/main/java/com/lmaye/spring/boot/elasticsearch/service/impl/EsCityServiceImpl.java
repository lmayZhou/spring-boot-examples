package com.lmaye.spring.boot.elasticsearch.service.impl;

import com.lmaye.spring.boot.elasticsearch.entity.EsCityEntity;
import com.lmaye.spring.boot.elasticsearch.param.EsSearchParam;
import com.lmaye.spring.boot.elasticsearch.repository.EsCityRepository;
import com.lmaye.spring.boot.elasticsearch.service.EsCityService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
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
     * @return  Iterator<EsCityEntity>
     */
    @Override
    public Iterator<EsCityEntity> searchAll() {
        return cityRepository.findAll().iterator();
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
     * @param searchParam 搜索参数
     * @return List<EsCityEntity>
     */
    @Override
    public List<EsCityEntity> searchCities(EsSearchParam searchParam) {
        String searchContent = searchParam.getSearchContent();
        // 分页参数（传入页码 - 1）
        Pageable pageable = PageRequest.of(searchParam.getPageNumber() - 1, searchParam.getPageSize());
        // 权重查询
        List<FunctionScoreQueryBuilder.FilterFunctionBuilder> filterFunctionBuilders = new ArrayList<>();
        filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(
                QueryBuilders.matchQuery("cityName", searchContent), ScoreFunctionBuilders.weightFactorFunction(3)));
        filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(
                QueryBuilders.matchQuery("description", searchContent), ScoreFunctionBuilders.weightFactorFunction(2)));
        FunctionScoreQueryBuilder.FilterFunctionBuilder[] builders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[filterFunctionBuilders.size()];
        filterFunctionBuilders.toArray(builders);
        FunctionScoreQueryBuilder builder = QueryBuilders.functionScoreQuery(builders).scoreMode(FunctionScoreQuery.ScoreMode.SUM).setMinScore(2);
        // 创建搜索 DSL 查询
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable).withQuery(builder).build();
        log.info("\n searchCity(): searchContent [{}] \n DSL  = \n {}", searchContent, searchQuery.getQuery().toString());
        Page<EsCityEntity> searchPageResults = cityRepository.search(searchQuery);
        return searchPageResults.getContent();
    }
}
