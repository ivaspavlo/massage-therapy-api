package pavlo.pro.massagetherapyapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pavlo.pro.massagetherapyapi.payload.request.CreateProductReq;
import pavlo.pro.massagetherapyapi.payload.request.UpdateProductReq;
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

    @PutMapping ("/update/{id}")
    public Response updateProduct(
        @PathVariable("id") String productId,
        @RequestBody @Valid UpdateProductReq updateProductReq
    ) {
        return Response.ok().setPayload(productService.updateProduct(productId, updateProductReq));
    }

}
