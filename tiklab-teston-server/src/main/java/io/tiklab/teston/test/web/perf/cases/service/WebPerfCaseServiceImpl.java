package io.tiklab.teston.test.web.perf.cases.service;

import io.tiklab.teston.category.model.Category;
import io.tiklab.teston.category.service.CategoryService;
import io.tiklab.teston.test.web.perf.cases.dao.WebPerfCaseDao;
import io.tiklab.teston.test.web.perf.cases.entity.WebPerfCaseEntity;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;

import io.tiklab.teston.test.test.model.TestCase;
import io.tiklab.teston.test.test.model.TestCaseQuery;
import io.tiklab.teston.test.test.service.TestCaseService;
import io.tiklab.teston.test.web.perf.cases.model.WebPerfCase;
import io.tiklab.teston.test.web.perf.cases.model.WebPerfCaseQuery;
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
 * web性能用例 服务
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

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;

    @Override
    public String createWebPerfCase(@NotNull @Valid WebPerfCase webPerfCase) {
        WebPerfCaseEntity webPerfCaseEntity = BeanMapper.map(webPerfCase, WebPerfCaseEntity.class);

        //初始值
        webPerfCaseEntity.setExecuteCount(1);
        webPerfCaseEntity.setThreadCount(1);
        webPerfCaseEntity.setExecuteType(1);
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

        //手动添加字段
        TestCase testCase = webPerfCase.getTestCase();
        if(testCase.getCategory()!=null) {
            Category category = categoryService.findCategory(testCase.getCategory().getId());
            webPerfCase.getTestCase().setCategory(category);
        }
        if(testCase.getUpdateUser()!=null) {
            User updateUser = userService.findUser(testCase.getUpdateUser().getId());
            webPerfCase.getTestCase().setUpdateUser(updateUser);
        }

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