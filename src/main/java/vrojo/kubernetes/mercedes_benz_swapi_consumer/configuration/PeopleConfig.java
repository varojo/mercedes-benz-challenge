package vrojo.kubernetes.mercedes_benz_swapi_consumer.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.properties.SWAPIProperties;

/**
 * @author valentin.rojo
 */
@Configuration
public class PeopleConfig {
    @Bean
    public SWAPIProperties capProperties(){
        return new SWAPIProperties();
    }
}
