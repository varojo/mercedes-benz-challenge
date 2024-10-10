package vrojo.kubernetes.mercedes_benz_swapi_consumer.exception.error;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.dto.AbstractDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestApiError {

    // Unix timestamp
    private long timestamp;

    @JsonIgnore
    private HttpStatus status;

    private RestApiErrorCode code;
    private String customMessage;
    private AbstractDto relevantDto;

    public RestApiError() {
        // Tiempo en formato UNIX en milisegundos
        this.timestamp = Instant.now().toEpochMilli();
    }

    public RestApiError(RestApiErrorCode code) {
        this();
        this.code = code;
    }

    public RestApiError(HttpStatus status, RestApiErrorCode code) {
        this(code);
        this.status = status;
    }

    public RestApiError(RestApiErrorCode code, String customMessage) {
        this(code);
        this.customMessage = customMessage;
    }
    public RestApiError(RestApiErrorCode code, String customMessage, AbstractDto relevantDto) {
        this(code);
        this.customMessage = customMessage;
        this.relevantDto = relevantDto;
    }

    public RestApiError(HttpStatus status, RestApiErrorCode code, String customMessage) {
        this(code, customMessage);
        this.status = status;
    }
    public RestApiError(HttpStatus status, RestApiErrorCode code, String customMessage, AbstractDto relevantDto) {
        this(code, customMessage, relevantDto);
        this.status = status;
    }

    @Override
    public String toString() {
        return  "{" +
                "\"timestamp\":" +"\""+ timestamp +"\","+
                "\"status\":" +"\""+ status +"\","+
                "\"restApiError\":" + code.toString() +","+
                "\"customMessage\":" +"\""+ customMessage +"\""+
                "}";
    }
}
