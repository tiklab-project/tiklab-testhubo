package io.thoughtware.teston.test.apix.http.unit.cases.service;

import io.thoughtware.teston.test.apix.http.unit.cases.dao.QueryParamDao;
import io.thoughtware.teston.test.apix.http.unit.cases.model.ApiUnitCase;
import io.thoughtware.teston.test.apix.http.unit.cases.model.QueryParamUnit;
import io.thoughtware.teston.test.apix.http.unit.cases.model.QueryParamUnitQuery;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.teston.test.apix.http.unit.cases.entity.QueryParamsEntity;
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
    public String createQueryParam(@NotNull @Valid QueryParamUnit queryParamUnit) {
        QueryParamsEntity queryParamsEntity = BeanMapper.map(queryParamUnit, QueryParamsEntity.class);

        return queryParamDao.createQueryParam(queryParamsEntity);
    }

    @Override
    public void updateQueryParam(@NotNull @Valid QueryParamUnit queryParamUnit) {
        QueryParamsEntity queryParamsEntity = BeanMapper.map(queryParamUnit, QueryParamsEntity.class);

        queryParamDao.updateQueryParam(queryParamsEntity);
    }

    @Override
    public void deleteQueryParam(@NotNull String id) {
        queryParamDao.deleteQueryParam(id);
    }

    @Override
    public void deleteAllQueryParam( String caseId) {
        QueryParamUnitQuery queryParamUnitQuery = new QueryParamUnitQuery();
        queryParamUnitQuery.setApiUnitId(caseId);
        List<QueryParamUnit> queryParamList = findQueryParamList(queryParamUnitQuery);
        for(QueryParamUnit queryParamUnit : queryParamList){
            deleteQueryParam(queryParamUnit.getId());
        }
    }


    @Override
    public QueryParamUnit findOne(String id) {
        QueryParamsEntity queryParamsEntity = queryParamDao.findQueryParam(id);

        QueryParamUnit queryParamUnit = BeanMapper.map(queryParamsEntity, QueryParamUnit.class);
        return queryParamUnit;
    }

    @Override
    public List<QueryParamUnit> findList(List<String> idList) {
        List<QueryParamsEntity> queryParamsEntityList =  queryParamDao.findQueryParamList(idList);

        List<QueryParamUnit> queryParamUnitList =  BeanMapper.mapList(queryParamsEntityList, QueryParamUnit.class);
        return queryParamUnitList;
    }

    @Override
    public QueryParamUnit findQueryParam(@NotNull String id) {
        QueryParamUnit queryParamUnit = findOne(id);

        joinTemplate.joinQuery(queryParamUnit);
        return queryParamUnit;
    }

    @Override
    public List<QueryParamUnit> findAllQueryParam() {
        List<QueryParamsEntity> queryParamsEntityList =  queryParamDao.findAllQueryParam();

        List<QueryParamUnit> queryParamUnitList =  BeanMapper.mapList(queryParamsEntityList, QueryParamUnit.class);

        joinTemplate.joinQuery(queryParamUnitList);
        return queryParamUnitList;
    }

    @Override
    public List<QueryParamUnit> findQueryParamList(QueryParamUnitQuery queryParamUnitQuery) {
        List<QueryParamsEntity> queryParamsEntityList = queryParamDao.findQueryParamList(queryParamUnitQuery);

        List<QueryParamUnit> queryParamUnitList = BeanMapper.mapList(queryParamsEntityList, QueryParamUnit.class);

        joinTemplate.joinQuery(queryParamUnitList);

        return queryParamUnitList;
    }

    @Override
    public Pagination<QueryParamUnit> findQueryParamPage(QueryParamUnitQuery queryParamUnitQuery) {

        Pagination<QueryParamsEntity>  pagination = queryParamDao.findQueryParamPage(queryParamUnitQuery);

        List<QueryParamUnit> queryParamUnitList = BeanMapper.mapList(pagination.getDataList(), QueryParamUnit.class);

        joinTemplate.joinQuery(queryParamUnitList);

        return PaginationBuilder.build(pagination, queryParamUnitList);
    }

    /**
     *请求param参数
     * @param
     */
    @Override
    public String jointParam(ApiUnitCase apiUnitCase){
        String param = "";
        QueryParamUnitQuery queryParamUnitQuery = new QueryParamUnitQuery();
        queryParamUnitQuery.setApiUnitId(apiUnitCase.getId());
        List<QueryParamUnit> queryParamUnitList = this.findQueryParamList(queryParamUnitQuery);

        if (CollectionUtils.isNotEmpty(queryParamUnitList)){
            for (int i = 0; i < queryParamUnitList.size(); i++) {
                QueryParamUnit queryParamUnit = queryParamUnitList.get(i);
                param += queryParamUnit.getParamName() + "=" + queryParamUnit.getValue();
                if (i < queryParamUnitList.size() - 1) {
                    param += "&";
                }
            }
        }

        return param;
    }
}