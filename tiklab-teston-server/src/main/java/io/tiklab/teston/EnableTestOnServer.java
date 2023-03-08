package io.tiklab.teston;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({
        TestOnServerAutoConfiguration.class
})
public @interface EnableTestOnServer {
}
