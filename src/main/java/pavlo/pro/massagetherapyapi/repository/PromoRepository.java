package pavlo.pro.massagetherapyapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pavlo.pro.massagetherapyapi.model.Role;

public interface PromoRepository extends MongoRepository<Role, String> {
}
