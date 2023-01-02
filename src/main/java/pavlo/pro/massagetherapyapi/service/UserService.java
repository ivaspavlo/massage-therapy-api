package pavlo.pro.massagetherapyapi.service;

import org.springframework.web.server.ResponseStatusException;
import pavlo.pro.massagetherapyapi.model.User;
import pavlo.pro.massagetherapyapi.payload.request.UserSignupReq;

public interface UserService {
    public User signup(UserSignupReq userData) throws ResponseStatusException;
    User findUserByEmail(String email);
    User updateProfile(User user);
    User changePassword(User user, String newPassword);
}
