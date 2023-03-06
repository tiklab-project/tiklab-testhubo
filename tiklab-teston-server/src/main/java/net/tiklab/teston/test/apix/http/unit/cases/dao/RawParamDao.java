package net.tiklab.teston.test.apix.http.unit.cases.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.teston.test.apix.http.unit.cases.model.RawParamQuery;
import net.tiklab.teston.test.apix.http.unit.cases.entity.RawParamEntity;
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
     * @param rawParamQuery
     * @return
     */
    public List<RawParamEntity> findRawParamList(RawParamQuery rawParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RawParamEntity.class)
                .eq("apiUnitId", rawParamQuery.getApiUnitId())
                .orders(rawParamQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, RawParamEntity.class);
    }

    /**
     * 根据查询参数按分页查询raw
     * @param rawParamQuery
     * @return
     */
    public Pagination<RawParamEntity> findRawParamPage(RawParamQuery rawParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RawParamEntity.class)
                .eq("apiUnitId", rawParamQuery.getApiUnitId())
                .orders(rawParamQuery.getOrderParams())
                .pagination(rawParamQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, RawParamEntity.class);
    }
}