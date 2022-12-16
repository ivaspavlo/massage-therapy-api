package pavlo.pro.massagetherapyapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pavlo.pro.massagetherapyapi.model.User;
import pavlo.pro.massagetherapyapi.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

//    @PostMapping("/signup")
//    public User signup(@RequestBody @Valid User userSignupRequest) {
//        return Response.ok().setPayload(registerUser(userSignupRequest, false));
//    }

    // https://github.com/khandelwal-arpit/springboot-starterkit
    // https://github.com/bezkoder/spring-boot-security-jwt-auth-mongodb

}
