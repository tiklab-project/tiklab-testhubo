package io.thoughtware.testrubo;

import io.thoughtware.testrubo.annotation.EnableTestRubo;
import io.thoughtware.core.property.PropertyAndYamlSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * TestRuboApplication
 */
@SpringBootApplication
@EnableTestRubo
@EnableScheduling
@PropertySource(value = "classpath:application.yaml",factory = PropertyAndYamlSourceFactory.class)
public class TestRuboApplication {

    public static final Logger logger = LoggerFactory.getLogger(TestRuboApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TestRuboApplication.class, args);
    }

}
