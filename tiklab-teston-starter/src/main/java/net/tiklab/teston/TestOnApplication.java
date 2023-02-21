package net.tiklab.teston;

import net.tiklab.utils.property.PropertyAndYamlSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;


/**
 * TestOnApplication
 */
@SpringBootApplication
@EnableTestOn
@PropertySource(value = "classpath:application.yaml",factory = PropertyAndYamlSourceFactory.class)
public class TestOnApplication {

    public static final Logger logger = LoggerFactory.getLogger(TestOnApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TestOnApplication.class, args);
    }

}
