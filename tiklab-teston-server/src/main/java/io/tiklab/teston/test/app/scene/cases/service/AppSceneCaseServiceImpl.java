package io.tiklab.teston.test.app.scene.cases.service;

import io.tiklab.teston.category.model.Category;
import io.tiklab.teston.category.service.CategoryService;
import io.tiklab.teston.test.app.scene.cases.dao.AppSceneCaseDao;
import io.tiklab.teston.test.app.scene.cases.entity.AppSceneCaseEntity;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;

import io.tiklab.teston.test.test.model.TestCases;
import io.tiklab.teston.test.test.model.TestCaseQuery;
import io.tiklab.teston.test.test.service.TestCaseService;
import io.tiklab.teston.test.app.scene.cases.model.AppSceneCase;
import io.tiklab.teston.test.app.scene.cases.model.AppSceneCaseQuery;
import io.tiklab.user.user.model.User;
import io.tiklab.user.user.service.UserService;
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

        TestCases testCases = appSceneCase.getTestCase();
        testCases.setId(id);
        testCaseService.createTestCase(testCases);

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
        TestCases testCases = appSceneCase.getTestCase();
        if(testCases.getCategory()!=null) {
            Category category = categoryService.findCategory(testCases.getCategory().getId());
            appSceneCase.getTestCase().setCategory(category);
        }
        if(testCases.getUpdateUser()!=null) {
            User updateUser = userService.findUser(testCases.getUpdateUser().getId());
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
        List<TestCases> testCasesList = testCaseService.findTestCaseList(testCaseQuery);

        List<AppSceneCase> appSceneCaseList = new ArrayList<>();

        if(CollectionUtils.isNotEmpty(testCasesList)){
            for(TestCases testCases : testCasesList){
                //因为中间层testcase与 跟在下面的场景id相同，所有直接通过id查询出一个
                AppSceneCase appSceneCase = findAppSceneCase(testCases.getId());

                appSceneCaseList.add(appSceneCase);

            }
        }

        return appSceneCaseList;
    }
}