package io.tiklab.testhubo;

import io.tiklab.testhubo.annotation.EnableTestHubo;
import io.tiklab.core.property.PropertyAndYamlSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * TestHuboApplication
 */
@SpringBootApplication
@EnableTestHubo
@EnableScheduling
@PropertySource(value = "classpath:application.yaml",factory = PropertyAndYamlSourceFactory.class)
public class TestHuboApplication {

    public static final Logger logger = LoggerFactory.getLogger(TestHuboApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TestHuboApplication.class, args);
    }

}
