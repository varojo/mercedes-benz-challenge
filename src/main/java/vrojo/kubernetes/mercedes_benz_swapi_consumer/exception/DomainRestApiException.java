package vrojo.kubernetes.mercedes_benz_swapi_consumer.exception;

import vrojo.kubernetes.mercedes_benz_swapi_consumer.dto.AbstractDto;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.exception.error.RestApiErrorCode;
import org.springframework.http.HttpStatus;

/**
 * @author valentin.rojo
 */
public class DomainRestApiException extends CustomRestApiException{

    public DomainRestApiException(RestApiErrorCode code) {
        super(HttpStatus.CONFLICT, code);
    }

    public DomainRestApiException(RestApiErrorCode code, String customMessage) {
        super(HttpStatus.CONFLICT, code, customMessage);
    }
    public DomainRestApiException(RestApiErrorCode code, String customMessage, AbstractDto dto) {
        super(HttpStatus.CONFLICT, code, customMessage, dto);
    }
    public DomainRestApiException(RestApiErrorCode code, String customMessage, Throwable cause) {
        super(HttpStatus.CONFLICT, code, customMessage, cause);
    }
}