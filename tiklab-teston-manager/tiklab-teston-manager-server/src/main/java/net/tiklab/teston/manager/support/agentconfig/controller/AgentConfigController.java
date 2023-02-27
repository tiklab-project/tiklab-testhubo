package net.tiklab.teston.manager.support.agentconfig.controller;

import net.tiklab.core.Result;
import net.tiklab.core.page.Pagination;
import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.teston.manager.support.agentconfig.model.AgentConfig;
import net.tiklab.teston.manager.support.agentconfig.model.AgentConfigQuery;
import net.tiklab.teston.manager.support.agentconfig.service.AgentConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * AgentConfigController
 */
@RestController
@RequestMapping("/agentConfig")
@Api(name = "AgentConfigController",desc = "AgentConfigController")
public class AgentConfigController {

    private static Logger logger = LoggerFactory.getLogger(AgentConfigController.class);

    @Autowired
    private AgentConfigService agentConfigService;

    @RequestMapping(path="/createAgentConfig",method = RequestMethod.POST)
    @ApiMethod(name = "createAgentConfig",desc = "createAgentConfig")
    @ApiParam(name = "agentConfig",desc = "agentConfig",required = true)
    public Result<String> createAgentConfig(@RequestBody @NotNull @Valid AgentConfig agentConfig){
        String id = agentConfigService.createAgentConfig(agentConfig);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateAgentConfig",method = RequestMethod.POST)
    @ApiMethod(name = "updateAgentConfig",desc = "updateAgentConfig")
    @ApiParam(name = "agentConfig",desc = "agentConfig",required = true)
    public Result<Void> updateAgentConfig(@RequestBody @NotNull @Valid AgentConfig agentConfig){
        agentConfigService.updateAgentConfig(agentConfig);

        return Result.ok();
    }

    @RequestMapping(path="/deleteAgentConfig",method = RequestMethod.POST)
    @ApiMethod(name = "deleteAgentConfig",desc = "deleteAgentConfig")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteAgentConfig(@NotNull String id){
        agentConfigService.deleteAgentConfig(id);

        return Result.ok();
    }

    @RequestMapping(path="/findAgentConfig",method = RequestMethod.POST)
    @ApiMethod(name = "findAgentConfig",desc = "findAgentConfig")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<AgentConfig> findAgentConfig(@NotNull String id){
        AgentConfig agentConfig = agentConfigService.findAgentConfig(id);

        return Result.ok(agentConfig);
    }

    @RequestMapping(path="/findAllAgentConfig",method = RequestMethod.POST)
    @ApiMethod(name = "findAllAgentConfig",desc = "findAllAgentConfig")
    public Result<List<AgentConfig>> findAllAgentConfig(){
        List<AgentConfig> agentConfigList = agentConfigService.findAllAgentConfig();

        return Result.ok(agentConfigList);
    }

    @RequestMapping(path = "/findAgentConfigList",method = RequestMethod.POST)
    @ApiMethod(name = "findAgentConfigList",desc = "findAgentConfigList")
    @ApiParam(name = "agentConfigQuery",desc = "agentConfigQuery",required = true)
    public Result<List<AgentConfig>> findAgentConfigList(@RequestBody @Valid @NotNull AgentConfigQuery agentConfigQuery){
        List<AgentConfig> agentConfigList = agentConfigService.findAgentConfigList(agentConfigQuery);

        return Result.ok(agentConfigList);
    }

    @RequestMapping(path = "/findAgentConfigPage",method = RequestMethod.POST)
    @ApiMethod(name = "findAgentConfigPage",desc = "findAgentConfigPage")
    @ApiParam(name = "agentConfigQuery",desc = "agentConfigQuery",required = true)
    public Result<Pagination<AgentConfig>> findAgentConfigPage(@RequestBody @Valid @NotNull AgentConfigQuery agentConfigQuery){
        Pagination<AgentConfig> pagination = agentConfigService.findAgentConfigPage(agentConfigQuery);

        return Result.ok(pagination);
    }

}
