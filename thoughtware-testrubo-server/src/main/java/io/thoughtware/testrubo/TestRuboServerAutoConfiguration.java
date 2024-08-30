package io.thoughtware.testrubo;


import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"io.thoughtware.testrubo"})
@ServletComponentScan({"io.thoughtware.testrubo"})
public class TestRuboServerAutoConfiguration {


}
