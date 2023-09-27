package io.tiklab.teston.test.apix.http.perf.cases.service;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.core.exception.ApplicationException;
import io.tiklab.teston.category.model.Category;
import io.tiklab.teston.category.service.CategoryService;
import io.tiklab.teston.test.apix.http.perf.cases.dao.ApiPerfCaseDao;
import io.tiklab.teston.test.apix.http.perf.cases.entity.ApiPerfCaseEntity;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teston.test.apix.http.perf.cases.model.ApiPerfCase;
import io.tiklab.teston.test.apix.http.perf.cases.model.ApiPerfCaseQuery;
import io.tiklab.teston.test.test.model.TestCase;
import io.tiklab.teston.test.test.model.TestCaseQuery;
import io.tiklab.teston.test.test.service.TestCaseService;
import io.tiklab.user.user.model.User;
import io.tiklab.user.user.service.UserService;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
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

    public List<JSONObject> testDataList = new ArrayList<>();

    @Override
    public String createApiPerfCase(@NotNull @Valid ApiPerfCase apiPerfCase) {
        ApiPerfCaseEntity apiPerfCaseEntity = BeanMapper.map(apiPerfCase, ApiPerfCaseEntity.class);

        //初始值
        apiPerfCaseEntity.setExecuteCount(1);
        apiPerfCaseEntity.setThreadCount(1);
        apiPerfCaseEntity.setExecuteType(1);
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