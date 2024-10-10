package vrojo.kubernetes.mercedes_benz_swapi_consumer.web;

import vrojo.kubernetes.mercedes_benz_swapi_consumer.logging.AnsiFormatter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

import static vrojo.kubernetes.mercedes_benz_swapi_consumer.constants.AppConstants.MDC_REQUEST_ID;

/**
 * @author valentin.rojo
 *
 */
@Order(value = Ordered.HIGHEST_PRECEDENCE+1)
@Component
@WebFilter(filterName = "MDCFilter", urlPatterns = "/*") // Match all requests
@Slf4j
public class MDCFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException,
                                                                    IOException {

        // GENERATE A NEW REQUEST ID (UUID), FOR THIS INCOMING REQUEST, BEFORE INVOKING THE SERVLET
        String currentRequestId = UUID.randomUUID().toString().replaceAll("-","");
        String colorizedCurrentRequestId = AnsiFormatter.randomlyColorizeString(currentRequestId);

        MDC.put(MDC_REQUEST_ID, colorizedCurrentRequestId);
        log.info("BEGIN ({})", request.getRequestURI());

        // ADD REQUEST-ID TO RESPONSE HEADER
        response.setHeader("Request-Id", currentRequestId);

        // INVOKE THE SERVLET (forward the the current request)
        filterChain.doFilter(request, response);

        log.info("END ({})", request.getRequestURI());

        // REMOVE REQUEST ID (UUID) AFTER INVOKING THE SERVLET
        MDC.remove(MDC_REQUEST_ID);

    }
}