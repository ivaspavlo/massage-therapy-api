package pavlo.pro.massagetherapyapi.config;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import java.util.Arrays;

public class AppEnvironmentAware implements EnvironmentAware {
    private static final Logger logger = LogManager.getLogger(AppEnvironmentAware.class);
    @Override
    public void setEnvironment(Environment environment) {
        String activeProfile = Arrays
            .stream(environment.getActiveProfiles())
            .filter(p -> p != null)
            .findAny()
            .orElse("default");
        logger.info("Application active profile -- {}", activeProfile);
    }
}
