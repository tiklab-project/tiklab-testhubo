package net.tiklab.teston.apix.http.unit.http.cases.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.apix.http.unit.cases.model.ApiUnitCase;
import net.tiklab.teston.apix.http.unit.cases.model.ApiUnitCaseQuery;
import net.tiklab.teston.apix.http.unit.http.cases.entity.ApiUnitCaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * StepDao
 */
@Repository
public class ApiUnitCaseDao {

    private static Logger logger = LoggerFactory.getLogger(ApiUnitCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param apiUnitCaseEntity
     * @return
     */
    public String createApiUnitCase(ApiUnitCaseEntity apiUnitCaseEntity) {
        return jpaTemplate.save(apiUnitCaseEntity,String.class);
    }

    /**
     * 更新
     * @param apiUnitCaseEntity
     */
    public void updateApiUnitCase(ApiUnitCaseEntity apiUnitCaseEntity){
        jpaTemplate.update(apiUnitCaseEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteApiUnitCase(String id){
        jpaTemplate.delete(ApiUnitCaseEntity.class,id);
    }

    public void deleteApiUnitCase(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public ApiUnitCaseEntity findApiUnitCase(String id){
        return jpaTemplate.findOne(ApiUnitCaseEntity.class,id);
    }

    /**
    * findAllStep
    * @return
    */
    public List<ApiUnitCaseEntity> findAllApiUnitCase() {
        return jpaTemplate.findAll(ApiUnitCaseEntity.class);
    }

    public List<ApiUnitCaseEntity> findApiUnitCaseList(List<String> idList) {
        return jpaTemplate.findList(ApiUnitCaseEntity.class,idList);
    }

    public List<ApiUnitCaseEntity> findApiUnitCaseList(ApiUnitCaseQuery apiUnitCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiUnitCaseEntity.class)
                .eq("testCaseId",apiUnitCaseQuery.getTestCaseId())
                .orders(apiUnitCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ApiUnitCaseEntity.class);
    }

    public Pagination<ApiUnitCaseEntity> findApiUnitCasePage(ApiUnitCaseQuery apiUnitCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiUnitCaseEntity.class)
                .eq("testCaseId",apiUnitCaseQuery.getTestCaseId())
                .orders(apiUnitCaseQuery.getOrderParams())
                .pagination(apiUnitCaseQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, ApiUnitCaseEntity.class);
    }

    public List<ApiUnitCase> findApiUnitCaseBylist(List<String> testCaseId){
        String sql="select id from teston_step where testcase_id in (:testCaseId)";
        Map<String, Object> map = new HashMap<>();
        map.put("testCaseId",testCaseId);
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getJdbcTemplate());
        List<ApiUnitCase> steps = jdbcTemplate.query(sql, map, new BeanPropertyRowMapper<>(ApiUnitCase.class));
        return steps;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jpaTemplate.getJdbcTemplate();
    }
}