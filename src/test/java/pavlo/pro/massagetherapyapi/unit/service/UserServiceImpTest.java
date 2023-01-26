package pavlo.pro.massagetherapyapi.unit.service;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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

    static final String testEmail = "testEmail@mail.com";
    static final String testPwd = "testPwd";
    static final SignupReq mockSignupReq = new SignupReq(
            testEmail,
            testPwd,
            "testFirstName",
            "testLastName",
            "+43111222333444"
    );

    @Mock
    private PasswordEncoder mockPasswordEncoder;
    @Mock
    private RoleRepository mockRoleRepository;
    @Mock
    private UserRepository mockUserRepository;

    private UserServiceImpl userServiceIpml;
    private User mockUser;

    @BeforeEach
    public void setupTestData() {
        MockitoAnnotations.openMocks(this);

        userServiceIpml = new UserServiceImpl(
            mockPasswordEncoder,
            mockRoleRepository,
            mockUserRepository
        );

        mockUser = new User();
        mockUser.setEmail(testEmail);

        Role mockRole = new Role();
        mockRole.setName(ERole.ROLE_USER);

        when(mockUserRepository.save(ArgumentMatchers.any(User.class))).thenReturn(mockUser);
        when(mockRoleRepository.findByName(ERole.ROLE_USER.toString())).thenReturn(mockRole);
        when(mockPasswordEncoder.encode(testPwd)).thenReturn(testPwd);
    }

    @Test
    public void whenSignup_shouldReturnUser() {
        User created = userServiceIpml.signup(mockSignupReq);
        assertThat(created).isInstanceOf(User.class);
    }

    @Test(expected = ResponseStatusException.class)
    public void whenSignupWithTheSameEmail_shouldThrowResponseStatusException() {
        userServiceIpml.signup(mockSignupReq);
        // Mock the response from the repo
        when(mockUserRepository.findByEmail(testEmail)).thenReturn(mockUser);

        userServiceIpml.signup(mockSignupReq);
    }

    @Test()
    public void whenFindUserByEmail_shouldReturnCorrectUser() {
        // Mock the response from the repo
        when(mockUserRepository.findByEmail(testEmail)).thenReturn(mockUser);

        User foundUser = userServiceIpml.findUserByEmail(testEmail);
        assertThat(foundUser).isInstanceOf(User.class);
        assertThat(foundUser.getEmail()).isEqualTo(testEmail);
    }

    @Test(expected = RuntimeException.class)
    public void whenFindUserByEmail_shouldThrowRuntimeException() {
        User foundUser = userServiceIpml.findUserByEmail(testEmail);
    }

    @Test()
    public void whenChangePassword_shouldReturnUserWithNewPassword() {
        User testUser = new User();
        testUser.setEmail(testEmail);
        String testNewPassword = "testNewPassword";

        // Mock the response from the repo
        mockUser.setPassword(testNewPassword);
        when(mockUserRepository.save(ArgumentMatchers.any(User.class))).thenReturn(mockUser);

        assertThat(userServiceIpml.changePassword(testUser, testNewPassword).getPassword()).isEqualTo(testNewPassword);
    }

}
