package net.tiklab.teston.test.apiscene.cases.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.test.apiscene.cases.entity.ApiSceneCaseEntity;
import net.tiklab.teston.test.apiscene.cases.model.ApiSceneCaseQuery;
import net.tiklab.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ApiSceneCaseDao
 */
@Repository
public class ApiSceneCaseDao {

    private static Logger logger = LoggerFactory.getLogger(ApiSceneCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param apiSceneCaseEntity
     * @return
     */
    public String createApiSceneCase(ApiSceneCaseEntity apiSceneCaseEntity) {
        return jpaTemplate.save(apiSceneCaseEntity,String.class);
    }

    /**
     * 更新
     * @param apiSceneCaseEntity
     */
    public void updateApiSceneCase(ApiSceneCaseEntity apiSceneCaseEntity){
        jpaTemplate.update(apiSceneCaseEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteApiSceneCase(String id){
        jpaTemplate.delete(ApiSceneCaseEntity.class,id);
    }

    public void deleteApiSceneCase(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public ApiSceneCaseEntity findApiSceneCase(String id){
        return jpaTemplate.findOne(ApiSceneCaseEntity.class,id);
    }

    /**
    * findAllApiSceneCase
    * @return
    */
    public List<ApiSceneCaseEntity> findAllApiSceneCase() {
        return jpaTemplate.findAll(ApiSceneCaseEntity.class);
    }

    public List<ApiSceneCaseEntity> findApiSceneCaseList(List<String> idList) {
        return jpaTemplate.findList(ApiSceneCaseEntity.class,idList);
    }

    public List<ApiSceneCaseEntity> findApiSceneCaseList(ApiSceneCaseQuery apiSceneCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiSceneCaseEntity.class)
                .eq("testCaseId",apiSceneCaseQuery.getTestCaseId())
                .orders(apiSceneCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ApiSceneCaseEntity.class);
    }

    public Pagination<ApiSceneCaseEntity> findApiSceneCasePage(ApiSceneCaseQuery apiSceneCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiSceneCaseEntity.class)
                .eq("testCaseId",apiSceneCaseQuery.getTestCaseId())
                .pagination(apiSceneCaseQuery.getPageParam())
                .orders(apiSceneCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, ApiSceneCaseEntity.class);
    }


    public JdbcTemplate getJdbcTemplate() {
        return jpaTemplate.getJdbcTemplate();
    }

}