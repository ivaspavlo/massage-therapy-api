package pavlo.pro.massagetherapyapi.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import pavlo.pro.massagetherapyapi.exception.AppException;
import pavlo.pro.massagetherapyapi.exception.EntityType;
import pavlo.pro.massagetherapyapi.exception.ExceptionType;
import pavlo.pro.massagetherapyapi.model.enums.ERole;
import pavlo.pro.massagetherapyapi.model.Role;
import pavlo.pro.massagetherapyapi.model.User;
import pavlo.pro.massagetherapyapi.dto.request.SignupReq;
import pavlo.pro.massagetherapyapi.dto.request.UpdateUserReq;
import pavlo.pro.massagetherapyapi.repository.interfaces.RoleRepository;
import pavlo.pro.massagetherapyapi.repository.interfaces.UserRepository;
import pavlo.pro.massagetherapyapi.service.interfaces.UserService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import static pavlo.pro.massagetherapyapi.exception.EntityType.USER;

@Component
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    private UserRepository userRepository;

    @Override
    public User signup(SignupReq signupRequest) throws ResponseStatusException {
        User user = userRepository.findByEmail(signupRequest.getEmail());
        if (user != null) {
            throw exception(USER, ExceptionType.DUPLICATE_ENTITY, signupRequest.getEmail());
        }
        Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN.toString());
        user = new User()
            .setEmail(signupRequest.getEmail())
            .setFirstName(signupRequest.getFirstName())
            .setLastName(signupRequest.getFirstName())
            .setPhone(signupRequest.getPhone())
            .setPassword(passwordEncoder.encode(signupRequest.getPassword()))
            .setRoles(new HashSet<>(Arrays.asList(userRole)));
        try {
            return userRepository.save(user);
        } catch (Exception exception) {
            // TODO: implement logging functionality
            throw exception(USER, ExceptionType.ENTITY_EXCEPTION);
        }
    }

    @Override
    public User findUserByEmail(String email) throws RuntimeException {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        if (user.isPresent()) {
            return user.get();
        }
        throw exception(USER, ExceptionType.ENTITY_NOT_FOUND, email);
    }

    @Override
    public User updateUser(String userId, UpdateUserReq updateUserReq) throws RuntimeException {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw exception(USER, ExceptionType.ENTITY_NOT_FOUND, userId);
        }
        User user = userOptional.get();
        if (updateUserReq.getFirstName() != null) {
            user.setFirstName(updateUserReq.getFirstName());
        }
        if (updateUserReq.getLastName() != null) {
            user.setLastName(updateUserReq.getLastName());
        }
        if (updateUserReq.getPhone() != null) {
            user.setPhone(updateUserReq.getPhone());
        }
        return userRepository.save(user);
    }

    @Override
    public User changePassword(User userData, String newPassword) throws RuntimeException {
        User user = findUserByEmail(userData.getEmail());
        user.setPassword(passwordEncoder.encode(newPassword));
        return userRepository.save(user);
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return AppException.throwException(entityType, exceptionType, args);
    }

}
