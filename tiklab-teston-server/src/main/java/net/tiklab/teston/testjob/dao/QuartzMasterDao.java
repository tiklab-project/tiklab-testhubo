package net.tiklab.teston.testjob.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.teston.testjob.entity.QuartzMasterEntity;
import net.tiklab.teston.testjob.model.QuartzMasterQuery;
import net.tiklab.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * QuartzMasterDao
 */
@Repository
public class QuartzMasterDao{

    private static Logger logger = LoggerFactory.getLogger(QuartzMasterDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param quartzMasterEntity
     * @return
     */
    public String createQuartzMaster(QuartzMasterEntity quartzMasterEntity) {
        return jpaTemplate.save(quartzMasterEntity,String.class);
    }

    /**
     * 更新
     * @param quartzMasterEntity
     */
    public void updateQuartzMaster(QuartzMasterEntity quartzMasterEntity){
        jpaTemplate.update(quartzMasterEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteQuartzMaster(String id){
        jpaTemplate.delete(QuartzMasterEntity.class,id);
    }

    public void deleteQuartzMaster(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public QuartzMasterEntity findQuartzMaster(String id){
        return jpaTemplate.findOne(QuartzMasterEntity.class,id);
    }

    /**
    * findAllQuartzMaster
    * @return
    */
    public List<QuartzMasterEntity> findAllQuartzMaster() {
        return jpaTemplate.findAll(QuartzMasterEntity.class);
    }

    public List<QuartzMasterEntity> findQuartzMasterList(List<String> idList) {
        return jpaTemplate.findList(QuartzMasterEntity.class,idList);
    }

    public List<QuartzMasterEntity> findQuartzMasterList(QuartzMasterQuery quartzMasterQuery) {
        return jpaTemplate.findList(quartzMasterQuery, QuartzMasterEntity.class);
    }

    public Pagination<QuartzMasterEntity> findQuartzMasterPage(QuartzMasterQuery quartzMasterQuery) {
        return jpaTemplate.findPage(quartzMasterQuery, QuartzMasterEntity.class);
    }
}