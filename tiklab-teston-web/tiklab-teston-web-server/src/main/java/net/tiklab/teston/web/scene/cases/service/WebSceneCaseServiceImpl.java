package net.tiklab.teston.web.scene.cases.service;

import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.join.JoinTemplate;
import net.tiklab.teston.manager.testcase.model.TestCase;
import net.tiklab.teston.manager.testcase.model.TestCaseQuery;
import net.tiklab.teston.manager.testcase.service.TestCaseService;
import net.tiklab.teston.web.scene.cases.dao.WebSceneCaseDao;
import net.tiklab.teston.web.scene.cases.entity.WebSceneCaseEntity;
import net.tiklab.teston.web.scene.cases.model.WebSceneCase;
import net.tiklab.teston.web.scene.cases.model.WebSceneCaseQuery;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


/**
* WebSceneCaseServiceImpl
*/
@Service
public class WebSceneCaseServiceImpl implements WebSceneCaseService {

    @Autowired
    WebSceneCaseDao webSceneCaseDao;

    @Autowired
    TestCaseService testCaseService;

    @Autowired
    JoinTemplate joinTemplate;

    

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