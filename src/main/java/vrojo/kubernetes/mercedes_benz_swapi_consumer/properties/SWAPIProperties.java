package vrojo.kubernetes.mercedes_benz_swapi_consumer.properties;

import com.google.gson.Gson;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

/**
 * @author valentin.rojo
 */
@Getter
@ConfigurationProperties("app.swapi")
public class SWAPIProperties {

    private String serverURL;
    private Timeout timeout;
    private String peopleEndpointPath;

    public void setServerURL(final String serverURL) {
        if(this.serverURL != null){ return; } // This field is final on practical terms
        this.serverURL = serverURL;
    }

    public void setTimeout(final Timeout timeout) {
        if(this.timeout != null){ return; } // This field is final on practical terms
        this.timeout = timeout;
    }

    public void setPeopleEndpointPath(final String peopleEndpointPath) {
        if(this.peopleEndpointPath != null){ return; } // This field is final on practical terms
        this.peopleEndpointPath = peopleEndpointPath;
    }
    public record Timeout(Long connectionMillis, Long readMillis) {}
}
