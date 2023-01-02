package pavlo.pro.massagetherapyapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pavlo.pro.massagetherapyapi.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
    public Product findByTitle(String title);
    public Product createProduct(Product product);
}
