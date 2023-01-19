package pavlo.pro.massagetherapyapi.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pavlo.pro.massagetherapyapi.model.Product;
import pavlo.pro.massagetherapyapi.dto.request.CreateProductReq;
import pavlo.pro.massagetherapyapi.dto.request.UpdateProductReq;

public interface ProductService {
    public Product createProduct(CreateProductReq createProductReq);
    public Product updateProduct(String productId, UpdateProductReq updateProductReq);
    public Page<Product> getProducts(Pageable paging);
    public Product getProductById(String productId);
}
