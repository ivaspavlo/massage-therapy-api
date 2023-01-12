package pavlo.pro.massagetherapyapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pavlo.pro.massagetherapyapi.exception.AppException;
import pavlo.pro.massagetherapyapi.exception.EntityType;
import pavlo.pro.massagetherapyapi.exception.ExceptionType;
import pavlo.pro.massagetherapyapi.model.Product;
import pavlo.pro.massagetherapyapi.payload.request.CreateProductReq;
import pavlo.pro.massagetherapyapi.payload.request.UpdateProductReq;
import pavlo.pro.massagetherapyapi.repository.ProductRepository;

import java.util.Optional;

import static pavlo.pro.massagetherapyapi.exception.EntityType.PRODUCT;
import static pavlo.pro.massagetherapyapi.exception.ExceptionType.DUPLICATE_ENTITY;
import static pavlo.pro.massagetherapyapi.exception.ExceptionType.ENTITY_NOT_FOUND;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(CreateProductReq createProductReq) throws RuntimeException {
        Product product = productRepository.findByTitle(createProductReq.getTitle());
        if (product == null) {
            return productRepository.save(
                new Product(
                    createProductReq.getTitle(),
                    createProductReq.getSubtitle(),
                    createProductReq.getPrice(),
                    createProductReq.getPrice()
                )
            );
        }
        throw exception(PRODUCT, DUPLICATE_ENTITY, createProductReq.getTitle());
    }

    public Product updateProduct(String id, UpdateProductReq updateProductReq) throws RuntimeException {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw exception(PRODUCT, ENTITY_NOT_FOUND, id);
        }
        Product product = productOptional.get();
        if (updateProductReq.getTitle() != null) {
            product.setDesc(updateProductReq.getTitle());
        }
        if (updateProductReq.getSubtitle() != null) {
            product.setDesc(updateProductReq.getSubtitle());
        }
        if (updateProductReq.getDesc() != null) {
            product.setDesc(updateProductReq.getDesc());
        }
        if (updateProductReq.getPrice() != null) {
            product.setDesc(updateProductReq.getPrice());
        }
        return productRepository.save(product);
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return AppException.throwException(entityType, exceptionType, args);
    }
}
