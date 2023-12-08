package io.thoughtware.teston.test.apix.http.scene.cases.dao;

import io.thoughtware.teston.test.apix.http.scene.cases.entity.ApiSceneStepEntity;
import io.thoughtware.teston.test.apix.http.scene.cases.model.ApiSceneStepQuery;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 接口场景下步骤 数据访问
 */
@Repository
public class ApiSceneStepDao{

    private static Logger logger = LoggerFactory.getLogger(ApiSceneStepDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建步骤
     * @param apiSceneStepEntity
     * @return
     */
    public String createApiSceneStep(ApiSceneStepEntity apiSceneStepEntity) {
        return jpaTemplate.save(apiSceneStepEntity,String.class);
    }

    /**
     * 更新步骤
     * @param apiSceneStepEntity
     */
    public void updateApiSceneStep(ApiSceneStepEntity apiSceneStepEntity){
        jpaTemplate.update(apiSceneStepEntity);
    }

    /**
     * 删除步骤
     * @param id
     */
    public void deleteApiSceneStep(String id){
        jpaTemplate.delete(ApiSceneStepEntity.class,id);
    }

    public void deleteApiSceneStep(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找步骤
     * @param id
     * @return
     */
    public ApiSceneStepEntity findApiSceneStep(String id){
        return jpaTemplate.findOne(ApiSceneStepEntity.class,id);
    }

    /**
    * 查找所有步骤
    * @return
    */
    public List<ApiSceneStepEntity> findAllApiSceneStep() {
        return jpaTemplate.findAll(ApiSceneStepEntity.class);
    }

    public List<ApiSceneStepEntity> findApiSceneStepList(List<String> idList) {
        return jpaTemplate.findList(ApiSceneStepEntity.class,idList);
    }

    /**
     * 根据查询参数查找查询步骤列表
     * @param apiSceneStepQuery
     * @return
     */
    public List<ApiSceneStepEntity> findApiSceneStepList(ApiSceneStepQuery apiSceneStepQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiSceneStepEntity.class)
                .eq("apiSceneId",apiSceneStepQuery.getApiSceneId())
                .orders(apiSceneStepQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,ApiSceneStepEntity.class);
    }

    /**
     * 根据查询参数查找按分页查询步骤
     * @param apiSceneStepQuery
     * @return
     */
    public Pagination<ApiSceneStepEntity> findApiSceneStepPage(ApiSceneStepQuery apiSceneStepQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiSceneStepEntity.class)
                .eq("apiSceneId",apiSceneStepQuery.getApiSceneId())
                .orders(apiSceneStepQuery.getOrderParams())
                .pagination(apiSceneStepQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,ApiSceneStepEntity.class);
    }
}