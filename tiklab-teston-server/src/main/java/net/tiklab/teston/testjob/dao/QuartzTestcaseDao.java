package net.tiklab.teston.testjob.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.teston.testjob.entity.QuartzTestcaseEntity;
import net.tiklab.teston.testjob.model.QuartzTestcaseQuery;
import net.tiklab.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * QuartzTestcaseDao
 */
@Repository
public class QuartzTestcaseDao{

    private static Logger logger = LoggerFactory.getLogger(QuartzTestcaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param quartzTestcaseEntity
     * @return
     */
    public String createQuartzTestcase(QuartzTestcaseEntity quartzTestcaseEntity) {
        return jpaTemplate.save(quartzTestcaseEntity,String.class);
    }

    /**
     * 更新
     * @param quartzTestcaseEntity
     */
    public void updateQuartzTestcase(QuartzTestcaseEntity quartzTestcaseEntity){
        jpaTemplate.update(quartzTestcaseEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteQuartzTestcase(String id){
        jpaTemplate.delete(QuartzTestcaseEntity.class,id);
    }

    public void deleteQuartzTestcase(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public QuartzTestcaseEntity findQuartzTestcase(String id){
        return jpaTemplate.findOne(QuartzTestcaseEntity.class,id);
    }

    /**
    * findAllQuartzTestcase
    * @return
    */
    public List<QuartzTestcaseEntity> findAllQuartzTestcase() {
        return jpaTemplate.findAll(QuartzTestcaseEntity.class);
    }

    public List<QuartzTestcaseEntity> findQuartzTestcaseList(List<String> idList) {
        return jpaTemplate.findList(QuartzTestcaseEntity.class,idList);
    }

    public List<QuartzTestcaseEntity> findQuartzTestcaseList(QuartzTestcaseQuery quartzTestcaseQuery) {
        return jpaTemplate.findList(quartzTestcaseQuery, QuartzTestcaseEntity.class);
    }

    public Pagination<QuartzTestcaseEntity> findQuartzTestcasePage(QuartzTestcaseQuery quartzTestcaseQuery) {
        return jpaTemplate.findPage(quartzTestcaseQuery, QuartzTestcaseEntity.class);
    }

}