package io.thoughtware.teston.test.apix.http.perf.cases.dao;

import io.thoughtware.teston.test.apix.http.perf.cases.entity.ApiPerfStepEntity;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.teston.test.apix.http.perf.cases.model.ApiPerfStepQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
}