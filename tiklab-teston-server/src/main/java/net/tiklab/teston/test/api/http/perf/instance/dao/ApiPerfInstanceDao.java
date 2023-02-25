package net.tiklab.teston.test.api.http.perf.instance.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.test.api.http.perf.instance.entity.ApiPerfInstanceEntity;
import net.tiklab.teston.test.api.http.perf.instance.model.ApiPerfInstanceQuery;
import net.tiklab.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ApiPerfInstanceDao
 */
@Repository
public class ApiPerfInstanceDao {

    private static Logger logger = LoggerFactory.getLogger(ApiPerfInstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param apiPerfInstanceEntity
     * @return
     */
    public String createApiPerfInstance(ApiPerfInstanceEntity apiPerfInstanceEntity) {
        return jpaTemplate.save(apiPerfInstanceEntity,String.class);
    }

    /**
     * 更新
     * @param apiPerfInstanceEntity
     */
    public void updateApiPerfInstance(ApiPerfInstanceEntity apiPerfInstanceEntity){
        jpaTemplate.update(apiPerfInstanceEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteApiPerfInstance(String id){
        jpaTemplate.delete(ApiPerfInstanceEntity.class,id);
    }

    public void deleteApiPerfInstance(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public ApiPerfInstanceEntity findApiPerfInstance(String id){
        return jpaTemplate.findOne(ApiPerfInstanceEntity.class,id);
    }

    /**
    * findAllApiPerfInstance
    * @return
    */
    public List<ApiPerfInstanceEntity> findAllApiPerfInstance() {
        return jpaTemplate.findAll(ApiPerfInstanceEntity.class);
    }

    public List<ApiPerfInstanceEntity> findApiPerfInstanceList(List<String> idList) {
        return jpaTemplate.findList(ApiPerfInstanceEntity.class,idList);
    }

    public List<ApiPerfInstanceEntity> findApiPerfInstanceList(ApiPerfInstanceQuery apiPerfInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiPerfInstanceEntity.class)
                .eq("apiPerfId", apiPerfInstanceQuery.getApiPerfId())
                .orders(apiPerfInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ApiPerfInstanceEntity.class);
    }

    public Pagination<ApiPerfInstanceEntity> findApiPerfInstancePage(ApiPerfInstanceQuery apiPerfInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiPerfInstanceEntity.class)
                .eq("apiPerfId", apiPerfInstanceQuery.getApiPerfId())
                .pagination(apiPerfInstanceQuery.getPageParam())
                .orders(apiPerfInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, ApiPerfInstanceEntity.class);
    }
}