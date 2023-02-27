package net.tiklab.teston.manager;

import net.tiklab.dsm.annotation.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@SQL(modules={
        "teston",
        "testoneg",
        "testoninit"
},order = 20)
@ComponentScan({"net.tiklab.teston.manager"})
public class TestOnServerAutoConfiguration {

    private static Logger logger = LoggerFactory.getLogger(TestOnServerAutoConfiguration.class);

}
