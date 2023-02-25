package net.tiklab.testonapix.http.unit.http.instance.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.testonapix.http.unit.http.instance.entity.AssertInstanceEntity;
import net.tiklab.testonapix.http.unit.instance.model.AssertInstanceQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AssertInstanceDao
 */
@Repository
public class AssertInstanceDao{

    private static Logger logger = LoggerFactory.getLogger(AssertInstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param assertInstanceEntity
     * @return
     */
    public String createAssertInstance(AssertInstanceEntity assertInstanceEntity) {
        return jpaTemplate.save(assertInstanceEntity,String.class);
    }

    /**
     * 更新
     * @param assertInstanceEntity
     */
    public void updateAssertInstance(AssertInstanceEntity assertInstanceEntity){
        jpaTemplate.update(assertInstanceEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteAssertInstance(String id){
        jpaTemplate.delete(AssertInstanceEntity.class,id);
    }

    public void deleteAssertInstance(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public AssertInstanceEntity findAssertInstance(String id){
        return jpaTemplate.findOne(AssertInstanceEntity.class,id);
    }

    /**
    * findAllAssertInstance
    * @return
    */
    public List<AssertInstanceEntity> findAllAssertInstance() {
        return jpaTemplate.findAll(AssertInstanceEntity.class);
    }

    public List<AssertInstanceEntity> findAssertInstanceList(List<String> idList) {
        return jpaTemplate.findList(AssertInstanceEntity.class,idList);
    }

    public List<AssertInstanceEntity> findAssertInstanceList(AssertInstanceQuery assertInstanceQuery) {
        return jpaTemplate.findList(assertInstanceQuery, AssertInstanceEntity.class);
    }

    public Pagination<AssertInstanceEntity> findAssertInstancePage(AssertInstanceQuery assertInstanceQuery) {
        return jpaTemplate.findPage(assertInstanceQuery, AssertInstanceEntity.class);
    }
}