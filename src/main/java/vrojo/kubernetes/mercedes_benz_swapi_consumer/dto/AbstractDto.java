package vrojo.kubernetes.mercedes_benz_swapi_consumer.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.exception.DomainRestApiException;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.exception.error.RestApiErrorCode;

/**
 * @author valentin.rojo
 */
public abstract class AbstractDto {

    public AbstractDto() {
    }

    /**
     * Object as JSON string, preceded by the DTO class simple name.
     * @return ClassSimpleName: {"key1": "value1", ..., "keyN": "valueN"}
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() +": "+ this.toJson();
    }

    /**
     * Object as JSON string.
     * WARNING! If called over and over again it ruins the JSON formatting
     *
     * @return {"key1": "value1", ..., "keyN": "valueN"}
     */
    public String toJson() {
        // FIXME: if we call this toJson() method over and over again it ruins the JSON formatting
        // Initiate GSON = new GsonBuilder().create();

        // TODO: (NECESSARY?)=> write custom serializer for SensibleInformationDto (https://www.baeldung.com/jackson-custom-serialization)
        ObjectMapper ow = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        try {
            String json = ow.writeValueAsString(this);

            return json;
        } catch (JsonProcessingException e) {
            throw new DomainRestApiException(RestApiErrorCode.FAILED_TO_CONVERT_DTO_TO_JSON, e.getMessage());
        }
    }
}
