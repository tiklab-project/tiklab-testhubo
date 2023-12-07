package io.tiklab.teston.repository.dao;

import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.teston.repository.model.RepositoryTotal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 空间关注 数据访问
 */
@Repository
public class RepositoryOverviewDao {

    private static Logger logger = LoggerFactory.getLogger(RepositoryOverviewDao.class);

    @Autowired
    JpaTemplate jpaTemplate;


    /**
     * 根据查询参数按分页查找空间关注
     * @param repositoryId
     * @return
     */
    public RepositoryTotal findWorkspaceOverview(String repositoryId) {

        String categorySql = "Select count(1) as total from teston_category where repository_id = '" + repositoryId+ "'";
        Integer categoryTotal = jpaTemplate.getJdbcTemplate().queryForObject(categorySql, new Object[]{}, Integer.class);

        String planSql = "Select count(1) as total from teston_test_plan where repository_id = '" + repositoryId+ "'";
        Integer planTotal = jpaTemplate.getJdbcTemplate().queryForObject(planSql, new Object[]{}, Integer.class);

        String caseSql = "Select count(1) as total from teston_testcase where repository_id = '" + repositoryId+ "'";
        Integer caseTotal = jpaTemplate.getJdbcTemplate().queryForObject(caseSql, new Object[]{}, Integer.class);

        RepositoryTotal repositoryTotal = new RepositoryTotal();
        repositoryTotal.setPlanTotal(planTotal);
        repositoryTotal.setCategoryTotal(categoryTotal);
        repositoryTotal.setCaseTotal(caseTotal);

        return repositoryTotal;
    }
}