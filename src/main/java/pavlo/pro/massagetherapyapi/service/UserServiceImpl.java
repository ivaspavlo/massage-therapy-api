package pavlo.pro.massagetherapyapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import pavlo.pro.massagetherapyapi.model.ERole;
import pavlo.pro.massagetherapyapi.model.Role;
import pavlo.pro.massagetherapyapi.model.User;
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
    public User signup(User userData) throws ResponseStatusException {
        Role userRole = roleRepository.findByName(ERole.ROLE_USER.toString());
        User user = userRepository.findByEmail(userData.getEmail());
        if (user == null) {
            user = new User()
                .setEmail(userData.getEmail())
                .setFirstName(userData.getFirstName())
                .setLastName(userData.getFirstName())
                .setRoles(new HashSet<>(Arrays.asList(userRole)));
            return userRepository.save(user);
        }
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "User with the email: " + userData.getEmail() + " already exists."
        );
    }

    @Override
    public User findUserByEmail(String email) throws ResponseStatusException {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        if (user.isPresent()) {
            return user.get();
        }
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "User with the email: " + email + " not found."
        );
    }

    @Override
    public User updateProfile(User userData) throws ResponseStatusException {
        Optional<User> user = Optional.ofNullable(
            userRepository.findByEmail(userData.getEmail())
        );
        if (user.isPresent()) {
            User userModel = user.get();
            userModel
                .setFirstName(userData.getFirstName())
                .setLastName(userData.getLastName());
            return userModel;
        }
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "User with the email: " + userData.getEmail() + " not found."
        );
    }

    @Override
    public User changePassword(User userData, String newPassword) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(userData.getEmail()));
        if (user.isPresent()) {
            User userModel = user.get();
            userModel.setPassword(passwordEncoder.encode(newPassword));
            return userModel;
        }
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "User with the email: " + userData.getEmail() + " not found."
        );
    }

}
