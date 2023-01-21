package pavlo.pro.massagetherapyapi.unit.service;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;
import pavlo.pro.massagetherapyapi.dto.request.SignupReq;
import pavlo.pro.massagetherapyapi.model.User;
import pavlo.pro.massagetherapyapi.repository.UserRepository;
import pavlo.pro.massagetherapyapi.service.impl.UserServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserServiceImpTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceIpml;

    private String testEmail = "testEmail@mail.com";
    private SignupReq testSignupReq = new SignupReq();
    private User testUser = new User();

    @BeforeEach
    public void setupTestData() {
        testSignupReq.setEmail(testEmail);
        testUser.setEmail(testEmail);
    }

    @Test
    public void whenSignup_shouldReturnUser() {
        when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(testUser);
        User created = userServiceIpml.signup(testSignupReq);
        assertThat(created.getFirstName()).isSameAs(testUser.getFirstName());
    }

    @Test
    public void whenSignupWithTheSameEmail_shouldThrowResponseStatusException() {
        when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(testUser);
        User created = userServiceIpml.signup(testSignupReq);

        when(userRepository.findByEmail(testUser.getEmail())).thenReturn(testUser);

        try {
            userServiceIpml.signup(testSignupReq);
        } catch (ResponseStatusException error) {
            assertThat(error).isNotNull();
        }
    }

}
