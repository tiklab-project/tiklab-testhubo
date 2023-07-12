package io.tiklab.teston;


import io.tiklab.dal.boot.starter.annotation.EnableDal;
import io.tiklab.dcs.boot.starter.EnableDcs;
import io.tiklab.dfs.boot.starter.EnableDfs;
import io.tiklab.dsm.boot.starter.EnableDsm;
import io.tiklab.dss.boot.starter.EnableDss;
import io.tiklab.eam.starter.EnableEam;
import io.tiklab.gateway.starter.EnableGateway;
import io.tiklab.integration.starter.EnableIntegration;
import io.tiklab.licence.starter.EnableLicenceServer;
import io.tiklab.messsage.starter.EnableMessage;
import io.tiklab.postgresql.EnablePostgresql;
import io.tiklab.postin.client.EnablePostInClient;
import io.tiklab.pluginx.starter.EnablePluginServer;
import io.tiklab.privilege.EnablePrivilegeServer;
import io.tiklab.rpc.boot.starter.EnableRpc;
import io.tiklab.security.stater.EnableSecurity;
import io.tiklab.teston.agent.EnableTestOnAgent;
import io.tiklab.todotask.stater.EnableTodoTask;
import io.tiklab.toolkit.boot.starter.EnableToolkit;
import io.tiklab.user.starter.EnableUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;


@Configuration
//platform
@EnableToolkit
@EnablePostgresql
@EnableDal
@EnableDsm
@EnableDfs
@EnableDcs
@EnableDss
@EnableRpc
@EnableGateway
@EnableMessage
//pcs
@EnableSecurity
@EnableIntegration
@EnableTodoTask
@EnableUser
@EnableEam
@EnablePluginServer
@EnableLicenceServer
@EnablePrivilegeServer

//other
@EnablePostInClient
@EnableTestOnAgent

@EnableTestOnServer
public class TestOnAutoConfiguration {

    private static Logger logger = LoggerFactory.getLogger(TestOnAutoConfiguration.class);
}
