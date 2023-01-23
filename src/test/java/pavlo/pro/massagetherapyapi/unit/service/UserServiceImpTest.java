package pavlo.pro.massagetherapyapi.unit.service;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;
import pavlo.pro.massagetherapyapi.dto.request.SignupReq;
import pavlo.pro.massagetherapyapi.model.Role;
import pavlo.pro.massagetherapyapi.model.User;
import pavlo.pro.massagetherapyapi.model.enums.ERole;
import pavlo.pro.massagetherapyapi.repository.RoleRepository;
import pavlo.pro.massagetherapyapi.repository.UserRepository;
import pavlo.pro.massagetherapyapi.service.impl.UserServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserServiceImpTest {
    @Mock
    private PasswordEncoder mockPasswordEncoder;
    @Mock
    private RoleRepository mockRoleRepository;
    @Mock
    private UserRepository mockUserRepository;
    private UserServiceImpl userServiceIpml;
    private String testEmail = "testEmail@mail.com";
    private String testPwd = "testPwd";
    private SignupReq mockSignupReq = new SignupReq(
        testEmail,
        testPwd,
        "testFirstName",
        "testLastName",
        "+43111222333444"
    );

    @BeforeEach
    public void setupTestData() {
        userServiceIpml = new UserServiceImpl(
            mockPasswordEncoder,
            mockRoleRepository,
            mockUserRepository
        );

        User mockUser = new User();
        mockUser.setEmail(testEmail);

        Role mockRole = new Role();
        mockRole.setName(ERole.ROLE_USER);

        when(mockUserRepository.save(ArgumentMatchers.any(User.class))).thenReturn(mockUser);
        when(mockUserRepository.findByEmail(testEmail)).thenReturn(mockUser);
        when(mockRoleRepository.findByName(ERole.ROLE_USER.toString())).thenReturn(mockRole);
        when(mockPasswordEncoder.encode(testPwd)).thenReturn(testPwd);
    }

    @Test
    public void whenSignup_shouldReturnUser() {
        User created = userServiceIpml.signup(mockSignupReq);
        assertThat(created).isInstanceOf(User.class);
    }

    @Test
    public void whenSignupWithTheSameEmail_shouldThrowResponseStatusException() {
        userServiceIpml.signup(mockSignupReq);
        try {
            userServiceIpml.signup(mockSignupReq);
        } catch (ResponseStatusException error) {
            assertThat(error).isInstanceOf(ResponseStatusException.class);
        }
    }

}
