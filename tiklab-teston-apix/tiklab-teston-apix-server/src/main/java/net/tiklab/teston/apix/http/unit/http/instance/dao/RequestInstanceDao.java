package net.tiklab.teston.apix.http.unit.http.instance.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.teston.apix.http.unit.instance.model.RequestInstanceQuery;
import net.tiklab.teston.apix.http.unit.http.instance.entity.RequestInstanceEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RequestInstanceDao
 */
@Repository
public class RequestInstanceDao{

    private static Logger logger = LoggerFactory.getLogger(RequestInstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建请求数据实例
     * @param requestInstanceEntity
     * @return
     */
    public String createRequestInstance(RequestInstanceEntity requestInstanceEntity) {
        return jpaTemplate.save(requestInstanceEntity,String.class);
    }

    /**
     * 更新
     * @param requestInstanceEntity
     */
    public void updateRequestInstance(RequestInstanceEntity requestInstanceEntity){
        jpaTemplate.update(requestInstanceEntity);
    }

    /**
     * 删除请求数据实例
     * @param id
     */
    public void deleteRequestInstance(String id){
        jpaTemplate.delete(RequestInstanceEntity.class,id);
    }

    public void deleteRequestInstance(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找请求数据实例
     * @param id
     * @return
     */
    public RequestInstanceEntity findRequestInstance(String id){
        return jpaTemplate.findOne(RequestInstanceEntity.class,id);
    }

    /**
    * 查找所有请求数据实例
    * @return
    */
    public List<RequestInstanceEntity> findAllRequestInstance() {
        return jpaTemplate.findAll(RequestInstanceEntity.class);
    }

    public List<RequestInstanceEntity> findRequestInstanceList(List<String> idList) {
        return jpaTemplate.findList(RequestInstanceEntity.class,idList);
    }

    /**
     * 根据查询参数查询请求数据实例列表
     * @param requestInstanceQuery
     * @return
     */
    public List<RequestInstanceEntity> findRequestInstanceList(RequestInstanceQuery requestInstanceQuery) {
        return jpaTemplate.findList(requestInstanceQuery, RequestInstanceEntity.class);
    }

    /**
     * 根据查询参数按分页查询请求数据实例
     * @param requestInstanceQuery
     * @return
     */
    public Pagination<RequestInstanceEntity> findRequestInstancePage(RequestInstanceQuery requestInstanceQuery) {
        return jpaTemplate.findPage(requestInstanceQuery, RequestInstanceEntity.class);
    }
}