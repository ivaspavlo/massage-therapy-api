package pavlo.pro.massagetherapyapi.service.interfaces;

import org.springframework.web.server.ResponseStatusException;
import pavlo.pro.massagetherapyapi.model.User;
import pavlo.pro.massagetherapyapi.dto.request.UpdateUserReq;
import pavlo.pro.massagetherapyapi.dto.request.SignupReq;

public interface UserService {
    public User signup(SignupReq signupReq) throws ResponseStatusException;
    User findUserByEmail(String email);
    User updateUser(String userId, UpdateUserReq updateUserReq) throws RuntimeException;
    User changePassword(User user, String newPassword);
}
