package pavlo.pro.massagetherapyapi.repository.interfaces;

import org.springframework.data.mongodb.repository.MongoRepository;
import pavlo.pro.massagetherapyapi.model.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}
