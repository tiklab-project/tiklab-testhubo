package net.tiklab.teston.func.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"net.tiklab.teston.func"})
public class TestOnFuncAutoConfiguration {

    private static Logger logger = LoggerFactory.getLogger(TestOnFuncAutoConfiguration.class);
}
