package pavlo.pro.massagetherapyapi.service;

import pavlo.pro.massagetherapyapi.model.Product;
import pavlo.pro.massagetherapyapi.payload.request.CreateProductReq;

public interface ProductService {
    public Product createProduct(CreateProductReq createProductReq);
}
