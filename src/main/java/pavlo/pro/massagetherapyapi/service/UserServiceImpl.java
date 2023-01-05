package pavlo.pro.massagetherapyapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import pavlo.pro.massagetherapyapi.exception.AppException;
import pavlo.pro.massagetherapyapi.exception.EntityType;
import pavlo.pro.massagetherapyapi.exception.ExceptionType;
import pavlo.pro.massagetherapyapi.model.ERole;
import pavlo.pro.massagetherapyapi.model.Role;
import pavlo.pro.massagetherapyapi.model.User;
import pavlo.pro.massagetherapyapi.payload.request.UserSignupReq;
import pavlo.pro.massagetherapyapi.repository.RoleRepository;
import pavlo.pro.massagetherapyapi.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User signup(UserSignupReq signupRequest) throws ResponseStatusException {
        Role userRole = roleRepository.findByName(ERole.ROLE_USER.toString());
        User user = userRepository.findByEmail(signupRequest.getEmail());
        if (user == null) {
            user = new User()
                .setEmail(signupRequest.getEmail())
                .setFirstName(signupRequest.getFirstName())
                .setLastName(signupRequest.getFirstName())
                .setPassword(passwordEncoder.encode(signupRequest.getPassword()))
                .setRoles(new HashSet<>(Arrays.asList(userRole)));
            return userRepository.save(user);
        }
        throw exception(EntityType.USER, ExceptionType.DUPLICATE_ENTITY, signupRequest.getEmail());
    }

    @Override
    public User findUserByEmail(String email) throws RuntimeException {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        if (user.isPresent()) {
            return user.get();
        }
        throw exception(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND, email);
    }

    @Override
    public User updateProfile(User userData) throws RuntimeException {
        User user = findUserByEmail(userData.getEmail());
        user
            .setFirstName(userData.getFirstName())
            .setLastName(userData.getLastName());
        return userRepository.save(user);
    }

    @Override
    public User changePassword(User userData, String newPassword) {
        User user = findUserByEmail(userData.getEmail());
        user.setPassword(passwordEncoder.encode(newPassword));
        return userRepository.save(user);
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return AppException.throwException(entityType, exceptionType, args);
    }

}
