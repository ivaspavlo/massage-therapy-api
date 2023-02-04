package pavlo.pro.massagetherapyapi.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class AppConfig {

    @Value("${spring.profiles.active}")
    private String activeProfiles;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @PostConstruct
    public void printConfigValues() {
        System.out.println("**** APPLICATION ACTIVE PROFILE ****");
        System.out.println(activeProfiles);
    }

}
