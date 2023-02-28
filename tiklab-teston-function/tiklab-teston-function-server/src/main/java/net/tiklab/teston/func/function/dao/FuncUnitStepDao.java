package net.tiklab.teston.func.function.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;

import net.tiklab.teston.func.function.entity.FuncUnitStepEntity;
import net.tiklab.teston.func.model.FuncUnitStepQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * FuncUnitStepDao
 */
@Repository
public class FuncUnitStepDao{

    private static Logger logger = LoggerFactory.getLogger(FuncUnitStepDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param funcUnitStepEntity
     * @return
     */
    public String createFuncUnitStep(FuncUnitStepEntity funcUnitStepEntity) {
        return jpaTemplate.save(funcUnitStepEntity,String.class);
    }

    /**
     * 更新
     * @param funcUnitStepEntity
     */
    public void updateFuncUnitStep(FuncUnitStepEntity funcUnitStepEntity){
        jpaTemplate.update(funcUnitStepEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteFuncUnitStep(String id){
        jpaTemplate.delete(FuncUnitStepEntity.class,id);
    }

    public void deleteFuncUnitStep(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public FuncUnitStepEntity findFuncUnitStep(String id){
        return jpaTemplate.findOne(FuncUnitStepEntity.class,id);
    }

    /**
    * findAllFuncUnitStep
    * @return
    */
    public List<FuncUnitStepEntity> findAllFuncUnitStep() {
        return jpaTemplate.findAll(FuncUnitStepEntity.class);
    }

    public List<FuncUnitStepEntity> findFuncUnitStepList(List<String> idList) {
        return jpaTemplate.findList(FuncUnitStepEntity.class,idList);
    }

    public List<FuncUnitStepEntity> findFuncUnitStepList(FuncUnitStepQuery funcUnitStepQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(FuncUnitStepEntity.class)
                .eq("funcUnitId", funcUnitStepQuery.getFuncUnitId())
                .orders(funcUnitStepQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition,FuncUnitStepEntity.class);
    }

    public Pagination<FuncUnitStepEntity> findFuncUnitStepPage(FuncUnitStepQuery funcUnitStepQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(FuncUnitStepEntity.class)
                .eq("funcUnitId", funcUnitStepQuery.getFuncUnitId())
                .orders(funcUnitStepQuery.getOrderParams())
                .pagination(funcUnitStepQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,FuncUnitStepEntity.class);
    }
}