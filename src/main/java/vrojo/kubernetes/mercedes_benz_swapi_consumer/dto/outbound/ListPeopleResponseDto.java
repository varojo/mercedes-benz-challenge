package vrojo.kubernetes.mercedes_benz_swapi_consumer.dto.outbound;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.dto.AbstractDto;

/**
 * @author valentin.rojo
 */
@Getter
@Setter
@Builder
public class ListPeopleResponseDto extends AbstractDto {
    // TODO: not currently in use, we return the SVC OUTPUT DTO straight away
}
