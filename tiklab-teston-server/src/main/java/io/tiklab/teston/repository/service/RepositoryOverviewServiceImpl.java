package io.tiklab.teston.repository.service;

import io.tiklab.teston.repository.model.RepositoryTotal;
import io.tiklab.teston.category.model.Categorys;
import io.tiklab.teston.category.model.CategoryQuery;
import io.tiklab.teston.category.service.CategoryService;
import io.tiklab.teston.testplan.cases.model.TestPlan;
import io.tiklab.teston.testplan.cases.model.TestPlanQuery;
import io.tiklab.teston.testplan.cases.service.TestPlanService;
import io.tiklab.user.dmUser.model.DmUser;
import io.tiklab.user.dmUser.model.DmUserQuery;
import io.tiklab.user.dmUser.service.DmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
* 仓库概况 服务
*/
@Service
public class RepositoryOverviewServiceImpl implements RepositoryOverviewService {


    @Autowired
    CategoryService categoryService;

    @Autowired
    DmUserService dmUserService;


    @Autowired
    TestPlanService testPlanService;


    @Override
    public RepositoryTotal findRepositoryOverview(String id) {
        RepositoryTotal repositoryTotal = new RepositoryTotal();

        //获取测试计划总和
        TestPlanQuery testPlanQuery = new TestPlanQuery();
        testPlanQuery.setRepositoryId(id);
        List<TestPlan> testPlanList = testPlanService.findTestPlanList(testPlanQuery);
        repositoryTotal.setPlanTotal(testPlanList.size());

        //获取分组的总和
        List<Categorys> categorysList = categoryService.findCategoryList(new CategoryQuery().setRepositoryId(id));
        repositoryTotal.setCategoryTotal(categorysList.size());


        //成员总和
        DmUserQuery dmUserQuery = new DmUserQuery();
        dmUserQuery.setDomainId(id);
        List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);
        repositoryTotal.setMemberTotal(dmUserList.size());

        repositoryTotal.setReviewTotal(0);

        return repositoryTotal;
    }


}