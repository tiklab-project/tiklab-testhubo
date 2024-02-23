package io.thoughtware.teston.test.web.scene.cases.service;

import io.thoughtware.teston.instance.service.InstanceService;
import io.thoughtware.teston.support.variable.service.VariableService;
import io.thoughtware.teston.test.common.stepcommon.service.StepCommonService;
import io.thoughtware.teston.test.test.model.TestCase;
import io.thoughtware.teston.test.test.model.TestCaseQuery;
import io.thoughtware.teston.test.test.service.TestCaseService;
import io.thoughtware.teston.category.model.Category;
import io.thoughtware.teston.category.service.CategoryService;
import io.thoughtware.teston.test.web.scene.cases.dao.WebSceneCaseDao;
import io.thoughtware.teston.test.web.scene.cases.entity.WebSceneCaseEntity;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.teston.test.web.scene.cases.model.WebSceneCase;
import io.thoughtware.teston.test.web.scene.cases.model.WebSceneCaseQuery;
import io.thoughtware.user.user.model.User;
import io.thoughtware.user.user.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


/**
* web场景用例 服务
*/
@Service
public class WebSceneCaseServiceImpl implements WebSceneCaseService {

    @Autowired
    WebSceneCaseDao webSceneCaseDao;

    @Autowired
    TestCaseService testCaseService;

    @Autowired
    JoinTemplate joinTemplate;

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

    @Override
    public String createWebSceneCase(@NotNull @Valid WebSceneCase webSceneCase) {
        WebSceneCaseEntity webSceneCaseEntity = BeanMapper.map(webSceneCase, WebSceneCaseEntity.class);
        String id = webSceneCaseDao.createWebSceneCase(webSceneCaseEntity);
        
        webSceneCaseEntity.setTestCaseId(id);
        webSceneCaseEntity.setId(id);
        webSceneCaseDao.updateWebSceneCase(webSceneCaseEntity);

        TestCase testCase = webSceneCase.getTestCase();
        testCase.setId(id);
        testCaseService.createTestCase(testCase);
        
        return id;
    }

    @Override
    public void updateWebSceneCase(@NotNull @Valid WebSceneCase webSceneCase) {
        WebSceneCaseEntity webSceneCaseEntity = BeanMapper.map(webSceneCase, WebSceneCaseEntity.class);

        webSceneCaseDao.updateWebSceneCase(webSceneCaseEntity);

        testCaseService.updateTestCase(webSceneCase.getTestCase());
    }

    @Override
    public void deleteWebSceneCase(@NotNull String id) {
        webSceneCaseDao.deleteWebSceneCase(id);

        stepCommonService.deleteAllStepCommon(id);

        variableService.deleteAllVariable(id);

        instanceService.deleteAllInstance(id);
    }

    @Override
    public WebSceneCase findOne(String id) {
        WebSceneCaseEntity webSceneCaseEntity = webSceneCaseDao.findWebSceneCase(id);

        WebSceneCase webSceneCase = BeanMapper.map(webSceneCaseEntity, WebSceneCase.class);
        joinTemplate.joinQuery(webSceneCase);
        return webSceneCase;
    }

    @Override
    public List<WebSceneCase> findList(List<String> idList) {
        List<WebSceneCaseEntity> testCaseEntityList =  webSceneCaseDao.findWebSceneCaseList(idList);

        List<WebSceneCase> webSceneCaseList =  BeanMapper.mapList(testCaseEntityList, WebSceneCase.class);
        return webSceneCaseList;
    }

    @Override
    public WebSceneCase findWebSceneCase(@NotNull String id) {
        WebSceneCase webSceneCase = findOne(id);

        joinTemplate.joinQuery(webSceneCase);

        int webSceneStepNum = stepCommonService.findStepNum(id);
        webSceneCase.setStepNum(webSceneStepNum);

        //手动添加字段
        TestCase testCase = webSceneCase.getTestCase();
        if(testCase.getCategory()!=null) {
            Category category = categoryService.findCategory(testCase.getCategory().getId());
            webSceneCase.getTestCase().setCategory(category);
        }
        if(testCase.getUpdateUser()!=null) {
            User updateUser = userService.findUser(testCase.getUpdateUser().getId());
            webSceneCase.getTestCase().setUpdateUser(updateUser);
        }


        return webSceneCase;
    }

    @Override
    public List<WebSceneCase> findAllWebSceneCase() {
        List<WebSceneCaseEntity> testCaseEntityList =  webSceneCaseDao.findAllWebSceneCase();

        List<WebSceneCase> webSceneCaseList =  BeanMapper.mapList(testCaseEntityList, WebSceneCase.class);

        joinTemplate.joinQuery(webSceneCaseList);
        return webSceneCaseList;
    }

    @Override
    public List<WebSceneCase> findWebSceneCaseList(WebSceneCaseQuery webSceneCaseQuery) {
        List<WebSceneCaseEntity> testCaseEntityList = webSceneCaseDao.findWebSceneCaseList(webSceneCaseQuery);

        List<WebSceneCase> webSceneCaseList = BeanMapper.mapList(testCaseEntityList, WebSceneCase.class);

        joinTemplate.joinQuery(webSceneCaseList);

        return webSceneCaseList;
    }

    @Override
    public Pagination<WebSceneCase> findWebSceneCasePage(WebSceneCaseQuery webSceneCaseQuery) {

        Pagination<WebSceneCaseEntity>  pagination = webSceneCaseDao.findWebSceneCasePage(webSceneCaseQuery);


        List<WebSceneCase> webSceneCaseList = BeanMapper.mapList(pagination.getDataList(), WebSceneCase.class);
        joinTemplate.joinQuery(webSceneCaseList);

        return PaginationBuilder.build(pagination,webSceneCaseList);
    }

    @Override
    public List<WebSceneCase> findWebSceneCaseListByTestCase(TestCaseQuery testCaseQuery) {
        List<TestCase> testCaseList = testCaseService.findTestCaseList(testCaseQuery);

        List<WebSceneCase> webSceneCaseList = new ArrayList<>();

        if(CollectionUtils.isNotEmpty(testCaseList)){
            for(TestCase testCase : testCaseList){
                //因为中间层testcase与 跟在下面的场景id相同，所有直接通过id查询出一个
                WebSceneCase webSceneCase = findWebSceneCase(testCase.getId());

                if(!ObjectUtils.isEmpty(webSceneCase)){
                    webSceneCaseList.add(webSceneCase);
                }
            }
        }

        return webSceneCaseList;
    }


}