package vrojo.kubernetes.mercedes_benz_swapi_consumer.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author valentin.rojo
 */
public class FailedAuthenticationException extends AuthenticationException {
    public FailedAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public FailedAuthenticationException(String msg) {
        super(msg);
    }
}
