package io.tiklab.teston;


import io.tiklab.dsm.model.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"io.tiklab.teston"})
public class TestOnServerAutoConfiguration {

    private static Logger logger = LoggerFactory.getLogger(TestOnServerAutoConfiguration.class);

    @Bean
    SQL initSQL(){
        return new SQL(new String[]{
                "teston",
                "testoneg",
                "testoninit"
        }, 20);
    }

}
