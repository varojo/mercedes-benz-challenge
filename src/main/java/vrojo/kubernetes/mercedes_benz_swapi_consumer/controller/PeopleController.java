package vrojo.kubernetes.mercedes_benz_swapi_consumer.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.dto.internal.ListPeopleSvcOutputDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.constants.ControllerConstants;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.converter.PeopleConverter;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.service.PeopleService;

import java.time.LocalDateTime;

import static vrojo.kubernetes.mercedes_benz_swapi_consumer.constants.ControllerConstants.PARENT_MAPPING;


/**
 * @author valentin.rojo
 */
@RestController
@RequestMapping(value = PARENT_MAPPING) //Root
@Slf4j
@Tag(name = "SWAPI People Service", description = "Consume and transform data from Star Wars API (SWAPI) People endpoint")
public class PeopleController {

    private final PeopleService service;
    private final PeopleConverter converter;
    private final MeterRegistry meterRegistry;
    private final Counter listPeopleCounter;

    public PeopleController(final PeopleService service, final MeterRegistry meterRegistry) {
        this.service = service;
        this.meterRegistry = meterRegistry;

        this.listPeopleCounter = Counter.builder("http_requests_total")
                .tag("job", "mercedes_benz_swapi_consumer")
                .tag("verb", "GET")
                .tag("title", "The best or nothing")
                .description("Total number of requests to /people to get a sorted SWAPI people endpoint list")
                .register(meterRegistry);

        this.converter = new PeopleConverter();
    }

    @RequestScope
    @GetMapping(value = ControllerConstants.ListPeopleController.METHOD)
    @Operation(summary = "Sort and return data from SWAPI people endpoint. Sorting is done in ascending order.") // SWAGGER INFO
    public ResponseEntity<ListPeopleSvcOutputDto>
    listPeople(@Schema(description = "This field indicates if we desire the people list to be sorted in ascending or descending order. If not provided sorting will be done in ascending order.",
                              name = "order",
                              example = "ASC")
              @RequestParam(required=false) final String order){ // You can also set a default order value here
        log.info("REQUEST: order={}", order);
        this.listPeopleCounter.increment(); // PROMETHEUS CUSTOM METRIC

        final ListPeopleSvcOutputDto result = service.listPeople(converter.svcInputDto(order));

        return ResponseEntity
                .status(HttpStatusCode.valueOf(200))
                .body(result);
    }
}
