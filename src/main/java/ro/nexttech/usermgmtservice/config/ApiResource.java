package ro.nexttech.usermgmtservice.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class ApiResource extends ResourceConfig {
    public ApiResource() {
        packages("ro.nexttech.usermgmtservice.api.endpoints");
    }
}
