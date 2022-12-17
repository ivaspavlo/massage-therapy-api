package pavlo.pro.massageservicesapi.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import pavlo.pro.massageservicesapi.model.ERole;
import pavlo.pro.massageservicesapi.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
