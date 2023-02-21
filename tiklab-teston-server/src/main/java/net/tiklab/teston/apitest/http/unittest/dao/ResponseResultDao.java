package net.tiklab.teston.apitest.http.unittest.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.apitest.http.unittest.entity.ResponseResultEntity;
import net.tiklab.teston.apitest.http.unittest.model.ResponseResultQuery;
import net.tiklab.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ResponseResultDao
 */
@Repository
public class ResponseResultDao{

    private static Logger logger = LoggerFactory.getLogger(ResponseResultDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param responseResultEntity
     * @return
     */
    public String createResponseResult(ResponseResultEntity responseResultEntity) {
        return jpaTemplate.save(responseResultEntity,String.class);
    }

    /**
     * 更新
     * @param responseResultEntity
     */
    public void updateResponseResult(ResponseResultEntity responseResultEntity){
        jpaTemplate.update(responseResultEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteResponseResult(String id){
        jpaTemplate.delete(ResponseResultEntity.class,id);
    }

    public void deleteResponseResult(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public ResponseResultEntity findResponseResult(String id){
        return jpaTemplate.findOne(ResponseResultEntity.class,id);
    }

    /**
    * findAllResponseResult
    * @return
    */
    public List<ResponseResultEntity> findAllResponseResult() {
        return jpaTemplate.findAll(ResponseResultEntity.class);
    }

    public List<ResponseResultEntity> findResponseResultList(List<String> idList) {
        return jpaTemplate.findList(ResponseResultEntity.class,idList);
    }

    public List<ResponseResultEntity> findResponseResultList(ResponseResultQuery responseResultQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ResponseResultEntity.class)
                .eq("apiUnitId", responseResultQuery.getApiUnitId())
                .get();
        return jpaTemplate.findList(queryCondition, ResponseResultEntity.class);
    }

    public Pagination<ResponseResultEntity> findResponseResultPage(ResponseResultQuery responseResultQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ResponseResultEntity.class)
                .eq("apiUnitId", responseResultQuery.getApiUnitId())
                .get();
        return jpaTemplate.findPage(queryCondition, ResponseResultEntity.class);
    }
}