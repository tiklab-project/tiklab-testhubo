package io.tiklab.teston.test.app.perf.cases.service;

import io.tiklab.teston.category.model.Categorys;
import io.tiklab.teston.category.service.CategoryService;
import io.tiklab.teston.test.app.perf.cases.dao.AppPerfCaseDao;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;

import io.tiklab.teston.test.test.model.TestCases;
import io.tiklab.teston.test.test.model.TestCaseQuery;
import io.tiklab.teston.test.test.service.TestCaseService;
import io.tiklab.teston.test.app.perf.cases.entity.AppPerfCaseEntity;
import io.tiklab.teston.test.app.perf.cases.model.AppPerfCase;
import io.tiklab.teston.test.app.perf.cases.model.AppPerfCaseQuery;
import io.tiklab.user.user.model.User;
import io.tiklab.user.user.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
* app性能测试用例 服务
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

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;

    @Override
    public String createAppPerfCase(@NotNull @Valid AppPerfCase appPerfCase) {
        AppPerfCaseEntity appPerfCaseEntity = BeanMapper.map(appPerfCase, AppPerfCaseEntity.class);

        //初始值
        appPerfCaseEntity.setExecuteCount(1);
        appPerfCaseEntity.setExecuteType(1);
        appPerfCaseEntity.setThreadCount(1);
        String id = appPerfCaseDao.createAppPerfCase(appPerfCaseEntity);

        appPerfCaseEntity.setTestCaseId(id);
        appPerfCaseEntity.setId(id);
        appPerfCaseDao.updateAppPerfCase(appPerfCaseEntity);

        TestCases testCases = appPerfCase.getTestCase();
        testCases.setId(id);
        testCaseService.createTestCase(testCases);

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

        //手动添加字段
        TestCases testCases = appPerfCase.getTestCase();
        if(testCases.getCategory()!=null) {
            Categorys categorys = categoryService.findCategory(testCases.getCategory().getId());
            appPerfCase.getTestCase().setCategory(categorys);
        }
        if(testCases.getUpdateUser()!=null) {
            User updateUser = userService.findUser(testCases.getUpdateUser().getId());
            appPerfCase.getTestCase().setUpdateUser(updateUser);
        }

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
        List<TestCases> testCasesList = testCaseService.findTestCaseList(testCaseQuery);

        List<AppPerfCase> appPerfCaseList = new ArrayList<>();

        if(CollectionUtils.isNotEmpty(testCasesList)){
            for(TestCases testCases : testCasesList){
                //因为中间层testcase与 跟在下面的场景id相同，所有直接通过id查询出一个
                AppPerfCase appPerfCase = findAppPerfCase(testCases.getId());

                if(ObjectUtils.isEmpty(appPerfCase)){
                    continue;
                }
                appPerfCaseList.add(appPerfCase);
            }
        }

        return appPerfCaseList;
    }
}