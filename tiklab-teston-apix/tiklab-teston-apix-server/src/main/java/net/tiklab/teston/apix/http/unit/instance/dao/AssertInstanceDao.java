package net.tiklab.teston.apix.http.unit.instance.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.teston.apix.http.unit.instance.model.AssertInstanceQuery;
import net.tiklab.teston.apix.http.unit.instance.entity.AssertInstanceEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 断言实例 舒服访问
 */
@Repository
public class AssertInstanceDao{

    private static Logger logger = LoggerFactory.getLogger(AssertInstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建断言实例
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
     * 删除断言实例
     * @param id
     */
    public void deleteAssertInstance(String id){
        jpaTemplate.delete(AssertInstanceEntity.class,id);
    }

    public void deleteAssertInstance(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找断言实例
     * @param id
     * @return
     */
    public AssertInstanceEntity findAssertInstance(String id){
        return jpaTemplate.findOne(AssertInstanceEntity.class,id);
    }

    /**
    * 查找所有断言实例
    * @return
    */
    public List<AssertInstanceEntity> findAllAssertInstance() {
        return jpaTemplate.findAll(AssertInstanceEntity.class);
    }

    public List<AssertInstanceEntity> findAssertInstanceList(List<String> idList) {
        return jpaTemplate.findList(AssertInstanceEntity.class,idList);
    }

    /**
     * 根据查询参数查询断言实例列表
     * @param assertInstanceQuery
     * @return
     */
    public List<AssertInstanceEntity> findAssertInstanceList(AssertInstanceQuery assertInstanceQuery) {
        return jpaTemplate.findList(assertInstanceQuery, AssertInstanceEntity.class);
    }

    /**
     * 根据查询参数按分页查询断言实例
     * @param assertInstanceQuery
     * @return
     */
    public Pagination<AssertInstanceEntity> findAssertInstancePage(AssertInstanceQuery assertInstanceQuery) {
        return jpaTemplate.findPage(assertInstanceQuery, AssertInstanceEntity.class);
    }
}