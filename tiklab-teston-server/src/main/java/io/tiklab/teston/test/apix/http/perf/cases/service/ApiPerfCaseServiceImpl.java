package io.tiklab.teston.test.apix.http.perf.cases.service;

import io.tiklab.teston.category.model.Categorys;
import io.tiklab.teston.category.service.CategoryService;
import io.tiklab.teston.test.apix.http.perf.cases.dao.ApiPerfCaseDao;
import io.tiklab.teston.test.apix.http.perf.cases.entity.ApiPerfCaseEntity;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teston.test.apix.http.perf.cases.model.ApiPerfCase;
import io.tiklab.teston.test.apix.http.perf.cases.model.ApiPerfCaseQuery;
import io.tiklab.teston.test.test.model.TestCases;
import io.tiklab.teston.test.test.model.TestCaseQuery;
import io.tiklab.teston.test.test.service.TestCaseService;
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

        TestCases testCases =apiPerfCase.getTestCase();
        testCases.setId(id);
        testCaseService.createTestCase(testCases);

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
        TestCases testCases = apiPerfCase.getTestCase();
        if(testCases.getCategory()!=null) {
            Categorys categorys = categoryService.findCategory(testCases.getCategory().getId());
            apiPerfCase.getTestCase().setCategory(categorys);
        }
        if(testCases.getUpdateUser()!=null) {
            User updateUser = userService.findUser(testCases.getUpdateUser().getId());
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
        List<TestCases> testCasesList = testCaseService.findTestCaseList(testCaseQuery);

        List<ApiPerfCase> apiPerfList = new ArrayList<>();

        if(!ObjectUtils.isEmpty(testCasesList)){
            for(TestCases testCases : testCasesList){

                //因为一个testcase对应一个apiPerfCase，所以只需要取第一个即可
                List<ApiPerfCase> apiPerfCaseList = findApiPerfCaseList(new ApiPerfCaseQuery().setTestCaseId(testCases.getId()));

                if(!ObjectUtils.isEmpty(apiPerfCaseList)){
                    apiPerfList.add(apiPerfCaseList.get(0));
                }
            }
        }


        return apiPerfList;
    }


}