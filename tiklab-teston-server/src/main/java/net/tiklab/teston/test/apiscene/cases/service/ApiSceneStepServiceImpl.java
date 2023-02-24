package net.tiklab.teston.test.apiscene.cases.service;

import net.tiklab.teston.test.apiscene.cases.dao.ApiSceneStepDao;
import net.tiklab.teston.test.apiscene.cases.entity.ApiSceneStepEntity;
import net.tiklab.teston.test.apiscene.cases.model.ApiSceneStep;
import net.tiklab.teston.test.apiscene.cases.model.ApiSceneStepQuery;

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
* ApiSceneStepServiceImpl
*/
@Service
public class ApiSceneStepServiceImpl implements ApiSceneStepService {

    @Autowired
    ApiSceneStepDao apiSceneStepDao;

    @Autowired
    TestCaseService testCaseService;


    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createApiSceneStep(@NotNull @Valid ApiSceneStep apiSceneStep) {
        ApiSceneStepEntity apiSceneStepEntity = BeanMapper.map(apiSceneStep, ApiSceneStepEntity.class);

        apiSceneStepEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));

        return apiSceneStepDao.createApiSceneStep(apiSceneStepEntity);
    }

    @Override
    public void updateApiSceneStep(@NotNull @Valid ApiSceneStep apiSceneStep) {
        ApiSceneStepEntity apiSceneStepEntity = BeanMapper.map(apiSceneStep, ApiSceneStepEntity.class);

        apiSceneStepDao.updateApiSceneStep(apiSceneStepEntity);
    }

    @Override
    public void deleteApiSceneStep(@NotNull String id) {
        apiSceneStepDao.deleteApiSceneStep(id);
    }

    @Override
    public ApiSceneStep findOne(String id) {
        ApiSceneStepEntity apiSceneStepEntity = apiSceneStepDao.findApiSceneStep(id);

        ApiSceneStep apiSceneStep = BeanMapper.map(apiSceneStepEntity, ApiSceneStep.class);
        return apiSceneStep;
    }

    @Override
    public List<ApiSceneStep> findList(List<String> idList) {
        List<ApiSceneStepEntity> apiSceneStepEntityList =  apiSceneStepDao.findApiSceneStepList(idList);

        List<ApiSceneStep> apiSceneStepList =  BeanMapper.mapList(apiSceneStepEntityList,ApiSceneStep.class);
        return apiSceneStepList;
    }

    @Override
    public ApiSceneStep findApiSceneStep(@NotNull String id) {
        ApiSceneStep apiSceneStep = findOne(id);

        joinTemplate.joinQuery(apiSceneStep);

        return apiSceneStep;
    }

    @Override
    public List<ApiSceneStep> findAllApiSceneStep() {
        List<ApiSceneStepEntity> apiSceneStepEntityList =  apiSceneStepDao.findAllApiSceneStep();

        List<ApiSceneStep> apiSceneStepList =  BeanMapper.mapList(apiSceneStepEntityList,ApiSceneStep.class);

        joinTemplate.joinQuery(apiSceneStepList);

        return apiSceneStepList;
    }

    @Override
    public List<ApiSceneStep> findApiSceneStepList(ApiSceneStepQuery apiSceneStepQuery) {
        List<ApiSceneStepEntity> apiSceneStepEntityList = apiSceneStepDao.findApiSceneStepList(apiSceneStepQuery);

        List<ApiSceneStep> apiSceneStepList = BeanMapper.mapList(apiSceneStepEntityList,ApiSceneStep.class);

        joinTemplate.joinQuery(apiSceneStepList);

        //第三层字段显示不出来，手动加入
        ArrayList<ApiSceneStep> arrayList = new ArrayList<>();
        if(apiSceneStepList.size()>0){
            for(ApiSceneStep apiSceneStep:apiSceneStepList){
                TestCase testCase = testCaseService.findTestCase(apiSceneStep.getApiUnit().getId());

                apiSceneStep.getApiUnit().setTestCase(testCase);

                arrayList.add(apiSceneStep);
            }
        }

        return arrayList;
    }

    @Override
    public Pagination<ApiSceneStep> findApiSceneStepPage(ApiSceneStepQuery apiSceneStepQuery) {
        Pagination<ApiSceneStepEntity>  pagination = apiSceneStepDao.findApiSceneStepPage(apiSceneStepQuery);

        List<ApiSceneStep> apiSceneStepList = BeanMapper.mapList(pagination.getDataList(),ApiSceneStep.class);

        joinTemplate.joinQuery(apiSceneStepList);

        return PaginationBuilder.build(pagination,apiSceneStepList);
    }

    @Override
    public void bindApiUnit(List<ApiSceneStep> apiSceneStepList) {
        for (ApiSceneStep apiSceneStep : apiSceneStepList) {
            createApiSceneStep(apiSceneStep);
        }
    }
}