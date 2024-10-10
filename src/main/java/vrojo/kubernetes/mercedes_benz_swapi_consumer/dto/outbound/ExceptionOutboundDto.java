package vrojo.kubernetes.mercedes_benz_swapi_consumer.dto.outbound;

import vrojo.kubernetes.mercedes_benz_swapi_consumer.dto.AbstractDto;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.exception.error.RestApiError;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

/**
 * @author valentin.rojo
 */
@Getter
@Builder
public class ExceptionOutboundDto extends AbstractDto {
    private RestApiError error;
    private String traceId;
    private Instant dateTime;
    private String apparentCause;
    private String rootCause;
}
