package io.tiklab.testhubo.test.apix.http.unit.cases.dao;

import io.tiklab.testhubo.test.apix.http.unit.cases.entity.RawResponseEntity;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.testhubo.test.apix.http.unit.cases.model.RawResponseUnitQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 响应中raw 数据访问
 */
@Repository
public class RawResponseDao{

    private static Logger logger = LoggerFactory.getLogger(RawResponseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建响应中raw
     * @param rawResponseEntity
     * @return
     */
    public String createRawResponse(RawResponseEntity rawResponseEntity) {
        return jpaTemplate.save(rawResponseEntity,String.class);
    }

    /**
     * 更新响应中raw
     * @param rawResponseEntity
     */
    public void updateRawResponse(RawResponseEntity rawResponseEntity){
        jpaTemplate.update(rawResponseEntity);
    }

    /**
     * 删除响应中raw
     * @param id
     */
    public void deleteRawResponse(String id){
        jpaTemplate.delete(RawResponseEntity.class,id);
    }

    public void deleteRawResponse(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找响应中raw
     * @param id
     * @return
     */
    public RawResponseEntity findRawResponse(String id){
        return jpaTemplate.findOne(RawResponseEntity.class,id);
    }

    /**
    * 查找所有响应中raw
    * @return
    */
    public List<RawResponseEntity> findAllRawResponse() {
        return jpaTemplate.findAll(RawResponseEntity.class);
    }

    public List<RawResponseEntity> findRawResponseList(List<String> idList) {
        return jpaTemplate.findList(RawResponseEntity.class,idList);
    }

    /**
     * 根据查询参数查询响应中raw列表
     * @param rawResponseUnitQuery
     * @return
     */
    public List<RawResponseEntity> findRawResponseList(RawResponseUnitQuery rawResponseUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RawResponseEntity.class)
                .eq("apiUnitId", rawResponseUnitQuery.getApiUnitId())
                .orders(rawResponseUnitQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, RawResponseEntity.class);
    }

    /**
     * 根据查询参数按分页查询响应中raw
     * @param rawResponseUnitQuery
     * @return
     */
    public Pagination<RawResponseEntity> findRawResponsePage(RawResponseUnitQuery rawResponseUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RawResponseEntity.class)
                .eq("apiUnitId", rawResponseUnitQuery.getApiUnitId())
                .orders(rawResponseUnitQuery.getOrderParams())
                .pagination(rawResponseUnitQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, RawResponseEntity.class);
    }
}