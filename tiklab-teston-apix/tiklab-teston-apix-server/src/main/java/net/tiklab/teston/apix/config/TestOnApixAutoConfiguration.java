package net.tiklab.teston.apix.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"net.tiklab.teston.apix"})
public class TestOnApixAutoConfiguration {

    private static Logger logger = LoggerFactory.getLogger(TestOnApixAutoConfiguration.class);
}
