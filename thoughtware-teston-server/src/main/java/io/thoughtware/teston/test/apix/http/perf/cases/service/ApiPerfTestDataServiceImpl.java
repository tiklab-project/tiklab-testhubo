package io.thoughtware.teston.test.apix.http.perf.cases.service;

import com.alibaba.fastjson.JSONObject;
import io.thoughtware.teston.test.apix.http.perf.cases.dao.ApiPerfTestDataDao;
import io.thoughtware.teston.test.apix.http.perf.cases.entity.ApiPerfTestDataEntity;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.exception.ApplicationException;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.teston.test.apix.http.perf.cases.model.ApiPerfTestData;
import io.thoughtware.teston.test.apix.http.perf.cases.model.ApiPerfTestDataQuery;
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
    public void deleteAllApiPerfTestData(String caseId) {
        ApiPerfTestDataQuery apiPerfTestDataQuery = new ApiPerfTestDataQuery();
        apiPerfTestDataQuery.setStepId(caseId);
        List<ApiPerfTestData> apiPerfTestDataList = findApiPerfTestDataList(apiPerfTestDataQuery);
        for(ApiPerfTestData apiPerfTestData: apiPerfTestDataList){
            deleteApiPerfTestData(apiPerfTestData.getId());
        }
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

    @Override
    public List<JSONObject> getTestData(String stepId) {

        ApiPerfTestDataQuery apiPerfTestDataQuery = new ApiPerfTestDataQuery();
        apiPerfTestDataQuery.setStepId(stepId);
        List<ApiPerfTestData> apiPerfTestDataList = findApiPerfTestDataList(apiPerfTestDataQuery);

        List<JSONObject> testDataList = new ArrayList<>();

        if (apiPerfTestDataList == null) {
            return testDataList;
        }

        for (ApiPerfTestData apiPerfTestData : apiPerfTestDataList) {
            try {
                // 读取首行作为标题
                String csvString = apiPerfTestData.getTestData();

                String[] lines = csvString.split("\n");

                // 解析第一行为标题
                String[] headers = lines[0].split(",");

                // 解析后续每行为数据
                for(int i=1; i<lines.length; i++) {

                    String[] values = lines[i].split(",");

                    JSONObject obj = new JSONObject();
                    for (int j=0; j<values.length; j++) {
                        obj.put(headers[j], values[j]);
                    }

                    testDataList.add(obj);
                }
            } catch (Exception e) {
                throw new ApplicationException("get test data error");
            }
        }
        return testDataList;
    }

}