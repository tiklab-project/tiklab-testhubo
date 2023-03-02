package net.tiklab.teston.apix.http.unit.http.cases.dao;

import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.teston.apix.http.unit.cases.model.FormUrlencodedQuery;
import net.tiklab.teston.apix.http.unit.http.cases.entity.FormUrlencodedEntity;
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
    public String createFormUrlencoded(FormUrlencodedEntity formUrlencodedEntity) {
        return jpaTemplate.save(formUrlencodedEntity,String.class);
    }

    /**
     * 更新form-urlencoded
     * @param formUrlencodedEntity
     */
    public void updateFormUrlencoded(FormUrlencodedEntity formUrlencodedEntity){
        jpaTemplate.update(formUrlencodedEntity);
    }

    /**
     *  删除form-urlencoded
     * @param id
     */
    public void deleteFormUrlencoded(String id){
        jpaTemplate.delete(FormUrlencodedEntity.class,id);
    }

    public void deleteFormUrlencoded(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找form-urlencoded
     * @param id
     * @return
     */
    public FormUrlencodedEntity findFormUrlencoded(String id){
        return jpaTemplate.findOne(FormUrlencodedEntity.class,id);
    }

    /**
    * 查找所有form-urlencoded
    * @return
    */
    public List<FormUrlencodedEntity> findAllFormUrlencoded() {
        return jpaTemplate.findAll(FormUrlencodedEntity.class);
    }

    public List<FormUrlencodedEntity> findFormUrlencodedList(List<String> idList) {
        return jpaTemplate.findList(FormUrlencodedEntity.class,idList);
    }

    /**
     * 查询form-urlencoded 列表
     * @param formUrlencodedQuery
     * @return
     */
    public List<FormUrlencodedEntity> findFormUrlencodedList(FormUrlencodedQuery formUrlencodedQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(FormUrlencodedEntity.class)
                .eq("apiUnitId", formUrlencodedQuery.getApiUnitId())
                .orders(formUrlencodedQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,FormUrlencodedEntity.class);
    }

    /**
     * 按分页查询form-urlencoded
     * @param formUrlencodedQuery
     * @return
     */
    public Pagination<FormUrlencodedEntity> findFormUrlencodedPage(FormUrlencodedQuery formUrlencodedQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(FormUrlencodedEntity.class)
                .eq("apiUnitId", formUrlencodedQuery.getApiUnitId())
                .orders(formUrlencodedQuery.getOrderParams())
                .pagination(formUrlencodedQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,FormUrlencodedEntity.class);
    }
}