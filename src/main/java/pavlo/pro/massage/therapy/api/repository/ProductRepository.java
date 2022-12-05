package pavlo.pro.massage.therapy.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;
import pavlo.pro.massage.therapy.api.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

    @Query("{title:'?0'}")
    Product findItemByTitle(String title);

    @Query(value = "{category:'?0'}", fields = "{'title' : 1, 'price' : 1}")
    List<Product> findAll(String category);

    public long count();

}
