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
import pavlo.pro.massagetherapyapi.dto.response.PaginationRes;
import pavlo.pro.massagetherapyapi.dto.response.Response;
import pavlo.pro.massagetherapyapi.model.Product;
import pavlo.pro.massagetherapyapi.model.User;
import pavlo.pro.massagetherapyapi.service.ProductService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/create")
    public Response<ProductDto> addProduct(@RequestBody @Valid CreateProductReq createProductReq) {
        ProductDto productDto = modelMapper.map(productService.createProduct(createProductReq), ProductDto.class);
        return Response.ok().setPayload(productDto);
    }

    @PutMapping("/update/{id}")
    public Response<ProductDto> updateProduct(
        @PathVariable("id") String productId,
        @RequestBody @Valid UpdateProductReq updateProductReq
    ) {
        ProductDto productDto = modelMapper.map(
            productService.updateProduct(productId, updateProductReq),
            ProductDto.class
        );
        return Response.ok().setPayload(productDto);
    }

    @GetMapping("/{id}")
    public Response<ProductDto> getProductById(
        @PathVariable("id") String productId
    ) {
        ProductDto productDto = modelMapper.map(
            productService.getProductById(productId),
            ProductDto.class
        );
        return Response.ok().setPayload(productDto);
    }

    @GetMapping()
    public Response<PaginationRes> getProducts(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "3") int size
    ) {
        Pageable paging = PageRequest.of(page, size);
        Page<Product> pageProducts = productService.getProducts(paging);
        List<ProductDto> productsDto = pageProducts.getContent()
            .stream().map(p -> modelMapper.map(p, ProductDto.class))
            .collect(Collectors.toList());
        PaginationRes<ProductDto> payload = new PaginationRes(
            productsDto, pageProducts.getTotalElements(), pageProducts.getNumber(), pageProducts.getTotalPages()
        );
        return Response.ok().setPayload(payload);
    }

}
