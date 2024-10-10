package vrojo.kubernetes.mercedes_benz_swapi_consumer.service;

import vrojo.kubernetes.mercedes_benz_swapi_consumer.dto.internal.ListPeopleSvcInputDto;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.dto.internal.ListPeopleSvcOutputDto;

/**
 * @author valentin.rojo <vrojobianchi@gmail.com>
 */
public interface PeopleService {
    ListPeopleSvcOutputDto listPeople(ListPeopleSvcInputDto dto);
}
