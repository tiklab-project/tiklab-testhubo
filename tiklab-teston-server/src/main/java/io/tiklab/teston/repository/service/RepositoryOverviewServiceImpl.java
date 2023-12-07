package io.tiklab.teston.repository.service;

import io.tiklab.teston.repository.dao.RepositoryOverviewDao;
import io.tiklab.teston.repository.model.RepositoryTotal;
import io.tiklab.teston.category.model.Category;
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
    DmUserService dmUserService;

    @Autowired
    RepositoryOverviewDao repositoryOverviewDao;


    @Override
    public RepositoryTotal findRepositoryOverview(String id) {

        RepositoryTotal repositoryTotal = repositoryOverviewDao.findWorkspaceOverview(id);

        //成员总和
        DmUserQuery dmUserQuery = new DmUserQuery();
        dmUserQuery.setDomainId(id);
        List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);
        repositoryTotal.setMemberTotal(dmUserList.size());

        repositoryTotal.setReviewTotal(0);

        return repositoryTotal;
    }


}