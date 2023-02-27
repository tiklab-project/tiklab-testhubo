package net.tiklab.teston.manager;

import net.tiklab.teston.apix.config.TestOnApixAutoConfiguration;
import net.tiklab.teston.app.TestOnAppAutoConfiguration;
import net.tiklab.teston.func.config.TestOnFuncAutoConfiguration;
import net.tiklab.teston.web.TestOnWebAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({
        TestOnServerAutoConfiguration.class,
        TestOnApixAutoConfiguration.class,
        TestOnAppAutoConfiguration.class,
        TestOnWebAutoConfiguration.class,
        TestOnFuncAutoConfiguration.class
})
public @interface EnableTestOnServer {
}
