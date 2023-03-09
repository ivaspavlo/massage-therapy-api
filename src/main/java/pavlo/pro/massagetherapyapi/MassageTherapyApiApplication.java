package pavlo.pro.massagetherapyapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MassageTherapyApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(MassageTherapyApiApplication.class, args);
	}
}

// Terminal commands
// mvn spring-boot:run -Dspring-boot.run.profiles=dev
// mvn jasypt:encrypt -Djasypt.encryptor.password=...