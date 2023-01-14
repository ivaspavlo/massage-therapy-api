package pavlo.pro.massagetherapyapi.service;

import pavlo.pro.massagetherapyapi.model.Product;
import pavlo.pro.massagetherapyapi.payload.request.CreateProductReq;
import pavlo.pro.massagetherapyapi.payload.request.UpdateProductReq;

public interface ProductService {
    public Product createProduct(CreateProductReq createProductReq);
    public Product updateProduct(String productId, UpdateProductReq updateProductReq);
}
