package io.thoughtware.testhubo.test.apix.http.unit.cases.dao;

import io.thoughtware.testhubo.test.apix.http.unit.cases.entity.RawParamEntity;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.testhubo.test.apix.http.unit.cases.model.RawParamUnitQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * raw类型 数据访问
 */
@Repository
public class RawParamDao{

    private static Logger logger = LoggerFactory.getLogger(RawParamDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建raw
     * @param rawParamEntity
     * @return
     */
    public String createRawParam(RawParamEntity rawParamEntity) {
        return jpaTemplate.save(rawParamEntity,String.class);
    }

    /**
     * 更新raw
     * @param rawParamEntity
     */
    public void updateRawParam(RawParamEntity rawParamEntity){
        jpaTemplate.update(rawParamEntity);
    }

    /**
     * 删除raw
     * @param id
     */
    public void deleteRawParam(String id){
        jpaTemplate.delete(RawParamEntity.class,id);
    }

    public void deleteRawParam(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找raw
     * @param id
     * @return
     */
    public RawParamEntity findRawParam(String id){
        return jpaTemplate.findOne(RawParamEntity.class,id);
    }

    /**
    * 查找所有raw
    * @return
    */
    public List<RawParamEntity> findAllRawParam() {
        return jpaTemplate.findAll(RawParamEntity.class);
    }

    public List<RawParamEntity> findRawParamList(List<String> idList) {
        return jpaTemplate.findList(RawParamEntity.class,idList);
    }

    /**
     * 根据查询参数查询raw列表
     * @param rawParamUnitQuery
     * @return
     */
    public List<RawParamEntity> findRawParamList(RawParamUnitQuery rawParamUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RawParamEntity.class)
                .eq("apiUnitId", rawParamUnitQuery.getApiUnitId())
                .orders(rawParamUnitQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, RawParamEntity.class);
    }

    /**
     * 根据查询参数按分页查询raw
     * @param rawParamUnitQuery
     * @return
     */
    public Pagination<RawParamEntity> findRawParamPage(RawParamUnitQuery rawParamUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RawParamEntity.class)
                .eq("apiUnitId", rawParamUnitQuery.getApiUnitId())
                .orders(rawParamUnitQuery.getOrderParams())
                .pagination(rawParamUnitQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, RawParamEntity.class);
    }
}