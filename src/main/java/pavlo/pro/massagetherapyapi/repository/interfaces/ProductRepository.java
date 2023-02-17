package pavlo.pro.massagetherapyapi.repository.interfaces;

import org.springframework.data.mongodb.repository.MongoRepository;
import pavlo.pro.massagetherapyapi.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
    Product findByTitle(String title);
}
