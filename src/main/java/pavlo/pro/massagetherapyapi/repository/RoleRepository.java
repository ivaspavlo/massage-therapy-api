package pavlo.pro.massagetherapyapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pavlo.pro.massagetherapyapi.model.Role;
import pavlo.pro.massagetherapyapi.model.User;

public interface RoleRepository extends MongoRepository<Role, String> {
    Role findByName(String name);
}
