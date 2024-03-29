package pavlo.pro.massagetherapyapi.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import pavlo.pro.massagetherapyapi.exception.AppException;
import pavlo.pro.massagetherapyapi.exception.ExceptionType;
import pavlo.pro.massagetherapyapi.model.enums.ERole;
import pavlo.pro.massagetherapyapi.model.Role;
import pavlo.pro.massagetherapyapi.model.User;
import pavlo.pro.massagetherapyapi.dto.request.SignupReq;
import pavlo.pro.massagetherapyapi.dto.request.UpdateUserReq;
import pavlo.pro.massagetherapyapi.repository.RoleRepository;
import pavlo.pro.massagetherapyapi.repository.UserRepository;
import pavlo.pro.massagetherapyapi.service.UserService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import static pavlo.pro.massagetherapyapi.exception.EntityType.USER;

@Slf4j
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
            throw AppException.buildException(USER, ExceptionType.DUPLICATE_ENTITY, signupRequest.getEmail());
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
            User created = userRepository.save(user);
            log.info("Created User with id: {}", created.getId());
            return created;
        } catch (Exception exception) {
            throw AppException.buildException(USER, ExceptionType.ENTITY_EXCEPTION);
        }
    }

    @Override
    public User findUserByEmail(String email) throws RuntimeException {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        if (user.isPresent()) {
            log.info("Found User by email: {}; with id: {}", email, user.get().getId());
            return user.get();
        }
        throw AppException.buildException(USER, ExceptionType.ENTITY_NOT_FOUND, email);
    }

    @Override
    public User updateUser(String userId, UpdateUserReq updateUserReq) throws RuntimeException {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw AppException.buildException(USER, ExceptionType.ENTITY_NOT_FOUND, userId);
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
        User updated = userRepository.save(user);
        log.info("Updated User with id: {}; with value: {}", userId, updateUserReq);
        return updated;
    }

    @Override
    public User changePassword(User userData, String newPassword) throws RuntimeException {
        User user = findUserByEmail(userData.getEmail());
        user.setPassword(passwordEncoder.encode(newPassword));
        User updated = userRepository.save(user);
        log.info("Updated password for User with id: {}", userData.getId());
        return updated;
    }

}
