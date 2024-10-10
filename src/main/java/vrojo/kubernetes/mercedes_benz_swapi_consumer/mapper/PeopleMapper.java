package vrojo.kubernetes.mercedes_benz_swapi_consumer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.dto.inbound.SWAPIPeopleResponseDto;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.dto.internal.ListPeopleRepoOutputDto;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.dto.internal.ListPeopleSvcOutputDto;

/**
 * @author valentin.rojo
 */
@Mapper
public interface PeopleMapper {

    PeopleMapper INSTANCE = Mappers.getMapper(PeopleMapper.class);

    ListPeopleRepoOutputDto.PersonDTO map(final SWAPIPeopleResponseDto.PersonDTO dto);
    ListPeopleSvcOutputDto.PersonDTO map(final ListPeopleRepoOutputDto.PersonDTO dto);
}
