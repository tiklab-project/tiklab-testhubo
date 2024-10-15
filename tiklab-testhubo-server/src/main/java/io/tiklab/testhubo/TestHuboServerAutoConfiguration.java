package io.tiklab.testhubo;


import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"io.tiklab.testhubo"})
@ServletComponentScan({"io.tiklab.testhubo"})
public class TestHuboServerAutoConfiguration {


}
