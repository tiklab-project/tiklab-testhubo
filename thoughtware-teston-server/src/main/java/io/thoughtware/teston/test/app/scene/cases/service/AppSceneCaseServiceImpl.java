package io.thoughtware.teston.test.app.scene.cases.service;

import io.thoughtware.teston.test.app.scene.cases.model.AppSceneCase;
import io.thoughtware.teston.test.app.scene.cases.model.AppSceneCaseQuery;
import io.thoughtware.teston.test.test.model.TestCase;
import io.thoughtware.teston.test.test.model.TestCaseQuery;
import io.thoughtware.teston.test.test.service.TestCaseService;
import io.thoughtware.teston.category.model.Category;
import io.thoughtware.teston.category.service.CategoryService;
import io.thoughtware.teston.test.app.scene.cases.dao.AppSceneCaseDao;
import io.thoughtware.teston.test.app.scene.cases.entity.AppSceneCaseEntity;
import io.thoughtware.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.join.JoinTemplate;

import io.thoughtware.user.user.model.User;
import io.thoughtware.user.user.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
* app场景用例 服务
*/
@Service
public class AppSceneCaseServiceImpl implements AppSceneCaseService {

    @Autowired
    AppSceneCaseDao appSceneCaseDao;

    @Autowired
    TestCaseService testCaseService;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;

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

        //手动添加字段
        TestCase testCase = appSceneCase.getTestCase();
        if(testCase.getCategory()!=null) {
            Category category = categoryService.findCategory(testCase.getCategory().getId());
            appSceneCase.getTestCase().setCategory(category);
        }
        if(testCase.getUpdateUser()!=null) {
            User updateUser = userService.findUser(testCase.getUpdateUser().getId());
            appSceneCase.getTestCase().setUpdateUser(updateUser);
        }

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