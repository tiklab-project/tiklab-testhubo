package io.tiklab.teston.test.apix.http.perf.cases.service;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teston.test.apix.http.perf.cases.dao.ApiPerfTestDataDao;
import io.tiklab.teston.test.apix.http.perf.cases.entity.ApiPerfTestDataEntity;
import io.tiklab.teston.test.apix.http.perf.cases.model.ApiPerfTestData;
import io.tiklab.teston.test.apix.http.perf.cases.model.ApiPerfTestDataQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 接口性能测试数据 服务
 */
@Service
public class ApiPerfTestDataServiceImpl implements ApiPerfTestDataService {

    @Autowired
    ApiPerfTestDataDao apiPerfTestDataDao;

    @Autowired
    JoinTemplate joinTemplate;
    

    public List<JSONObject> testDataList = new ArrayList<>();

    @Override
    public String createApiPerfTestData(@NotNull @Valid ApiPerfTestData apiPerfTestData) {
        ApiPerfTestDataEntity apiPerfTestDataEntity = BeanMapper.map(apiPerfTestData, ApiPerfTestDataEntity.class);
        apiPerfTestDataEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return apiPerfTestDataDao.createApiPerfTestData(apiPerfTestDataEntity);
    }

    @Override
    public void updateApiPerfTestData(@NotNull @Valid ApiPerfTestData apiPerfTestData) {
        ApiPerfTestDataEntity apiPerfTestDataEntity = BeanMapper.map(apiPerfTestData, ApiPerfTestDataEntity.class);

        apiPerfTestDataDao.updateApiPerfTestData(apiPerfTestDataEntity);
    }

    @Override
    public void deleteApiPerfTestData(@NotNull String id) {
        apiPerfTestDataDao.deleteApiPerfTestData(id);
    }

    @Override
    public ApiPerfTestData findOne(String id) {
        ApiPerfTestDataEntity apiPerfTestDataEntity = apiPerfTestDataDao.findApiPerfTestData(id);

        ApiPerfTestData apiPerfTestData = BeanMapper.map(apiPerfTestDataEntity, ApiPerfTestData.class);
        return apiPerfTestData;
    }

    @Override
    public List<ApiPerfTestData> findList(List<String> idList) {
        List<ApiPerfTestDataEntity> apiPerfTestDataEntityList = apiPerfTestDataDao.findApiPerfTestDataList(idList);

        List<ApiPerfTestData> apiPerfTestDataList = BeanMapper.mapList(apiPerfTestDataEntityList, ApiPerfTestData.class);
        return apiPerfTestDataList;
    }

    @Override
    public ApiPerfTestData findApiPerfTestData(@NotNull String id) {
        ApiPerfTestData apiPerfTestData = findOne(id);

        joinTemplate.joinQuery(apiPerfTestData);

        return apiPerfTestData;
    }

    @Override
    public List<ApiPerfTestData> findAllApiPerfTestData() {
        List<ApiPerfTestDataEntity> apiPerfTestDataEntityList = apiPerfTestDataDao.findAllApiPerfTestData();

        List<ApiPerfTestData> apiPerfTestDataList = BeanMapper.mapList(apiPerfTestDataEntityList, ApiPerfTestData.class);

        joinTemplate.joinQuery(apiPerfTestDataList);
        return apiPerfTestDataList;
    }

    @Override
    public List<ApiPerfTestData> findApiPerfTestDataList(ApiPerfTestDataQuery apiPerfTestDataQuery) {
        List<ApiPerfTestDataEntity> apiPerfTestDataEntityList = apiPerfTestDataDao.findApiPerfTestDataList(apiPerfTestDataQuery);

        List<ApiPerfTestData> apiPerfTestDataList = BeanMapper.mapList(apiPerfTestDataEntityList, ApiPerfTestData.class);

        joinTemplate.joinQuery(apiPerfTestDataList);

        return apiPerfTestDataList;
    }

    @Override
    public Pagination<ApiPerfTestData> findApiPerfTestDataPage(ApiPerfTestDataQuery apiPerfTestDataQuery) {
        Pagination<ApiPerfTestDataEntity> pagination = apiPerfTestDataDao.findApiPerfTestDataPage(apiPerfTestDataQuery);

        List<ApiPerfTestData> apiPerfTestDataList = BeanMapper.mapList(pagination.getDataList(), ApiPerfTestData.class);

        joinTemplate.joinQuery(apiPerfTestDataList);

        return PaginationBuilder.build(pagination,apiPerfTestDataList);
    }
    
}