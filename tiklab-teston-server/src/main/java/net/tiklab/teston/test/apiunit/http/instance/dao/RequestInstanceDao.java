package net.tiklab.teston.test.apiunit.http.instance.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.teston.test.apiunit.http.instance.entity.RequestInstanceEntity;
import net.tiklab.teston.test.apiunit.http.instance.model.RequestInstanceQuery;
import net.tiklab.dal.jpa.JpaTemplate;
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
     * 创建
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
     * 删除
     * @param id
     */
    public void deleteRequestInstance(String id){
        jpaTemplate.delete(RequestInstanceEntity.class,id);
    }

    public void deleteRequestInstance(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public RequestInstanceEntity findRequestInstance(String id){
        return jpaTemplate.findOne(RequestInstanceEntity.class,id);
    }

    /**
    * findAllRequestInstance
    * @return
    */
    public List<RequestInstanceEntity> findAllRequestInstance() {
        return jpaTemplate.findAll(RequestInstanceEntity.class);
    }

    public List<RequestInstanceEntity> findRequestInstanceList(List<String> idList) {
        return jpaTemplate.findList(RequestInstanceEntity.class,idList);
    }

    public List<RequestInstanceEntity> findRequestInstanceList(RequestInstanceQuery requestInstanceQuery) {
        return jpaTemplate.findList(requestInstanceQuery, RequestInstanceEntity.class);
    }

    public Pagination<RequestInstanceEntity> findRequestInstancePage(RequestInstanceQuery requestInstanceQuery) {
        return jpaTemplate.findPage(requestInstanceQuery, RequestInstanceEntity.class);
    }
}