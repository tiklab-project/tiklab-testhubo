package io.thoughtware.teston;


import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"io.thoughtware.teston"})
@ServletComponentScan({"io.thoughtware.teston"})
public class TestOnServerAutoConfiguration {


}
