package io.tiklab.testhubo.annotation;

import io.tiklab.testhubo.config.TestHuboAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({TestHuboAutoConfiguration.class})
public @interface EnableTestHubo {
}
