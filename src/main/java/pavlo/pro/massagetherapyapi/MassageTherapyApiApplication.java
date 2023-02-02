package pavlo.pro.massagetherapyapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class MassageTherapyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MassageTherapyApiApplication.class, args);
	}

}

// Jasypt instruction
// https://medium.com/developervisits/hiding-encrypting-database-password-in-the-application-properties-34d59fe104eb
// https://www.devglan.com/online-tools/jasypt-online-encryption-decryption