package pavlo.pro.massagetherapyapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import pavlo.pro.massagetherapyapi.exception.AppException;
import pavlo.pro.massagetherapyapi.exception.EntityType;
import pavlo.pro.massagetherapyapi.exception.ExceptionType;
import pavlo.pro.massagetherapyapi.model.Product;
import pavlo.pro.massagetherapyapi.dto.request.CreateProductReq;
import pavlo.pro.massagetherapyapi.dto.request.UpdateProductReq;
import pavlo.pro.massagetherapyapi.repository.ProductRepository;
import pavlo.pro.massagetherapyapi.service.interfaces.ProductService;

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

    public Product updateProduct(String productId, UpdateProductReq updateProductReq) throws RuntimeException {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isEmpty()) {
            throw exception(PRODUCT, ENTITY_NOT_FOUND, productId);
        }
        Product product = productOptional.get();
        if (updateProductReq.getTitle() != null) {
            product.setTitle(updateProductReq.getTitle());
        }
        if (updateProductReq.getSubtitle() != null) {
            product.setSubtitle(updateProductReq.getSubtitle());
        }
        if (updateProductReq.getDesc() != null) {
            product.setDesc(updateProductReq.getDesc());
        }
        if (updateProductReq.getPrice() != null) {
            product.setPrice(updateProductReq.getPrice());
        }
        return productRepository.save(product);
    }

    public Page<Product> getProducts(Pageable paging) {
        return productRepository.findAll(paging);
    }

    public Product getProductById(String productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isEmpty()) {
            throw exception(PRODUCT, ENTITY_NOT_FOUND, productId);
        }
        return productOptional.get();
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return AppException.throwException(entityType, exceptionType, args);
    }
}