package vrojo.kubernetes.mercedes_benz_swapi_consumer.handler;

import vrojo.kubernetes.mercedes_benz_swapi_consumer.dto.outbound.ExceptionOutboundDto;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.exception.DomainRestApiException;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.exception.error.RestApiError;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.exception.error.RestApiErrorCode;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.logging.AnsiFormatter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.Arrays;

import static vrojo.kubernetes.mercedes_benz_swapi_consumer.constants.AppConstants.MDC_REQUEST_ID;

/**
 * @author valentin.rojo
 */
@ControllerAdvice
@Slf4j
public class RestApiExceptionHandler {

    @ExceptionHandler({DomainRestApiException.class})
    public ResponseEntity<ExceptionOutboundDto> handleDomainRestApiException(final DomainRestApiException e) {
        final String traceId = AnsiFormatter.removeAnsiColoringFromRequestId(MDC.get(MDC_REQUEST_ID));

        Throwable rootCause = getRootCause(e);

        final ExceptionOutboundDto exceptionOutboundDto = ExceptionOutboundDto.builder()
                                                      .error(e.getError())
                                                      .traceId(traceId)
                                                      .dateTime(Instant.ofEpochMilli(e.getError().getTimestamp()))
                                                      .apparentCause(e.getCause() == null ? "unknown" : e.getCause().toString())
                                                      .rootCause(rootCause == null ? "unknown" : rootCause.toString())
                                                      .build();

        log.error(exceptionOutboundDto.toString());
        log.error("EXCEPTION STACK TRACE: {}", Arrays.toString(e.getStackTrace()));
        log.error("EXCEPTION CAUSE: {}", exceptionOutboundDto.getApparentCause());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionOutboundDto);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ExceptionOutboundDto> handleRuntimeException(final RuntimeException e) {
        final RestApiError restApiError = new RestApiError(HttpStatus.CONFLICT, RestApiErrorCode.UNCONTROLLED_EXCEPTION, e.getMessage());
        final String traceId = AnsiFormatter.removeAnsiColoringFromRequestId(MDC.get(MDC_REQUEST_ID));

        Throwable rootCause = getRootCause(e);

        final ExceptionOutboundDto exceptionOutboundDto = ExceptionOutboundDto.builder()
                                                      .error(restApiError)
                                                      .traceId(traceId)
                                                      .dateTime(Instant.ofEpochMilli(restApiError.getTimestamp()))
                                                      .apparentCause(e.getCause() == null ? "unknown" : e.getCause().toString())
                                                      .rootCause(rootCause == null ? "unknown" : rootCause.toString())
                                                      .build();

        log.error(exceptionOutboundDto.toString());
        log.error("EXCEPTION MSG: {}", e.getMessage());
        log.error("EXCEPTION STACK TRACE: {}", Arrays.toString(e.getStackTrace()));
        log.error("EXCEPTION CAUSE: {}", exceptionOutboundDto.getApparentCause());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionOutboundDto);
    }

    private Throwable getRootCause(final Exception e){
        Throwable rootCause = e.getCause();
        while (rootCause != null){
            if(rootCause.getCause() == null) break;
            else rootCause = rootCause.getCause();
        }
        return rootCause;
    }

}
