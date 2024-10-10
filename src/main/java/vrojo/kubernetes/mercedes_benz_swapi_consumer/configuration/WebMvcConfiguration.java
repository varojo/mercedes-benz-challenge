package vrojo.kubernetes.mercedes_benz_swapi_consumer.configuration;

import vrojo.kubernetes.mercedes_benz_swapi_consumer.web.LoggingInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author valentin.rojo
 */
@Component
@AllArgsConstructor
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private final LoggingInterceptor loggingInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor);
    }

}
