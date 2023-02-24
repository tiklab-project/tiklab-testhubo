package net.tiklab.teston.support.agentconfig.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.support.agentconfig.entity.AgentConfigEntity;
import net.tiklab.teston.support.agentconfig.model.AgentConfigQuery;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AgentConfigDao
 */
@Repository
public class AgentConfigDao{

    private static Logger logger = LoggerFactory.getLogger(AgentConfigDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param agentConfigEntity
     * @return
     */
    public String createAgentConfig(AgentConfigEntity agentConfigEntity) {
        return jpaTemplate.save(agentConfigEntity,String.class);
    }

    /**
     * 更新
     * @param agentConfigEntity
     */
    public void updateAgentConfig(AgentConfigEntity agentConfigEntity){
        jpaTemplate.update(agentConfigEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteAgentConfig(String id){
        jpaTemplate.delete(AgentConfigEntity.class,id);
    }

    public void deleteAgentConfig(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public AgentConfigEntity findAgentConfig(String id){
        return jpaTemplate.findOne(AgentConfigEntity.class,id);
    }

    /**
    * findAllAgentConfig
    * @return
    */
    public List<AgentConfigEntity> findAllAgentConfig() {
        return jpaTemplate.findAll(AgentConfigEntity.class);
    }

    public List<AgentConfigEntity> findAgentConfigList(List<String> idList) {
        return jpaTemplate.findList(AgentConfigEntity.class,idList);
    }

    public List<AgentConfigEntity> findAgentConfigList(AgentConfigQuery agentConfigQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AgentConfigEntity.class)
                .eq("repositoryId", agentConfigQuery.getRepositoryId())
                .orders(agentConfigQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,AgentConfigEntity.class);
    }

    public Pagination<AgentConfigEntity> findAgentConfigPage(AgentConfigQuery agentConfigQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AgentConfigEntity.class)
                .eq("repositoryId", agentConfigQuery.getRepositoryId())
                .orders(agentConfigQuery.getOrderParams())
                .pagination(agentConfigQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,AgentConfigEntity.class);
    }
}