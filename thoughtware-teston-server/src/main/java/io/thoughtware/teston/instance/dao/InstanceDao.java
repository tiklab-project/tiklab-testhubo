package io.thoughtware.teston.instance.dao;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.thoughtware.teston.instance.entity.InstanceEntity;
import io.thoughtware.teston.instance.model.InstanceQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 公共实例 数据访问
 */
@Repository
public class InstanceDao {

    private static Logger logger = LoggerFactory.getLogger(InstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建公共实例
     */
    public String createInstance(InstanceEntity instanceEntity) {
        return jpaTemplate.save(instanceEntity,String.class);
    }

    /**
     * 更新公共实例
     * @param instanceEntity
     */
    public void updateInstance(InstanceEntity instanceEntity){
        jpaTemplate.update(instanceEntity);
    }

    /**
     * 删除公共实例
     * @param id
     */
    public void deleteInstance(String id){
        jpaTemplate.delete(InstanceEntity.class,id);
    }

    public void deleteInstance(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找公共实例
     * @param id
     * @return
     */
    public InstanceEntity findInstance(String id){
        return jpaTemplate.findOne(InstanceEntity.class,id);
    }


    /**
    * 查找所有公共实例
    * @return
    */
    public List<InstanceEntity> findAllInstance() {
        return jpaTemplate.findAll(InstanceEntity.class);
    }

    public List<InstanceEntity> findInstanceList(List<String> idList) {
        return jpaTemplate.findList(InstanceEntity.class,idList);
    }

    /**
     * 查询公共实例列表
     * @param instanceQuery
     * @return
     */
    public List<InstanceEntity> findInstanceList(InstanceQuery instanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(InstanceEntity.class)
                .eq("repositoryId",instanceQuery.getRepositoryId())
                .eq("belongId",instanceQuery.getBelongId())
                .eq("type",instanceQuery.getType())
                .eq("createUser",instanceQuery.getCreateUser())
                .like("name",instanceQuery.getName())
                .in("type",instanceQuery.getTypeList())
                .orders(instanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, InstanceEntity.class);
    }

    /**
     * 按分页查询公共实例
     * @param instanceQuery
     * @return
     */
    public Pagination<InstanceEntity> findInstancePage(InstanceQuery instanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(InstanceEntity.class)
                .eq("repositoryId",instanceQuery.getRepositoryId())
                .eq("belongId",instanceQuery.getBelongId())
                .eq("type",instanceQuery.getType())
                .eq("createUser",instanceQuery.getCreateUser())
                .like("name",instanceQuery.getName())
                .in("type",instanceQuery.getTypeList())
                .pagination(instanceQuery.getPageParam())
                .orders(instanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, InstanceEntity.class);
    }


}