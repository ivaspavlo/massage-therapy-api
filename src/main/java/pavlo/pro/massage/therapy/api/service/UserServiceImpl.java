package pavlo.pro.massage.therapy.api.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pavlo.pro.massage.therapy.api.dto.mapper.UserMapper;
import pavlo.pro.massage.therapy.api.dto.model.UserDto;
import pavlo.pro.massage.therapy.api.exception.AppException;
import pavlo.pro.massage.therapy.api.exception.EntityType;
import pavlo.pro.massage.therapy.api.exception.ExceptionType;
import pavlo.pro.massage.therapy.api.model.ERole;
import pavlo.pro.massage.therapy.api.model.Role;
import pavlo.pro.massage.therapy.api.model.User;
import pavlo.pro.massage.therapy.api.repository.RoleRepository;
import pavlo.pro.massage.therapy.api.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import static pavlo.pro.massage.therapy.api.exception.EntityType.USER;
import static pavlo.pro.massage.therapy.api.exception.ExceptionType.DUPLICATE_ENTITY;
import static pavlo.pro.massage.therapy.api.exception.ExceptionType.ENTITY_NOT_FOUND;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto signup(UserDto userDto) throws RuntimeException {
        Role userRole;
        Optional<User> existingUser = userRepository.findByEmail(userDto.getEmail());
        User newUser;
        if (existingUser == null) {
            if (userDto.isAdmin()) {
                userRole = roleRepository.findByName(ERole.ROLE_ADMIN);
            } else {
                userRole = roleRepository.findByName(ERole.ROLE_USER);
            }
            newUser = new User()
                .setEmail(userDto.getEmail())
                .setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()))
                .setRoles(new HashSet<>(Arrays.asList(userRole)))
                .setFirstName(userDto.getFirstName())
                .setLastName(userDto.getLastName())
                .setPhone(userDto.getPhone());
            return UserMapper.toUserDto(userRepository.save(newUser));
        }
        throw exception(USER, DUPLICATE_ENTITY, userDto.getEmail());
    }

    public UserDto findUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return modelMapper.map(user.get(), UserDto.class);
        }
        throw exception(USER, ENTITY_NOT_FOUND, email);
    }

    @Override
    public UserDto updateProfile(UserDto userDto) {
        Optional<User> user = userRepository.findByEmail(userDto.getEmail());
        if (user.isPresent()) {
            User userModel = user.get();
            userModel.setFirstName(userDto.getFirstName())
                    .setLastName(userDto.getLastName())
                    .setPhone(userDto.getPhone());
            return UserMapper.toUserDto(userRepository.save(userModel));
        }
        throw exception(USER, ENTITY_NOT_FOUND, userDto.getEmail());
    }

    @Override
    public UserDto changePassword(UserDto userDto, String newPassword) {
        Optional<User> user = userRepository.findByEmail(userDto.getEmail());
        if (user.isPresent()) {
            User userModel = user.get();
            userModel.setPassword(bCryptPasswordEncoder.encode(newPassword));
            return UserMapper.toUserDto(userRepository.save(userModel));
        }
        throw exception(USER, ENTITY_NOT_FOUND, userDto.getEmail());
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return AppException.throwException(entityType, exceptionType, args);
    }
}