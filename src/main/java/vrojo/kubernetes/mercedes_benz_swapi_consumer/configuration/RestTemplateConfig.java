package vrojo.kubernetes.mercedes_benz_swapi_consumer.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

import static vrojo.kubernetes.mercedes_benz_swapi_consumer.constants.AppConstants.RestTemplateProperties.REST_TEMPLATE_MILLIS_CONNECTION_TIMEOUT;
import static vrojo.kubernetes.mercedes_benz_swapi_consumer.constants.AppConstants.RestTemplateProperties.REST_TEMPLATE_MILLIS_READ_TIMEOUT;

/**
 * @author valentin.rojo
 */
@Configuration
public class RestTemplateConfig {

    private final RestTemplateBuilder restTemplateBuilder;

    public RestTemplateConfig(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Bean("basicRestTemplate")
    public RestTemplate
    basicRestTemplate(@Value(REST_TEMPLATE_MILLIS_CONNECTION_TIMEOUT) final Long millisConnectionTimeout,
                      @Value(REST_TEMPLATE_MILLIS_READ_TIMEOUT) final Long millisReadTimeout) {
        return restTemplateBuilder
                .setConnectTimeout(Duration.ofMillis(millisConnectionTimeout))
                .setReadTimeout(Duration.ofMillis(millisReadTimeout))
                .build();
    }
}
