package net.tiklab.teston.test.api.http.scene.instance.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.test.api.http.scene.instance.entity.ApiSceneInstanceEntity;
import net.tiklab.teston.test.api.http.scene.instance.model.ApiSceneInstanceQuery;
import net.tiklab.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TestInstanceDao
 */
@Repository
public class ApiSceneInstanceDao {

    private static Logger logger = LoggerFactory.getLogger(ApiSceneInstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param testInstanceEntity
     * @return
     */
    public String createTestInstance(ApiSceneInstanceEntity testInstanceEntity) {
        return jpaTemplate.save(testInstanceEntity,String.class);
    }

    /**
     * 更新
     * @param testInstanceEntity
     */
    public void updateTestInstance(ApiSceneInstanceEntity testInstanceEntity){
        jpaTemplate.update(testInstanceEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteTestInstance(String id){
        jpaTemplate.delete(ApiSceneInstanceEntity.class,id);
    }

    public void deleteTestInstance(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public ApiSceneInstanceEntity findTestInstance(String id){
        return jpaTemplate.findOne(ApiSceneInstanceEntity.class,id);
    }

    /**
    * findAllTestInstance
    * @return
    */
    public List<ApiSceneInstanceEntity> findAllTestInstance() {
        return jpaTemplate.findAll(ApiSceneInstanceEntity.class);
    }

    public List<ApiSceneInstanceEntity> findTestInstanceList(List<String> idList) {
        return jpaTemplate.findList(ApiSceneInstanceEntity.class,idList);
    }

    public List<ApiSceneInstanceEntity> findTestInstanceList(ApiSceneInstanceQuery apiSceneInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiSceneInstanceEntity.class)
                .eq("apiSceneId", apiSceneInstanceQuery.getApiSceneId())
                .orders(apiSceneInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ApiSceneInstanceEntity.class);
    }

    public Pagination<ApiSceneInstanceEntity> findTestInstancePage(ApiSceneInstanceQuery apiSceneInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiSceneInstanceEntity.class)
                .eq("apiSceneId", apiSceneInstanceQuery.getApiSceneId())
                .orders(apiSceneInstanceQuery.getOrderParams())
                .pagination(apiSceneInstanceQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, ApiSceneInstanceEntity.class);
    }
}