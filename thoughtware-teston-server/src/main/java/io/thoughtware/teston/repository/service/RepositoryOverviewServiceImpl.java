package io.thoughtware.teston.repository.service;

import io.thoughtware.teston.category.service.CategoryService;
import io.thoughtware.teston.repository.model.RepositoryTotal;
import io.thoughtware.teston.test.test.service.TestCaseService;
import io.thoughtware.teston.testplan.cases.service.TestPlanService;
import io.thoughtware.user.dmUser.model.DmUser;
import io.thoughtware.user.dmUser.model.DmUserQuery;
import io.thoughtware.user.dmUser.service.DmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
* 仓库概况 服务
*/
@Service
public class RepositoryOverviewServiceImpl implements RepositoryOverviewService {

    @Autowired
    DmUserService dmUserService;

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

        int categoryNum = categoryService.findCategoryNum(repositoryId);
        repositoryTotal.setCategoryTotal(categoryNum);

        //成员总和
        DmUserQuery dmUserQuery = new DmUserQuery();
        dmUserQuery.setDomainId(repositoryId);
        List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);
        repositoryTotal.setMemberTotal(dmUserList.size());

        repositoryTotal.setReviewTotal(0);

        return repositoryTotal;
    }


}