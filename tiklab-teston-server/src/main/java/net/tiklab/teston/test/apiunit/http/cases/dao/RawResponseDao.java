package net.tiklab.teston.test.apiunit.http.cases.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.test.apiunit.http.cases.entity.RawResponseEntity;
import net.tiklab.teston.test.apiunit.http.cases.model.RawResponseQuery;
import net.tiklab.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RawResponseDao
 */
@Repository
public class RawResponseDao{

    private static Logger logger = LoggerFactory.getLogger(RawResponseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param rawResponseEntity
     * @return
     */
    public String createRawResponse(RawResponseEntity rawResponseEntity) {
        return jpaTemplate.save(rawResponseEntity,String.class);
    }

    /**
     * 更新
     * @param rawResponseEntity
     */
    public void updateRawResponse(RawResponseEntity rawResponseEntity){
        jpaTemplate.update(rawResponseEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteRawResponse(String id){
        jpaTemplate.delete(RawResponseEntity.class,id);
    }

    public void deleteRawResponse(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public RawResponseEntity findRawResponse(String id){
        return jpaTemplate.findOne(RawResponseEntity.class,id);
    }

    /**
    * findAllRawResponse
    * @return
    */
    public List<RawResponseEntity> findAllRawResponse() {
        return jpaTemplate.findAll(RawResponseEntity.class);
    }

    public List<RawResponseEntity> findRawResponseList(List<String> idList) {
        return jpaTemplate.findList(RawResponseEntity.class,idList);
    }

    public List<RawResponseEntity> findRawResponseList(RawResponseQuery rawResponseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RawResponseEntity.class)
                .eq("apiUnitId", rawResponseQuery.getApiUnitId())
                .orders(rawResponseQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, RawResponseEntity.class);
    }

    public Pagination<RawResponseEntity> findRawResponsePage(RawResponseQuery rawResponseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RawResponseEntity.class)
                .eq("apiUnitId", rawResponseQuery.getApiUnitId())
                .orders(rawResponseQuery.getOrderParams())
                .pagination(rawResponseQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, RawResponseEntity.class);
    }
}