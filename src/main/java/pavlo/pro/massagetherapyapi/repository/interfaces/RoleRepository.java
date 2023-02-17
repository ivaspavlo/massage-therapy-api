package pavlo.pro.massagetherapyapi.repository.interfaces;

import org.springframework.data.mongodb.repository.MongoRepository;
import pavlo.pro.massagetherapyapi.model.Role;
import pavlo.pro.massagetherapyapi.model.User;

public interface RoleRepository extends MongoRepository<Role, String> {
    Role findByName(String name);
}
