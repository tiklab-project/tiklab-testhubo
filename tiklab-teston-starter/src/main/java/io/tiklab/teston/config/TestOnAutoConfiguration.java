package io.tiklab.teston.config;

import io.tiklab.dal.boot.starter.annotation.EnableDal;
import io.tiklab.dcs.boot.starter.annotation.EnableDcsClient;
import io.tiklab.dcs.boot.starter.annotation.EnableDcsServer;
import io.tiklab.eam.boot.starter.annotation.EnableEamClient;
import io.tiklab.eam.boot.starter.annotation.EnableEamServer;
import io.tiklab.gateway.boot.starter.annotation.EnableGateway;
import io.tiklab.licence.boot.starter.annotation.EnableLicenceServer;
import io.tiklab.messsage.boot.starter.annotation.EnableMessageClient;
import io.tiklab.messsage.boot.starter.annotation.EnableMessageServer;
import io.tiklab.plugin.starter.EnablePluginServer;
import io.tiklab.postgresql.EnablePostgresql;
import io.tiklab.postin.client.EnablePostInClient;
import io.tiklab.privilege.boot.starter.annotation.EnablePrivilegeServer;
import io.tiklab.rpc.boot.starter.annotation.EnableRpc;
import io.tiklab.security.boot.stater.annotation.EnableSecurityClient;
import io.tiklab.security.boot.stater.annotation.EnableSecurityServer;
import io.tiklab.teston.EnableTestOnServer;
import io.tiklab.teston.agent.EnableTestOnAgent;
import io.tiklab.todotask.boot.stater.annotation.EnableTodoTaskClient;
import io.tiklab.todotask.boot.stater.annotation.EnableTodoTaskServer;
import io.tiklab.toolkit.boot.starter.annotation.EnableToolkit;
import io.tiklab.user.boot.starter.annotation.EnableUserClient;
import io.tiklab.user.boot.starter.annotation.EnableUserServer;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableToolkit
@EnablePostgresql
//@EnableDfsClient
//@EnableDfsServer
//@EnableDssClient
//@EnableDssServer
@EnableDcsClient
@EnableDcsServer
@EnableDal
@EnableRpc
@EnableGateway
//
@EnableMessageServer
@EnableMessageClient
@EnableSecurityServer
@EnableSecurityClient
@EnableTodoTaskServer
@EnableTodoTaskClient
@EnableUserServer
@EnableUserClient
@EnableEamServer
@EnableEamClient
@EnablePluginServer
@EnableLicenceServer
@EnablePrivilegeServer

//other
@EnablePostInClient
@EnableTestOnAgent

@EnableTestOnServer
public class TestOnAutoConfiguration {

}
