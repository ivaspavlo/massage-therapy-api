package pavlo.pro.massagetherapyapi.service;

import org.springframework.web.server.ResponseStatusException;
import pavlo.pro.massagetherapyapi.model.User;

public interface UserService {
    public User signup(User userData) throws ResponseStatusException;
    User findUserByEmail(String email);
    User updateProfile(User user);
    User changePassword(User user, String newPassword);
}
