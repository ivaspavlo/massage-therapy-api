package pavlo.pro.massagetherapyapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Document("products")
public class Product {
    @Id
    private String id;

    @NotBlank
    @Size(max = 30)
    @Indexed(unique = true)
    private String title;

    @NotBlank
    private String subtitle;

    @NotBlank
    private String price;

    @NotBlank
    private String desc;

    public Product(
        String title,
        String subtitle,
        String price,
        String desc
    ) {
        this.title = title;
        this.subtitle = subtitle;
        this.price = price;
        this.desc = desc;
    }
}
