package net.tiklab.teston.webtest.perftest.service;

import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.join.JoinTemplate;
import net.tiklab.teston.testcase.model.TestCase;
import net.tiklab.teston.testcase.model.TestCaseQuery;
import net.tiklab.teston.testcase.service.TestCaseService;
import net.tiklab.teston.webtest.perftest.dao.WebPerfCaseDao;
import net.tiklab.teston.webtest.perftest.entity.WebPerfCaseEntity;
import net.tiklab.teston.webtest.perftest.model.WebPerfCase;
import net.tiklab.teston.webtest.perftest.model.WebPerfCaseQuery;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * WebPerfCaseServiceImpl
 */
@Service
public class WebPerfCaseServiceImpl implements WebPerfCaseService {

    @Autowired
    WebPerfCaseDao webPerfCaseDao;

    /**
     * 中间层
     */
    @Autowired
    TestCaseService testCaseService;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createWebPerfCase(@NotNull @Valid WebPerfCase webPerfCase) {
        WebPerfCaseEntity webPerfCaseEntity = BeanMapper.map(webPerfCase, WebPerfCaseEntity.class);

        //初始值
        webPerfCaseEntity.setExecuteCount(1);
        webPerfCaseEntity.setThreadCount(1);
        webPerfCaseEntity.setExecuteType(0);
        String id = webPerfCaseDao.createWebPerfCase(webPerfCaseEntity);
        
        webPerfCaseEntity.setTestCaseId(id);
        webPerfCaseEntity.setId(id);
        webPerfCaseDao.updateWebPerfCase(webPerfCaseEntity);

        TestCase testCase = webPerfCase.getTestCase();
        testCase.setId(id);
        testCaseService.createTestCase(testCase);

        return id;
    }

    @Override
    public void updateWebPerfCase(@NotNull @Valid WebPerfCase webPerfCase) {
        WebPerfCaseEntity webPerfCaseEntity = BeanMapper.map(webPerfCase, WebPerfCaseEntity.class);

        webPerfCaseEntity.setTestCaseId(webPerfCase.getId());
        webPerfCaseDao.updateWebPerfCase(webPerfCaseEntity);

        testCaseService.updateTestCase(webPerfCase.getTestCase());
    }

    @Override
    public void deleteWebPerfCase(@NotNull String id) {
        webPerfCaseDao.deleteWebPerfCase(id);

        testCaseService.deleteTestCase(id);
    }

    @Override
    public WebPerfCase findOne(String id) {
        WebPerfCaseEntity webPerfCaseEntity = webPerfCaseDao.findWebPerfCase(id);

        WebPerfCase webPerfCase = BeanMapper.map(webPerfCaseEntity, WebPerfCase.class);
        return webPerfCase;
    }

    @Override
    public List<WebPerfCase> findList(List<String> idList) {
        List<WebPerfCaseEntity> webPerfCaseEntityList = webPerfCaseDao.findWebPerfCaseList(idList);

        List<WebPerfCase> webPerfCaseList = BeanMapper.mapList(webPerfCaseEntityList, WebPerfCase.class);
        return webPerfCaseList;
    }

    @Override
    public WebPerfCase findWebPerfCase(@NotNull String id) {
        WebPerfCase webPerfCase = findOne(id);

        joinTemplate.joinQuery(webPerfCase);
        return webPerfCase;
    }

    @Override
    public List<WebPerfCase> findAllWebPerfCase() {
        List<WebPerfCaseEntity> webPerfCaseEntityList = webPerfCaseDao.findAllWebPerfCase();

        List<WebPerfCase> webPerfCaseList = BeanMapper.mapList(webPerfCaseEntityList, WebPerfCase.class);

        joinTemplate.joinQuery(webPerfCaseList);
        return webPerfCaseList;
    }

    @Override
    public List<WebPerfCase> findWebPerfCaseList(WebPerfCaseQuery webPerfCaseQuery) {
        List<WebPerfCaseEntity> webPerfCaseEntityList = webPerfCaseDao.findWebPerfCaseList(webPerfCaseQuery);

        List<WebPerfCase> webPerfCaseList = BeanMapper.mapList(webPerfCaseEntityList, WebPerfCase.class);

        joinTemplate.joinQuery(webPerfCaseList);

        return webPerfCaseList;
    }

    @Override
    public Pagination<WebPerfCase> findWebPerfCasePage(WebPerfCaseQuery webPerfCaseQuery) {
        Pagination<WebPerfCaseEntity> pagination = webPerfCaseDao.findWebPerfCasePage(webPerfCaseQuery);

        List<WebPerfCase> webPerfCaseList = BeanMapper.mapList(pagination.getDataList(), WebPerfCase.class);

        joinTemplate.joinQuery(webPerfCaseList);

        return PaginationBuilder.build(pagination,webPerfCaseList);
    }

    @Override
    public List<WebPerfCase> findWebPerfCaseListByTestCase(TestCaseQuery testCaseQuery) {
        List<TestCase> testCaseList = testCaseService.findTestCaseList(testCaseQuery);

        List<WebPerfCase> webPerfCaseList = new ArrayList<>();

        if(CollectionUtils.isNotEmpty(testCaseList)){
            for (TestCase testCase : testCaseList){
                //因为中间层testcase与 跟在下面的场景id相同，所有直接通过id查询出一个
                WebPerfCase webPerfCase = findWebPerfCase(testCase.getId());

                webPerfCaseList.add(webPerfCase);
            }
        }

        return webPerfCaseList;
    }


}