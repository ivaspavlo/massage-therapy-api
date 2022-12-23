package pavlo.pro.massagetherapyapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
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
    private String title;

    @NotBlank
    private String subtitle;

    @NotBlank
    private Float price;

    @NotBlank
    private String desc;
}
