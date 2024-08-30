package io.thoughtware.testrubo.test.test.service;

import io.thoughtware.testrubo.repository.model.Repository;
import io.thoughtware.testrubo.repository.service.RepositoryService;
import io.thoughtware.testrubo.test.test.model.TestCase;
import io.thoughtware.testrubo.test.test.model.TestCaseRecent;
import io.thoughtware.testrubo.test.test.model.TestCaseRecentQuery;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.testrubo.test.test.dao.TestCaseRecentDao;
import io.thoughtware.testrubo.test.test.entity.TestCaseRecentEntity;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
* 最近访问仓库 服务
*/
@Service
public class TestCaseRecentServiceImpl implements TestCaseRecentService {

    @Autowired
    TestCaseRecentDao testCaseRecentDao;

    @Autowired
    TestCaseService testCaseService;

    @Autowired
    RepositoryService repositoryService;


    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createTestCaseRecent(@NotNull @Valid TestCaseRecent testCaseRecent) {
        TestCaseRecentEntity testCaseRecentEntity = BeanMapper.map(testCaseRecent, TestCaseRecentEntity.class);

        return testCaseRecentDao.createTestCaseRecent(testCaseRecentEntity);
    }

    @Override
    public void updateTestCaseRecent(@NotNull @Valid TestCaseRecent testCaseRecent) {
        TestCaseRecentEntity testCaseRecentEntity = BeanMapper.map(testCaseRecent, TestCaseRecentEntity.class);

        testCaseRecentDao.updateTestCaseRecent(testCaseRecentEntity);
    }

    @Override
    public void deleteTestCaseRecent(@NotNull String id) {
        testCaseRecentDao.deleteTestCaseRecent(id);
    }

    @Override
    public TestCaseRecent findOne(String id) {
        TestCaseRecentEntity testCaseRecentEntity = testCaseRecentDao.findTestCaseRecent(id);

        TestCaseRecent testCaseRecent = BeanMapper.map(testCaseRecentEntity, TestCaseRecent.class);
        return testCaseRecent;
    }

    @Override
    public List<TestCaseRecent> findList(List<String> idList) {
        List<TestCaseRecentEntity> testCaseRecentEntityList =  testCaseRecentDao.findTestCaseRecentList(idList);

        List<TestCaseRecent> testCaseRecentList =  BeanMapper.mapList(testCaseRecentEntityList,TestCaseRecent.class);
        return testCaseRecentList;
    }

    @Override
    public TestCaseRecent findTestCaseRecent(@NotNull String id) {
        TestCaseRecent testCaseRecent = findOne(id);

        joinTemplate.joinQuery(testCaseRecent);

        return testCaseRecent;
    }

    @Override
    public List<TestCaseRecent> findAllTestCaseRecent() {
        List<TestCaseRecentEntity> testCaseRecentEntityList =  testCaseRecentDao.findAllTestCaseRecent();

        List<TestCaseRecent> testCaseRecentList =  BeanMapper.mapList(testCaseRecentEntityList,TestCaseRecent.class);

        joinTemplate.joinQuery(testCaseRecentList);

        return testCaseRecentList;
    }



    @Override
    public List<TestCaseRecent> findTestCaseRecentList(TestCaseRecentQuery testCaseRecentQuery) {
        List<TestCaseRecentEntity> testCaseRecentEntityList = testCaseRecentDao.findTestCaseRecentList(testCaseRecentQuery);
        List<TestCaseRecent> testCaseRecentList = BeanMapper.mapList(testCaseRecentEntityList,TestCaseRecent.class);

        //第三层获取不到值，手动设置值
        if(testCaseRecentList!=null&&testCaseRecentList.size()>0){
            for(TestCaseRecent testCaseRecent:testCaseRecentList){
                TestCase testCase = testCaseService.findTestCase(testCaseRecent.getTestCase().getId());
                testCaseRecent.setTestCase(testCase);

                Repository repository = repositoryService.findRepository(testCaseRecent.getRepository().getId());
                testCaseRecent.setRepository(repository);
            }
        }

        return testCaseRecentList;
    }

    @Override
    public Pagination<TestCaseRecent> findTestCaseRecentPage(TestCaseRecentQuery testCaseRecentQuery) {

        Pagination<TestCaseRecentEntity>  pagination = testCaseRecentDao.findTestCaseRecentPage(testCaseRecentQuery);

        List<TestCaseRecent> testCaseRecentList = BeanMapper.mapList(pagination.getDataList(),TestCaseRecent.class);

        //第三层获取不到值，手动设置值
//        if(testCaseRecentList!=null&&testCaseRecentList.size()>0){
//            for(TestCaseRecent testCaseRecent:testCaseRecentList){
//                TestCase testCase = testCaseService.findTestCase(testCaseRecent.getTestCase().getId());
//                testCaseRecent.setTestCase(testCase);
//
//                Repository repository = repositoryService.findRepository(testCaseRecent.getRepository().getId());
//                testCaseRecent.setRepository(repository);
//            }
//        }

        joinTemplate.joinQuery(testCaseRecentList);

        return PaginationBuilder.build(pagination,testCaseRecentList);
    }

    @Override
    public void testCaseRecent(TestCaseRecent testCaseRecent) {
        TestCaseRecentQuery testCaseRecentQuery = new TestCaseRecentQuery();
        testCaseRecentQuery.setTestCaseId(testCaseRecent.getTestCase().getId());
        testCaseRecentQuery.setUserId(testCaseRecent.getUser().getId());

        List<TestCaseRecentEntity> testCaseRecentEntityList = testCaseRecentDao.findTestCaseRecentList(testCaseRecentQuery);
        List<TestCaseRecent> testCaseRecentList = BeanMapper.mapList(testCaseRecentEntityList, TestCaseRecent.class);

        if(CollectionUtils.isNotEmpty(testCaseRecentList)&&testCaseRecentList.size()>0){
            TestCaseRecent testCaseRecent1 = testCaseRecentList.get(0);

            testCaseRecent.setId(testCaseRecent1.getId());
            testCaseRecent.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            updateTestCaseRecent(testCaseRecent);
        }else {
            testCaseRecent.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            createTestCaseRecent(testCaseRecent);
        }

    }


}