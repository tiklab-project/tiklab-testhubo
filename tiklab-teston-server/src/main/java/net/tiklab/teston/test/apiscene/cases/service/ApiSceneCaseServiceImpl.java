package net.tiklab.teston.test.apiscene.cases.service;

import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.join.JoinTemplate;
import net.tiklab.teston.test.apiscene.cases.dao.ApiSceneCaseDao;
import net.tiklab.teston.test.apiscene.cases.entity.ApiSceneCaseEntity;
import net.tiklab.teston.test.apiscene.cases.model.ApiSceneCase;
import net.tiklab.teston.test.apiscene.cases.model.ApiSceneCaseQuery;
import net.tiklab.teston.test.testcase.model.TestCase;
import net.tiklab.teston.test.testcase.model.TestCaseQuery;
import net.tiklab.teston.test.testcase.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
* ApiSceneCaseServiceImpl
*/
@Service
public class ApiSceneCaseServiceImpl implements ApiSceneCaseService {

    @Autowired
    ApiSceneCaseDao apiSceneCaseDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    TestCaseService testCaseService;
    

    @Override
    public String createApiSceneCase(@NotNull @Valid ApiSceneCase apiSceneCase) {
        ApiSceneCaseEntity apiSceneCaseEntity = BeanMapper.map(apiSceneCase, ApiSceneCaseEntity.class);
        String id = apiSceneCaseDao.createApiSceneCase(apiSceneCaseEntity);

        //把testCaseId 设置成与 apiSceneId 一样
        apiSceneCaseEntity.setTestCaseId(id);
        apiSceneCaseEntity.setId(id);
        apiSceneCaseDao.updateApiSceneCase(apiSceneCaseEntity);

        //添加testCase
        TestCase testCase = apiSceneCase.getTestCase();
        testCase.setId(id);
        testCaseService.createTestCase(testCase);

        return id;
    }

    @Override
    public void updateApiSceneCase(@NotNull @Valid ApiSceneCase apiSceneCase) {
        ApiSceneCaseEntity apiSceneCaseEntity = BeanMapper.map(apiSceneCase, ApiSceneCaseEntity.class);

        apiSceneCaseEntity.setTestCaseId(apiSceneCase.getId());
        apiSceneCaseDao.updateApiSceneCase(apiSceneCaseEntity);


        testCaseService.updateTestCase(apiSceneCase.getTestCase());
    }

    @Override
    public void deleteApiSceneCase(@NotNull String id) {
        //删除测试用例下面的步骤 和子表
        /*
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(AppApiSceneCaseEntity.class)
                .eq("apiSceneCaseId", id)
                .get();
        apiSceneCaseAppDao.deleteApiSceneCaseApp(deleteCondition);

        deleteCondition = DeleteBuilders.createDelete(ApiUnitCaseEntity.class)
                .eq("apiSceneCaseId", id)
                .get();
        stepDao.deleteApiApiSceneCase(deleteCondition);
         */

        apiSceneCaseDao.deleteApiSceneCase(id);

        testCaseService.deleteTestCase(id);
    }

    @Override
    public ApiSceneCase findOne(String id) {
        ApiSceneCaseEntity apiSceneCaseEntity = apiSceneCaseDao.findApiSceneCase(id);

        ApiSceneCase apiSceneCase = BeanMapper.map(apiSceneCaseEntity, ApiSceneCase.class);
        joinTemplate.joinQuery(apiSceneCase);
        return apiSceneCase;
    }

    @Override
    public List<ApiSceneCase> findList(List<String> idList) {
        List<ApiSceneCaseEntity> apiSceneCaseEntityList =  apiSceneCaseDao.findApiSceneCaseList(idList);

        List<ApiSceneCase> apiSceneCaseList =  BeanMapper.mapList(apiSceneCaseEntityList, ApiSceneCase.class);
        return apiSceneCaseList;
    }

    @Override
    public ApiSceneCase findApiSceneCase(@NotNull String id) {
        ApiSceneCase apiSceneCase = findOne(id);

        joinTemplate.joinQuery(apiSceneCase);
        return apiSceneCase;
    }

    @Override
    public List<ApiSceneCase> findAllApiSceneCase() {
        List<ApiSceneCaseEntity> apiSceneCaseEntityList =  apiSceneCaseDao.findAllApiSceneCase();

        List<ApiSceneCase> apiSceneCaseList =  BeanMapper.mapList(apiSceneCaseEntityList, ApiSceneCase.class);

        joinTemplate.joinQuery(apiSceneCaseList);
        return apiSceneCaseList;
    }

    @Override
    public List<ApiSceneCase> findApiSceneCaseList(ApiSceneCaseQuery apiSceneCaseQuery) {
        List<ApiSceneCaseEntity> apiSceneCaseEntityList = apiSceneCaseDao.findApiSceneCaseList(apiSceneCaseQuery);

        List<ApiSceneCase> apiSceneCaseList = BeanMapper.mapList(apiSceneCaseEntityList, ApiSceneCase.class);

        joinTemplate.joinQuery(apiSceneCaseList);

        return apiSceneCaseList;
    }

    @Override
    public List<ApiSceneCase> findApiSceneCaseListByTestCase(TestCaseQuery testCaseQuery){

        List<TestCase> testCaseList = testCaseService.findTestCaseList(testCaseQuery);

        List<ApiSceneCase> apiSceneCaseList = new ArrayList<>();

        if(!ObjectUtils.isEmpty(testCaseList)){
            for (TestCase testcase:testCaseList){
                //因为中间层testcase与 跟在下面的场景id相同，所有直接通过id查询出一个
                ApiSceneCase apiSceneCase = findApiSceneCase(testcase.getId());

                apiSceneCaseList.add(apiSceneCase);

            }
        }

        return apiSceneCaseList;
    }

    @Override
    public Pagination<ApiSceneCase> findApiSceneCasePage(ApiSceneCaseQuery apiSceneCaseQuery) {

        Pagination<ApiSceneCaseEntity>  pagination = apiSceneCaseDao.findApiSceneCasePage(apiSceneCaseQuery);


        List<ApiSceneCase> apiSceneCaseList = BeanMapper.mapList(pagination.getDataList(), ApiSceneCase.class);
        joinTemplate.joinQuery(apiSceneCaseList);

        return PaginationBuilder.build(pagination,apiSceneCaseList);
    }





}