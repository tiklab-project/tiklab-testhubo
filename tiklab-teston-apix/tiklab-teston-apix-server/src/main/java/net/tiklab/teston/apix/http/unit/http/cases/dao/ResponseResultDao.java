package net.tiklab.teston.apix.http.unit.http.cases.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.teston.apix.http.unit.cases.model.ResponseResultQuery;
import net.tiklab.teston.apix.http.unit.http.cases.entity.ResponseResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 响应结果 数据访问
 */
@Repository
public class ResponseResultDao{

    private static Logger logger = LoggerFactory.getLogger(ResponseResultDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建响应结果
     * @param responseResultEntity
     * @return
     */
    public String createResponseResult(ResponseResultEntity responseResultEntity) {
        return jpaTemplate.save(responseResultEntity,String.class);
    }

    /**
     * 更新响应结果
     * @param responseResultEntity
     */
    public void updateResponseResult(ResponseResultEntity responseResultEntity){
        jpaTemplate.update(responseResultEntity);
    }

    /**
     * 删除响应结果
     * @param id
     */
    public void deleteResponseResult(String id){
        jpaTemplate.delete(ResponseResultEntity.class,id);
    }

    public void deleteResponseResult(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找响应结果
     * @param id
     * @return
     */
    public ResponseResultEntity findResponseResult(String id){
        return jpaTemplate.findOne(ResponseResultEntity.class,id);
    }

    /**
    * 查找所有响应结果
    * @return
    */
    public List<ResponseResultEntity> findAllResponseResult() {
        return jpaTemplate.findAll(ResponseResultEntity.class);
    }

    public List<ResponseResultEntity> findResponseResultList(List<String> idList) {
        return jpaTemplate.findList(ResponseResultEntity.class,idList);
    }

    /**
     * 根据查询参数查询响应结果列表
     * @param responseResultQuery
     * @return
     */
    public List<ResponseResultEntity> findResponseResultList(ResponseResultQuery responseResultQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ResponseResultEntity.class)
                .eq("apiUnitId", responseResultQuery.getApiUnitId())
                .get();
        return jpaTemplate.findList(queryCondition, ResponseResultEntity.class);
    }

    /**
     * 根据查询参数按分页查询响应结果
     * @param responseResultQuery
     * @return
     */
    public Pagination<ResponseResultEntity> findResponseResultPage(ResponseResultQuery responseResultQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ResponseResultEntity.class)
                .eq("apiUnitId", responseResultQuery.getApiUnitId())
                .get();
        return jpaTemplate.findPage(queryCondition, ResponseResultEntity.class);
    }
}