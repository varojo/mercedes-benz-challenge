package vrojo.kubernetes.mercedes_benz_swapi_consumer.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.StreamSupport;

/**
 * @author valentin.rojo
 *
 *     Prints the app's properties (environment variables) to standart output
 */
@Component
@Slf4j
public class PropertyLoggerHandler {

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {

        final Environment env = event.getApplicationContext().getEnvironment();
        log.info("====== BEGIN - Environment and configuration ======");
        log.info("IMPORTANT NOTICE! Be sure you exclude sensible information from this logger!");
        log.info("Active profiles: {}", Arrays.toString(env.getActiveProfiles()));
        final MutablePropertySources sources = ((AbstractEnvironment) env).getPropertySources();
        StreamSupport.stream(sources.spliterator(), false)
                .filter(ps -> ps instanceof EnumerablePropertySource)
                .map(ps -> ((EnumerablePropertySource) ps).getPropertyNames())
                .flatMap(Arrays::stream)
                .distinct()
                .filter( prop -> !(prop.contains("credentials") || prop.contains("CREDENTIALS") ||
                                   prop.contains("password") || prop.contains("PASSWORD") ||
                                   prop.contains("secret") || prop.contains("SECRET")) )
                .forEach(prop -> log.info("{}: {}", prop, env.getProperty(prop)));
        log.info("IMPORTANT NOTICE! Be sure you exclude sensible information from this logger!");
        log.info("====== END - Environment and configuration ======");
    }
}
