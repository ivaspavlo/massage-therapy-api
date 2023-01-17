package pavlo.pro.massagetherapyapi.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pavlo.pro.massagetherapyapi.dto.ProductDto;
import pavlo.pro.massagetherapyapi.dto.request.CreateProductReq;
import pavlo.pro.massagetherapyapi.dto.request.UpdateProductReq;
import pavlo.pro.massagetherapyapi.dto.response.Response;
import pavlo.pro.massagetherapyapi.service.interfaces.ProductService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/create")
    public Response addProduct(@RequestBody @Valid CreateProductReq createProductReq) {
        ProductDto productDto = modelMapper.map(productService.createProduct(createProductReq), ProductDto.class);
        return Response.ok().setPayload(productDto);
    }

    @PutMapping ("/update/{id}")
    public Response updateProduct(
        @PathVariable("id") String productId,
        @RequestBody @Valid UpdateProductReq updateProductReq
    ) {
        ProductDto productDto = modelMapper.map(productService.updateProduct(productId, updateProductReq), ProductDto.class);
        return Response.ok().setPayload(productDto);
    }

}
