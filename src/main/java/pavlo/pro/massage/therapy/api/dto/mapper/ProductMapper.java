package pavlo.pro.massage.therapy.api.dto.mapper;

import pavlo.pro.massage.therapy.api.dto.model.ProductDto;
import pavlo.pro.massage.therapy.api.model.Product;

public class ProductMapper {
    public static ProductDto toProductDto(Product product) {
        return new ProductDto()
                .setId(product.getId())
                .setTitle(product.getTitle())
                .setSubtitle(product.getSubtitle())
                .setText(product.getText())
                .setPrice(product.getPrice())
                .setCategory(product.getCategory());
    }
}
