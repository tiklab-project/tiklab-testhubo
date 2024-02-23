package io.thoughtware.teston.test.apix.http.scene.cases.service;

import io.thoughtware.teston.instance.service.InstanceService;
import io.thoughtware.teston.support.variable.service.VariableService;
import io.thoughtware.teston.test.apix.http.scene.cases.model.ApiSceneCase;
import io.thoughtware.teston.test.apix.http.scene.cases.model.ApiSceneCaseQuery;
import io.thoughtware.teston.test.common.stepcommon.service.StepCommonService;
import io.thoughtware.teston.test.test.model.TestCase;
import io.thoughtware.teston.test.test.model.TestCaseQuery;
import io.thoughtware.teston.test.test.service.TestCaseService;
import io.thoughtware.teston.category.model.Category;
import io.thoughtware.teston.category.service.CategoryService;
import io.thoughtware.teston.test.apix.http.scene.cases.dao.ApiSceneCaseDao;
import io.thoughtware.teston.test.apix.http.scene.cases.entity.ApiSceneCaseEntity;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.toolkit.join.JoinTemplate;

import io.thoughtware.user.user.model.User;
import io.thoughtware.user.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
* 接口场景 服务
*/
@Service
public class ApiSceneCaseServiceImpl implements ApiSceneCaseService {

    @Autowired
    ApiSceneCaseDao apiSceneCaseDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    TestCaseService testCaseService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;

    @Autowired
    StepCommonService stepCommonService;

    @Autowired
    VariableService variableService;

    @Autowired
    InstanceService instanceService;

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
        apiSceneCaseDao.deleteApiSceneCase(id);

        stepCommonService.deleteAllStepCommon(id);

        variableService.deleteAllVariable(id);

        instanceService.deleteAllInstance(id);
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

        //步骤数量
        int apiSceneStepNum = stepCommonService.findStepNum(id);
        apiSceneCase.setStepNum(apiSceneStepNum);

        //手动添加字段
        TestCase testCase = apiSceneCase.getTestCase();
        if(testCase.getCategory()!=null) {
            Category category = categoryService.findCategory(testCase.getCategory().getId());
            apiSceneCase.getTestCase().setCategory(category);
        }
        if(testCase.getUpdateUser()!=null){
            User updateUser = userService.findUser(testCase.getUpdateUser().getId());
            apiSceneCase.getTestCase().setUpdateUser(updateUser);
        }

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
            for (TestCase testcase: testCaseList){
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