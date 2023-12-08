package io.thoughtware.teston.test.common.stepassert.dao;

import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.teston.test.common.stepassert.entity.ElementAssertEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 元素类型的断言 数据访问
 */
@Repository
public class ElementAssertDao {

    private static Logger logger = LoggerFactory.getLogger(ElementAssertDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param elementAssertEntity
     * @return
     */
    public String createElementAssert(ElementAssertEntity elementAssertEntity) {
        return jpaTemplate.save(elementAssertEntity,String.class);
    }

    /**
     * 更新
     * @param elementAssertEntity
     */
    public void updateElementAssert(ElementAssertEntity elementAssertEntity){
        jpaTemplate.update(elementAssertEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteElementAssert(String id){
        jpaTemplate.delete(ElementAssertEntity.class,id);
    }

    public void deleteElementAssert(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找
     * @param id
     * @return
     */
    public ElementAssertEntity findElementAssert(String id){
        return jpaTemplate.findOne(ElementAssertEntity.class,id);
    }


 
}