package vrojo.kubernetes.mercedes_benz_swapi_consumer.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.converter.PeopleConverter;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.dto.internal.ListPeopleRepoOutputDto;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.dto.internal.ListPeopleSvcInputDto;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.dto.internal.ListPeopleSvcOutputDto;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.exception.DomainRestApiException;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.repository.PeopleRepository;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.service.PeopleService;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.exception.error.RestApiErrorCode;

import java.util.Optional;

/**
 * @author valentin.rojo
 */
@Service
@Slf4j
public class PeopleServiceImpl implements PeopleService {
    private final PeopleRepository peopleRepository;
    private final PeopleConverter converter;

    public PeopleServiceImpl(final PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
        this.converter = new PeopleConverter();
    }

    @Override
    public ListPeopleSvcOutputDto listPeople(final ListPeopleSvcInputDto dto) {
        log.info("PARAMETERS: {}", dto.toString());

        Optional<ListPeopleRepoOutputDto> listPeople = peopleRepository.listPeople();
        if(listPeople.isEmpty()){
            final String errMsg = "Failed to retrieve the list of people. Repository returned no results";
            log.error(errMsg);

            throw new DomainRestApiException(RestApiErrorCode.OBJECT_NOT_FOUND, errMsg);
        }
        log.info("People list found!");

        return converter.repoOutputDto(listPeople.get(), dto.getOrder());
    }
}
