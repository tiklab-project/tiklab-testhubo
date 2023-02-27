package net.tiklab.teston.apix.http.unit.http.cases.service;

import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.join.JoinTemplate;
import net.tiklab.teston.apix.http.unit.cases.model.ApiUnitCase;
import net.tiklab.teston.apix.http.unit.cases.model.QueryParam;
import net.tiklab.teston.apix.http.unit.cases.model.QueryParamQuery;
import net.tiklab.teston.apix.http.unit.cases.service.QueryParamService;
import net.tiklab.teston.apix.http.unit.http.cases.dao.QueryParamDao;
import net.tiklab.teston.apix.http.unit.http.cases.entity.QueryParamEntity;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* QueryParamServiceImpl
*/
@Service
public class QueryParamServiceImpl implements QueryParamService {

    @Autowired
    QueryParamDao queryParamDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createQueryParam(@NotNull @Valid QueryParam queryParam) {
        QueryParamEntity queryParamEntity = BeanMapper.map(queryParam, QueryParamEntity.class);

        return queryParamDao.createQueryParam(queryParamEntity);
    }

    @Override
    public void updateQueryParam(@NotNull @Valid QueryParam queryParam) {
        QueryParamEntity queryParamEntity = BeanMapper.map(queryParam, QueryParamEntity.class);

        queryParamDao.updateQueryParam(queryParamEntity);
    }

    @Override
    public void deleteQueryParam(@NotNull String id) {
        queryParamDao.deleteQueryParam(id);
    }

    @Override
    public QueryParam findOne(String id) {
        QueryParamEntity queryParamEntity = queryParamDao.findQueryParam(id);

        QueryParam queryParam = BeanMapper.map(queryParamEntity, QueryParam.class);
        return queryParam;
    }

    @Override
    public List<QueryParam> findList(List<String> idList) {
        List<QueryParamEntity> queryParamEntityList =  queryParamDao.findQueryParamList(idList);

        List<QueryParam> queryParamList =  BeanMapper.mapList(queryParamEntityList,QueryParam.class);
        return queryParamList;
    }

    @Override
    public QueryParam findQueryParam(@NotNull String id) {
        QueryParam queryParam = findOne(id);

        joinTemplate.joinQuery(queryParam);
        return queryParam;
    }

    @Override
    public List<QueryParam> findAllQueryParam() {
        List<QueryParamEntity> queryParamEntityList =  queryParamDao.findAllQueryParam();

        List<QueryParam> queryParamList =  BeanMapper.mapList(queryParamEntityList,QueryParam.class);

        joinTemplate.joinQuery(queryParamList);
        return queryParamList;
    }

    @Override
    public List<QueryParam> findQueryParamList(QueryParamQuery queryParamQuery) {
        List<QueryParamEntity> queryParamEntityList = queryParamDao.findQueryParamList(queryParamQuery);

        List<QueryParam> queryParamList = BeanMapper.mapList(queryParamEntityList,QueryParam.class);

        joinTemplate.joinQuery(queryParamList);

        return queryParamList;
    }

    @Override
    public Pagination<QueryParam> findQueryParamPage(QueryParamQuery queryParamQuery) {

        Pagination<QueryParamEntity>  pagination = queryParamDao.findQueryParamPage(queryParamQuery);

        List<QueryParam> queryParamList = BeanMapper.mapList(pagination.getDataList(),QueryParam.class);

        joinTemplate.joinQuery(queryParamList);

        return PaginationBuilder.build(pagination,queryParamList);
    }

    /**
     *请求param参数
     * @param
     */
    public String jointParam(ApiUnitCase apiUnitCase){
        String param = "";
        QueryParamQuery queryParamQuery = new QueryParamQuery();
        queryParamQuery.setApiUnitId(apiUnitCase.getId());
        List<QueryParam> queryParamList = this.findQueryParamList(queryParamQuery);
        if (CollectionUtils.isNotEmpty(queryParamList)){
            for (QueryParam queryParam : queryParamList) {
                param += queryParam.getParamName() + "=" + queryParam.getValue() + "&";
            }
        }

        return param;
    }
}