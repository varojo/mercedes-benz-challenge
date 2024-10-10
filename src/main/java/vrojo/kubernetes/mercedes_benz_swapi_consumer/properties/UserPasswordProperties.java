package vrojo.kubernetes.mercedes_benz_swapi_consumer.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author valentin.rojo
 */

@Getter
@Setter
@ConfigurationProperties("app.secret")
public class UserPasswordProperties {
    private String httpBasicUsers;
}
