package pavlo.pro.massage.therapy.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pavlo.pro.massage.therapy.api.model.ERole;
import pavlo.pro.massage.therapy.api.model.Role;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
