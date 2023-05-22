package io.tiklab.teston.test.web.scene.cases.service;

import io.tiklab.teston.category.model.Categorys;
import io.tiklab.teston.category.service.CategoryService;
import io.tiklab.teston.test.test.model.TestCases;
import io.tiklab.teston.test.web.scene.cases.dao.WebSceneCaseDao;
import io.tiklab.teston.test.web.scene.cases.entity.WebSceneCaseEntity;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teston.test.test.model.TestCaseQuery;
import io.tiklab.teston.test.test.service.TestCaseService;
import io.tiklab.teston.test.web.scene.cases.model.WebSceneCase;
import io.tiklab.teston.test.web.scene.cases.model.WebSceneCaseQuery;
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

    @Override
    public String createWebSceneCase(@NotNull @Valid WebSceneCase webSceneCase) {
        WebSceneCaseEntity webSceneCaseEntity = BeanMapper.map(webSceneCase, WebSceneCaseEntity.class);
        String id = webSceneCaseDao.createWebSceneCase(webSceneCaseEntity);
        
        webSceneCaseEntity.setTestCaseId(id);
        webSceneCaseEntity.setId(id);
        webSceneCaseDao.updateWebSceneCase(webSceneCaseEntity);

        TestCases testCases = webSceneCase.getTestCase();
        testCases.setId(id);
        testCaseService.createTestCase(testCases);
        
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

        testCaseService.deleteTestCase(id);
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

        //手动添加字段
        TestCases testCases = webSceneCase.getTestCase();
        if(testCases.getCategory()!=null) {
            Categorys categorys = categoryService.findCategory(testCases.getCategory().getId());
            webSceneCase.getTestCase().setCategory(categorys);
        }
        if(testCases.getUpdateUser()!=null) {
            User updateUser = userService.findUser(testCases.getUpdateUser().getId());
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
        List<TestCases> testCasesList = testCaseService.findTestCaseList(testCaseQuery);

        List<WebSceneCase> webSceneCaseList = new ArrayList<>();

        if(CollectionUtils.isNotEmpty(testCasesList)){
            for(TestCases testCases : testCasesList){
                //因为中间层testcase与 跟在下面的场景id相同，所有直接通过id查询出一个
                WebSceneCase webSceneCase = findWebSceneCase(testCases.getId());

                if(!ObjectUtils.isEmpty(webSceneCase)){
                    webSceneCaseList.add(webSceneCase);
                }
            }
        }

        return webSceneCaseList;
    }


}