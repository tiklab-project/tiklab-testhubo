package net.tiklab.teston.test.apiscene.instance.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.test.apiscene.instance.entity.ApiSceneStepInstanceBindEntity;
import net.tiklab.teston.test.apiscene.instance.model.ApiSceneStepInstanceBindQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ApiSceneStepInstanceBindDao
 */
@Repository
public class ApiSceneStepInstanceBindDao{

    private static Logger logger = LoggerFactory.getLogger(ApiSceneStepInstanceBindDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param apiSceneStepInstanceBindEntity
     * @return
     */
    public String createApiSceneStepInstanceBind(ApiSceneStepInstanceBindEntity apiSceneStepInstanceBindEntity) {
        return jpaTemplate.save(apiSceneStepInstanceBindEntity,String.class);
    }

    /**
     * 更新
     * @param apiSceneStepInstanceBindEntity
     */
    public void updateApiSceneStepInstanceBind(ApiSceneStepInstanceBindEntity apiSceneStepInstanceBindEntity){
        jpaTemplate.update(apiSceneStepInstanceBindEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteApiSceneStepInstanceBind(String id){
        jpaTemplate.delete(ApiSceneStepInstanceBindEntity.class,id);
    }

    public void deleteApiSceneStepInstanceBind(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public ApiSceneStepInstanceBindEntity findApiSceneStepInstanceBind(String id){
        return jpaTemplate.findOne(ApiSceneStepInstanceBindEntity.class,id);
    }

    /**
    * findAllApiSceneStepInstanceBind
    * @return
    */
    public List<ApiSceneStepInstanceBindEntity> findAllApiSceneStepInstanceBind() {
        return jpaTemplate.findAll(ApiSceneStepInstanceBindEntity.class);
    }

    public List<ApiSceneStepInstanceBindEntity> findApiSceneStepInstanceBindList(List<String> idList) {
        return jpaTemplate.findList(ApiSceneStepInstanceBindEntity.class,idList);
    }

    public List<ApiSceneStepInstanceBindEntity> findApiSceneStepInstanceBindList(ApiSceneStepInstanceBindQuery apiSceneStepInstanceBindQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiSceneStepInstanceBindEntity.class)
                .eq("apiSceneInstanceId",apiSceneStepInstanceBindQuery.getApiSceneInstanceId())
                .orders(apiSceneStepInstanceBindQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,ApiSceneStepInstanceBindEntity.class);
    }

    public Pagination<ApiSceneStepInstanceBindEntity> findApiSceneStepInstanceBindPage(ApiSceneStepInstanceBindQuery apiSceneStepInstanceBindQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiSceneStepInstanceBindEntity.class)
                .eq("apiSceneInstanceId",apiSceneStepInstanceBindQuery.getApiSceneInstanceId())
                .pagination(apiSceneStepInstanceBindQuery.getPageParam())
                .orders(apiSceneStepInstanceBindQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition,ApiSceneStepInstanceBindEntity.class);
    }
}