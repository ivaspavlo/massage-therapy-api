package pavlo.pro.massagetherapyapi.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import pavlo.pro.massagetherapyapi.dto.ProductDto;
import pavlo.pro.massagetherapyapi.dto.request.CreateProductReq;
import pavlo.pro.massagetherapyapi.dto.request.UpdateProductReq;
import pavlo.pro.massagetherapyapi.dto.response.Response;
import pavlo.pro.massagetherapyapi.model.Product;
import pavlo.pro.massagetherapyapi.service.interfaces.ProductService;

import javax.validation.Valid;
import java.util.List;

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

    @PutMapping("/update/{id}")
    public Response updateProduct(
        @PathVariable("id") String productId,
        @RequestBody @Valid UpdateProductReq updateProductReq
    ) {
        ProductDto productDto = modelMapper.map(productService.updateProduct(productId, updateProductReq), ProductDto.class);
        return Response.ok().setPayload(productDto);
    }

    @GetMapping("")
    public Response getProducts(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "3") int size
    ) {
        Pageable paging = PageRequest.of(page, size);
        Page<Product> pageProducts = productService.getProducts(paging);
        List<Product> products = pageProducts.getContent();
        return Response.ok().setPayload(
            products.stream().map(p -> modelMapper.map(p, ProductDto.class))
        );
    }

}
