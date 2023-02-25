package net.tiklab.testonapp.scene.cases.service;

import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.join.JoinTemplate;
import net.tiklab.teston.test.app.scene.cases.dao.AppSceneCaseDao;
import net.tiklab.teston.test.app.scene.cases.entity.AppSceneCaseEntity;
import net.tiklab.teston.test.app.scene.cases.model.AppSceneCase;
import net.tiklab.teston.test.app.scene.cases.model.AppSceneCaseQuery;
import net.tiklab.teston.test.app.scene.cases.service.AppSceneCaseService;
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

/**
* AppSceneCaseServiceImpl
*/
@Service
public class AppSceneCaseServiceImpl implements AppSceneCaseService {

    @Autowired
    AppSceneCaseDao appSceneCaseDao;

    @Autowired
    TestCaseService testCaseService;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createAppSceneCase(@NotNull @Valid AppSceneCase appSceneCase) {
        AppSceneCaseEntity appSceneCaseEntity = BeanMapper.map(appSceneCase, AppSceneCaseEntity.class);
        String id = appSceneCaseDao.createAppSceneCase(appSceneCaseEntity);

        appSceneCaseEntity.setTestCaseId(id);
        appSceneCaseEntity.setId(id);
        appSceneCaseDao.updateAppSceneCase(appSceneCaseEntity);

        TestCase testCase = appSceneCase.getTestCase();
        testCase.setId(id);
        testCaseService.createTestCase(testCase);

        return id;
    }

    @Override
    public void updateAppSceneCase(@NotNull @Valid AppSceneCase appSceneCase) {
        AppSceneCaseEntity appSceneCaseEntity = BeanMapper.map(appSceneCase, AppSceneCaseEntity.class);

        appSceneCaseDao.updateAppSceneCase(appSceneCaseEntity);

        testCaseService.updateTestCase(appSceneCase.getTestCase());
    }

    @Override
    public void deleteAppSceneCase(@NotNull String id) {
        appSceneCaseDao.deleteAppSceneCase(id);

        testCaseService.deleteTestCase(id);
    }

    @Override
    public AppSceneCase findOne(String id) {
        AppSceneCaseEntity appSceneCaseEntity = appSceneCaseDao.findAppSceneCase(id);

        AppSceneCase appSceneCase = BeanMapper.map(appSceneCaseEntity, AppSceneCase.class);
        return appSceneCase;
    }

    @Override
    public List<AppSceneCase> findList(List<String> idList) {
        List<AppSceneCaseEntity> appSceneCaseEntityList =  appSceneCaseDao.findAppSceneCaseList(idList);

        List<AppSceneCase> appSceneCaseList =  BeanMapper.mapList(appSceneCaseEntityList,AppSceneCase.class);
        return appSceneCaseList;
    }

    @Override
    public AppSceneCase findAppSceneCase(@NotNull String id) {
        AppSceneCase appSceneCase = findOne(id);

        joinTemplate.joinQuery(appSceneCase);

        return appSceneCase;
    }

    @Override
    public List<AppSceneCase> findAllAppSceneCase() {
        List<AppSceneCaseEntity> appSceneCaseEntityList =  appSceneCaseDao.findAllAppSceneCase();

        List<AppSceneCase> appSceneCaseList =  BeanMapper.mapList(appSceneCaseEntityList,AppSceneCase.class);

        joinTemplate.joinQuery(appSceneCaseList);

        return appSceneCaseList;
    }

    @Override
    public List<AppSceneCase> findAppSceneCaseList(AppSceneCaseQuery appSceneCaseQuery) {
        List<AppSceneCaseEntity> appSceneCaseEntityList = appSceneCaseDao.findAppSceneCaseList(appSceneCaseQuery);

        List<AppSceneCase> appSceneCaseList = BeanMapper.mapList(appSceneCaseEntityList,AppSceneCase.class);

        joinTemplate.joinQuery(appSceneCaseList);

        return appSceneCaseList;
    }

    @Override
    public Pagination<AppSceneCase> findAppSceneCasePage(AppSceneCaseQuery appSceneCaseQuery) {
        Pagination<AppSceneCaseEntity>  pagination = appSceneCaseDao.findAppSceneCasePage(appSceneCaseQuery);

        List<AppSceneCase> appSceneCaseList = BeanMapper.mapList(pagination.getDataList(),AppSceneCase.class);

        joinTemplate.joinQuery(appSceneCaseList);

        return PaginationBuilder.build(pagination,appSceneCaseList);
    }

    @Override
    public List<AppSceneCase> findAppSceneCaseListByTestCase(TestCaseQuery testCaseQuery) {
        List<TestCase> testCaseList = testCaseService.findTestCaseList(testCaseQuery);

        List<AppSceneCase> appSceneCaseList = new ArrayList<>();

        if(CollectionUtils.isNotEmpty(testCaseList)){
            for(TestCase testCase : testCaseList){
                //因为中间层testcase与 跟在下面的场景id相同，所有直接通过id查询出一个
                AppSceneCase appSceneCase = findAppSceneCase(testCase.getId());

                appSceneCaseList.add(appSceneCase);

            }
        }

        return appSceneCaseList;
    }
}