package io.tiklab.testhubo.test.apix.http.scene.cases.service;

import io.tiklab.testhubo.instance.service.InstanceService;
import io.tiklab.testhubo.support.variable.service.VariableService;
import io.tiklab.testhubo.test.apix.http.perf.cases.service.ApiPerfStepService;
import io.tiklab.testhubo.test.apix.http.scene.cases.model.ApiSceneCase;
import io.tiklab.testhubo.test.apix.http.scene.cases.model.ApiSceneCaseQuery;
import io.tiklab.testhubo.test.common.stepcommon.service.StepCommonService;
import io.tiklab.testhubo.test.test.model.TestCase;
import io.tiklab.testhubo.test.test.model.TestCaseQuery;
import io.tiklab.testhubo.test.test.service.TestCaseService;
import io.tiklab.testhubo.category.model.Category;
import io.tiklab.testhubo.category.service.CategoryService;
import io.tiklab.testhubo.test.apix.http.scene.cases.dao.ApiSceneCaseDao;
import io.tiklab.testhubo.test.apix.http.scene.cases.entity.ApiSceneCaseEntity;
import io.tiklab.testhubo.testplan.cases.service.TestPlanCaseService;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.join.JoinTemplate;

import io.tiklab.user.user.model.User;
import io.tiklab.user.user.service.UserService;
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

    @Autowired
    ApiPerfStepService apiPerfStepService;

    @Autowired
    TestPlanCaseService testPlanCaseService;

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

        int variableNum = variableService.findVariableNum(id);
        apiSceneCase.setVariableNum(variableNum);

        int instanceNum = instanceService.findInstanceNum(id);
        apiSceneCase.setInstanceNum(instanceNum);

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
    public Boolean isApiSceneBind(String id) {
        Boolean apiSceneExist = apiPerfStepService.isApiSceneExist(id);
        Boolean caseExist = testPlanCaseService.isCaseExist(id);

        if(apiSceneExist&&caseExist){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Pagination<ApiSceneCase> findApiSceneCasePage(ApiSceneCaseQuery apiSceneCaseQuery) {

        Pagination<ApiSceneCaseEntity>  pagination = apiSceneCaseDao.findApiSceneCasePage(apiSceneCaseQuery);


        List<ApiSceneCase> apiSceneCaseList = BeanMapper.mapList(pagination.getDataList(), ApiSceneCase.class);
        joinTemplate.joinQuery(apiSceneCaseList);

        return PaginationBuilder.build(pagination,apiSceneCaseList);
    }





}