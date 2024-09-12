package io.thoughtware.testhubo;


import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"io.thoughtware.testhubo"})
@ServletComponentScan({"io.thoughtware.testhubo"})
public class TestHuboServerAutoConfiguration {


}
