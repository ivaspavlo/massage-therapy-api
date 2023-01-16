package pavlo.pro.massagetherapyapi.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import pavlo.pro.massagetherapyapi.dto.UserDto;
import pavlo.pro.massagetherapyapi.model.User;
import pavlo.pro.massagetherapyapi.payload.request.LoginReq;
import pavlo.pro.massagetherapyapi.payload.request.UpdateUserReq;
import pavlo.pro.massagetherapyapi.payload.request.SignupReq;
import pavlo.pro.massagetherapyapi.payload.response.JwtRes;
import pavlo.pro.massagetherapyapi.payload.response.Response;
import pavlo.pro.massagetherapyapi.security.CustomUserDetails;
import pavlo.pro.massagetherapyapi.security.JwtUtils;
import pavlo.pro.massagetherapyapi.service.UserService;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/auth/signup")
    public Response signup(@RequestBody @Valid SignupReq signupRequest) {
        UserDto userDto = convertToDto(userService.signup(signupRequest));
        // TODO: clarify the generics type
        return Response.ok().setPayload(userDto);
    }

    @PostMapping("/auth/signin")
    public Response signin(@RequestBody @Valid LoginReq loginReq) {
        UsernamePasswordAuthenticationToken authType = new UsernamePasswordAuthenticationToken(
            loginReq.getEmail(),
            loginReq.getPassword()
        );
        Authentication authentication = authenticationManager.authenticate(authType);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails
            .getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());

        return Response.ok().setPayload(new JwtRes(jwt, userDetails.getId(), roles));
    }

    @PutMapping("/update")
    public Response<?> update(@RequestBody @Valid UpdateUserReq updateUserReq) {
        return Response.ok().setPayload(userService.updateUser(updateUserReq));
    }

    private UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

}
