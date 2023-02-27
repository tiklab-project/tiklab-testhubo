package net.tiklab.teston.apix.http.unit.http.cases.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.teston.apix.http.unit.cases.model.AssertCaseQuery;
import net.tiklab.teston.apix.http.unit.http.cases.entity.AssertCaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AssertCaseDao
 */
@Repository
public class AssertCaseDao{

    private static Logger logger = LoggerFactory.getLogger(AssertCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param assertCaseEntity
     * @return
     */
    public String createAssertCase(AssertCaseEntity assertCaseEntity) {
        return jpaTemplate.save(assertCaseEntity,String.class);
    }

    /**
     * 更新
     * @param assertCaseEntity
     */
    public void updateAssertCase(AssertCaseEntity assertCaseEntity){
        jpaTemplate.update(assertCaseEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteAssertCase(String id){
        jpaTemplate.delete(AssertCaseEntity.class,id);
    }

    public void deleteAssertCase(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public AssertCaseEntity findAssertCase(String id){
        return jpaTemplate.findOne(AssertCaseEntity.class,id);
    }

    /**
    * findAllAssertCase
    * @return
    */
    public List<AssertCaseEntity> findAllAssertCase() {
        return jpaTemplate.findAll(AssertCaseEntity.class);
    }

    public List<AssertCaseEntity> findAssertCaseList(List<String> idList) {
        return jpaTemplate.findList(AssertCaseEntity.class,idList);
    }

    public List<AssertCaseEntity> findAssertCaseList(AssertCaseQuery assertCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AssertCaseEntity.class)
                .eq("apiUnitId", assertCaseQuery.getApiUnitId())
                .orders(assertCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, AssertCaseEntity.class);
    }

    public Pagination<AssertCaseEntity> findAssertCasePage(AssertCaseQuery assertCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AssertCaseEntity.class)
                .eq("apiUnitId", assertCaseQuery.getApiUnitId())
                .pagination(assertCaseQuery.getPageParam())
                .orders(assertCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, AssertCaseEntity.class);
    }
}