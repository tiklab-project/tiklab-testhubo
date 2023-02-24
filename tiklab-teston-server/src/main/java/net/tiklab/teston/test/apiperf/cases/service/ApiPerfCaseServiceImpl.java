package net.tiklab.teston.test.apiperf.cases.service;

import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.join.JoinTemplate;
import net.tiklab.teston.test.apiperf.cases.dao.ApiPerfCaseDao;
import net.tiklab.teston.test.apiperf.cases.entity.ApiPerfCaseEntity;
import net.tiklab.teston.test.apiperf.cases.model.ApiPerfCase;
import net.tiklab.teston.test.apiperf.cases.model.ApiPerfCaseQuery;
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
 * ApiPerfCaseServiceImpl
 */
@Service
public class ApiPerfCaseServiceImpl implements ApiPerfCaseService {

    @Autowired
    ApiPerfCaseDao apiPerfCaseDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    TestCaseService testCaseService;

    @Override
    public String createApiPerfCase(@NotNull @Valid ApiPerfCase apiPerfCase) {
        ApiPerfCaseEntity apiPerfCaseEntity = BeanMapper.map(apiPerfCase, ApiPerfCaseEntity.class);

        //初始值
        apiPerfCaseEntity.setExecuteCount(1);
        apiPerfCaseEntity.setThreadCount(1);
        apiPerfCaseEntity.setExecuteType(0);
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