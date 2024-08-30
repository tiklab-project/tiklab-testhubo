package io.thoughtware.testrubo;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({
        TestRuboServerAutoConfiguration.class
})
public @interface EnableTestRuboServer {
}
