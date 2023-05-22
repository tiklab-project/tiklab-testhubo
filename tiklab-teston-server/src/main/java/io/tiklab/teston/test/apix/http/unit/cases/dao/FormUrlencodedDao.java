package io.tiklab.teston.test.apix.http.unit.cases.dao;

import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.teston.test.apix.http.unit.cases.entity.FormUrlEncodedEntity;
import io.tiklab.teston.test.apix.http.unit.cases.model.FormUrlencodedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * form-urlencoded 数据访问
 */
@Repository
public class FormUrlencodedDao{

    private static Logger logger = LoggerFactory.getLogger(FormUrlencodedDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建form-urlencoded
     * @param formUrlencodedEntity
     * @return
     */
    public String createFormUrlencoded(FormUrlEncodedEntity formUrlencodedEntity) {
        return jpaTemplate.save(formUrlencodedEntity,String.class);
    }

    /**
     * 更新form-urlencoded
     * @param formUrlencodedEntity
     */
    public void updateFormUrlencoded(FormUrlEncodedEntity formUrlencodedEntity){
        jpaTemplate.update(formUrlencodedEntity);
    }

    /**
     *  删除form-urlencoded
     * @param id
     */
    public void deleteFormUrlencoded(String id){
        jpaTemplate.delete(FormUrlEncodedEntity.class,id);
    }

    public void deleteFormUrlencoded(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找form-urlencoded
     * @param id
     * @return
     */
    public FormUrlEncodedEntity findFormUrlencoded(String id){
        return jpaTemplate.findOne(FormUrlEncodedEntity.class,id);
    }

    /**
    * 查找所有form-urlencoded
    * @return
    */
    public List<FormUrlEncodedEntity> findAllFormUrlencoded() {
        return jpaTemplate.findAll(FormUrlEncodedEntity.class);
    }

    public List<FormUrlEncodedEntity> findFormUrlencodedList(List<String> idList) {
        return jpaTemplate.findList(FormUrlEncodedEntity.class,idList);
    }

    /**
     * 查询form-urlencoded 列表
     * @param formUrlencodedQuery
     * @return
     */
    public List<FormUrlEncodedEntity> findFormUrlencodedList(FormUrlencodedQuery formUrlencodedQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(FormUrlEncodedEntity.class)
                .eq("apiUnitId", formUrlencodedQuery.getApiUnitId())
                .orders(formUrlencodedQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, FormUrlEncodedEntity.class);
    }

    /**
     * 按分页查询form-urlencoded
     * @param formUrlencodedQuery
     * @return
     */
    public Pagination<FormUrlEncodedEntity> findFormUrlencodedPage(FormUrlencodedQuery formUrlencodedQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(FormUrlEncodedEntity.class)
                .eq("apiUnitId", formUrlencodedQuery.getApiUnitId())
                .orders(formUrlencodedQuery.getOrderParams())
                .pagination(formUrlencodedQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, FormUrlEncodedEntity.class);
    }
}