package pavlo.pro.massagetherapyapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pavlo.pro.massagetherapyapi.model.User;
import pavlo.pro.massagetherapyapi.payload.request.UserSignupRequest;
import pavlo.pro.massagetherapyapi.payload.response.Response;
import pavlo.pro.massagetherapyapi.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public Response signup(@RequestBody @Valid UserSignupRequest userSignupRequest) {
        return Response.ok().setPayload(registerUser(userSignupRequest));
    }

    private User registerUser(UserSignupRequest userSignupRequest) {
        User user = new User()
            .setEmail(userSignupRequest.getEmail())
            .setFirstName(userSignupRequest.getFirstName())
            .setLastName(userSignupRequest.getLastName())
            .setPassword(userSignupRequest.getPassword());
        return userService.signup(user);
    }

}
