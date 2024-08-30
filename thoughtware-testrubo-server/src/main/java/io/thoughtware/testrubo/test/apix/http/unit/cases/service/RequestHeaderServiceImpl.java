package io.thoughtware.testrubo.test.apix.http.unit.cases.service;

import io.thoughtware.postin.api.apix.model.RequestHeaderQuery;
import io.thoughtware.testrubo.test.apix.http.unit.cases.dao.RequestHeaderDao;
import io.thoughtware.testrubo.test.apix.http.unit.cases.entity.RequestHeaderEntity;
import io.thoughtware.testrubo.test.apix.http.unit.cases.model.ApiUnitCase;
import io.thoughtware.testrubo.test.apix.http.unit.cases.model.RequestHeaderUnit;
import io.thoughtware.testrubo.test.apix.http.unit.cases.model.RequestHeaderUnitQuery;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.toolkit.join.JoinTemplate;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* RequestHeaderServiceImpl
*/
@Service
public class RequestHeaderServiceImpl implements RequestHeaderService {

    @Autowired
    RequestHeaderDao requestHeaderDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createRequestHeader(@NotNull @Valid RequestHeaderUnit requestHeaderUnit) {
        RequestHeaderEntity requestHeaderEntity = BeanMapper.map(requestHeaderUnit, RequestHeaderEntity.class);

        return requestHeaderDao.createRequestHeader(requestHeaderEntity);
    }

    @Override
    public void updateRequestHeader(@NotNull @Valid RequestHeaderUnit requestHeaderUnit) {
        RequestHeaderEntity requestHeaderEntity = BeanMapper.map(requestHeaderUnit, RequestHeaderEntity.class);

        requestHeaderDao.updateRequestHeader(requestHeaderEntity);
    }

    @Override
    public void deleteRequestHeader(@NotNull String id) {
        requestHeaderDao.deleteRequestHeader(id);
    }

    @Override
    public void deleteAllRequestHeader(String caseId){
        RequestHeaderUnitQuery requestHeaderQuery = new RequestHeaderUnitQuery();
        requestHeaderQuery.setApiUnitId(caseId);
        List<RequestHeaderUnit> requestHeaderList = findRequestHeaderList(requestHeaderQuery);
        for(RequestHeaderUnit requestHeaderUnit:requestHeaderList){
            deleteRequestHeader(requestHeaderUnit.getId());
        }

    }


    @Override
    public RequestHeaderUnit findOne(String id) {
        RequestHeaderEntity requestHeaderEntity = requestHeaderDao.findRequestHeader(id);

        RequestHeaderUnit requestHeaderUnit = BeanMapper.map(requestHeaderEntity, RequestHeaderUnit.class);
        return requestHeaderUnit;
    }

    @Override
    public List<RequestHeaderUnit> findList(List<String> idList) {
        List<RequestHeaderEntity> requestHeaderEntityList =  requestHeaderDao.findRequestHeaderList(idList);

        List<RequestHeaderUnit> requestHeaderUnitList =  BeanMapper.mapList(requestHeaderEntityList, RequestHeaderUnit.class);
        return requestHeaderUnitList;
    }

    @Override
    public RequestHeaderUnit findRequestHeader(@NotNull String id) {
        RequestHeaderUnit requestHeaderUnit = findOne(id);

        joinTemplate.joinQuery(requestHeaderUnit);
        return requestHeaderUnit;
    }

    @Override
    public List<RequestHeaderUnit> findAllRequestHeader() {
        List<RequestHeaderEntity> requestHeaderEntityList =  requestHeaderDao.findAllRequestHeader();

        List<RequestHeaderUnit> requestHeaderUnitList =  BeanMapper.mapList(requestHeaderEntityList, RequestHeaderUnit.class);

        joinTemplate.joinQuery(requestHeaderUnitList);
        return requestHeaderUnitList;
    }

    @Override
    public List<RequestHeaderUnit> findRequestHeaderList(RequestHeaderUnitQuery requestHeaderUnitQuery) {
        List<RequestHeaderEntity> requestHeaderEntityList = requestHeaderDao.findRequestHeaderList(requestHeaderUnitQuery);

        List<RequestHeaderUnit> requestHeaderUnitList = BeanMapper.mapList(requestHeaderEntityList, RequestHeaderUnit.class);

        joinTemplate.joinQuery(requestHeaderUnitList);

        return requestHeaderUnitList;
    }

    @Override
    public Pagination<RequestHeaderUnit> findRequestHeaderPage(RequestHeaderUnitQuery requestHeaderUnitQuery) {

        Pagination<RequestHeaderEntity>  pagination = requestHeaderDao.findRequestHeaderPage(requestHeaderUnitQuery);

        List<RequestHeaderUnit> requestHeaderUnitList = BeanMapper.mapList(pagination.getDataList(), RequestHeaderUnit.class);

        joinTemplate.joinQuery(requestHeaderUnitList);

        return PaginationBuilder.build(pagination, requestHeaderUnitList);
    }

    /**
     *请求头拼接
     * @param apiUnitCase 用例步骤
     *
     */
    @Override
    public Map<String, String> jointHeader(ApiUnitCase apiUnitCase){
        Map headerMap = new HashMap();
        RequestHeaderUnitQuery requestHeaderUnitQuery = new RequestHeaderUnitQuery();
        requestHeaderUnitQuery.setApiUnitId(apiUnitCase.getId());
        //通过步骤id查询请步骤请求头数据
        List<RequestHeaderUnit> requestHeaderUnitList = this.findRequestHeaderList(requestHeaderUnitQuery);

        if (CollectionUtils.isNotEmpty(requestHeaderUnitList)){
            for (RequestHeaderUnit requestHeaderUnit : requestHeaderUnitList){
                //请求头属性名
                String headerName = requestHeaderUnit.getHeaderName();
                //请求头参数
                String value = requestHeaderUnit.getValue();
                headerMap.put(headerName,value);
            }
        }
        return headerMap;
    }
}