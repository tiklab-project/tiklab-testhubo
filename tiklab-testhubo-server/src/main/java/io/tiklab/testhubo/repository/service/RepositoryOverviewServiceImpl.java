package io.tiklab.testhubo.repository.service;

import io.tiklab.testhubo.category.service.CategoryService;
import io.tiklab.testhubo.instance.service.InstanceService;
import io.tiklab.testhubo.repository.model.RepositoryTotal;
import io.tiklab.testhubo.test.test.service.TestCaseService;
import io.tiklab.testhubo.testplan.cases.service.TestPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
* 仓库概况 服务
*/
@Service
public class RepositoryOverviewServiceImpl implements RepositoryOverviewService {

    @Autowired
    InstanceService instanceService;

    @Autowired
    TestCaseService testCaseService;

    @Autowired
    TestPlanService testPlanService;

    @Autowired
    CategoryService categoryService;


    @Override
    public RepositoryTotal findRepositoryOverview(String repositoryId) {

        RepositoryTotal repositoryTotal = new RepositoryTotal();

        int testCaseNum = testCaseService.findTestCaseNum(repositoryId);
        repositoryTotal.setCaseTotal(testCaseNum);

        int testPlanNum = testPlanService.findTestPlanNum(repositoryId);
        repositoryTotal.setPlanTotal(testPlanNum);

        int instanceNumByRepositoryId = instanceService.findInstanceNumByRepositoryId(repositoryId);
        repositoryTotal.setInstanceTotal(instanceNumByRepositoryId);

        int categoryNum = categoryService.findCategoryNum(repositoryId);
        repositoryTotal.setCategoryTotal(categoryNum);

        repositoryTotal.setReviewTotal(0);

        return repositoryTotal;
    }


}