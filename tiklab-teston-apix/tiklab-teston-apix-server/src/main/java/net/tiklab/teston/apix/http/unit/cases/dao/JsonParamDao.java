package net.tiklab.teston.apix.http.unit.cases.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.teston.apix.http.unit.cases.model.JsonParamQuery;
import net.tiklab.teston.apix.http.unit.cases.entity.JsonParamEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 请求中json 数据访问
 */
@Repository
public class JsonParamDao{

    private static Logger logger = LoggerFactory.getLogger(JsonParamDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建请求中json
     * @param jsonParamEntity
     * @return
     */
    public String createJsonParam(JsonParamEntity jsonParamEntity) {
        return jpaTemplate.save(jsonParamEntity,String.class);
    }

    /**
     * 更新请求中json
     * @param jsonParamEntity
     */
    public void updateJsonParam(JsonParamEntity jsonParamEntity){
        jpaTemplate.update(jsonParamEntity);
    }

    /**
     * 删除请求中json
     * @param id
     */
    public void deleteJsonParam(String id){
        jpaTemplate.delete(JsonParamEntity.class,id);
    }

    public void deleteJsonParam(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找请求中json
     * @param id
     * @return
     */
    public JsonParamEntity findJsonParam(String id){
        return jpaTemplate.findOne(JsonParamEntity.class,id);
    }

    /**
    * 查找所有json
    * @return
    */
    public List<JsonParamEntity> findAllJsonParam() {
        return jpaTemplate.findAll(JsonParamEntity.class);
    }

    public List<JsonParamEntity> findJsonParamList(List<String> idList) {
        return jpaTemplate.findList(JsonParamEntity.class,idList);
    }

    /**
     * 根据查询参数查询json列表
     * @param jsonParamQuery
     * @return
     */
    public List<JsonParamEntity> findJsonParamList(JsonParamQuery jsonParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(JsonParamEntity.class)
                .eq("apiUnitId", jsonParamQuery.getApiUnitId())
                .orders(jsonParamQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, JsonParamEntity.class);
    }

    /**
     * 根据查询参数按分页查询json
     * @param jsonParamQuery
     * @return
     */
    public Pagination<JsonParamEntity> findJsonParamPage(JsonParamQuery jsonParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(JsonParamEntity.class)
                .eq("apiUnitId", jsonParamQuery.getApiUnitId())
                .orders(jsonParamQuery.getOrderParams())
                .pagination(jsonParamQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, JsonParamEntity.class);
    }
}