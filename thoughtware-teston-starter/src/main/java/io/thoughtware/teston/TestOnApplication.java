package io.thoughtware.teston;

import io.thoughtware.teston.annotation.EnableTestOn;
import io.thoughtware.core.property.PropertyAndYamlSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * TestOnApplication
 */
@SpringBootApplication
@EnableTestOn
@EnableScheduling
@PropertySource(value = "classpath:application.yaml",factory = PropertyAndYamlSourceFactory.class)
public class TestOnApplication {

    public static final Logger logger = LoggerFactory.getLogger(TestOnApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TestOnApplication.class, args);
    }

}
