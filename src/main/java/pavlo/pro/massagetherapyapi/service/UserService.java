package pavlo.pro.massagetherapyapi.service;

import org.springframework.web.server.ResponseStatusException;
import pavlo.pro.massagetherapyapi.model.User;
import pavlo.pro.massagetherapyapi.payload.request.UpdateUserReq;
import pavlo.pro.massagetherapyapi.payload.request.SignupReq;

public interface UserService {
    public User signup(SignupReq signupReq) throws ResponseStatusException;
    User findUserByEmail(String email);
    User updateUser(UpdateUserReq updateUserReq) throws RuntimeException;
    User changePassword(User user, String newPassword);
}
