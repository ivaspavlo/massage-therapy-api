package pavlo.pro.massage.therapy.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pavlo.pro.massage.therapy.api.dto.model.UserDto;
import pavlo.pro.massage.therapy.api.model.ERole;
import pavlo.pro.massage.therapy.api.model.Role;
import pavlo.pro.massage.therapy.api.model.User;
import pavlo.pro.massage.therapy.api.payload.request.LoginRequest;
import pavlo.pro.massage.therapy.api.payload.response.JwtResponse;
import pavlo.pro.massage.therapy.api.repository.RoleRepository;
import pavlo.pro.massage.therapy.api.repository.UserRepository;
import pavlo.pro.massage.therapy.api.security.UserDetailsImpl;
import pavlo.pro.massage.therapy.api.security.jwt.JwtUtils;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());

        return ResponseEntity.ok(
            new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles)
        );
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                .badRequest()
                .body("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                .badRequest()
                .body("Error: Email is already in use!");
        }

        User user = new User()
                .setEmail(signUpRequest.getEmail())
                .setBirth(signUpRequest.getBirth())
                .setPhone(signUpRequest.getPhone())
                .setFirstName(signUpRequest.getFirstName())
                .setLastName(signUpRequest.getLastName())
                .setPassword(encoder.encode(signUpRequest.getPassword()));

        HashSet<Role> roles = new HashSet();
        roles.add(roleRepository.findByName(ERole.ROLE_USER));

        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }
}
