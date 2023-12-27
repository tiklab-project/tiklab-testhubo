package io.thoughtware.teston.support.agentconfig.service;

import io.thoughtware.teston.support.agentconfig.model.AgentConfig;
import io.thoughtware.teston.support.agentconfig.model.AgentConfigQuery;
import io.thoughtware.teston.support.agentconfig.entity.AgentConfigEntity;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.teston.support.agentconfig.dao.AgentConfigDao;

import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;


/**
* agent配置 服务
*/
@Service
public class AgentConfigServiceImpl implements AgentConfigService {

    @Autowired
    AgentConfigDao agentConfigDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createAgentConfig(@NotNull @Valid AgentConfig agentConfig) {
        AgentConfigEntity agentConfigEntity = BeanMapper.map(agentConfig, AgentConfigEntity.class);

        agentConfigEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));


        return agentConfigDao.createAgentConfig(agentConfigEntity);
    }

    @Override
    public void updateAgentConfig(@NotNull @Valid AgentConfig agentConfig) {
        AgentConfigEntity agentConfigEntity = BeanMapper.map(agentConfig, AgentConfigEntity.class);

        agentConfigDao.updateAgentConfig(agentConfigEntity);
    }

    @Override
    public void deleteAgentConfig(@NotNull String id) {
        agentConfigDao.deleteAgentConfig(id);
    }

    @Override
    public AgentConfig findOne(String id) {
        AgentConfigEntity agentConfigEntity = agentConfigDao.findAgentConfig(id);

        AgentConfig agentConfig = BeanMapper.map(agentConfigEntity, AgentConfig.class);
        return agentConfig;
    }

    @Override
    public List<AgentConfig> findList(List<String> idList) {
        List<AgentConfigEntity> agentConfigEntityList =  agentConfigDao.findAgentConfigList(idList);

        List<AgentConfig> agentConfigList =  BeanMapper.mapList(agentConfigEntityList,AgentConfig.class);
        return agentConfigList;
    }

    @Override
    public AgentConfig findAgentConfig(@NotNull String id) {
        AgentConfig agentConfig = findOne(id);

        joinTemplate.joinQuery(agentConfig);

        return agentConfig;
    }

    @Override
    public List<AgentConfig> findAllAgentConfig() {
        List<AgentConfigEntity> agentConfigEntityList =  agentConfigDao.findAllAgentConfig();

        List<AgentConfig> agentConfigList =  BeanMapper.mapList(agentConfigEntityList,AgentConfig.class);

        joinTemplate.joinQuery(agentConfigList);

        return agentConfigList;
    }

    @Override
    public List<AgentConfig> findAgentConfigList(AgentConfigQuery agentConfigQuery) {
        List<AgentConfigEntity> agentConfigEntityList = agentConfigDao.findAgentConfigList(agentConfigQuery);

        List<AgentConfig> agentConfigList = BeanMapper.mapList(agentConfigEntityList,AgentConfig.class);

        joinTemplate.joinQuery(agentConfigList);

        return agentConfigList;
    }

    @Override
    public Pagination<AgentConfig> findAgentConfigPage(AgentConfigQuery agentConfigQuery) {
        Pagination<AgentConfigEntity>  pagination = agentConfigDao.findAgentConfigPage(agentConfigQuery);

        List<AgentConfig> agentConfigList = BeanMapper.mapList(pagination.getDataList(),AgentConfig.class);

        joinTemplate.joinQuery(agentConfigList);

        return PaginationBuilder.build(pagination,agentConfigList);
    }
}