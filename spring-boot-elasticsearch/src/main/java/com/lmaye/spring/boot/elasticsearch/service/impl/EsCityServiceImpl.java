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
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.querydsl.QPageRequest;
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
     *
     * @param searchParam 搜索参数
     * @return List<EsCityEntity>
     */
    @Override
    public List<EsCityEntity> searchCities(EsSearchParam searchParam) {
        String searchContent = searchParam.getSearchContent();
        // 分页参数
        Pageable pageable = new QPageRequest(searchParam.getPageNumber(), searchParam.getPageSize());
        // 权重查询
        List<FunctionScoreQueryBuilder.FilterFunctionBuilder> filterFunctionBuilders = new ArrayList<>();
        filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(
                QueryBuilders.matchQuery("cityName", searchContent), ScoreFunctionBuilders.weightFactorFunction(3)));
        filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(
                QueryBuilders.matchQuery("description", searchContent), ScoreFunctionBuilders.weightFactorFunction(2)));
        FunctionScoreQueryBuilder.FilterFunctionBuilder[] builders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[filterFunctionBuilders.size()];
        filterFunctionBuilders.toArray(builders);
        FunctionScoreQueryBuilder builder = QueryBuilders.functionScoreQuery(builders).scoreMode(FunctionScoreQuery.ScoreMode.SUM).setMinScore(5);
        // 创建搜索 DSL 查询
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable).withQuery(builder).build();
        log.info("\n searchCity(): searchContent [{}] \n DSL  = \n {}", searchContent, searchQuery.getQuery().toString());
        Page<EsCityEntity> searchPageResults = cityRepository.search(searchQuery);
        return searchPageResults.getContent();
    }
}
