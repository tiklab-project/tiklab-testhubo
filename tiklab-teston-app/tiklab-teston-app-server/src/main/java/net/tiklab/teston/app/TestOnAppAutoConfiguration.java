package net.tiklab.teston.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"net.tiklab.teston.app"})
public class TestOnAppAutoConfiguration {

    private static Logger logger = LoggerFactory.getLogger(TestOnAppAutoConfiguration.class);
}
