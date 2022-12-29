package pavlo.pro.massagetherapyapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pavlo.pro.massagetherapyapi.model.User;
import pavlo.pro.massagetherapyapi.model.Role;
import pavlo.pro.massagetherapyapi.payload.request.LoginRequest;
import pavlo.pro.massagetherapyapi.payload.request.UserSignupRequest;
import pavlo.pro.massagetherapyapi.payload.response.JwtResponse;
import pavlo.pro.massagetherapyapi.payload.response.Response;
import pavlo.pro.massagetherapyapi.repository.UserRepository;
import pavlo.pro.massagetherapyapi.security.JwtUtils;
import pavlo.pro.massagetherapyapi.service.UserService;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/auth/signup")
    public Response signup(@RequestBody @Valid UserSignupRequest signupRequest) {
        return Response.ok().setPayload(userService.signup(signupRequest));
    }

//    private User registerUser(UserSignupRequest userSignupRequest) {
//        User user = new User()
//            .setEmail(userSignupRequest.getEmail())
//            .setFirstName(userSignupRequest.getFirstName())
//            .setLastName(userSignupRequest.getLastName())
//            .setPassword(encoder.encode(userSignupRequest.getPassword()));
//        return userService.signup(user);
//    }

    @PostMapping("/auth/signin")
    public ResponseEntity<?> signin(@RequestBody @Valid LoginRequest loginRequest) {

        UsernamePasswordAuthenticationToken authType = new UsernamePasswordAuthenticationToken(
            loginRequest.getEmail(),
            loginRequest.getPassword()
        );
        Authentication authentication = authenticationManager.authenticate(authType);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());

        return ResponseEntity.ok(
            new JwtResponse(jwt, userDetails.getUsername(), roles)
        );
    }

}
