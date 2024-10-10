package vrojo.kubernetes.mercedes_benz_swapi_consumer.repository;


import com.google.gson.Gson;

import vrojo.kubernetes.mercedes_benz_swapi_consumer.converter.PeopleConverter;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.dto.inbound.SWAPIPeopleResponseDto;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.exception.DomainRestApiException;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.exception.error.RestApiErrorCode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.dto.internal.ListPeopleRepoOutputDto;
import vrojo.kubernetes.mercedes_benz_swapi_consumer.properties.SWAPIProperties;

import java.util.Optional;

/**
 * @author valentin.rojo
 */
@Repository
@Slf4j
@Validated
public class HttpPeopleRepository implements PeopleRepository {

    private final RestTemplate restTemplate;
    private final Gson gson;
    private final SWAPIProperties properties;

    private final PeopleConverter converter;

    public HttpPeopleRepository(final SWAPIProperties properties,
                                @Qualifier("basicRestTemplate") final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.properties = properties;

        this.gson = new Gson();
        this.converter = new PeopleConverter();
    }


    @Override
    public Optional<ListPeopleRepoOutputDto> listPeople() {
        log.info("PARAMETERS: {}", "none");

        // ENDPOINT URL
        final String endpoint
                = UriComponentsBuilder
                .fromHttpUrl(properties.getServerURL() + properties.getPeopleEndpointPath())
                .encode()
                .toUriString();

        log.info("SWAPI endpoint: {}", endpoint);

        // REQUEST HEADERS
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // SET ENTITY: REQUEST BODY + HEADERS
        final HttpEntity<Object> entity = new HttpEntity<>(headers);

        log.info("OUTBOUND HTTP-ENTITY BODY: {}", entity.getBody());

        // SEND REQUEST
        ResponseEntity<String> response;
        try {
            response =
            restTemplate.exchange(endpoint,
                    HttpMethod.GET,
                    entity,
                    String.class);
        }catch (HttpClientErrorException httpClientErrorException){
            log.error("EXCEPTION MSG: {}", httpClientErrorException.getMessage());
            log.error("EXCEPTION CAUSE: {}", httpClientErrorException.getCause() == null ? "unknown" : httpClientErrorException.getCause().toString());
            return Optional.empty();

        }catch (Exception e){
            log.error("EXCEPTION MSG: {}", e.getMessage());
            log.error("EXCEPTION CAUSE: {}", e.getCause() == null ? "unknown" : e.getCause().toString());
            throw new DomainRestApiException(RestApiErrorCode.THIRD_PARTY_REST_API_FAILED,
                                             String.format("Call to SWAPI failed: %s", e.getMessage()),
                                             e.getCause());
        }

        log.info("SWAPI API CALL HTTP STATUS CODE: {}", response.getStatusCode());

        // ERROR CONTROL
        if (!response.getStatusCode().is2xxSuccessful()) {
            final String errMsg = String.format("SWAPI failed to return the requested data. HTTP STATUS CODE: %s | BODY: %s", response.getStatusCode(), response.getBody());
            log.error(errMsg);
            throw new DomainRestApiException(RestApiErrorCode.THIRD_PARTY_REST_API_FAILED, errMsg);
        }

        SWAPIPeopleResponseDto responseDto;
        try{
            responseDto = gson.fromJson(response.getBody(), SWAPIPeopleResponseDto.class);
        }catch (Exception e){
            final String errMsg = String.format("Failed to convert JSON body response: %s", response.getBody());
            log.error(errMsg);
            throw new DomainRestApiException(RestApiErrorCode.FAILED_TO_CONVERT_JSON_TO_OBJECT, errMsg);
        }
        log.info("RESPONSE: {}", responseDto.toString()); // TODO: this line can be removed or commented out

        if(responseDto.getCount() == 0){
            log.error("SWAPI people endpoint returned no results");
            return Optional.empty();
        }
        // TODO: sort the results array here

        return Optional.of(converter.repoOutputDto(responseDto));
    }
}