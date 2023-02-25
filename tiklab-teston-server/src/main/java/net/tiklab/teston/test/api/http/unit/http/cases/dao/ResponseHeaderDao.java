package net.tiklab.teston.test.api.http.unit.http.cases.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.test.api.http.unit.http.cases.entity.ResponseHeaderEntity;
import net.tiklab.teston.test.api.http.unit.cases.model.ResponseHeaderQuery;
import net.tiklab.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ResponseHeaderDao
 */
@Repository
public class ResponseHeaderDao{

    private static Logger logger = LoggerFactory.getLogger(ResponseHeaderDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param responseHeaderEntity
     * @return
     */
    public String createResponseHeader(ResponseHeaderEntity responseHeaderEntity) {
        return jpaTemplate.save(responseHeaderEntity,String.class);
    }

    /**
     * 更新
     * @param responseHeaderEntity
     */
    public void updateResponseHeader(ResponseHeaderEntity responseHeaderEntity){
        jpaTemplate.update(responseHeaderEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteResponseHeader(String id){
        jpaTemplate.delete(ResponseHeaderEntity.class,id);
    }

    public void deleteResponseHeader(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public ResponseHeaderEntity findResponseHeader(String id){
        return jpaTemplate.findOne(ResponseHeaderEntity.class,id);
    }

    /**
    * findAllResponseHeader
    * @return
    */
    public List<ResponseHeaderEntity> findAllResponseHeader() {
        return jpaTemplate.findAll(ResponseHeaderEntity.class);
    }

    public List<ResponseHeaderEntity> findResponseHeaderList(List<String> idList) {
        return jpaTemplate.findList(ResponseHeaderEntity.class,idList);
    }

    public List<ResponseHeaderEntity> findResponseHeaderList(ResponseHeaderQuery responseHeaderQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ResponseHeaderEntity.class)
                .eq("apiUnitId", responseHeaderQuery.getApiUnitId())
                .orders(responseHeaderQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ResponseHeaderEntity.class);
    }

    public Pagination<ResponseHeaderEntity> findResponseHeaderPage(ResponseHeaderQuery responseHeaderQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ResponseHeaderEntity.class)
                .eq("apiUnitId", responseHeaderQuery.getApiUnitId())
                .orders(responseHeaderQuery.getOrderParams())
                .pagination(responseHeaderQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, ResponseHeaderEntity.class);
    }
}