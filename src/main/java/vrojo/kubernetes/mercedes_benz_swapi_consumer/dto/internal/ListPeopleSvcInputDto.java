package vrojo.kubernetes.mercedes_benz_swapi_consumer.dto.internal;

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
public class ListPeopleSvcInputDto  extends AbstractDto {

    private String order;
}
