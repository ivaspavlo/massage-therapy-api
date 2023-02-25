package pavlo.pro.massagetherapyapi.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public AppEnvironmentAware myEnvironmentAware() {
        return new AppEnvironmentAware();
    }

}
