package pavlo.pro.massagetherapyapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import pavlo.pro.massagetherapyapi.model.Product;
import pavlo.pro.massagetherapyapi.payload.request.CreateProductReq;
import pavlo.pro.massagetherapyapi.repository.ProductRepository;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(CreateProductReq createProductReq) throws ResponseStatusException {
        Product product = productRepository.findByTitle(createProductReq.getTitle());
        if (product == null) {
            return productRepository.createProduct(
                new Product(
                    createProductReq.getTitle(),
                    createProductReq.getSubtitle(),
                    createProductReq.getPrice(),
                    createProductReq.getPrice()
                )
            );
        }
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Product with the title: " + createProductReq.getTitle() + " already exists."
        );

    }
}
