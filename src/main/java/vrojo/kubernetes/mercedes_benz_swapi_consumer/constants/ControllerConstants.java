package vrojo.kubernetes.mercedes_benz_swapi_consumer.constants;

/**
 * @author valentin.rojo
 */
public class ControllerConstants {
    public static final String PARENT_MAPPING = "/swapi-consumer";

    public static class ListPeopleController {
        public static final String ENDPOINT = PARENT_MAPPING + ListPeopleController.METHOD;
        public static final String METHOD = "list";
    }
}
