package net.tiklab.teston.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"net.tiklab.teston.web"})
public class TestOnWebAutoConfiguration {

    private static Logger logger = LoggerFactory.getLogger(TestOnWebAutoConfiguration.class);
}
