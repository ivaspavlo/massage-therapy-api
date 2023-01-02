package pavlo.pro.massagetherapyapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pavlo.pro.massagetherapyapi.payload.request.CreateProductReq;
import pavlo.pro.massagetherapyapi.payload.response.Response;
import pavlo.pro.massagetherapyapi.service.ProductService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/create")
    public Response addProduct(@RequestBody @Valid CreateProductReq createProductReq) {
        return Response.ok().setPayload(productService.createProduct(createProductReq));
    }

}
