package net.tiklab.teston.manager.support.agentconfig.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.teston.manager.support.agentconfig.model.AgentConfig;
import net.tiklab.teston.manager.support.agentconfig.model.AgentConfigQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* AgentConfigService
*/
public interface AgentConfigService {

    /**
    * 创建
    * @param agentConfig
    * @return
    */
    String createAgentConfig(@NotNull @Valid AgentConfig agentConfig);

    /**
    * 更新
    * @param agentConfig
    */
    void updateAgentConfig(@NotNull @Valid AgentConfig agentConfig);

    /**
    * 删除
    * @param id
    */
    void deleteAgentConfig(@NotNull String id);

    AgentConfig findOne(@NotNull String id);

    List<AgentConfig> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    AgentConfig findAgentConfig(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<AgentConfig> findAllAgentConfig();

    /**
    * 查询列表
    * @param agentConfigQuery
    * @return
    */
    List<AgentConfig> findAgentConfigList(AgentConfigQuery agentConfigQuery);

    /**
    * 按分页查询
    * @param agentConfigQuery
    * @return
    */
    Pagination<AgentConfig> findAgentConfigPage(AgentConfigQuery agentConfigQuery);

}