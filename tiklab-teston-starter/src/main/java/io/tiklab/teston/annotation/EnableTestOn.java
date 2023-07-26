package io.tiklab.teston.annotation;

import io.tiklab.teston.config.TestOnAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({TestOnAutoConfiguration.class})
public @interface EnableTestOn {
}
