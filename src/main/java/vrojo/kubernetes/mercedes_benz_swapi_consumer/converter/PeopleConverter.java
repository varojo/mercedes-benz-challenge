package vrojo.kubernetes.mercedes_benz_swapi_consumer.converter;

import lombok.extern.slf4j.Slf4j;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.dto.inbound.SWAPIPeopleResponseDto;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.dto.internal.ListPeopleRepoInputDto;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.dto.internal.ListPeopleRepoOutputDto;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.dto.internal.ListPeopleSvcInputDto;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.dto.internal.ListPeopleSvcOutputDto;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.exception.DomainRestApiException;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.exception.error.RestApiErrorCode;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.mapper.PeopleMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author valentin.rojo
 *
 *  README
 *
 *  The converter is a nice piece of code to improve the DECOUPLING principle
 *  If we do not care so much about decoupling and instead we want to improve performance,
 *  then we can disregard the CONVERTER and the MAPPER and use only ONE DTO
 *  for the whole process, but that is up for the system requirements to decide.
 */
@Slf4j
public class PeopleConverter {

    // REQUEST TO SVC
    public ListPeopleSvcInputDto svcInputDto(final String order){
        if(order == null || order.equals("")){
            log.warn("Order not provided. Setting default order ASC");
            return ListPeopleSvcInputDto.builder()
                    .order("ASC")
                    .build();
        }

        final boolean isASCorDESC = order.equals("ASC") || order.equals("DESC");
        if(!isASCorDESC){
            String errMsg = String.format("Value %s is not allowed. Value must be either 'ASC' or 'DESC'. You can also not provide it at all.", order);
            log.error(errMsg);

            throw new DomainRestApiException(RestApiErrorCode.VALUE_NOT_ALLOWED, errMsg);
        }

        return ListPeopleSvcInputDto.builder()
            .order(order)
            .build();
    }

    // SVC TO REPO
    // TODO: not used
    public ListPeopleRepoInputDto repoInputDto(final ListPeopleSvcInputDto dto){

        return ListPeopleRepoInputDto.builder()
                .order(dto.getOrder())
                .build();
    }

    // INBOUND DTO to OUTPUT REPO DTO
    public ListPeopleRepoOutputDto repoOutputDto(final SWAPIPeopleResponseDto dto){
        return ListPeopleRepoOutputDto.builder()
                .count(dto.getCount())
                .next(dto.getNext())
                .previous(dto.getPrevious())
                .results(dto.getResults().stream().map(PeopleMapper.INSTANCE::map)
                        .collect(Collectors.toList()))
                .build();
    }

    // OUTPUT REPO DTO to OUTPUT SVC DTO
    public ListPeopleSvcOutputDto repoOutputDto(final ListPeopleRepoOutputDto dto, final String order){

        if(order.equals("ASC")){
            log.info("Reordering people by name asc");
            dto.setResults(this.sortPersonListByNameAsc(dto.getResults()));
        }else{
            log.info("Reordering people by name desc");
            dto.setResults(this.sortPersonListByNameDesc(dto.getResults()));
        }

        return ListPeopleSvcOutputDto.builder()
                .count(dto.getCount())
                .next(dto.getNext())
                .previous(dto.getPrevious())
                .results(dto.getResults().stream().map(PeopleMapper.INSTANCE::map)
                        .collect(Collectors.toList()))
                .build();
    }

    public List<ListPeopleRepoOutputDto.PersonDTO> sortPersonListByNameAsc(List<ListPeopleRepoOutputDto.PersonDTO> list){
        return list.stream().sorted((o1, o2) -> o1.getName().compareTo(o2.getName())).collect(Collectors.toList());
    }
    public List<ListPeopleRepoOutputDto.PersonDTO> sortPersonListByNameDesc(List<ListPeopleRepoOutputDto.PersonDTO> list){
        return list.stream().sorted((o1, o2) -> o2.getName().compareTo(o1.getName())).collect(Collectors.toList());
    }
}
