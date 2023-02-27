package net.tiklab.teston.apix.http.perf.cases.dao;

import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.teston.apix.http.perf.cases.entity.ApiPerfStepEntity;
import net.tiklab.teston.apix.http.perf.cases.model.ApiPerfStepQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ApiPerfStepDao
 */
@Repository
public class ApiPerfStepDao {

    private static Logger logger = LoggerFactory.getLogger(ApiPerfStepDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param apiPerfStepEntity
     * @return
     */
    public String createApiPerfStep(ApiPerfStepEntity apiPerfStepEntity) {
        return jpaTemplate.save(apiPerfStepEntity,String.class);
    }

    /**
     * 更新
     * @param apiPerfStepEntity
     */
    public void updateApiPerfStep(ApiPerfStepEntity apiPerfStepEntity){
        jpaTemplate.update(apiPerfStepEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteApiPerfStep(String id){
        jpaTemplate.delete(ApiPerfStepEntity.class,id);
    }

    public void deleteApiPerfStep(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public ApiPerfStepEntity findApiPerfStep(String id){
        return jpaTemplate.findOne(ApiPerfStepEntity.class,id);
    }

    /**
    * findAllApiPerfStep
    * @return
    */
    public List<ApiPerfStepEntity> findAllApiPerfStep() {
        return jpaTemplate.findAll(ApiPerfStepEntity.class);
    }

    public List<ApiPerfStepEntity> findApiPerfStepList(List<String> idList) {
        return jpaTemplate.findList(ApiPerfStepEntity.class,idList);
    }

    public List<ApiPerfStepEntity> findApiPerfStepList(ApiPerfStepQuery apiPerfStepQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiPerfStepEntity.class)
                .eq("apiPerfId", apiPerfStepQuery.getApiPerfId())
                .orders(apiPerfStepQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ApiPerfStepEntity.class);
    }

    public Pagination<ApiPerfStepEntity> findApiPerfStepPage(ApiPerfStepQuery apiPerfStepQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiPerfStepEntity.class)
                .eq("apiPerfId", apiPerfStepQuery.getApiPerfId())
                .pagination(apiPerfStepQuery.getPageParam())
                .orders(apiPerfStepQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, ApiPerfStepEntity.class);
    }
}