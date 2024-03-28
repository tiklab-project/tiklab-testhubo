package io.thoughtware.teston.test.apix.http.perf.cases.dao;

import io.thoughtware.teston.test.apix.http.perf.cases.entity.ApiPerfStepEntity;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.teston.test.apix.http.perf.cases.model.ApiPerfStepQuery;
import io.thoughtware.teston.test.apix.http.perf.cases.entity.ApiPerfStepWillBindCaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 接口性能下场景步骤 数据访问
 */
@Repository
public class ApiPerfStepDao {

    private static Logger logger = LoggerFactory.getLogger(ApiPerfStepDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建接口性能场景步骤
     * @param apiPerfStepEntity
     * @return
     */
    public String createApiPerfStep(ApiPerfStepEntity apiPerfStepEntity) {
        return jpaTemplate.save(apiPerfStepEntity,String.class);
    }

    /**
     * 更新接口性能场景步骤
     * @param apiPerfStepEntity
     */
    public void updateApiPerfStep(ApiPerfStepEntity apiPerfStepEntity){
        jpaTemplate.update(apiPerfStepEntity);
    }

    /**
     * 删除接口性能场景步骤
     * @param id
     */
    public void deleteApiPerfStep(String id){
        jpaTemplate.delete(ApiPerfStepEntity.class,id);
    }

    public void deleteApiPerfStep(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找接口性能场景步骤
     * @param id
     * @return
     */
    public ApiPerfStepEntity findApiPerfStep(String id){
        return jpaTemplate.findOne(ApiPerfStepEntity.class,id);
    }


    public Integer isApiSceneExist(String caseId) {
        String sql = " Select count(1) as total from teston_api_perf_step where api_scene_id = '" + caseId + "'";
        Integer modelTotal = jpaTemplate.getJdbcTemplate().queryForObject(sql, new Object[]{}, Integer.class);

        return modelTotal;
    }


    /**
    * 查找所有接口性能场景步骤
    * @return
    */
    public List<ApiPerfStepEntity> findAllApiPerfStep() {
        return jpaTemplate.findAll(ApiPerfStepEntity.class);
    }

    public List<ApiPerfStepEntity> findApiPerfStepList(List<String> idList) {
        return jpaTemplate.findList(ApiPerfStepEntity.class,idList);
    }

    /**
     * 查询列表接口性能场景步骤
     * @param apiPerfStepQuery
     * @return
     */
    public List<ApiPerfStepEntity> findApiPerfStepList(ApiPerfStepQuery apiPerfStepQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiPerfStepEntity.class)
                .eq("apiPerfId", apiPerfStepQuery.getApiPerfId())
                .orders(apiPerfStepQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ApiPerfStepEntity.class);
    }

    /**
     * 按分页查询接口性能场景步骤
     * @param apiPerfStepQuery
     * @return
     */
    public Pagination<ApiPerfStepEntity> findApiPerfStepPage(ApiPerfStepQuery apiPerfStepQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiPerfStepEntity.class)
                .eq("apiPerfId", apiPerfStepQuery.getApiPerfId())
                .pagination(apiPerfStepQuery.getPageParam())
                .orders(apiPerfStepQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, ApiPerfStepEntity.class);
    }


    /**
     * 查询未关联的用例
     * @param apiPerfStepQuery
     * @return
     */
    public Pagination<ApiPerfStepWillBindCaseEntity> findApiPerfStepWillBindCasePage(ApiPerfStepQuery apiPerfStepQuery) {
        StringBuilder modelSqlBuilder = new StringBuilder();
        modelSqlBuilder.append("SELECT  teston_api_scene.id,teston_testcase.name,teston_testcase.create_user,teston_testcase.case_type ,teston_testcase.create_time ")
                .append(" FROM teston_api_scene  ")
                .append(" JOIN teston_testcase on teston_api_scene.testcase_id = teston_testcase.id  ")
                .append(" WHERE teston_testcase.repository_id = '").append(apiPerfStepQuery.getRepositoryId()).append("'");

        if (apiPerfStepQuery.getName() != null) {
            modelSqlBuilder.append(" And teston_testcase.name LIKE '%").append(apiPerfStepQuery.getName()).append("%'");
        }

        modelSqlBuilder .append(" AND NOT EXISTS (  ")
                .append(" SELECT 1 FROM teston_api_perf_step   ")
                .append(" JOIN teston_api_perfcase ON teston_api_perfcase.id = teston_api_perf_step.api_perf_id ")
                .append(" WHERE teston_api_perf_step.api_scene_id = teston_api_scene.id  ")
                .append(" AND teston_api_perfcase.id =  '").append(apiPerfStepQuery.getApiPerfId()).append("')");

        String modelSql = modelSqlBuilder.toString();

        Pagination<ApiPerfStepWillBindCaseEntity> page = jpaTemplate.getJdbcTemplate().findPage(modelSql, new Object[]{}, apiPerfStepQuery.getPageParam(), new BeanPropertyRowMapper<>(ApiPerfStepWillBindCaseEntity.class));
        return page;
    }


}