package pavlo.pro.massagetherapyapi.unit.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;
import pavlo.pro.massagetherapyapi.dto.request.SignupReq;
import pavlo.pro.massagetherapyapi.model.Role;
import pavlo.pro.massagetherapyapi.model.User;
import pavlo.pro.massagetherapyapi.model.enums.ERole;
import pavlo.pro.massagetherapyapi.repository.RoleRepository;
import pavlo.pro.massagetherapyapi.repository.UserRepository;
import pavlo.pro.massagetherapyapi.service.impl.UserServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
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
    private Role mockRole;

    @BeforeEach
    private void setupTestData() {
        userServiceIpml = new UserServiceImpl(
            mockPasswordEncoder,
            mockRoleRepository,
            mockUserRepository
        );

        mockUser = new User();
        mockUser.setEmail(testEmail);

        mockRole = new Role();
        mockRole.setName(ERole.ROLE_USER);
    }

    @DisplayName("When signup is triggered it should return a User")
    @Test
    public void whenSignup_shouldReturnUser() {
        when(mockUserRepository.save(ArgumentMatchers.any(User.class))).thenReturn(mockUser);
        User created = userServiceIpml.signup(mockSignupReq);
        assertThat(created).isInstanceOf(User.class);
    }

    @DisplayName("When user signups with the email that already exists should throw ResponseStatusException.")
    @Test()
    public void whenSignupWithTheSameEmail_Failure_shouldThrowRuntimeException() {
        RuntimeException thrown = assertThrows(
            RuntimeException.class,
            () -> {
                when(mockUserRepository.save(ArgumentMatchers.any(User.class))).thenReturn(mockUser);
                userServiceIpml.signup(mockSignupReq);
                when(mockUserRepository.findByEmail(testEmail)).thenReturn(mockUser);
                userServiceIpml.signup(mockSignupReq);
            },
    "Should throw when user with the same email already exists."
        );
        assertThat(thrown).isInstanceOf(RuntimeException.class);
    }

    @DisplayName("When user with the email exists it should return a user with the correct email.")
    @Test()
    public void whenFindUserByEmail_Successful_shouldReturnUser() {
        when(mockUserRepository.findByEmail(testEmail)).thenReturn(mockUser);

        User foundUser = userServiceIpml.findUserByEmail(testEmail);
        assertThat(foundUser).isInstanceOf(User.class);
        assertThat(foundUser.getEmail()).isEqualTo(testEmail);
    }

    @DisplayName("When user with the email doesn't exist it should throw RuntimeException.")
    @Test()
    public void whenFindUserByEmail_Failure_shouldThrowRuntimeException() {
        RuntimeException thrown = assertThrows(
            RuntimeException.class,
            () -> { userServiceIpml.findUserByEmail(testEmail); },
    "Expected to throw when email was not found."
        );
        assertThat(thrown).isInstanceOf(RuntimeException.class);
    }

    @DisplayName("When changePassword is triggered it should return a User with the new Password.")
    @Test()
    public void whenChangePassword_Successful_shouldReturnUserWithTheNewPassword() {
        String testNewPassword = "testNewPassword";

        when(mockUserRepository.findByEmail(testEmail)).thenReturn(mockUser);
        when(mockPasswordEncoder.encode(testNewPassword)).thenReturn(testNewPassword);
        when(mockUserRepository.save(ArgumentMatchers.any(User.class))).thenReturn(mockUser);

        assertThat(userServiceIpml.changePassword(mockUser, testNewPassword).getPassword()).isEqualTo(testNewPassword);
    }

    public void whenUpdateUser_Successful_shouldReturnUserWithUpdatedFields() {
        String testId = "testId";
        when(mockUserRepository.findById(testId)).thenReturn(Optional.of(mockUser));

    }

}
