package io.thoughtware.testhubo;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({
        TestHuboServerAutoConfiguration.class
})
public @interface EnableTestHuboServer {
}
