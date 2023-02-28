package net.tiklab.teston.manager.repository.service;

import net.tiklab.teston.manager.category.model.Category;
import net.tiklab.teston.manager.category.model.CategoryQuery;
import net.tiklab.teston.manager.category.service.CategoryService;
import net.tiklab.teston.manager.repository.model.RepositoryTotal;
import net.tiklab.teston.manager.testplan.cases.model.TestPlan;
import net.tiklab.teston.manager.testplan.cases.model.TestPlanQuery;
import net.tiklab.teston.manager.testplan.cases.service.TestPlanService;
import net.tiklab.user.dmUser.model.DmUser;
import net.tiklab.user.dmUser.model.DmUserQuery;
import net.tiklab.user.dmUser.service.DmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
* RepositoryServiceImpl
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
        List<Category> categoryList = categoryService.findCategoryList(new CategoryQuery().setRepositoryId(id));
        repositoryTotal.setCategoryTotal(categoryList.size());


        //成员总和
        DmUserQuery dmUserQuery = new DmUserQuery();
        dmUserQuery.setDomainId(id);
        List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);
        repositoryTotal.setMemberTotal(dmUserList.size());

        repositoryTotal.setReviewTotal(0);

        return repositoryTotal;
    }


}