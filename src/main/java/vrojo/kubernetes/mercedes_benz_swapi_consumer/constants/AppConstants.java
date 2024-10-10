package vrojo.kubernetes.mercedes_benz_swapi_consumer.constants;

/**
 * @author valentin.rojo
 */
public class AppConstants {
    public static final String MDC_REQUEST_ID = "requestId";

    public static class RestTemplateProperties {
        public static final String REST_TEMPLATE_MILLIS_CONNECTION_TIMEOUT ="${app.resttemplate.timeout.millisConnectionTimeout}";
        public static final String REST_TEMPLATE_MILLIS_READ_TIMEOUT ="${app.resttemplate.timeout.millisReadTimeout}";
    }
}
