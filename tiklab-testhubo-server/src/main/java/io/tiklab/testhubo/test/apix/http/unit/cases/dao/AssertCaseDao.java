package io.tiklab.testhubo.test.apix.http.unit.cases.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.testhubo.test.apix.http.unit.cases.model.AssertUnitQuery;
import io.tiklab.testhubo.test.apix.http.unit.cases.entity.AssertCaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 断言 数据访问
 */
@Repository
public class AssertCaseDao{

    private static Logger logger = LoggerFactory.getLogger(AssertCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建断言
     * @param assertCaseEntity
     * @return
     */
    public String createAssertCase(AssertCaseEntity assertCaseEntity) {
        return jpaTemplate.save(assertCaseEntity,String.class);
    }

    /**
     * 更新断言
     * @param assertCaseEntity
     */
    public void updateAssertCase(AssertCaseEntity assertCaseEntity){
        jpaTemplate.update(assertCaseEntity);
    }

    /**
     * 删除断言
     * @param id
     */
    public void deleteAssertCase(String id){
        jpaTemplate.delete(AssertCaseEntity.class,id);
    }

    public void deleteAssertCase(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 通过id查找断言
     * @param id
     * @return
     */
    public AssertCaseEntity findAssertCase(String id){
        return jpaTemplate.findOne(AssertCaseEntity.class,id);
    }

    /**
    * 查找所有断言
    * @return
    */
    public List<AssertCaseEntity> findAllAssertCase() {
        return jpaTemplate.findAll(AssertCaseEntity.class);
    }

    public List<AssertCaseEntity> findAssertCaseList(List<String> idList) {
        return jpaTemplate.findList(AssertCaseEntity.class,idList);
    }

    /**
     * 查询断言列表
     * @param assertUnitQuery
     * @return
     */
    public List<AssertCaseEntity> findAssertCaseList(AssertUnitQuery assertUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AssertCaseEntity.class)
                .eq("apiUnitId", assertUnitQuery.getApiUnitId())
                .orders(assertUnitQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, AssertCaseEntity.class);
    }

    /**
     * 按分页查询断言
     * @param assertUnitQuery
     * @return
     */
    public Pagination<AssertCaseEntity> findAssertCasePage(AssertUnitQuery assertUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AssertCaseEntity.class)
                .eq("apiUnitId", assertUnitQuery.getApiUnitId())
                .pagination(assertUnitQuery.getPageParam())
                .orders(assertUnitQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, AssertCaseEntity.class);
    }
}