package pavlo.pro.massagetherapyapi.config;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

public class AppEnvironmentAware implements EnvironmentAware {
    private static Environment env = null;

    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("**** APPLICATION ACTIVE PROFILE ****");
        System.out.println(environment.getActiveProfiles());
    }
}
