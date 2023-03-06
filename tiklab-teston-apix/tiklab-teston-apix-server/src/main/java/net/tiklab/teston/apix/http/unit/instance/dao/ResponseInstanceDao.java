package net.tiklab.teston.apix.http.unit.instance.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.teston.apix.http.unit.instance.model.ResponseInstanceQuery;
import net.tiklab.teston.apix.http.unit.instance.entity.ResponseInstanceEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 响应数据实例 数据访问
 */
@Repository
public class ResponseInstanceDao{

    private static Logger logger = LoggerFactory.getLogger(ResponseInstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建响应数据实例
     * @param responseInstanceEntity
     * @return
     */
    public String createResponseInstance(ResponseInstanceEntity responseInstanceEntity) {
        return jpaTemplate.save(responseInstanceEntity,String.class);
    }

    /**
     * 更新响应数据实例
     * @param responseInstanceEntity
     */
    public void updateResponseInstance(ResponseInstanceEntity responseInstanceEntity){
        jpaTemplate.update(responseInstanceEntity);
    }

    /**
     * 删除响应数据实例
     * @param id
     */
    public void deleteResponseInstance(String id){
        jpaTemplate.delete(ResponseInstanceEntity.class,id);
    }

    public void deleteResponseInstance(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找响应数据实例
     * @param id
     * @return
     */
    public ResponseInstanceEntity findResponseInstance(String id){
        return jpaTemplate.findOne(ResponseInstanceEntity.class,id);
    }

    /**
    * 查找所有响应数据实例
    * @return
    */
    public List<ResponseInstanceEntity> findAllResponseInstance() {
        return jpaTemplate.findAll(ResponseInstanceEntity.class);
    }

    public List<ResponseInstanceEntity> findResponseInstanceList(List<String> idList) {
        return jpaTemplate.findList(ResponseInstanceEntity.class,idList);
    }

    /**
     * 根据查询参数查询响应数据实例列表
     * @param responseInstanceQuery
     * @return
     */
    public List<ResponseInstanceEntity> findResponseInstanceList(ResponseInstanceQuery responseInstanceQuery) {
        return jpaTemplate.findList(responseInstanceQuery, ResponseInstanceEntity.class);
    }

    /**
     * 根据查询参数按分页查询响应数据实例
     * @param responseInstanceQuery
     * @return
     */
    public Pagination<ResponseInstanceEntity> findResponseInstancePage(ResponseInstanceQuery responseInstanceQuery) {
        return jpaTemplate.findPage(responseInstanceQuery, ResponseInstanceEntity.class);
    }
}