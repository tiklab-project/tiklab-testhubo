package net.tiklab.teston.functest.unittest.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.functest.unittest.entity.FuncUnitCaseEntity;
import net.tiklab.teston.functest.unittest.model.FuncUnitCaseQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * FuncUnitCaseDao
 */
@Repository
public class FuncUnitCaseDao {

    private static Logger logger = LoggerFactory.getLogger(FuncUnitCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param funcUnitCaseEntity
     * @return
     */
    public String createFuncUnitCase(FuncUnitCaseEntity funcUnitCaseEntity) {
        return jpaTemplate.save(funcUnitCaseEntity,String.class);
    }

    /**
     * 更新
     * @param funcUnitCaseEntity
     */
    public void updateFuncUnitCase(FuncUnitCaseEntity funcUnitCaseEntity){
        jpaTemplate.update(funcUnitCaseEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteFuncUnitCase(String id){
        jpaTemplate.delete(FuncUnitCaseEntity.class,id);
    }

    public void deleteFuncUnitCase(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public FuncUnitCaseEntity findFuncUnitCase(String id){
        return jpaTemplate.findOne(FuncUnitCaseEntity.class,id);
    }

    /**
    * findAllFunctionalTestStep
    * @return
    */
    public List<FuncUnitCaseEntity> findAllFuncUnitCase() {
        return jpaTemplate.findAll(FuncUnitCaseEntity.class);
    }

    public List<FuncUnitCaseEntity> findFuncUnitCaseList(List<String> idList) {
        return jpaTemplate.findList(FuncUnitCaseEntity.class,idList);
    }

    public List<FuncUnitCaseEntity> findFuncUnitCaseList(FuncUnitCaseQuery funcUnitCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(FuncUnitCaseEntity.class)
                .eq("testCaseId", funcUnitCaseQuery.getTestCaseId())
                .orders(funcUnitCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, FuncUnitCaseEntity.class);
    }

    public Pagination<FuncUnitCaseEntity> findFuncUnitCasePage(FuncUnitCaseQuery funcUnitCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(FuncUnitCaseEntity.class)
                .eq("testCaseId", funcUnitCaseQuery.getTestCaseId())
                .pagination(funcUnitCaseQuery.getPageParam())
                .orders(funcUnitCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, FuncUnitCaseEntity.class);
    }
}