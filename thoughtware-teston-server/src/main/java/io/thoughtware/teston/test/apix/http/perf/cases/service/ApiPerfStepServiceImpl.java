package io.thoughtware.teston.test.apix.http.perf.cases.service;


import io.thoughtware.teston.test.apix.http.perf.cases.entity.ApiPerfStepWillBindCaseEntity;
import io.thoughtware.teston.test.apix.http.perf.cases.model.ApiPerfStepWillBindCase;
import io.thoughtware.teston.test.apix.http.scene.cases.model.ApiSceneStepQuery;
import io.thoughtware.teston.test.apix.http.scene.cases.model.ApiSceneStepWillBindCase;
import io.thoughtware.teston.test.test.model.TestCase;
import io.thoughtware.teston.test.test.service.TestCaseService;
import io.thoughtware.teston.test.apix.http.perf.cases.dao.ApiPerfStepDao;
import io.thoughtware.teston.test.apix.http.perf.cases.entity.ApiPerfStepEntity;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.teston.test.apix.http.perf.cases.model.ApiPerfStep;
import io.thoughtware.teston.test.apix.http.perf.cases.model.ApiPerfStepQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
* 接口性能下场景步骤 服务
*/
@Service
public class ApiPerfStepServiceImpl implements ApiPerfStepService {

    @Autowired
    ApiPerfStepDao apiPerfStepDao;


    @Autowired
    TestCaseService testCaseService;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createApiPerfStep(@NotNull @Valid ApiPerfStep apiPerfStep) {
        ApiPerfStepEntity apiPerfStepEntity = BeanMapper.map(apiPerfStep, ApiPerfStepEntity.class);

        apiPerfStepEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));

        return apiPerfStepDao.createApiPerfStep(apiPerfStepEntity);
    }

    @Override
    public void updateApiPerfStep(@NotNull @Valid ApiPerfStep apiPerfStep) {
        ApiPerfStepEntity apiPerfStepEntity = BeanMapper.map(apiPerfStep, ApiPerfStepEntity.class);

        apiPerfStepDao.updateApiPerfStep(apiPerfStepEntity);
    }

    @Override
    public void deleteApiPerfStep(@NotNull String id) {
        apiPerfStepDao.deleteApiPerfStep(id);
    }

    @Override
    public void deleteAllApiPerfStep( String caseId) {
        ApiPerfStepQuery apiPerfStepQuery = new ApiPerfStepQuery();
        apiPerfStepQuery.setApiPerfId(caseId);
        List<ApiPerfStep> apiPerfStepList = findApiPerfStepList(apiPerfStepQuery);
        for(ApiPerfStep apiPerfStep : apiPerfStepList){
            apiPerfStepDao.deleteApiPerfStep(apiPerfStep.getId());
        }

    }


    @Override
    public ApiPerfStep findOne(String id) {
        ApiPerfStepEntity apiPerfStepEntity = apiPerfStepDao.findApiPerfStep(id);

        ApiPerfStep apiPerfStep = BeanMapper.map(apiPerfStepEntity, ApiPerfStep.class);
        return apiPerfStep;
    }

    @Override
    public List<ApiPerfStep> findList(List<String> idList) {
        List<ApiPerfStepEntity> apiPerfStepEntityList =  apiPerfStepDao.findApiPerfStepList(idList);

        List<ApiPerfStep> apiPerfStepList =  BeanMapper.mapList(apiPerfStepEntityList, ApiPerfStep.class);
        return apiPerfStepList;
    }

    @Override
    public ApiPerfStep findApiPerfStep(@NotNull String id) {
        ApiPerfStep apiPerfStep = findOne(id);

        joinTemplate.joinQuery(apiPerfStep);

        return apiPerfStep;
    }

    @Override
    public List<ApiPerfStep> findAllApiPerfStep() {
        List<ApiPerfStepEntity> apiPerfStepEntityList =  apiPerfStepDao.findAllApiPerfStep();

        List<ApiPerfStep> apiPerfStepList =  BeanMapper.mapList(apiPerfStepEntityList, ApiPerfStep.class);

        joinTemplate.joinQuery(apiPerfStepList);

        return apiPerfStepList;
    }

    @Override
    public List<ApiPerfStep> findApiPerfStepList(ApiPerfStepQuery apiPerfStepQuery) {
        List<ApiPerfStepEntity> apiPerfStepEntityList = apiPerfStepDao.findApiPerfStepList(apiPerfStepQuery);

        List<ApiPerfStep> apiPerfStepList = BeanMapper.mapList(apiPerfStepEntityList, ApiPerfStep.class);

        joinTemplate.joinQuery(apiPerfStepList);

        //第三层字段显示不出来，手动加入
        ArrayList<ApiPerfStep> arrayList = new ArrayList<>();
        if(apiPerfStepList.size()>0){
            for(ApiPerfStep apiPerfStep:apiPerfStepList){
                TestCase testCase = testCaseService.findTestCase(apiPerfStep.getApiScene().getId());

                apiPerfStep.getApiScene().setTestCase(testCase);

                arrayList.add(apiPerfStep);
            }
        }

        return arrayList;
    }

    @Override
    public Pagination<ApiPerfStep> findApiPerfStepPage(ApiPerfStepQuery apiPerfStepQuery) {
        Pagination<ApiPerfStepEntity>  pagination = apiPerfStepDao.findApiPerfStepPage(apiPerfStepQuery);

        List<ApiPerfStep> apiPerfStepList = BeanMapper.mapList(pagination.getDataList(), ApiPerfStep.class);

        joinTemplate.joinQuery(apiPerfStepList);

        return PaginationBuilder.build(pagination, apiPerfStepList);
    }

    @Override
    public void bindApiScene(List<ApiPerfStep> apiSceneStepList) {
        //循环添加
        for(ApiPerfStep apiPerfStep : apiSceneStepList){
            createApiPerfStep(apiPerfStep);
        }
    }

    @Override
    public Pagination<ApiPerfStepWillBindCase> findApiPerfStepWillBindCasePage(ApiPerfStepQuery apiPerfStepQuery){
        Pagination<ApiPerfStepWillBindCaseEntity> pagination = apiPerfStepDao.findApiPerfStepWillBindCasePage(apiPerfStepQuery);
        List<ApiPerfStepWillBindCase> apiPerfStepWillBindCaseList = BeanMapper.mapList(pagination.getDataList(), ApiPerfStepWillBindCase.class);
        joinTemplate.joinQuery(apiPerfStepWillBindCaseList);
        return PaginationBuilder.build(pagination, apiPerfStepWillBindCaseList);

    }

}