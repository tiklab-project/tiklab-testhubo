package io.thoughtware.teston.config;

import io.thoughtware.dal.dsm.config.model.DsmConfig;
import io.thoughtware.dal.dsm.support.DsmConfigBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestOnDalAutoConfiguration {
    /**
     * 初始化dsm
     */
    @Bean
    DsmConfig initDsmConfig() {
        DsmConfig dsmConfig = DsmConfigBuilder.instance();
        //1.0.0
        dsmConfig.newVersion("1.0.0", new String[]{
                //PrivilegeDsm
                "privilege_1.0.0",
                //UserDsm
                "user_1.0.0",
                "userCe_1.0.0",
                //IntegrationDsm
                "tool_1.0.0",
                //LicenceDsm
                "app-authorization_1.0.0",
                //MessageDsm
                "message_1.0.0",
                //SecurityDsm
                "oplog_1.0.0",
                //TodoTaskDsm
                "todotask_1.0.0",
                "backups_1.0.0",

                //TestOn
                "teston_1.0.0",
                "teston-init_1.0.0",

                "teston-platform_1.0.0",

        });
        dsmConfig.newVersion("1.0.1", new String[]{
                "oplog_1.0.1",
                "message_1.0.1",
                "todotask_1.0.1",

                "teston_1.0.1",
                "teston-platform_1.0.1",
        });
        dsmConfig.newVersion("1.0.2", new String[]{
                "teston_1.0.2",
                "teston-platform_1.0.2",

                "oplog_1.0.2",
                "message_1.0.2",
        });
        dsmConfig.newVersion("1.0.3", new String[]{
                "teston_1.0.3",

                "message_1.0.3",
                "teston-platform_1.0.3",
        });
        dsmConfig.newVersion("1.0.4", new String[]{
                "teston_1.0.4",
        });
        dsmConfig.newVersion("1.0.5", new String[]{
                "teston_1.0.5",
        });
        dsmConfig.newVersion("1.0.6", new String[]{
                "teston_1.0.6",
        });
        dsmConfig.newVersion("1.0.7", new String[]{
                "teston_1.0.7",
        });
        dsmConfig.newVersion("1.0.8", new String[]{
                "teston_1.0.8",
        });
        dsmConfig.newVersion("1.0.9", new String[]{
                "teston_1.0.9",
        });
        dsmConfig.newVersion("1.0.10", new String[]{
                "teston_1.0.10",
        });
        dsmConfig.newVersion("1.0.11", new String[]{
                "teston_1.0.11",
        });
        dsmConfig.newVersion("1.0.12", new String[]{
                "teston_1.0.12",
        });


        return dsmConfig;
    }
}
