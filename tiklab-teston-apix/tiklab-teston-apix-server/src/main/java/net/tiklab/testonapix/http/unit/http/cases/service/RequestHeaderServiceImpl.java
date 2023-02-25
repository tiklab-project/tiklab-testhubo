package net.tiklab.testonapix.http.unit.http.cases.service;

import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.core.page.Pagination;
import net.tiklab.beans.BeanMapper;
import net.tiklab.join.JoinTemplate;
import net.tiklab.testonapix.http.unit.cases.model.ApiUnitCase;
import net.tiklab.testonapix.http.unit.cases.model.RequestHeader;
import net.tiklab.testonapix.http.unit.cases.model.RequestHeaderQuery;
import net.tiklab.testonapix.http.unit.cases.service.RequestHeaderService;
import net.tiklab.testonapix.http.unit.http.cases.dao.RequestHeaderDao;
import net.tiklab.testonapix.http.unit.http.cases.entity.RequestHeaderEntity;
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
    public String createRequestHeader(@NotNull @Valid RequestHeader requestHeader) {
        RequestHeaderEntity requestHeaderEntity = BeanMapper.map(requestHeader, RequestHeaderEntity.class);

        return requestHeaderDao.createRequestHeader(requestHeaderEntity);
    }

    @Override
    public void updateRequestHeader(@NotNull @Valid RequestHeader requestHeader) {
        RequestHeaderEntity requestHeaderEntity = BeanMapper.map(requestHeader, RequestHeaderEntity.class);

        requestHeaderDao.updateRequestHeader(requestHeaderEntity);
    }

    @Override
    public void deleteRequestHeader(@NotNull String id) {
        requestHeaderDao.deleteRequestHeader(id);
    }

    @Override
    public RequestHeader findOne(String id) {
        RequestHeaderEntity requestHeaderEntity = requestHeaderDao.findRequestHeader(id);

        RequestHeader requestHeader = BeanMapper.map(requestHeaderEntity, RequestHeader.class);
        return requestHeader;
    }

    @Override
    public List<RequestHeader> findList(List<String> idList) {
        List<RequestHeaderEntity> requestHeaderEntityList =  requestHeaderDao.findRequestHeaderList(idList);

        List<RequestHeader> requestHeaderList =  BeanMapper.mapList(requestHeaderEntityList,RequestHeader.class);
        return requestHeaderList;
    }

    @Override
    public RequestHeader findRequestHeader(@NotNull String id) {
        RequestHeader requestHeader = findOne(id);

        joinTemplate.joinQuery(requestHeader);
        return requestHeader;
    }

    @Override
    public List<RequestHeader> findAllRequestHeader() {
        List<RequestHeaderEntity> requestHeaderEntityList =  requestHeaderDao.findAllRequestHeader();

        List<RequestHeader> requestHeaderList =  BeanMapper.mapList(requestHeaderEntityList,RequestHeader.class);

        joinTemplate.joinQuery(requestHeaderList);
        return requestHeaderList;
    }

    @Override
    public List<RequestHeader> findRequestHeaderList(RequestHeaderQuery requestHeaderQuery) {
        List<RequestHeaderEntity> requestHeaderEntityList = requestHeaderDao.findRequestHeaderList(requestHeaderQuery);

        List<RequestHeader> requestHeaderList = BeanMapper.mapList(requestHeaderEntityList,RequestHeader.class);

        joinTemplate.joinQuery(requestHeaderList);

        return requestHeaderList;
    }

    @Override
    public Pagination<RequestHeader> findRequestHeaderPage(RequestHeaderQuery requestHeaderQuery) {

        Pagination<RequestHeaderEntity>  pagination = requestHeaderDao.findRequestHeaderPage(requestHeaderQuery);

        List<RequestHeader> requestHeaderList = BeanMapper.mapList(pagination.getDataList(),RequestHeader.class);

        joinTemplate.joinQuery(requestHeaderList);

        return PaginationBuilder.build(pagination,requestHeaderList);
    }

    /**
     *请求头拼接
     * @param apiUnitCase 用例步骤
     *
     */
    public Map<String, String> jointHeader(ApiUnitCase apiUnitCase){
        Map headerMap = new HashMap();
        RequestHeaderQuery requestHeaderQuery = new RequestHeaderQuery();
        requestHeaderQuery.setApiUnitId(apiUnitCase.getId());
        //通过步骤id查询请步骤请求头数据
        List<RequestHeader> requestHeaderList = this.findRequestHeaderList(requestHeaderQuery);

        if (CollectionUtils.isNotEmpty(requestHeaderList)){
            for (RequestHeader requestHeader:requestHeaderList){
                //请求头属性名
                String headerName = requestHeader.getHeaderName();
                //请求头参数
                String value = requestHeader.getValue();
                headerMap.put(headerName,value);
            }
        }
        return headerMap;
    }
}