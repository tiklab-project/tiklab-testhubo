package net.tiklab.teston.test.api.http.unit.http.cases.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.test.api.http.unit.http.cases.entity.RawParamEntity;
import net.tiklab.teston.test.api.http.unit.cases.model.RawParamQuery;
import net.tiklab.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RawParamDao
 */
@Repository
public class RawParamDao{

    private static Logger logger = LoggerFactory.getLogger(RawParamDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param rawParamEntity
     * @return
     */
    public String createRawParam(RawParamEntity rawParamEntity) {
        return jpaTemplate.save(rawParamEntity,String.class);
    }

    /**
     * 更新
     * @param rawParamEntity
     */
    public void updateRawParam(RawParamEntity rawParamEntity){
        jpaTemplate.update(rawParamEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteRawParam(String id){
        jpaTemplate.delete(RawParamEntity.class,id);
    }

    public void deleteRawParam(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public RawParamEntity findRawParam(String id){
        return jpaTemplate.findOne(RawParamEntity.class,id);
    }

    /**
    * findAllRawParam
    * @return
    */
    public List<RawParamEntity> findAllRawParam() {
        return jpaTemplate.findAll(RawParamEntity.class);
    }

    public List<RawParamEntity> findRawParamList(List<String> idList) {
        return jpaTemplate.findList(RawParamEntity.class,idList);
    }

    public List<RawParamEntity> findRawParamList(RawParamQuery rawParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RawParamEntity.class)
                .eq("apiUnitId", rawParamQuery.getApiUnitId())
                .orders(rawParamQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, RawParamEntity.class);
    }

    public Pagination<RawParamEntity> findRawParamPage(RawParamQuery rawParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RawParamEntity.class)
                .eq("apiUnitId", rawParamQuery.getApiUnitId())
                .orders(rawParamQuery.getOrderParams())
                .pagination(rawParamQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, RawParamEntity.class);
    }
}