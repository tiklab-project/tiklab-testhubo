package io.tiklab.teston.test.apix.http.unit.cases.service;

import io.tiklab.teston.test.apix.http.unit.cases.dao.QueryParamDao;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teston.test.apix.http.unit.cases.entity.QueryParamsEntity;
import io.tiklab.teston.test.apix.http.unit.cases.model.ApiUnitCase;
import io.tiklab.teston.test.apix.http.unit.cases.model.QueryParams;
import io.tiklab.teston.test.apix.http.unit.cases.model.QueryParamQuery;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* query 服务
*/
@Service
public class QueryParamServiceImpl implements QueryParamService {

    @Autowired
    QueryParamDao queryParamDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createQueryParam(@NotNull @Valid QueryParams queryParams) {
        QueryParamsEntity queryParamsEntity = BeanMapper.map(queryParams, QueryParamsEntity.class);

        return queryParamDao.createQueryParam(queryParamsEntity);
    }

    @Override
    public void updateQueryParam(@NotNull @Valid QueryParams queryParams) {
        QueryParamsEntity queryParamsEntity = BeanMapper.map(queryParams, QueryParamsEntity.class);

        queryParamDao.updateQueryParam(queryParamsEntity);
    }

    @Override
    public void deleteQueryParam(@NotNull String id) {
        queryParamDao.deleteQueryParam(id);
    }

    @Override
    public QueryParams findOne(String id) {
        QueryParamsEntity queryParamsEntity = queryParamDao.findQueryParam(id);

        QueryParams queryParams = BeanMapper.map(queryParamsEntity, QueryParams.class);
        return queryParams;
    }

    @Override
    public List<QueryParams> findList(List<String> idList) {
        List<QueryParamsEntity> queryParamsEntityList =  queryParamDao.findQueryParamList(idList);

        List<QueryParams> queryParamsList =  BeanMapper.mapList(queryParamsEntityList, QueryParams.class);
        return queryParamsList;
    }

    @Override
    public QueryParams findQueryParam(@NotNull String id) {
        QueryParams queryParams = findOne(id);

        joinTemplate.joinQuery(queryParams);
        return queryParams;
    }

    @Override
    public List<QueryParams> findAllQueryParam() {
        List<QueryParamsEntity> queryParamsEntityList =  queryParamDao.findAllQueryParam();

        List<QueryParams> queryParamsList =  BeanMapper.mapList(queryParamsEntityList, QueryParams.class);

        joinTemplate.joinQuery(queryParamsList);
        return queryParamsList;
    }

    @Override
    public List<QueryParams> findQueryParamList(QueryParamQuery queryParamQuery) {
        List<QueryParamsEntity> queryParamsEntityList = queryParamDao.findQueryParamList(queryParamQuery);

        List<QueryParams> queryParamsList = BeanMapper.mapList(queryParamsEntityList, QueryParams.class);

        joinTemplate.joinQuery(queryParamsList);

        return queryParamsList;
    }

    @Override
    public Pagination<QueryParams> findQueryParamPage(QueryParamQuery queryParamQuery) {

        Pagination<QueryParamsEntity>  pagination = queryParamDao.findQueryParamPage(queryParamQuery);

        List<QueryParams> queryParamsList = BeanMapper.mapList(pagination.getDataList(), QueryParams.class);

        joinTemplate.joinQuery(queryParamsList);

        return PaginationBuilder.build(pagination, queryParamsList);
    }

    /**
     *请求param参数
     * @param
     */
    @Override
    public String jointParam(ApiUnitCase apiUnitCase){
        String param = "";
        QueryParamQuery queryParamQuery = new QueryParamQuery();
        queryParamQuery.setApiUnitId(apiUnitCase.getId());
        List<QueryParams> queryParamsList = this.findQueryParamList(queryParamQuery);
        if (CollectionUtils.isNotEmpty(queryParamsList)){
            for (QueryParams queryParams : queryParamsList) {
                param += queryParams.getParamName() + "=" + queryParams.getValue() + "&";
            }
        }

        return param;
    }
}