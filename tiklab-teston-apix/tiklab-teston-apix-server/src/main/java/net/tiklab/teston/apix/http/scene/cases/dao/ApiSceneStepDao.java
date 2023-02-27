package net.tiklab.teston.apix.http.scene.cases.dao;

import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.teston.apix.http.scene.cases.model.ApiSceneStepQuery;
import net.tiklab.teston.apix.http.scene.cases.entity.ApiSceneStepEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ApiSceneStepDao
 */
@Repository
public class ApiSceneStepDao{

    private static Logger logger = LoggerFactory.getLogger(ApiSceneStepDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param apiSceneStepEntity
     * @return
     */
    public String createApiSceneStep(ApiSceneStepEntity apiSceneStepEntity) {
        return jpaTemplate.save(apiSceneStepEntity,String.class);
    }

    /**
     * 更新
     * @param apiSceneStepEntity
     */
    public void updateApiSceneStep(ApiSceneStepEntity apiSceneStepEntity){
        jpaTemplate.update(apiSceneStepEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteApiSceneStep(String id){
        jpaTemplate.delete(ApiSceneStepEntity.class,id);
    }

    public void deleteApiSceneStep(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public ApiSceneStepEntity findApiSceneStep(String id){
        return jpaTemplate.findOne(ApiSceneStepEntity.class,id);
    }

    /**
    * findAllApiSceneStep
    * @return
    */
    public List<ApiSceneStepEntity> findAllApiSceneStep() {
        return jpaTemplate.findAll(ApiSceneStepEntity.class);
    }

    public List<ApiSceneStepEntity> findApiSceneStepList(List<String> idList) {
        return jpaTemplate.findList(ApiSceneStepEntity.class,idList);
    }

    public List<ApiSceneStepEntity> findApiSceneStepList(ApiSceneStepQuery apiSceneStepQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiSceneStepEntity.class)
                .eq("apiSceneId",apiSceneStepQuery.getApiSceneId())
                .orders(apiSceneStepQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,ApiSceneStepEntity.class);
    }

    public Pagination<ApiSceneStepEntity> findApiSceneStepPage(ApiSceneStepQuery apiSceneStepQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiSceneStepEntity.class)
                .eq("apiSceneId",apiSceneStepQuery.getApiSceneId())
                .orders(apiSceneStepQuery.getOrderParams())
                .pagination(apiSceneStepQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,ApiSceneStepEntity.class);
    }
}