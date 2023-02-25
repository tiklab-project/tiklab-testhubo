package net.tiklab.teston.test.api.http.unit.http.instance.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.teston.test.api.http.unit.http.instance.entity.ResponseInstanceEntity;
import net.tiklab.teston.test.api.http.unit.instance.model.ResponseInstanceQuery;
import net.tiklab.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ResponseInstanceDao
 */
@Repository
public class ResponseInstanceDao{

    private static Logger logger = LoggerFactory.getLogger(ResponseInstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param responseInstanceEntity
     * @return
     */
    public String createResponseInstance(ResponseInstanceEntity responseInstanceEntity) {
        return jpaTemplate.save(responseInstanceEntity,String.class);
    }

    /**
     * 更新
     * @param responseInstanceEntity
     */
    public void updateResponseInstance(ResponseInstanceEntity responseInstanceEntity){
        jpaTemplate.update(responseInstanceEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteResponseInstance(String id){
        jpaTemplate.delete(ResponseInstanceEntity.class,id);
    }

    public void deleteResponseInstance(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public ResponseInstanceEntity findResponseInstance(String id){
        return jpaTemplate.findOne(ResponseInstanceEntity.class,id);
    }

    /**
    * findAllResponseInstance
    * @return
    */
    public List<ResponseInstanceEntity> findAllResponseInstance() {
        return jpaTemplate.findAll(ResponseInstanceEntity.class);
    }

    public List<ResponseInstanceEntity> findResponseInstanceList(List<String> idList) {
        return jpaTemplate.findList(ResponseInstanceEntity.class,idList);
    }

    public List<ResponseInstanceEntity> findResponseInstanceList(ResponseInstanceQuery responseInstanceQuery) {
        return jpaTemplate.findList(responseInstanceQuery, ResponseInstanceEntity.class);
    }

    public Pagination<ResponseInstanceEntity> findResponseInstancePage(ResponseInstanceQuery responseInstanceQuery) {
        return jpaTemplate.findPage(responseInstanceQuery, ResponseInstanceEntity.class);
    }
}