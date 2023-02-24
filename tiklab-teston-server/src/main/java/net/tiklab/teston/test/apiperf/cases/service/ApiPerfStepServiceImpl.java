package net.tiklab.teston.test.apiperf.cases.service;

import net.tiklab.teston.test.apiperf.cases.service.ApiPerfStepService;
import net.tiklab.teston.test.apiperf.cases.dao.ApiPerfStepDao;
import net.tiklab.teston.test.apiperf.cases.entity.ApiPerfStepEntity;
import net.tiklab.teston.test.apiperf.cases.model.ApiPerfStep;
import net.tiklab.teston.test.apiperf.cases.model.ApiPerfStepQuery;

import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.beans.BeanMapper;
import net.tiklab.join.JoinTemplate;
import net.tiklab.teston.test.testcase.model.TestCase;
import net.tiklab.teston.test.testcase.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
* ApiPerfStepServiceImpl
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
}