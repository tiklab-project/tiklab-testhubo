package io.thoughtware.testhubo.test.apix.http.perf.cases.service;

import com.alibaba.fastjson.JSONObject;
import io.thoughtware.testhubo.instance.service.InstanceService;
import io.thoughtware.testhubo.test.common.stepcommon.service.StepCommonService;
import io.thoughtware.testhubo.test.test.model.TestCase;
import io.thoughtware.testhubo.test.test.model.TestCaseQuery;
import io.thoughtware.testhubo.test.test.service.TestCaseService;
import io.thoughtware.testhubo.category.model.Category;
import io.thoughtware.testhubo.category.service.CategoryService;
import io.thoughtware.testhubo.test.apix.http.perf.cases.dao.ApiPerfCaseDao;
import io.thoughtware.testhubo.test.apix.http.perf.cases.entity.ApiPerfCaseEntity;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.testhubo.test.apix.http.perf.cases.model.ApiPerfCase;
import io.thoughtware.testhubo.test.apix.http.perf.cases.model.ApiPerfCaseQuery;
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
 * 接口性能用例 服务
 */
@Service
public class ApiPerfCaseServiceImpl implements ApiPerfCaseService {

    @Autowired
    ApiPerfCaseDao apiPerfCaseDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    TestCaseService testCaseService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;

    @Autowired
    ApiPerfStepService apiPerfStepService;

    @Autowired
    StepCommonService stepCommonService;


    @Autowired
    InstanceService instanceService;

    public List<JSONObject> testDataList = new ArrayList<>();

    @Override
    public String createApiPerfCase(@NotNull @Valid ApiPerfCase apiPerfCase) {
        ApiPerfCaseEntity apiPerfCaseEntity = BeanMapper.map(apiPerfCase, ApiPerfCaseEntity.class);
        String id = apiPerfCaseDao.createApiPerfCase(apiPerfCaseEntity);

        apiPerfCaseEntity.setTestCaseId(id);
        apiPerfCaseEntity.setId(id);
        apiPerfCaseDao.updateApiPerfCase(apiPerfCaseEntity);

        TestCase testCase =apiPerfCase.getTestCase();
        testCase.setId(id);
        testCaseService.createTestCase(testCase);

        return id;
    }

    @Override
    public void updateApiPerfCase(@NotNull @Valid ApiPerfCase apiPerfCase) {
        ApiPerfCaseEntity apiPerfCaseEntity = BeanMapper.map(apiPerfCase, ApiPerfCaseEntity.class);

        apiPerfCaseDao.updateApiPerfCase(apiPerfCaseEntity);

        testCaseService.updateTestCase(apiPerfCase.getTestCase());
    }

    @Override
    public void deleteApiPerfCase(@NotNull String id) {
        apiPerfCaseDao.deleteApiPerfCase(id);

        apiPerfStepService.deleteAllApiPerfStep(id);

        instanceService.deleteAllInstance(id);
    }

    @Override
    public ApiPerfCase findOne(String id) {
        ApiPerfCaseEntity apiPerfCaseEntity = apiPerfCaseDao.findApiPerfCase(id);

        ApiPerfCase apiPerfCase = BeanMapper.map(apiPerfCaseEntity, ApiPerfCase.class);
        return apiPerfCase;
    }

    @Override
    public List<ApiPerfCase> findList(List<String> idList) {
        List<ApiPerfCaseEntity> apiPerfCaseEntityList = apiPerfCaseDao.findApiPerfCaseList(idList);

        List<ApiPerfCase> apiPerfCaseList = BeanMapper.mapList(apiPerfCaseEntityList, ApiPerfCase.class);
        return apiPerfCaseList;
    }

    @Override
    public ApiPerfCase findApiPerfCase(@NotNull String id) {
        ApiPerfCase apiPerfCase = findOne(id);
        joinTemplate.joinQuery(apiPerfCase);

        //步骤数量
        int apiSceneStepNum = apiPerfStepService.findApiPerfStepNum(id);
        apiPerfCase.setStepNum(apiSceneStepNum);

        int instanceNum = instanceService.findInstanceNum(id);
        apiPerfCase.setInstanceNum(instanceNum);

        //手动添加字段
        TestCase testCase = apiPerfCase.getTestCase();
        if(testCase.getCategory()!=null) {
            Category category = categoryService.findCategory(testCase.getCategory().getId());
            apiPerfCase.getTestCase().setCategory(category);
        }
        if(testCase.getUpdateUser()!=null) {
            User updateUser = userService.findUser(testCase.getUpdateUser().getId());
            apiPerfCase.getTestCase().setUpdateUser(updateUser);
        }

        return apiPerfCase;
    }

    @Override
    public List<ApiPerfCase> findAllApiPerfCase() {
        List<ApiPerfCaseEntity> apiPerfCaseEntityList = apiPerfCaseDao.findAllApiPerfCase();

        List<ApiPerfCase> apiPerfCaseList = BeanMapper.mapList(apiPerfCaseEntityList, ApiPerfCase.class);

        joinTemplate.joinQuery(apiPerfCaseList);
        return apiPerfCaseList;
    }

    @Override
    public List<ApiPerfCase> findApiPerfCaseList(ApiPerfCaseQuery apiPerfCaseQuery) {
        List<ApiPerfCaseEntity> apiPerfCaseEntityList = apiPerfCaseDao.findApiPerfCaseList(apiPerfCaseQuery);

        List<ApiPerfCase> apiPerfCaseList = BeanMapper.mapList(apiPerfCaseEntityList, ApiPerfCase.class);

        joinTemplate.joinQuery(apiPerfCaseList);

        return apiPerfCaseList;
    }

    @Override
    public Pagination<ApiPerfCase> findApiPerfCasePage(ApiPerfCaseQuery apiPerfCaseQuery) {
        Pagination<ApiPerfCaseEntity> pagination = apiPerfCaseDao.findApiPerfCasePage(apiPerfCaseQuery);

        List<ApiPerfCase> apiPerfCaseList = BeanMapper.mapList(pagination.getDataList(), ApiPerfCase.class);

        joinTemplate.joinQuery(apiPerfCaseList);

        return PaginationBuilder.build(pagination,apiPerfCaseList);
    }

    @Override
    public List<ApiPerfCase> findApiPerfCaseListByTestCase(TestCaseQuery testCaseQuery) {
        List<TestCase> testCaseList = testCaseService.findTestCaseList(testCaseQuery);

        List<ApiPerfCase> apiPerfList = new ArrayList<>();

        if(!ObjectUtils.isEmpty(testCaseList)){
            for(TestCase testCase : testCaseList){

                //因为一个testcase对应一个apiPerfCase，所以只需要取第一个即可
                List<ApiPerfCase> apiPerfCaseList = findApiPerfCaseList(new ApiPerfCaseQuery().setTestCaseId(testCase.getId()));

                if(!ObjectUtils.isEmpty(apiPerfCaseList)){
                    apiPerfList.add(apiPerfCaseList.get(0));
                }
            }
        }


        return apiPerfList;
    }


}