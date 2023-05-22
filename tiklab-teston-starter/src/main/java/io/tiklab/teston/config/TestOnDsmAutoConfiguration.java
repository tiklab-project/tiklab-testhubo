package io.tiklab.teston.config;

import io.tiklab.dsm.model.DsmConfig;
import io.tiklab.dsm.support.DsmConfigBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestOnDsmAutoConfiguration {
    /**
     * 初始化dsm
     */
    @Bean
    DsmConfig initDsmConfig() {
        DsmConfig dsmConfig = DsmConfigBuilder.instance();
        //1.0.0
        dsmConfig.newVersion("1.0.0", new String[]{
                //UserDsm
                "dmprivilege_1.0.0_ddl",
                "dmuser_1.0.0_ddl",
                "privilege_1.0.0_ddl",
                "user_1.0.0_ddl",
                "userCe_1.0.0_ddl",
                "privilege_1.0.0_dml",
                "user_1.0.0_dml",
                "userCe_1.0.0_dml",
                //IntegrationDsm
                "tool_1.0.0_ddl",
                //LicenceDsm
                "app-authorization_1.0.0_ddl",
                "app-authorization_1.0.0_dml",
                //MessageDsm
                "message_1.0.0_ddl",
                "message_1.0.0_dml",
                //SecurityDsm
                "oplog_1.0.0_ddl",
                "oplog_1.0.0_dml",
                //TodoTaskDsm
                "todotask_1.0.0_ddl",
                "todotask_1.0.0_dml",


                //TestOn
                "teston_1.0.0_ddl",
                "teston-init_1.0.0_dml",

                "teston-platform_1.0.0_dml",

        });
        dsmConfig.newVersion("1.0.1", new String[]{
                "user_1.0.1_dml",
                "oplog_1.0.1_dml",
                "teston_1.0.1_dml",
        });
        dsmConfig.newVersion("1.0.2", new String[]{
                "oplog_1.0.2_dml",
                "teston_1.0.2_dml",
        });
        return dsmConfig;
    }
}
