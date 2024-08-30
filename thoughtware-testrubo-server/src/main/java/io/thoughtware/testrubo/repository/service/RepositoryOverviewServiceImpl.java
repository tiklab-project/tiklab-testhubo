package io.thoughtware.testrubo.repository.service;

import io.thoughtware.testrubo.category.service.CategoryService;
import io.thoughtware.testrubo.instance.service.InstanceService;
import io.thoughtware.testrubo.repository.model.RepositoryTotal;
import io.thoughtware.testrubo.test.test.service.TestCaseService;
import io.thoughtware.testrubo.testplan.cases.service.TestPlanService;
import io.thoughtware.user.dmUser.model.DmUser;
import io.thoughtware.user.dmUser.model.DmUserQuery;
import io.thoughtware.user.dmUser.service.DmUserService;
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