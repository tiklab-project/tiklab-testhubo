package io.thoughtware.testrubo.annotation;

import io.thoughtware.testrubo.config.TestRuboAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({TestRuboAutoConfiguration.class})
public @interface EnableTestRubo {
}
