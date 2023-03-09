package pavlo.pro.massagetherapyapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pavlo.pro.massagetherapyapi.model.Promo;

public interface PromoRepository extends MongoRepository<Promo, String> {
}
