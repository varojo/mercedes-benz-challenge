package vrojo.kubernetes.mercedes_benz_swapi_consumer.repository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.dto.internal.ListPeopleRepoOutputDto;

import java.util.Optional;

/**
 * @author valentin.rojo
 */
@Validated
public interface PeopleRepository {
    public Optional<ListPeopleRepoOutputDto> listPeople();
}
