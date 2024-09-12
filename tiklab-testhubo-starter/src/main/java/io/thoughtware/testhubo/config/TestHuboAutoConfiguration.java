package io.thoughtware.testhubo.config;

import io.thoughtware.dal.boot.starter.annotation.EnableDal;
import io.thoughtware.dcs.boot.starter.annotation.EnableDcsClient;
import io.thoughtware.dcs.boot.starter.annotation.EnableDcsServer;
import io.thoughtware.dsm.boot.starter.annotation.EnableDsm;
import io.thoughtware.eam.boot.starter.annotation.EnableEamClient;
import io.thoughtware.eam.boot.starter.annotation.EnableEamServer;
import io.thoughtware.gateway.boot.starter.annotation.EnableGateway;
import io.thoughtware.licence.boot.starter.annotation.EnableLicenceServer;
import io.thoughtware.messsage.boot.starter.annotation.EnableMessageServer;
import io.thoughtware.openapi.boot.starter.annotation.EnableOpenApi;
import io.thoughtware.postgresql.EnablePostgresql;
import io.thoughtware.postin.client.EnablePostInClient;
import io.thoughtware.privilege.boot.starter.annotation.EnablePrivilegeServer;
import io.thoughtware.rpc.boot.starter.annotation.EnableRpc;
import io.thoughtware.security.boot.stater.annotation.EnableSecurityServer;
import io.thoughtware.testhubo.EnableTestHuboServer;
import io.thoughtware.toolkit.boot.starter.annotation.EnableToolkit;
import io.thoughtware.user.boot.starter.annotation.EnableUserClient;
import io.thoughtware.user.boot.starter.annotation.EnableUserServer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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
@EnableOpenApi
@EnableDsm
//
@EnableMessageServer
@EnableSecurityServer
@EnableUserServer
@EnableUserClient
@EnableEamServer
@EnableEamClient
@EnableLicenceServer
@EnablePrivilegeServer

//other
@EnablePostInClient

@EnableTestHuboServer
@ComponentScan(value = "io.thoughtware.testhubo")
public class TestHuboAutoConfiguration {

}
