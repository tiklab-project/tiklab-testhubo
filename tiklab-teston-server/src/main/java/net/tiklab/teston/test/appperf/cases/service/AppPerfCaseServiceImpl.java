package net.tiklab.teston.test.appperf.cases.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.teston.test.appperf.cases.service.AppPerfCaseService;
import net.tiklab.teston.test.appperf.cases.dao.AppPerfCaseDao;
import net.tiklab.teston.test.appperf.cases.entity.AppPerfCaseEntity;
import net.tiklab.teston.test.appperf.cases.model.AppPerfCase;
import net.tiklab.teston.test.appperf.cases.model.AppPerfCaseQuery;

import net.tiklab.beans.BeanMapper;
import net.tiklab.join.JoinTemplate;
import net.tiklab.teston.test.testcase.model.TestCase;
import net.tiklab.teston.test.testcase.model.TestCaseQuery;
import net.tiklab.teston.test.testcase.service.TestCaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.ObjectUtils;

/**
* AppPerfCaseServiceImpl
*/
@Service
public class AppPerfCaseServiceImpl implements AppPerfCaseService {

    @Autowired
    AppPerfCaseDao appPerfCaseDao;

    /**
     * 中间层
     */
    @Autowired
    TestCaseService testCaseService;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createAppPerfCase(@NotNull @Valid AppPerfCase appPerfCase) {
        AppPerfCaseEntity appPerfCaseEntity = BeanMapper.map(appPerfCase, AppPerfCaseEntity.class);

        //初始值
        appPerfCaseEntity.setExecuteCount(1);
        appPerfCaseEntity.setExecuteType(0);
        appPerfCaseEntity.setThreadCount(1);
        String id = appPerfCaseDao.createAppPerfCase(appPerfCaseEntity);

        appPerfCaseEntity.setTestCaseId(id);
        appPerfCaseEntity.setId(id);
        appPerfCaseDao.updateAppPerfCase(appPerfCaseEntity);

        TestCase testCase = appPerfCase.getTestCase();
        testCase.setId(id);
        testCaseService.createTestCase(testCase);

        return id;
    }

    @Override
    public void updateAppPerfCase(@NotNull @Valid AppPerfCase appPerfCase) {
        AppPerfCaseEntity appPerfCaseEntity = BeanMapper.map(appPerfCase, AppPerfCaseEntity.class);

        appPerfCaseDao.updateAppPerfCase(appPerfCaseEntity);

        testCaseService.updateTestCase(appPerfCase.getTestCase());
    }

    @Override
    public void deleteAppPerfCase(@NotNull String id) {
        appPerfCaseDao.deleteAppPerfCase(id);

        testCaseService.deleteTestCase(id);
    }

    @Override
    public AppPerfCase findOne(String id) {
        AppPerfCaseEntity appPerfCaseEntity = appPerfCaseDao.findAppPerfCase(id);

        AppPerfCase appPerfCase = BeanMapper.map(appPerfCaseEntity, AppPerfCase.class);
        return appPerfCase;
    }

    @Override
    public List<AppPerfCase> findList(List<String> idList) {
        List<AppPerfCaseEntity> appPerfCaseEntityList =  appPerfCaseDao.findAppPerfCaseList(idList);

        List<AppPerfCase> appPerfCaseList =  BeanMapper.mapList(appPerfCaseEntityList,AppPerfCase.class);
        return appPerfCaseList;
    }

    @Override
    public AppPerfCase findAppPerfCase(@NotNull String id) {
        AppPerfCase appPerfCase = findOne(id);

        joinTemplate.joinQuery(appPerfCase);

        return appPerfCase;
    }

    @Override
    public List<AppPerfCase> findAllAppPerfCase() {
        List<AppPerfCaseEntity> appPerfCaseEntityList =  appPerfCaseDao.findAllAppPerfCase();

        List<AppPerfCase> appPerfCaseList =  BeanMapper.mapList(appPerfCaseEntityList,AppPerfCase.class);

        joinTemplate.joinQuery(appPerfCaseList);

        return appPerfCaseList;
    }

    @Override
    public List<AppPerfCase> findAppPerfCaseList(AppPerfCaseQuery appPerfCaseQuery) {
        List<AppPerfCaseEntity> appPerfCaseEntityList = appPerfCaseDao.findAppPerfCaseList(appPerfCaseQuery);

        List<AppPerfCase> appPerfCaseList = BeanMapper.mapList(appPerfCaseEntityList,AppPerfCase.class);

        joinTemplate.joinQuery(appPerfCaseList);

        return appPerfCaseList;
    }

    @Override
    public Pagination<AppPerfCase> findAppPerfCasePage(AppPerfCaseQuery appPerfCaseQuery) {
        Pagination<AppPerfCaseEntity>  pagination = appPerfCaseDao.findAppPerfCasePage(appPerfCaseQuery);

        List<AppPerfCase> appPerfCaseList = BeanMapper.mapList(pagination.getDataList(),AppPerfCase.class);

        joinTemplate.joinQuery(appPerfCaseList);

        return PaginationBuilder.build(pagination,appPerfCaseList);
    }

    @Override
    public List<AppPerfCase> findAppPerfCaseListByTestCase(TestCaseQuery testCaseQuery) {
        List<TestCase> testCaseList = testCaseService.findTestCaseList(testCaseQuery);

        List<AppPerfCase> appPerfCaseList = new ArrayList<>();

        if(CollectionUtils.isNotEmpty(testCaseList)){
            for(TestCase testCase:testCaseList){
                //因为中间层testcase与 跟在下面的场景id相同，所有直接通过id查询出一个
                AppPerfCase appPerfCase = findAppPerfCase(testCase.getId());

                if(ObjectUtils.isEmpty(appPerfCase)){
                    continue;
                }
                appPerfCaseList.add(appPerfCase);
            }
        }

        return appPerfCaseList;
    }
}