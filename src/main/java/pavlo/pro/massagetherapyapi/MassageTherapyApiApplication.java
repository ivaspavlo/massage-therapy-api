package pavlo.pro.massagetherapyapi;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class MassageTherapyApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(MassageTherapyApiApplication.class, args);
	}
}

// Terminal commands
// mvn spring-boot:run -Dspring-boot.run.profiles=dev
// mvn jasypt:encrypt-value -Djasypt.encryptor.password=... -Djasypt.plugin.value="..."