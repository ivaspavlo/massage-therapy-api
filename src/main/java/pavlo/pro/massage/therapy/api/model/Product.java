package pavlo.pro.massage.therapy.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Document(collection = "product")
public class Product {
    @Id
    private String id;
    private String title;
    private String subtitle;
    private String text;
    private int price;
    private String category;
}
