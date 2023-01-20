package pavlo.pro.massagetherapyapi.unit.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;
import pavlo.pro.massagetherapyapi.dto.request.SignupReq;
import pavlo.pro.massagetherapyapi.model.User;
import pavlo.pro.massagetherapyapi.repository.UserRepository;
import pavlo.pro.massagetherapyapi.service.impl.UserServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImpTest {

    // https://blog.devgenius.io/how-to-code-in-java-spring-like-a-pro-dependency-injection-69249fdb68
    // https://spring.io/guides/gs/testing-web/
    // https://reflectoring.io/spring-boot-test/

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceIpml;

    @Test
    public void whenSignup_shouldReturnUser() {
        String name = "Test Name";

        SignupReq signupReq = new SignupReq();
        signupReq.setFirstName(name);

        User user = new User();
        user.setFirstName(name);

        when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(user);
        User created = userServiceIpml.signup(signupReq);

        assertThat(created.getFirstName()).isSameAs(user.getFirstName());
    }

    @Test
    public void whenSignup_shouldThrowResponseStatusException() {
        String name = "Test Name";

        SignupReq signupReq = new SignupReq();
        signupReq.setFirstName(name);

        User user = new User();
        user.setFirstName(name);

        when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(user);
        User created = userServiceIpml.signup(signupReq);

        try {
            userServiceIpml.signup(signupReq);
        } catch (ResponseStatusException error) {

        }
    }

}
