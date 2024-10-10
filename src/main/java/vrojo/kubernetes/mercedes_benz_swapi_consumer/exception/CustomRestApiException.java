package vrojo.kubernetes.mercedes_benz_swapi_consumer.exception;

import vrojo.kubernetes.mercedes_benz_swapi_consumer.dto.AbstractDto;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.exception.error.RestApiError;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.exception.error.RestApiErrorCode;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.exception.error.RestApiErrorDetail;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * @author valentin.rojo
 */
public class CustomRestApiException extends RuntimeException {

    protected RestApiError error;

    public RestApiError getError(){
        return this.error;
    }

    public CustomRestApiException(RestApiErrorCode code) {
        super(code.getMessage());
        error = new RestApiError(code);
    }

    public CustomRestApiException(HttpStatus status, RestApiErrorCode code) {
        super(code.getMessage());
        error = new RestApiError(status, code);
    }

    public CustomRestApiException(HttpStatus status, RestApiErrorCode code, String customMessage, Throwable cause) {
        super(code.getMessage(), cause);
        error = new RestApiError(status, code, customMessage);
    }

    public CustomRestApiException(RestApiErrorCode code, String customMessage) {
        super(code.getMessage());
        error = new RestApiError(code, customMessage);
    }

    public CustomRestApiException(HttpStatus status, RestApiErrorCode code, String customMessage) {
        super(code.getMessage());
        error = new RestApiError(status, code, customMessage);
    }
    public CustomRestApiException(HttpStatus status, RestApiErrorCode code, String customMessage, AbstractDto dto) {
        super(code.getMessage());
        error = new RestApiError(status, code, customMessage, dto);
    }

    @Override
    public String toString() {
        return "CustomRestApiException{" +
                "error=" + error +
                '}';
    }
}