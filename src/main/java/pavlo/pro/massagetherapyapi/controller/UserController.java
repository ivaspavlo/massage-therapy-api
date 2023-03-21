package pavlo.pro.massagetherapyapi.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import pavlo.pro.massagetherapyapi.dto.UserDto;
import pavlo.pro.massagetherapyapi.model.User;
import pavlo.pro.massagetherapyapi.dto.request.LoginReq;
import pavlo.pro.massagetherapyapi.dto.request.UpdateUserReq;
import pavlo.pro.massagetherapyapi.dto.request.SignupReq;
import pavlo.pro.massagetherapyapi.dto.JwtDto;
import pavlo.pro.massagetherapyapi.dto.response.Response;
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
    public Response<UserDto> signup(@RequestBody @Valid SignupReq signupRequest) {
        UserDto userDto = convertToDto(userService.signup(signupRequest));
        return Response.ok().setPayload(userDto);
    }

    @PostMapping("/auth/signin")
    public Response<JwtDto> signin(@RequestBody @Valid LoginReq loginReq) {
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

        return Response.ok().setPayload(new JwtDto(jwt, userDetails.getId(), roles));
    }

    @PutMapping("/update/{id}")
    public Response<UserDto> update(
        @PathVariable("id") String userId,
        @RequestBody @Valid UpdateUserReq updateUserReq
    ) {
        UserDto userDto = convertToDto(userService.updateUser(userId, updateUserReq));
        return Response.ok().setPayload(userDto);
    }

    private UserDto convertToDto(User user) {
        if (modelMapper.getTypeMap(User.class, UserDto.class) == null) {
            TypeMap<User, UserDto> propertyMapper = modelMapper.createTypeMap(User.class, UserDto.class);
            propertyMapper.addMapping(User::getRoles, UserDto::setIsAdmin);
        }
        return modelMapper.map(user, UserDto.class);
    }

}
